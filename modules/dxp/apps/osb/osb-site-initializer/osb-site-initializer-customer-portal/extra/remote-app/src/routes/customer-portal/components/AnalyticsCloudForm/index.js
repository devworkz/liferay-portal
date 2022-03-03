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

import ClayForm from '@clayui/form';

// import {FieldArray} from 'formik';

import AnalyticsCloudInputs from '../AnalyticsCloudInputs';

const AnalyticsCloudForm = () => {
	return (
		<>
			<p className="text-neutral-10 text-paragraph">
				Allowed Email Domains
			</p>
			<ClayForm>
				<ClayForm.Group>
					<AnalyticsCloudInputs />
				</ClayForm.Group>
			</ClayForm>
		</>
	);
};

export default AnalyticsCloudForm;
