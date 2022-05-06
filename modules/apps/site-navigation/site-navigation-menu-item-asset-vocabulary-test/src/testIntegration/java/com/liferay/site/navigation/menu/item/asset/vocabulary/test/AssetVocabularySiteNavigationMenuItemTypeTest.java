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

package com.liferay.site.navigation.menu.item.asset.vocabulary.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetCategoryLocalService;
import com.liferay.asset.kernel.service.AssetVocabularyLocalService;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.UnicodePropertiesBuilder;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PermissionCheckerMethodTestRule;
import com.liferay.site.navigation.constants.SiteNavigationConstants;
import com.liferay.site.navigation.menu.item.layout.constants.SiteNavigationMenuItemTypeConstants;
import com.liferay.site.navigation.model.SiteNavigationMenu;
import com.liferay.site.navigation.model.SiteNavigationMenuItem;
import com.liferay.site.navigation.service.SiteNavigationMenuItemLocalService;
import com.liferay.site.navigation.service.SiteNavigationMenuLocalService;
import com.liferay.site.navigation.type.SiteNavigationMenuItemType;
import com.liferay.site.navigation.type.SiteNavigationMenuItemTypeRegistry;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.mock.web.MockHttpServletRequest;

/**
 * @author Lourdes Fernández Besada
 */
