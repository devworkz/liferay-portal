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

package com.liferay.push.notifications.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.push.notifications.model.PushNotificationsDevice;
import com.liferay.push.notifications.model.PushNotificationsDeviceModel;
import com.liferay.push.notifications.model.PushNotificationsDeviceSoap;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the PushNotificationsDevice service. Represents a row in the &quot;PushNotificationsDevice&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface </code>PushNotificationsDeviceModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link PushNotificationsDeviceImpl}.
 * </p>
 *
 * @author Bruno Farache
 * @see PushNotificationsDeviceImpl
 * @generated
 */
@JSON(strict = true)
@ProviderType
public class PushNotificationsDeviceModelImpl
	extends BaseModelImpl<PushNotificationsDevice>
	implements PushNotificationsDeviceModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a push notifications device model instance should use the <code>PushNotificationsDevice</code> interface instead.
	 */
	public static final String TABLE_NAME = "PushNotificationsDevice";

	public static final Object[][] TABLE_COLUMNS = {
		{"pushNotificationsDeviceId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"createDate", Types.TIMESTAMP}, {"platform", Types.VARCHAR},
		{"token", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("pushNotificationsDeviceId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("platform", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("token", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table PushNotificationsDevice (pushNotificationsDeviceId LONG not null primary key,companyId LONG,userId LONG,createDate DATE null,platform VARCHAR(75) null,token STRING null)";

	public static final String TABLE_SQL_DROP =
		"drop table PushNotificationsDevice";

	public static final String ORDER_BY_JPQL =
		" ORDER BY pushNotificationsDevice.pushNotificationsDeviceId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY PushNotificationsDevice.pushNotificationsDeviceId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.push.notifications.service.util.ServiceProps.get(
			"value.object.entity.cache.enabled.com.liferay.push.notifications.model.PushNotificationsDevice"),
		true);

	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.push.notifications.service.util.ServiceProps.get(
			"value.object.finder.cache.enabled.com.liferay.push.notifications.model.PushNotificationsDevice"),
		true);

	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(
		com.liferay.push.notifications.service.util.ServiceProps.get(
			"value.object.column.bitmask.enabled.com.liferay.push.notifications.model.PushNotificationsDevice"),
		true);

	public static final long PLATFORM_COLUMN_BITMASK = 1L;

	public static final long TOKEN_COLUMN_BITMASK = 2L;

	public static final long USERID_COLUMN_BITMASK = 4L;

	public static final long PUSHNOTIFICATIONSDEVICEID_COLUMN_BITMASK = 8L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static PushNotificationsDevice toModel(
		PushNotificationsDeviceSoap soapModel) {

		if (soapModel == null) {
			return null;
		}

		PushNotificationsDevice model = new PushNotificationsDeviceImpl();

		model.setPushNotificationsDeviceId(
			soapModel.getPushNotificationsDeviceId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setCreateDate(soapModel.getCreateDate());
		model.setPlatform(soapModel.getPlatform());
		model.setToken(soapModel.getToken());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<PushNotificationsDevice> toModels(
		PushNotificationsDeviceSoap[] soapModels) {

		if (soapModels == null) {
			return null;
		}

		List<PushNotificationsDevice> models =
			new ArrayList<PushNotificationsDevice>(soapModels.length);

		for (PushNotificationsDeviceSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.push.notifications.service.util.ServiceProps.get(
			"lock.expiration.time.com.liferay.push.notifications.model.PushNotificationsDevice"));

	public PushNotificationsDeviceModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _pushNotificationsDeviceId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setPushNotificationsDeviceId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _pushNotificationsDeviceId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return PushNotificationsDevice.class;
	}

	@Override
	public String getModelClassName() {
		return PushNotificationsDevice.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<PushNotificationsDevice, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<PushNotificationsDevice, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<PushNotificationsDevice, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((PushNotificationsDevice)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<PushNotificationsDevice, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<PushNotificationsDevice, Object>
				attributeSetterBiConsumer = attributeSetterBiConsumers.get(
					attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(PushNotificationsDevice)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<PushNotificationsDevice, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<PushNotificationsDevice, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, PushNotificationsDevice>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			PushNotificationsDevice.class.getClassLoader(),
			PushNotificationsDevice.class, ModelWrapper.class);

		try {
			Constructor<PushNotificationsDevice> constructor =
				(Constructor<PushNotificationsDevice>)proxyClass.getConstructor(
					InvocationHandler.class);

			return invocationHandler -> {
				try {
					return constructor.newInstance(invocationHandler);
				}
				catch (ReflectiveOperationException roe) {
					throw new InternalError(roe);
				}
			};
		}
		catch (NoSuchMethodException nsme) {
			throw new InternalError(nsme);
		}
	}

	private static final Map<String, Function<PushNotificationsDevice, Object>>
		_attributeGetterFunctions;
	private static final Map
		<String, BiConsumer<PushNotificationsDevice, Object>>
			_attributeSetterBiConsumers;

	static {
		Map<String, Function<PushNotificationsDevice, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String, Function<PushNotificationsDevice, Object>>();
		Map<String, BiConsumer<PushNotificationsDevice, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap
					<String, BiConsumer<PushNotificationsDevice, ?>>();

		attributeGetterFunctions.put(
			"pushNotificationsDeviceId",
			new Function<PushNotificationsDevice, Object>() {

				@Override
				public Object apply(
					PushNotificationsDevice pushNotificationsDevice) {

					return pushNotificationsDevice.
						getPushNotificationsDeviceId();
				}

			});
		attributeSetterBiConsumers.put(
			"pushNotificationsDeviceId",
			new BiConsumer<PushNotificationsDevice, Object>() {

				@Override
				public void accept(
					PushNotificationsDevice pushNotificationsDevice,
					Object pushNotificationsDeviceId) {

					pushNotificationsDevice.setPushNotificationsDeviceId(
						(Long)pushNotificationsDeviceId);
				}

			});
		attributeGetterFunctions.put(
			"companyId",
			new Function<PushNotificationsDevice, Object>() {

				@Override
				public Object apply(
					PushNotificationsDevice pushNotificationsDevice) {

					return pushNotificationsDevice.getCompanyId();
				}

			});
		attributeSetterBiConsumers.put(
			"companyId",
			new BiConsumer<PushNotificationsDevice, Object>() {

				@Override
				public void accept(
					PushNotificationsDevice pushNotificationsDevice,
					Object companyId) {

					pushNotificationsDevice.setCompanyId((Long)companyId);
				}

			});
		attributeGetterFunctions.put(
			"userId",
			new Function<PushNotificationsDevice, Object>() {

				@Override
				public Object apply(
					PushNotificationsDevice pushNotificationsDevice) {

					return pushNotificationsDevice.getUserId();
				}

			});
		attributeSetterBiConsumers.put(
			"userId",
			new BiConsumer<PushNotificationsDevice, Object>() {

				@Override
				public void accept(
					PushNotificationsDevice pushNotificationsDevice,
					Object userId) {

					pushNotificationsDevice.setUserId((Long)userId);
				}

			});
		attributeGetterFunctions.put(
			"createDate",
			new Function<PushNotificationsDevice, Object>() {

				@Override
				public Object apply(
					PushNotificationsDevice pushNotificationsDevice) {

					return pushNotificationsDevice.getCreateDate();
				}

			});
		attributeSetterBiConsumers.put(
			"createDate",
			new BiConsumer<PushNotificationsDevice, Object>() {

				@Override
				public void accept(
					PushNotificationsDevice pushNotificationsDevice,
					Object createDate) {

					pushNotificationsDevice.setCreateDate((Date)createDate);
				}

			});
		attributeGetterFunctions.put(
			"platform",
			new Function<PushNotificationsDevice, Object>() {

				@Override
				public Object apply(
					PushNotificationsDevice pushNotificationsDevice) {

					return pushNotificationsDevice.getPlatform();
				}

			});
		attributeSetterBiConsumers.put(
			"platform",
			new BiConsumer<PushNotificationsDevice, Object>() {

				@Override
				public void accept(
					PushNotificationsDevice pushNotificationsDevice,
					Object platform) {

					pushNotificationsDevice.setPlatform((String)platform);
				}

			});
		attributeGetterFunctions.put(
			"token",
			new Function<PushNotificationsDevice, Object>() {

				@Override
				public Object apply(
					PushNotificationsDevice pushNotificationsDevice) {

					return pushNotificationsDevice.getToken();
				}

			});
		attributeSetterBiConsumers.put(
			"token",
			new BiConsumer<PushNotificationsDevice, Object>() {

				@Override
				public void accept(
					PushNotificationsDevice pushNotificationsDevice,
					Object token) {

					pushNotificationsDevice.setToken((String)token);
				}

			});

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@JSON
	@Override
	public long getPushNotificationsDeviceId() {
		return _pushNotificationsDeviceId;
	}

	@Override
	public void setPushNotificationsDeviceId(long pushNotificationsDeviceId) {
		_pushNotificationsDeviceId = pushNotificationsDeviceId;
	}

	@JSON
	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	@JSON
	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_columnBitmask |= USERID_COLUMN_BITMASK;

		if (!_setOriginalUserId) {
			_setOriginalUserId = true;

			_originalUserId = _userId;
		}

		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return "";
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	public long getOriginalUserId() {
		return _originalUserId;
	}

	@JSON
	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	@JSON
	@Override
	public String getPlatform() {
		if (_platform == null) {
			return "";
		}
		else {
			return _platform;
		}
	}

	@Override
	public void setPlatform(String platform) {
		_columnBitmask |= PLATFORM_COLUMN_BITMASK;

		if (_originalPlatform == null) {
			_originalPlatform = _platform;
		}

		_platform = platform;
	}

	public String getOriginalPlatform() {
		return GetterUtil.getString(_originalPlatform);
	}

	@JSON
	@Override
	public String getToken() {
		if (_token == null) {
			return "";
		}
		else {
			return _token;
		}
	}

	@Override
	public void setToken(String token) {
		_columnBitmask |= TOKEN_COLUMN_BITMASK;

		if (_originalToken == null) {
			_originalToken = _token;
		}

		_token = token;
	}

	public String getOriginalToken() {
		return GetterUtil.getString(_originalToken);
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), PushNotificationsDevice.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public PushNotificationsDevice toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, PushNotificationsDevice>
				escapedModelProxyProviderFunction =
					EscapedModelProxyProviderFunctionHolder.
						_escapedModelProxyProviderFunction;

			_escapedModel = escapedModelProxyProviderFunction.apply(
				new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		PushNotificationsDeviceImpl pushNotificationsDeviceImpl =
			new PushNotificationsDeviceImpl();

		pushNotificationsDeviceImpl.setPushNotificationsDeviceId(
			getPushNotificationsDeviceId());
		pushNotificationsDeviceImpl.setCompanyId(getCompanyId());
		pushNotificationsDeviceImpl.setUserId(getUserId());
		pushNotificationsDeviceImpl.setCreateDate(getCreateDate());
		pushNotificationsDeviceImpl.setPlatform(getPlatform());
		pushNotificationsDeviceImpl.setToken(getToken());

		pushNotificationsDeviceImpl.resetOriginalValues();

		return pushNotificationsDeviceImpl;
	}

	@Override
	public int compareTo(PushNotificationsDevice pushNotificationsDevice) {
		long primaryKey = pushNotificationsDevice.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PushNotificationsDevice)) {
			return false;
		}

		PushNotificationsDevice pushNotificationsDevice =
			(PushNotificationsDevice)obj;

		long primaryKey = pushNotificationsDevice.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return ENTITY_CACHE_ENABLED;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
		PushNotificationsDeviceModelImpl pushNotificationsDeviceModelImpl =
			this;

		pushNotificationsDeviceModelImpl._originalUserId =
			pushNotificationsDeviceModelImpl._userId;

		pushNotificationsDeviceModelImpl._setOriginalUserId = false;

		pushNotificationsDeviceModelImpl._originalPlatform =
			pushNotificationsDeviceModelImpl._platform;

		pushNotificationsDeviceModelImpl._originalToken =
			pushNotificationsDeviceModelImpl._token;

		pushNotificationsDeviceModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<PushNotificationsDevice> toCacheModel() {
		PushNotificationsDeviceCacheModel pushNotificationsDeviceCacheModel =
			new PushNotificationsDeviceCacheModel();

		pushNotificationsDeviceCacheModel.pushNotificationsDeviceId =
			getPushNotificationsDeviceId();

		pushNotificationsDeviceCacheModel.companyId = getCompanyId();

		pushNotificationsDeviceCacheModel.userId = getUserId();

		Date createDate = getCreateDate();

		if (createDate != null) {
			pushNotificationsDeviceCacheModel.createDate = createDate.getTime();
		}
		else {
			pushNotificationsDeviceCacheModel.createDate = Long.MIN_VALUE;
		}

		pushNotificationsDeviceCacheModel.platform = getPlatform();

		String platform = pushNotificationsDeviceCacheModel.platform;

		if ((platform != null) && (platform.length() == 0)) {
			pushNotificationsDeviceCacheModel.platform = null;
		}

		pushNotificationsDeviceCacheModel.token = getToken();

		String token = pushNotificationsDeviceCacheModel.token;

		if ((token != null) && (token.length() == 0)) {
			pushNotificationsDeviceCacheModel.token = null;
		}

		return pushNotificationsDeviceCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<PushNotificationsDevice, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<PushNotificationsDevice, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<PushNotificationsDevice, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(
				attributeGetterFunction.apply((PushNotificationsDevice)this));
			sb.append(", ");
		}

		if (sb.index() > 1) {
			sb.setIndex(sb.index() - 1);
		}

		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		Map<String, Function<PushNotificationsDevice, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<PushNotificationsDevice, Object>>
				entry : attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<PushNotificationsDevice, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(
				attributeGetterFunction.apply((PushNotificationsDevice)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function
			<InvocationHandler, PushNotificationsDevice>
				_escapedModelProxyProviderFunction =
					_getProxyProviderFunction();

	}

	private long _pushNotificationsDeviceId;
	private long _companyId;
	private long _userId;
	private long _originalUserId;
	private boolean _setOriginalUserId;
	private Date _createDate;
	private String _platform;
	private String _originalPlatform;
	private String _token;
	private String _originalToken;
	private long _columnBitmask;
	private PushNotificationsDevice _escapedModel;

}