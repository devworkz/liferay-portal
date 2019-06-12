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

package com.liferay.portal.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.Subscription;
import com.liferay.portal.kernel.model.SubscriptionModel;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Types;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model implementation for the Subscription service. Represents a row in the &quot;Subscription&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface </code>SubscriptionModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link SubscriptionImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SubscriptionImpl
 * @deprecated
 * @generated
 */
@Deprecated
@ProviderType
public class SubscriptionModelImpl
	extends BaseModelImpl<Subscription> implements SubscriptionModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a subscription model instance should use the <code>Subscription</code> interface instead.
	 */
	public static final String TABLE_NAME = "Subscription";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"subscriptionId", Types.BIGINT},
		{"groupId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"classNameId", Types.BIGINT}, {"classPK", Types.BIGINT},
		{"frequency", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("subscriptionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("classNameId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("classPK", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("frequency", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table Subscription (mvccVersion LONG default 0 not null,subscriptionId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,classNameId LONG,classPK LONG,frequency VARCHAR(75) null)";

	public static final String TABLE_SQL_DROP = "drop table Subscription";

	public static final String ORDER_BY_JPQL =
		" ORDER BY subscription.subscriptionId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY Subscription.subscriptionId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.portal.util.PropsUtil.get(
			"value.object.entity.cache.enabled.com.liferay.portal.kernel.model.Subscription"),
		true);

	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.portal.util.PropsUtil.get(
			"value.object.finder.cache.enabled.com.liferay.portal.kernel.model.Subscription"),
		true);

	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(
		com.liferay.portal.util.PropsUtil.get(
			"value.object.column.bitmask.enabled.com.liferay.portal.kernel.model.Subscription"),
		true);

	public static final long CLASSNAMEID_COLUMN_BITMASK = 1L;

	public static final long CLASSPK_COLUMN_BITMASK = 2L;

	public static final long COMPANYID_COLUMN_BITMASK = 4L;

	public static final long GROUPID_COLUMN_BITMASK = 8L;

	public static final long USERID_COLUMN_BITMASK = 16L;

	public static final long SUBSCRIPTIONID_COLUMN_BITMASK = 32L;

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.portal.util.PropsUtil.get(
			"lock.expiration.time.com.liferay.portal.kernel.model.Subscription"));

	public SubscriptionModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _subscriptionId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setSubscriptionId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _subscriptionId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return Subscription.class;
	}

	@Override
	public String getModelClassName() {
		return Subscription.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<Subscription, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<Subscription, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Subscription, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((Subscription)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<Subscription, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<Subscription, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(Subscription)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<Subscription, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<Subscription, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, Subscription>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			Subscription.class.getClassLoader(), Subscription.class,
			ModelWrapper.class);

		try {
			Constructor<Subscription> constructor =
				(Constructor<Subscription>)proxyClass.getConstructor(
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

	private static final Map<String, Function<Subscription, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<Subscription, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<Subscription, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<Subscription, Object>>();
		Map<String, BiConsumer<Subscription, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<Subscription, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion", Subscription::getMvccVersion);
		attributeSetterBiConsumers.put(
			"mvccVersion",
			(BiConsumer<Subscription, Long>)Subscription::setMvccVersion);
		attributeGetterFunctions.put(
			"subscriptionId", Subscription::getSubscriptionId);
		attributeSetterBiConsumers.put(
			"subscriptionId",
			(BiConsumer<Subscription, Long>)Subscription::setSubscriptionId);
		attributeGetterFunctions.put("groupId", Subscription::getGroupId);
		attributeSetterBiConsumers.put(
			"groupId",
			(BiConsumer<Subscription, Long>)Subscription::setGroupId);
		attributeGetterFunctions.put("companyId", Subscription::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<Subscription, Long>)Subscription::setCompanyId);
		attributeGetterFunctions.put("userId", Subscription::getUserId);
		attributeSetterBiConsumers.put(
			"userId", (BiConsumer<Subscription, Long>)Subscription::setUserId);
		attributeGetterFunctions.put("userName", Subscription::getUserName);
		attributeSetterBiConsumers.put(
			"userName",
			(BiConsumer<Subscription, String>)Subscription::setUserName);
		attributeGetterFunctions.put("createDate", Subscription::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<Subscription, Date>)Subscription::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate", Subscription::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<Subscription, Date>)Subscription::setModifiedDate);
		attributeGetterFunctions.put(
			"classNameId", Subscription::getClassNameId);
		attributeSetterBiConsumers.put(
			"classNameId",
			(BiConsumer<Subscription, Long>)Subscription::setClassNameId);
		attributeGetterFunctions.put("classPK", Subscription::getClassPK);
		attributeSetterBiConsumers.put(
			"classPK",
			(BiConsumer<Subscription, Long>)Subscription::setClassPK);
		attributeGetterFunctions.put("frequency", Subscription::getFrequency);
		attributeSetterBiConsumers.put(
			"frequency",
			(BiConsumer<Subscription, String>)Subscription::setFrequency);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@Override
	public long getMvccVersion() {
		return _mvccVersion;
	}

	@Override
	public void setMvccVersion(long mvccVersion) {
		_mvccVersion = mvccVersion;
	}

	@Override
	public long getSubscriptionId() {
		return _subscriptionId;
	}

	@Override
	public void setSubscriptionId(long subscriptionId) {
		_subscriptionId = subscriptionId;
	}

	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_columnBitmask |= GROUPID_COLUMN_BITMASK;

		if (!_setOriginalGroupId) {
			_setOriginalGroupId = true;

			_originalGroupId = _groupId;
		}

		_groupId = groupId;
	}

	public long getOriginalGroupId() {
		return _originalGroupId;
	}

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_columnBitmask |= COMPANYID_COLUMN_BITMASK;

		if (!_setOriginalCompanyId) {
			_setOriginalCompanyId = true;

			_originalCompanyId = _companyId;
		}

		_companyId = companyId;
	}

	public long getOriginalCompanyId() {
		return _originalCompanyId;
	}

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

	@Override
	public String getUserName() {
		if (_userName == null) {
			return "";
		}
		else {
			return _userName;
		}
	}

	@Override
	public void setUserName(String userName) {
		_userName = userName;
	}

	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public boolean hasSetModifiedDate() {
		return _setModifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_setModifiedDate = true;

		_modifiedDate = modifiedDate;
	}

	@Override
	public String getClassName() {
		if (getClassNameId() <= 0) {
			return "";
		}

		return PortalUtil.getClassName(getClassNameId());
	}

	@Override
	public void setClassName(String className) {
		long classNameId = 0;

		if (Validator.isNotNull(className)) {
			classNameId = PortalUtil.getClassNameId(className);
		}

		setClassNameId(classNameId);
	}

	@Override
	public long getClassNameId() {
		return _classNameId;
	}

	@Override
	public void setClassNameId(long classNameId) {
		_columnBitmask |= CLASSNAMEID_COLUMN_BITMASK;

		if (!_setOriginalClassNameId) {
			_setOriginalClassNameId = true;

			_originalClassNameId = _classNameId;
		}

		_classNameId = classNameId;
	}

	public long getOriginalClassNameId() {
		return _originalClassNameId;
	}

	@Override
	public long getClassPK() {
		return _classPK;
	}

	@Override
	public void setClassPK(long classPK) {
		_columnBitmask |= CLASSPK_COLUMN_BITMASK;

		if (!_setOriginalClassPK) {
			_setOriginalClassPK = true;

			_originalClassPK = _classPK;
		}

		_classPK = classPK;
	}

	public long getOriginalClassPK() {
		return _originalClassPK;
	}

	@Override
	public String getFrequency() {
		if (_frequency == null) {
			return "";
		}
		else {
			return _frequency;
		}
	}

	@Override
	public void setFrequency(String frequency) {
		_frequency = frequency;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), Subscription.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public Subscription toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, Subscription>
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
		SubscriptionImpl subscriptionImpl = new SubscriptionImpl();

		subscriptionImpl.setMvccVersion(getMvccVersion());
		subscriptionImpl.setSubscriptionId(getSubscriptionId());
		subscriptionImpl.setGroupId(getGroupId());
		subscriptionImpl.setCompanyId(getCompanyId());
		subscriptionImpl.setUserId(getUserId());
		subscriptionImpl.setUserName(getUserName());
		subscriptionImpl.setCreateDate(getCreateDate());
		subscriptionImpl.setModifiedDate(getModifiedDate());
		subscriptionImpl.setClassNameId(getClassNameId());
		subscriptionImpl.setClassPK(getClassPK());
		subscriptionImpl.setFrequency(getFrequency());

		subscriptionImpl.resetOriginalValues();

		return subscriptionImpl;
	}

	@Override
	public int compareTo(Subscription subscription) {
		long primaryKey = subscription.getPrimaryKey();

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

		if (!(obj instanceof Subscription)) {
			return false;
		}

		Subscription subscription = (Subscription)obj;

		long primaryKey = subscription.getPrimaryKey();

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
		SubscriptionModelImpl subscriptionModelImpl = this;

		subscriptionModelImpl._originalGroupId = subscriptionModelImpl._groupId;

		subscriptionModelImpl._setOriginalGroupId = false;

		subscriptionModelImpl._originalCompanyId =
			subscriptionModelImpl._companyId;

		subscriptionModelImpl._setOriginalCompanyId = false;

		subscriptionModelImpl._originalUserId = subscriptionModelImpl._userId;

		subscriptionModelImpl._setOriginalUserId = false;

		subscriptionModelImpl._setModifiedDate = false;

		subscriptionModelImpl._originalClassNameId =
			subscriptionModelImpl._classNameId;

		subscriptionModelImpl._setOriginalClassNameId = false;

		subscriptionModelImpl._originalClassPK = subscriptionModelImpl._classPK;

		subscriptionModelImpl._setOriginalClassPK = false;

		subscriptionModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<Subscription> toCacheModel() {
		SubscriptionCacheModel subscriptionCacheModel =
			new SubscriptionCacheModel();

		subscriptionCacheModel.mvccVersion = getMvccVersion();

		subscriptionCacheModel.subscriptionId = getSubscriptionId();

		subscriptionCacheModel.groupId = getGroupId();

		subscriptionCacheModel.companyId = getCompanyId();

		subscriptionCacheModel.userId = getUserId();

		subscriptionCacheModel.userName = getUserName();

		String userName = subscriptionCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			subscriptionCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			subscriptionCacheModel.createDate = createDate.getTime();
		}
		else {
			subscriptionCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			subscriptionCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			subscriptionCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		subscriptionCacheModel.classNameId = getClassNameId();

		subscriptionCacheModel.classPK = getClassPK();

		subscriptionCacheModel.frequency = getFrequency();

		String frequency = subscriptionCacheModel.frequency;

		if ((frequency != null) && (frequency.length() == 0)) {
			subscriptionCacheModel.frequency = null;
		}

		return subscriptionCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<Subscription, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<Subscription, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Subscription, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((Subscription)this));
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
		Map<String, Function<Subscription, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<Subscription, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Subscription, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((Subscription)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, Subscription>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private long _mvccVersion;
	private long _subscriptionId;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _userId;
	private long _originalUserId;
	private boolean _setOriginalUserId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _classNameId;
	private long _originalClassNameId;
	private boolean _setOriginalClassNameId;
	private long _classPK;
	private long _originalClassPK;
	private boolean _setOriginalClassPK;
	private String _frequency;
	private long _columnBitmask;
	private Subscription _escapedModel;

}