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

import PropTypes from 'prop-types';
import React, {useCallback} from 'react';

function PageTypeSelector(props) {
	const handleOnChange = useCallback(
		event => {
			const pageType = event.target.value;

			if (pageType === 'private-pages') {
				Liferay.Util.Session.set(
					`${props.namespace}PRIVATE_LAYOUT`,
					'true'
				);
			} else {
				Liferay.Util.Session.set(
					`${props.namespace}PRIVATE_LAYOUT`,
					'false'
				);
			}

			Liferay.Util.navigate(window.location);
		},
		[props.namespace]
	);

	const handleOnClick = useCallback(() => {
		const tree = Liferay.component(`${props.namespace}pagesTree`);

		tree.collapseAll();
	}, [props.namespace]);

	return (
		<div className="align-items-center d-flex page-type-selector">
			<div className="flex-fill flex-grow-1">
				<select className="form-control" onChange={handleOnChange}>
					<option
						selected={!props.privateLayout}
						value="public-pages"
					>
						{Liferay.Language.get('public-pages')}
					</option>
					<option
						selected={props.privateLayout}
						value="private-pages"
					>
						{Liferay.Language.get('private-pages')}
					</option>
				</select>
			</div>
			<div className="flex-fill flex-grow-1 text-right">
				<a href="javascript: void(0);" onClick={handleOnClick}>
					{Liferay.Language.get('collapse-all')}
				</a>
			</div>
		</div>
	);
}

PageTypeSelector.propTypes = {
	namespace: PropTypes.string,
	privateLayout: PropTypes.bool
};

export default function(props) {
	return <PageTypeSelector {...props} />;
}