@RunWith(Arquillian.class)
public class AssetVocabularySiteNavigationMenuItemTypeTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			PermissionCheckerMethodTestRule.INSTANCE);

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();

		_serviceContext = ServiceContextTestUtil.getServiceContext(
			_group.getGroupId(), TestPropsValues.getUserId());

		_assetVocabulary = _assetVocabularyLocalService.addVocabulary(
			TestPropsValues.getUserId(), _group.getGroupId(),
			RandomTestUtil.randomString(), _serviceContext);
	}

	@Test
	public void testGetChildrenSiteNavigationMenuItems() throws Exception {
		_createAssetCategory(0);
		_createAssetCategory(0);

		AssetCategory rootAssetCategory = _createAssetCategory(0);

		_createAssetCategory(rootAssetCategory.getCategoryId());
		_createAssetCategory(rootAssetCategory.getCategoryId());

		AssetCategory firstLevelAssetCategory = _createAssetCategory(
			rootAssetCategory.getCategoryId());

		_createAssetCategory(firstLevelAssetCategory.getCategoryId());
		_createAssetCategory(firstLevelAssetCategory.getCategoryId());

		AssetCategory secondLevelAssetCategory = _createAssetCategory(
			firstLevelAssetCategory.getCategoryId());

		_createAssetCategory(secondLevelAssetCategory.getCategoryId());
		_createAssetCategory(secondLevelAssetCategory.getCategoryId());

		AssetCategory thirdLevelAssetCategory = _createAssetCategory(
			secondLevelAssetCategory.getCategoryId());

		_createAssetCategory(thirdLevelAssetCategory.getCategoryId());
		_createAssetCategory(thirdLevelAssetCategory.getCategoryId());
		_createAssetCategory(thirdLevelAssetCategory.getCategoryId());

		Assert.assertEquals(
			15,
			_assetCategoryLocalService.getVocabularyCategoriesCount(
				_assetVocabulary.getVocabularyId()));
		Assert.assertEquals(
			3,
			_assetCategoryLocalService.getChildCategoriesCount(
				rootAssetCategory.getCategoryId()));
		Assert.assertEquals(
			3,
			_assetCategoryLocalService.getChildCategoriesCount(
				firstLevelAssetCategory.getCategoryId()));
		Assert.assertEquals(
			3,
			_assetCategoryLocalService.getChildCategoriesCount(
				secondLevelAssetCategory.getCategoryId()));
		Assert.assertEquals(
			3,
			_assetCategoryLocalService.getChildCategoriesCount(
				thirdLevelAssetCategory.getCategoryId()));

		Locale locale = _portal.getSiteDefaultLocale(_group.getGroupId());

		_assertGetChildrenSiteNavigationMenuItems(
			locale, 0, _createSiteNavigationMenuItem(locale, "{}", false));
	}

	private void _assertGetChildrenSiteNavigationMenuItems(
			Locale locale, long parentAssetCategoryId,
			SiteNavigationMenuItem siteNavigationMenuItem)
		throws Exception {

		ThemeDisplay themeDisplay = _getThemeDisplay();

		MockHttpServletRequest mockHttpServletRequest =
			new MockHttpServletRequest();

		mockHttpServletRequest.setAttribute(
			WebKeys.THEME_DISPLAY, themeDisplay);

		SiteNavigationMenuItemType siteNavigationMenuItemType =
			_siteNavigationMenuItemTypeRegistry.getSiteNavigationMenuItemType(
				SiteNavigationMenuItemTypeConstants.ASSET_VOCABULARY);

		List<SiteNavigationMenuItem> childrenSiteNavigationMenuItems =
			siteNavigationMenuItemType.getChildrenSiteNavigationMenuItems(
				mockHttpServletRequest, siteNavigationMenuItem);

		List<AssetCategory> assetCategories =
			_assetCategoryLocalService.getVocabularyCategories(
				parentAssetCategoryId, _assetVocabulary.getVocabularyId(),
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

		Assert.assertEquals(
			childrenSiteNavigationMenuItems.toString(), assetCategories.size(),
			childrenSiteNavigationMenuItems.size());

		for (int i = 0; i < assetCategories.size(); i++) {
			AssetCategory assetCategory = assetCategories.get(i);

			SiteNavigationMenuItem childrenSiteNavigationMenuItem =
				childrenSiteNavigationMenuItems.get(i);

			UnicodeProperties typeSettingsUnicodeProperties =
				UnicodePropertiesBuilder.fastLoad(
					childrenSiteNavigationMenuItem.getTypeSettings()
				).build();

			Assert.assertEquals(
				"asset-category", typeSettingsUnicodeProperties.get("type"));
			Assert.assertEquals(
				assetCategory.getCategoryId(),
				GetterUtil.getLong(
					typeSettingsUnicodeProperties.get("classPK")));
			Assert.assertEquals(
				_assetVocabulary.getVocabularyId(),
				GetterUtil.getLong(
					typeSettingsUnicodeProperties.get("assetVocabularyId")));
			Assert.assertEquals(
				assetCategory.getTitle(locale),
				typeSettingsUnicodeProperties.get("title"));

			_assertGetChildrenSiteNavigationMenuItems(
				locale, assetCategory.getCategoryId(),
				childrenSiteNavigationMenuItem);
		}
	}

	private AssetCategory _createAssetCategory(long parentAssetCategoryId)
		throws Exception {

		return _assetCategoryLocalService.addCategory(
			null, TestPropsValues.getUserId(), _group.getGroupId(),
			parentAssetCategoryId, RandomTestUtil.randomLocaleStringMap(),
			RandomTestUtil.randomLocaleStringMap(),
			_assetVocabulary.getVocabularyId(), null, _serviceContext);
	}

	private SiteNavigationMenuItem _createSiteNavigationMenuItem(
			Locale defaultLocale, String localizedNames,
			boolean showAssetVocabularyLevel)
		throws Exception {

		SiteNavigationMenu siteNavigationMenu =
			_siteNavigationMenuLocalService.addSiteNavigationMenu(
				TestPropsValues.getUserId(), _group.getGroupId(),
				RandomTestUtil.randomString(),
				SiteNavigationConstants.TYPE_DEFAULT, true, _serviceContext);

		return _siteNavigationMenuItemLocalService.addSiteNavigationMenuItem(
			TestPropsValues.getUserId(), _group.getGroupId(),
			siteNavigationMenu.getSiteNavigationMenuId(), 0,
			SiteNavigationMenuItemTypeConstants.ASSET_VOCABULARY,
			UnicodePropertiesBuilder.create(
				true
			).put(
				Field.DEFAULT_LANGUAGE_ID,
				LocaleUtil.toLanguageId(defaultLocale)
			).put(
				"classPK", String.valueOf(_assetVocabulary.getVocabularyId())
			).put(
				"groupId", String.valueOf(_assetVocabulary.getGroupId())
			).put(
				"localizedNames", localizedNames
			).put(
				"showAssetVocabularyLevel",
				String.valueOf(showAssetVocabularyLevel)
			).put(
				"title", _assetVocabulary.getTitle(defaultLocale)
			).put(
				"type", "asset-vocabulary"
			).put(
				"useCustomName",
				String.valueOf(!Objects.equals("{}", localizedNames))
			).put(
				"uuid", _assetVocabulary.getUuid()
			).buildString(),
			_serviceContext);
	}

	private ThemeDisplay _getThemeDisplay() throws Exception {
		ThemeDisplay themeDisplay = new ThemeDisplay();

		themeDisplay.setCompany(
			_companyLocalService.getCompany(_group.getCompanyId()));
		themeDisplay.setPermissionChecker(
			PermissionThreadLocal.getPermissionChecker());
		themeDisplay.setSiteGroupId(_group.getGroupId());
		themeDisplay.setUser(TestPropsValues.getUser());

		return themeDisplay;
	}

	@Inject
	private AssetCategoryLocalService _assetCategoryLocalService;

	private AssetVocabulary _assetVocabulary;

	@Inject
	private AssetVocabularyLocalService _assetVocabularyLocalService;

	@Inject
	private CompanyLocalService _companyLocalService;

	@DeleteAfterTestRun
	private Group _group;

	@Inject
	private Portal _portal;

	private ServiceContext _serviceContext;

	@Inject
	private SiteNavigationMenuItemLocalService
		_siteNavigationMenuItemLocalService;

	@Inject
	private SiteNavigationMenuItemTypeRegistry
		_siteNavigationMenuItemTypeRegistry;

	@Inject
	private SiteNavigationMenuLocalService _siteNavigationMenuLocalService;

}