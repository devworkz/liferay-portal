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

package com.liferay.segments.web.internal.field.customizer;

import com.liferay.item.selector.ItemSelector;
import com.liferay.item.selector.ItemSelectorCriterion;
import com.liferay.item.selector.criteria.UUIDItemSelectorReturnType;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.ClassedModel;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.url.builder.PortletURLBuilder;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.roles.item.selector.organization.role.OrganizationRoleItemSelectorCriterion;
import com.liferay.roles.item.selector.site.role.SiteRoleItemSelectorCriterion;
import com.liferay.segments.field.Field;
import com.liferay.segments.field.customizer.SegmentsFieldCustomizer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import javax.portlet.PortletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eduardo García
 */
@Component(
	immediate = true,
	property = {
		"segments.field.customizer.entity.name=User",
		"segments.field.customizer.key=" + UserGroupRoleSegmentsFieldCustomizer.KEY,
		"segments.field.customizer.priority:Integer=60"
	},
	service = SegmentsFieldCustomizer.class
)
public class UserGroupRoleSegmentsFieldCustomizer
	extends BaseSegmentsFieldCustomizer {

	public static final String KEY = "userGroupRole";

	@Override
	public ClassedModel getClassedModel(String fieldValue) {
		return _getRole(fieldValue);
	}

	@Override
	public String getClassName() {
		return Role.class.getName();
	}

	@Override
	public List<String> getFieldNames() {
		return _fieldNames;
	}

	@Override
	public String getFieldValueName(String fieldValue, Locale locale) {
		Role role = _getRole(fieldValue);

		if (role == null) {
			return fieldValue;
		}

		try {
			return role.getDescriptiveName();
		}
		catch (PortalException portalException) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Unable to get name for user group role " + fieldValue,
					portalException);
			}

			return fieldValue;
		}
	}

	@Override
	public String getKey() {
		return KEY;
	}

	@Override
	public Field.SelectEntity getSelectEntity(PortletRequest portletRequest) {
		try {
			return new Field.SelectEntity(
				"selectEntity",
				getSelectEntityTitle(
					_portal.getLocale(portletRequest), Role.class.getName()),
				PortletURLBuilder.create(
					_itemSelector.getItemSelectorURL(
						RequestBackedPortletURLFactoryUtil.create(
							portletRequest),
						"selectEntity",
						_getItemSelectorCriteria().toArray(
							new ItemSelectorCriterion[0]))
				).buildString(),
				true);
		}
		catch (Exception exception) {
			if (_log.isWarnEnabled()) {
				_log.warn("Unable to get select entity", exception);
			}

			return null;
		}
	}

	private List<ItemSelectorCriterion> _getItemSelectorCriteria() {
		List<ItemSelectorCriterion> itemSelectorCriteria = new ArrayList<>();

		SiteRoleItemSelectorCriterion siteRoleItemSelectorCriterion =
			new SiteRoleItemSelectorCriterion();

		siteRoleItemSelectorCriterion.setDesiredItemSelectorReturnTypes(
			Collections.singletonList(new UUIDItemSelectorReturnType()));

		itemSelectorCriteria.add(siteRoleItemSelectorCriterion);

		OrganizationRoleItemSelectorCriterion
			organizationRoleItemSelectorCriterion =
				new OrganizationRoleItemSelectorCriterion();

		organizationRoleItemSelectorCriterion.setDesiredItemSelectorReturnTypes(
			Collections.singletonList(new UUIDItemSelectorReturnType()));

		itemSelectorCriteria.add(organizationRoleItemSelectorCriterion);

		return itemSelectorCriteria;
	}

	private Role _getRole(String fieldValue) {
		long roleId = GetterUtil.getLong(fieldValue);

		if (roleId == 0) {
			return null;
		}

		return _roleLocalService.fetchRole(roleId);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		UserGroupRoleSegmentsFieldCustomizer.class);

	private static final List<String> _fieldNames = ListUtil.fromArray(
		"userGroupRoleIds");

	@Reference
	private ItemSelector _itemSelector;

	@Reference
	private Portal _portal;

	@Reference
	private RoleLocalService _roleLocalService;

}