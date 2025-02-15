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

package com.liferay.source.formatter.check;

import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Peter Shin
 */
public class PropertiesLiferayPluginPackageLiferayVersionsCheck
	extends BaseFileCheck {

	@Override
	public boolean isLiferaySourceCheck() {
		return true;
	}

	@Override
	protected String doProcess(
			String fileName, String absolutePath, String content)
		throws Exception {

		if (fileName.endsWith("/liferay-plugin-package.properties")) {
			return _fixIncorrectLiferayVersions(absolutePath, content);
		}

		return content;
	}

	protected String getLiferayVersion(String absolutePath) throws Exception {
		return getPortalVersion(isModulesApp(absolutePath, true));
	}

	protected boolean isSkipFix(String absolutePath) {
		if (!isModulesApp(absolutePath, false) || !isPortalSource()) {
			return true;
		}

		return false;
	}

	private String _fixIncorrectLiferayVersions(
			String absolutePath, String content)
		throws Exception {

		if (isSkipFix(absolutePath)) {
			return content;
		}

		Matcher matcher = _liferayVersionsPattern.matcher(content);

		if (!matcher.find()) {
			return content;
		}

		String liferayVersion = getLiferayVersion(absolutePath);

		if (Validator.isNull(liferayVersion)) {
			return content;
		}

		return StringUtil.replace(
			content, "liferay-versions=" + matcher.group(1),
			"liferay-versions=" + liferayVersion + "+", matcher.start());
	}

	private static final Pattern _liferayVersionsPattern = Pattern.compile(
		"\nliferay-versions=(.*)\n");

}