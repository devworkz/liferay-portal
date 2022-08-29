<%--
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
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/clay" prefix="clay" %><%@
taglib uri="http://liferay.com/tld/frontend" prefix="liferay-frontend" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="com.liferay.info.collection.provider.InfoCollectionProvider" %><%@
page import="com.liferay.info.collection.provider.RelatedInfoItemCollectionProvider" %><%@
page import="com.liferay.info.collection.provider.item.selector.web.internal.constants.InfoCollectionProviderItemSelectorWebKeys" %><%@
page import="com.liferay.info.collection.provider.item.selector.web.internal.display.context.InfoCollectionProviderItemSelectorDisplayContext" %><%@
page import="com.liferay.info.collection.provider.item.selector.web.internal.display.context.InfoCollectionProviderItemSelectorManagementToolbarDisplayContext" %><%@
page import="com.liferay.info.collection.provider.item.selector.web.internal.display.context.RelatedInfoItemCollectionProviderItemSelectorDisplayContext" %><%@
page import="com.liferay.info.collection.provider.item.selector.web.internal.display.context.RelatedInfoItemCollectionProviderItemSelectorManagementToolbarDisplayContext" %><%@
page import="com.liferay.info.collection.provider.item.selector.web.internal.frontend.taglib.clay.servlet.taglib.InfoCollectionProviderVerticalCard" %><%@
page import="com.liferay.info.collection.provider.item.selector.web.internal.frontend.taglib.clay.servlet.taglib.RelatedInfoItemCollectionProviderVerticalCard" %><%@
page import="com.liferay.portal.kernel.dao.search.SearchContainer" %><%@
page import="com.liferay.portal.kernel.security.permission.ResourceActionsUtil" %><%@
page import="com.liferay.portal.kernel.util.HashMapBuilder" %><%@
page import="com.liferay.portal.kernel.util.Validator" %>

<liferay-frontend:defineObjects />

<liferay-theme:defineObjects />

<portlet:defineObjects />