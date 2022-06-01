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

import {ClayButtonWithIcon} from '@clayui/button';
import ClayForm, {ClayInput} from '@clayui/form';
import {openSelectionModal} from 'frontend-js-web';
import React, {useState} from 'react';

export default function ThemeCSSReplacementSelector({
	portletNamespace,
	selectThemeCSSClientExtensionEventName,
	selectThemeCSSClientExtensionURL,
	themeCSSExtensionName,
}) {
	const [extensionName, setExtensionName] = useState(themeCSSExtensionName);
	const [extensionEntryId, setExtensionEntryId] = useState('');
	const [extensionEntryType, setExtensionEntryType] = useState('');

	const onClick = () => {
		openSelectionModal({
			onSelect: (selectedItem) => {
				const item = JSON.parse(selectedItem.value);

				setExtensionEntryId(item.clientExtensionEntryId);
				setExtensionEntryType(item.type);
				setExtensionName(item.name);
			},
			selectEventName: selectThemeCSSClientExtensionEventName,
			title: Liferay.Language.get('select-theme-css-extension'),
			url: selectThemeCSSClientExtensionURL,
		});
	};

	return (
		<>
			<ClayInput
				name={`${portletNamespace}themeCSSExtensionEntryId`}
				type="hidden"
				value={extensionEntryId}
			/>
			<ClayInput
				name={`${portletNamespace}themeCSSExtensionEntryType`}
				type="hidden"
				value={extensionEntryType}
			/>
			<ClayForm.Group>
				<label
					htmlFor={`${portletNamespace}themeCSSReplacementExtension`}
				>
					{Liferay.Language.get('extension')}
				</label>

				<ClayInput.Group className="w-50" small>
					<ClayInput.GroupItem>
						<ClayInput
							id={`${portletNamespace}themeCSSReplacementExtension`}
							onClick={onClick}
							placeholder={Liferay.Language.get(
								'no-theme-css-extension-loaded'
							)}
							readOnly
							type="text"
							value={extensionName}
						/>
					</ClayInput.GroupItem>

					<ClayInput.GroupItem shrink>
						{extensionName ? (
							<>
								<ClayButtonWithIcon
									className="mr-2"
									displayType="secondary"
									onClick={onClick}
									small
									symbol="change"
								/>

								<ClayButtonWithIcon
									displayType="secondary"
									onClick={() => {
										setExtensionName('');
										setExtensionEntryId('');
										setExtensionEntryType('');
									}}
									small
									symbol="trash"
								/>
							</>
						) : (
							<ClayButtonWithIcon
								displayType="secondary"
								onClick={onClick}
								small
								symbol="plus"
							/>
						)}
					</ClayInput.GroupItem>
				</ClayInput.Group>
			</ClayForm.Group>
		</>
	);
}
