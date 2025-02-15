/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.marketplace.internal.lpkg.deployer;

import com.liferay.marketplace.model.App;
import com.liferay.marketplace.model.Module;
import com.liferay.marketplace.service.AppLocalService;
import com.liferay.marketplace.service.ModuleLocalService;
import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Release;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropertiesUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.lpkg.deployer.LPKGDeployer;

import java.net.URL;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.Set;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleEvent;
import org.osgi.framework.BundleListener;
import org.osgi.framework.SynchronousBundleListener;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Shuyang Zhou
 * @author Ryan Park
 */
@Component(immediate = true, service = {})
public class LPKGDeployerRegistrar {

	@Activate
	protected void activate(BundleContext bundleContext) throws Exception {
		bundleContext.addBundleListener(_bundleListener);

		Map<Bundle, List<Bundle>> deployedLPKGBundles =
			_lpkgDeployer.getDeployedLPKGBundles();

		if (deployedLPKGBundles.isEmpty()) {
			return;
		}

		Map<AppKey, App> apps = new HashMap<>();

		for (App app :
				_appLocalService.getApps(
					QueryUtil.ALL_POS, QueryUtil.ALL_POS)) {

			apps.put(
				new AppKey(
					app.getTitle(), app.getDescription(), app.getCategory(),
					app.getIconURL(), app.isRequired()),
				app);
		}

		Map<Long, List<Module>> modules = new HashMap<>();

		for (Module module :
				_moduleLocalService.getModules(
					QueryUtil.ALL_POS, QueryUtil.ALL_POS)) {

			List<Module> appModules = modules.computeIfAbsent(
				module.getAppId(), key -> new ArrayList<>());

			appModules.add(module);
		}

		for (Bundle bundle : deployedLPKGBundles.keySet()) {
			_register(bundle, apps, modules);
		}
	}

	@Deactivate
	protected void deactivate(BundleContext bundleContext) {
		bundleContext.removeBundleListener(_bundleListener);
	}

	private void _doRegister(
			Bundle lpkgBundle, Map<AppKey, App> apps,
			Map<Long, List<Module>> modules)
		throws Exception {

		URL url = lpkgBundle.getEntry("liferay-marketplace.properties");

		if (url == null) {
			return;
		}

		Properties properties = PropertiesUtil.load(
			url.openStream(), StringPool.ISO_8859_1);

		long remoteAppId = GetterUtil.getLong(
			properties.getProperty("remote-app-id"));
		String version = properties.getProperty("version");

		if ((remoteAppId <= 0) || Validator.isNull(version)) {
			return;
		}

		String title = properties.getProperty("title");
		String description = properties.getProperty("description");
		String category = properties.getProperty("category");
		String iconURL = properties.getProperty("icon-url");
		boolean required = GetterUtil.getBoolean(
			properties.getProperty("required"));

		App app = null;

		if (apps == null) {
			app = _appLocalService.fetchRemoteApp(remoteAppId);
		}
		else {
			app = apps.get(
				new AppKey(title, description, category, iconURL, required));
		}

		if ((app != null) && (app.getRemoteAppId() != remoteAppId)) {
			_appLocalService.uninstallApp(app.getRemoteAppId());

			app.setRemoteAppId(remoteAppId);
			app.setVersion(version);

			app = _appLocalService.updateApp(app);
		}
		else if (app == null) {
			app = _appLocalService.updateApp(
				0, remoteAppId, title, description, category, iconURL, version,
				required, null);
		}

		Set<Tuple> oldTuples = new HashSet<>();

		List<Module> oldModules = null;

		if (modules == null) {
			oldModules = _moduleLocalService.getModules(app.getAppId());
		}
		else {
			oldModules = modules.getOrDefault(
				app.getAppId(), Collections.emptyList());
		}

		for (Module module : oldModules) {
			oldTuples.add(
				new Tuple(
					module.getModuleId(), module.getBundleSymbolicName(),
					module.getBundleVersion(), module.getContextName()));
		}

		List<Tuple> newTuples = new ArrayList<>();

		String[] bundleStrings = StringUtil.split(
			properties.getProperty("bundles"));

		for (String bundleString : bundleStrings) {
			String[] bundleStringParts = StringUtil.split(
				bundleString, CharPool.POUND);

			Tuple tuple = new Tuple(
				0, bundleStringParts[0], bundleStringParts[1],
				bundleStringParts[2]);

			if (!oldTuples.remove(tuple)) {
				newTuples.add(tuple);
			}
		}

		String[] contextNames = StringUtil.split(
			properties.getProperty("context-names"));

		for (String contextName : contextNames) {
			Tuple tuple = new Tuple(
				0, contextName, StringPool.BLANK, contextName);

			if (!oldTuples.remove(tuple)) {
				newTuples.add(tuple);
			}
		}

		for (Tuple tuple : oldTuples) {
			Module module = _moduleLocalService.fetchModule(tuple._moduleId);

			if (module != null) {
				_moduleLocalService.deleteModule(tuple._moduleId);
			}
		}

		for (Tuple tuple : newTuples) {
			_moduleLocalService.addModule(
				app.getAppId(), tuple._symbolicName, tuple._version,
				tuple._contextName);
		}
	}

