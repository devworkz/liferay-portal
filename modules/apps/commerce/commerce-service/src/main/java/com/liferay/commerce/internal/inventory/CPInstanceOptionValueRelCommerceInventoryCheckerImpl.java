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

package com.liferay.commerce.internal.inventory;

import com.liferay.commerce.inventory.CommerceInventoryChecker;
import com.liferay.commerce.product.model.CPInstanceOptionValueRel;
import com.liferay.commerce.product.service.CPInstanceLocalService;
import com.liferay.portal.kernel.model.BaseModel;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Igor Beslic
 */
@Component(
	immediate = true,
	property = "commerce.inventory.checker.target=CPInstanceOptionValueRel",
	service = CommerceInventoryChecker.class
)
public class CPInstanceOptionValueRelCommerceInventoryCheckerImpl
	extends BaseCommerceInventoryChecker<CPInstanceOptionValueRel> {

	@Override
	public List<CPInstanceOptionValueRel> filterByAvailability(
		List<CPInstanceOptionValueRel> cpInstanceOptionValueRels) {

		List<CPInstanceOptionValueRel> filtered = new ArrayList<>();

		for (CPInstanceOptionValueRel cpInstanceOptionValueRel :
				cpInstanceOptionValueRels) {

			if (isAvailable(cpInstanceOptionValueRel)) {
				filtered.add(cpInstanceOptionValueRel);
			}
		}

		return filtered;
	}

	@Override
	public boolean isAvailable(BaseModel<CPInstanceOptionValueRel> baseModel) {
		CPInstanceOptionValueRel cpInstanceOptionValueRel =
			(CPInstanceOptionValueRel)baseModel;

		return isAvailable(
			_cpInstanceLocalService.fetchCPInstance(
				cpInstanceOptionValueRel.getCPInstanceId()),
			1);
	}

	@Reference
	private CPInstanceLocalService _cpInstanceLocalService;

}