/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 */

import MDFRequestActivity from '../../../../../../../../../../common/interfaces/mdfRequestActivity';
import {Liferay} from '../../../../../../../../../../common/services/liferay';
import Table from '../../../../../Table';

interface IProps {
	values: MDFRequestActivity;
}

const MiscellaneousMarket = ({values}: IProps) => (
	<div>
		<Table
			items={[
				{
					title: 'Activity name',
					value: values.name,
				},
				{
					title: 'Type of Activity',
					value: values.r_typeActivityToActivities_c_typeActivityId,
				},
				{
					title: 'Tactic',
					value: values.r_tacticToActivities_c_tacticId,
				},
				{
					title: 'Marketing activity',
					value: values.marketingActivity,
				},
				{
					title: 'Start Date',
					value: new Date(values.startDate).toLocaleDateString(
						Liferay.ThemeDisplay.getBCP47LanguageId()
					),
				},
				{
					title: 'End Date',
					value: new Date(values.endDate).toLocaleDateString(
						Liferay.ThemeDisplay.getBCP47LanguageId()
					),
				},
			]}
			title="Campaign Activity"
		/>
	</div>
);

export default MiscellaneousMarket;
