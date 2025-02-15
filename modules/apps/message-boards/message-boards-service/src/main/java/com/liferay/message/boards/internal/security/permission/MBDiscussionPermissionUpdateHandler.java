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

package com.liferay.message.boards.internal.security.permission;

import com.liferay.message.boards.model.MBDiscussion;
import com.liferay.message.boards.service.MBDiscussionLocalService;
import com.liferay.portal.kernel.security.permission.PermissionUpdateHandler;
import com.liferay.portal.kernel.util.GetterUtil;

import java.util.Date;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Gergely Mathe
 */
@Component(
	property = "model.class.name=com.liferay.message.boards.model.MBDiscussion",
	service = PermissionUpdateHandler.class
)
public class MBDiscussionPermissionUpdateHandler
	implements PermissionUpdateHandler {

	@Override
	public void updatedPermission(String primKey) {
		MBDiscussion mbDiscussion = _mbDiscussionLocalService.fetchMBDiscussion(
			GetterUtil.getLong(primKey));

		if (mbDiscussion == null) {
			return;
		}

		mbDiscussion.setModifiedDate(new Date());

		_mbDiscussionLocalService.updateMBDiscussion(mbDiscussion);
	}

	@Reference
	private MBDiscussionLocalService _mbDiscussionLocalService;

}