	private void _register(
		Bundle lpkgBundle, Map<AppKey, App> apps,
		Map<Long, List<Module>> modules) {

		try {
			_doRegister(lpkgBundle, apps, modules);
		}
		catch (Exception exception) {
			_log.error(
				"Unable to track installed app " +
					lpkgBundle.getSymbolicName() + " with Marketplace",
				exception);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		LPKGDeployerRegistrar.class);

	@Reference
	private AppLocalService _appLocalService;

	private final BundleListener _bundleListener =
		new SynchronousBundleListener() {

			@Override
			public void bundleChanged(BundleEvent bundleEvent) {
				if (bundleEvent.getType() == BundleEvent.STARTED) {
					_register(bundleEvent.getBundle(), null, null);
				}
			}

		};

	@Reference
	private LPKGDeployer _lpkgDeployer;

	@Reference
	private ModuleLocalService _moduleLocalService;

	@Reference(
		target = "(&(release.bundle.symbolic.name=com.liferay.marketplace.service)(release.schema.version=2.0.3))"
	)
	private Release _release;

	private static class AppKey {

		@Override
		public boolean equals(Object object) {
			AppKey appKey = (AppKey)object;

			if (Objects.equals(appKey._title, _title) &&
				Objects.equals(appKey._description, _description) &&
				Objects.equals(appKey._category, _category) &&
				Objects.equals(appKey._iconURL, _iconURL) &&
				(appKey._required == _required)) {

				return true;
			}

			return false;
		}

		@Override
		public int hashCode() {
			int hashCode = HashUtil.hash(0, _title);

			hashCode = HashUtil.hash(hashCode, _description);
			hashCode = HashUtil.hash(hashCode, _category);
			hashCode = HashUtil.hash(hashCode, _iconURL);

			return HashUtil.hash(hashCode, _required);
		}

		private AppKey(
			String title, String description, String category, String iconURL,
			boolean required) {

			_title = title;
			_description = description;
			_category = category;
			_iconURL = iconURL;
			_required = required;
		}

		private final String _category;
		private final String _description;
		private final String _iconURL;
		private final boolean _required;
		private final String _title;

	}

	private static class Tuple {

		@Override
		public boolean equals(Object object) {
			Tuple tuple = (Tuple)object;

			if (Objects.equals(_symbolicName, tuple._symbolicName) &&
				Objects.equals(_version, tuple._version) &&
				Objects.equals(_contextName, tuple._contextName)) {

				return true;
			}

			return false;
		}

		@Override
		public int hashCode() {
			int hash = HashUtil.hash(0, _symbolicName);

			hash = HashUtil.hash(hash, _version);

			return HashUtil.hash(hash, _contextName);
		}

		private Tuple(
			long moduleId, String symbolicName, String version,
			String contextName) {

			_moduleId = moduleId;
			_symbolicName = symbolicName;
			_version = version;
			_contextName = contextName;
		}

		private final String _contextName;
		private final long _moduleId;
		private final String _symbolicName;
		private final String _version;

	}

}