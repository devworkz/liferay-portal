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

package com.liferay.journal.content.asset.addon.entry.ratings.internal;

import com.liferay.journal.content.asset.addon.entry.ContentMetadataAssetAddonEntry;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.servlet.taglib.ui.BaseJSPAssetAddonEntry;

import java.util.Locale;

import javax.servlet.ServletContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Julio Camarero
 */
@Component(immediate = true, service = ContentMetadataAssetAddonEntry.class)
public class RatingsContentMetadataAssetAddonEntry
	extends BaseJSPAssetAddonEntry implements ContentMetadataAssetAddonEntry {

	@Override
	public String getIcon() {
		return "star-half";
	}

	@Override
	public String getJspPath() {
		return "/ratings.jsp";
	}

	@Override
	public String getKey() {
		return "enableRatings";
	}

	@Override
	public String getLabel(Locale locale) {
		return _language.get(locale, "ratings");
	}

	@Override
	public ServletContext getServletContext() {
		return _servletContext;
	}

	@Override
	public Double getWeight() {
		return 2.0;
	}

	@Reference
	private Language _language;

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.journal.content.asset.addon.entry.ratings)"
	)
	private ServletContext _servletContext;

}