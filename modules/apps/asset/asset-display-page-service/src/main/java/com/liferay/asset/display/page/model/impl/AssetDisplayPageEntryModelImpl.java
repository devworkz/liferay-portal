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

package com.liferay.asset.display.page.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.asset.display.page.model.AssetDisplayPageEntry;
import com.liferay.asset.display.page.model.AssetDisplayPageEntryModel;
import com.liferay.asset.display.page.model.AssetDisplayPageEntrySoap;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
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
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;

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
 * The base model implementation for the AssetDisplayPageEntry service. Represents a row in the &quot;AssetDisplayPageEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface </code>AssetDisplayPageEntryModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link AssetDisplayPageEntryImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetDisplayPageEntryImpl
 * @generated
 */
@JSON(strict = true)
@ProviderType
public class AssetDisplayPageEntryModelImpl
	extends BaseModelImpl<AssetDisplayPageEntry>
	implements AssetDisplayPageEntryModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a asset display page entry model instance should use the <code>AssetDisplayPageEntry</code> interface instead.
	 */
	public static final String TABLE_NAME = "AssetDisplayPageEntry";

	public static final Object[][] TABLE_COLUMNS = {
		{"uuid_", Types.VARCHAR}, {"assetDisplayPageEntryId", Types.BIGINT},
		{"groupId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"classNameId", Types.BIGINT}, {"classPK", Types.BIGINT},
		{"layoutPageTemplateEntryId", Types.BIGINT}, {"type_", Types.INTEGER}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("assetDisplayPageEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("classNameId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("classPK", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("layoutPageTemplateEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("type_", Types.INTEGER);
	}

	public static final String TABLE_SQL_CREATE =
		"create table AssetDisplayPageEntry (uuid_ VARCHAR(75) null,assetDisplayPageEntryId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,classNameId LONG,classPK LONG,layoutPageTemplateEntryId LONG,type_ INTEGER)";

	public static final String TABLE_SQL_DROP =
		"drop table AssetDisplayPageEntry";

	public static final String ORDER_BY_JPQL =
		" ORDER BY assetDisplayPageEntry.assetDisplayPageEntryId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY AssetDisplayPageEntry.assetDisplayPageEntryId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.asset.display.page.service.util.ServiceProps.get(
			"value.object.entity.cache.enabled.com.liferay.asset.display.page.model.AssetDisplayPageEntry"),
		true);

	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.asset.display.page.service.util.ServiceProps.get(
			"value.object.finder.cache.enabled.com.liferay.asset.display.page.model.AssetDisplayPageEntry"),
		true);

	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(
		com.liferay.asset.display.page.service.util.ServiceProps.get(
			"value.object.column.bitmask.enabled.com.liferay.asset.display.page.model.AssetDisplayPageEntry"),
		true);

	public static final long CLASSNAMEID_COLUMN_BITMASK = 1L;

	public static final long CLASSPK_COLUMN_BITMASK = 2L;

	public static final long COMPANYID_COLUMN_BITMASK = 4L;

	public static final long GROUPID_COLUMN_BITMASK = 8L;

	public static final long LAYOUTPAGETEMPLATEENTRYID_COLUMN_BITMASK = 16L;

	public static final long UUID_COLUMN_BITMASK = 32L;

	public static final long ASSETDISPLAYPAGEENTRYID_COLUMN_BITMASK = 64L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static AssetDisplayPageEntry toModel(
		AssetDisplayPageEntrySoap soapModel) {

		if (soapModel == null) {
			return null;
		}

		AssetDisplayPageEntry model = new AssetDisplayPageEntryImpl();

		model.setUuid(soapModel.getUuid());
		model.setAssetDisplayPageEntryId(
			soapModel.getAssetDisplayPageEntryId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setClassNameId(soapModel.getClassNameId());
		model.setClassPK(soapModel.getClassPK());
		model.setLayoutPageTemplateEntryId(
			soapModel.getLayoutPageTemplateEntryId());
		model.setType(soapModel.getType());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<AssetDisplayPageEntry> toModels(
		AssetDisplayPageEntrySoap[] soapModels) {

		if (soapModels == null) {
			return null;
		}

		List<AssetDisplayPageEntry> models =
			new ArrayList<AssetDisplayPageEntry>(soapModels.length);

		for (AssetDisplayPageEntrySoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.asset.display.page.service.util.ServiceProps.get(
			"lock.expiration.time.com.liferay.asset.display.page.model.AssetDisplayPageEntry"));

	public AssetDisplayPageEntryModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _assetDisplayPageEntryId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setAssetDisplayPageEntryId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _assetDisplayPageEntryId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return AssetDisplayPageEntry.class;
	}

	@Override
	public String getModelClassName() {
		return AssetDisplayPageEntry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<AssetDisplayPageEntry, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<AssetDisplayPageEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<AssetDisplayPageEntry, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((AssetDisplayPageEntry)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<AssetDisplayPageEntry, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<AssetDisplayPageEntry, Object>
				attributeSetterBiConsumer = attributeSetterBiConsumers.get(
					attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(AssetDisplayPageEntry)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<AssetDisplayPageEntry, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<AssetDisplayPageEntry, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, AssetDisplayPageEntry>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			AssetDisplayPageEntry.class.getClassLoader(),
			AssetDisplayPageEntry.class, ModelWrapper.class);

		try {
			Constructor<AssetDisplayPageEntry> constructor =
				(Constructor<AssetDisplayPageEntry>)proxyClass.getConstructor(
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

	private static final Map<String, Function<AssetDisplayPageEntry, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<AssetDisplayPageEntry, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<AssetDisplayPageEntry, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String, Function<AssetDisplayPageEntry, Object>>();
		Map<String, BiConsumer<AssetDisplayPageEntry, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap
					<String, BiConsumer<AssetDisplayPageEntry, ?>>();

		attributeGetterFunctions.put(
			"uuid",
			new Function<AssetDisplayPageEntry, Object>() {

				@Override
				public Object apply(
					AssetDisplayPageEntry assetDisplayPageEntry) {

					return assetDisplayPageEntry.getUuid();
				}

			});
		attributeSetterBiConsumers.put(
			"uuid",
			new BiConsumer<AssetDisplayPageEntry, Object>() {

				@Override
				public void accept(
					AssetDisplayPageEntry assetDisplayPageEntry, Object uuid) {

					assetDisplayPageEntry.setUuid((String)uuid);
				}

			});
		attributeGetterFunctions.put(
			"assetDisplayPageEntryId",
			new Function<AssetDisplayPageEntry, Object>() {

				@Override
				public Object apply(
					AssetDisplayPageEntry assetDisplayPageEntry) {

					return assetDisplayPageEntry.getAssetDisplayPageEntryId();
				}

			});
		attributeSetterBiConsumers.put(
			"assetDisplayPageEntryId",
			new BiConsumer<AssetDisplayPageEntry, Object>() {

				@Override
				public void accept(
					AssetDisplayPageEntry assetDisplayPageEntry,
					Object assetDisplayPageEntryId) {

					assetDisplayPageEntry.setAssetDisplayPageEntryId(
						(Long)assetDisplayPageEntryId);
				}

			});
		attributeGetterFunctions.put(
			"groupId",
			new Function<AssetDisplayPageEntry, Object>() {

				@Override
				public Object apply(
					AssetDisplayPageEntry assetDisplayPageEntry) {

					return assetDisplayPageEntry.getGroupId();
				}

			});
		attributeSetterBiConsumers.put(
			"groupId",
			new BiConsumer<AssetDisplayPageEntry, Object>() {

				@Override
				public void accept(
					AssetDisplayPageEntry assetDisplayPageEntry,
					Object groupId) {

					assetDisplayPageEntry.setGroupId((Long)groupId);
				}

			});
		attributeGetterFunctions.put(
			"companyId",
			new Function<AssetDisplayPageEntry, Object>() {

				@Override
				public Object apply(
					AssetDisplayPageEntry assetDisplayPageEntry) {

					return assetDisplayPageEntry.getCompanyId();
				}

			});
		attributeSetterBiConsumers.put(
			"companyId",
			new BiConsumer<AssetDisplayPageEntry, Object>() {

				@Override
				public void accept(
					AssetDisplayPageEntry assetDisplayPageEntry,
					Object companyId) {

					assetDisplayPageEntry.setCompanyId((Long)companyId);
				}

			});
		attributeGetterFunctions.put(
			"userId",
			new Function<AssetDisplayPageEntry, Object>() {

				@Override
				public Object apply(
					AssetDisplayPageEntry assetDisplayPageEntry) {

					return assetDisplayPageEntry.getUserId();
				}

			});
		attributeSetterBiConsumers.put(
			"userId",
			new BiConsumer<AssetDisplayPageEntry, Object>() {

				@Override
				public void accept(
					AssetDisplayPageEntry assetDisplayPageEntry,
					Object userId) {

					assetDisplayPageEntry.setUserId((Long)userId);
				}

			});
		attributeGetterFunctions.put(
			"userName",
			new Function<AssetDisplayPageEntry, Object>() {

				@Override
				public Object apply(
					AssetDisplayPageEntry assetDisplayPageEntry) {

					return assetDisplayPageEntry.getUserName();
				}

			});
		attributeSetterBiConsumers.put(
			"userName",
			new BiConsumer<AssetDisplayPageEntry, Object>() {

				@Override
				public void accept(
					AssetDisplayPageEntry assetDisplayPageEntry,
					Object userName) {

					assetDisplayPageEntry.setUserName((String)userName);
				}

			});
		attributeGetterFunctions.put(
			"createDate",
			new Function<AssetDisplayPageEntry, Object>() {

				@Override
				public Object apply(
					AssetDisplayPageEntry assetDisplayPageEntry) {

					return assetDisplayPageEntry.getCreateDate();
				}

			});
		attributeSetterBiConsumers.put(
			"createDate",
			new BiConsumer<AssetDisplayPageEntry, Object>() {

				@Override
				public void accept(
					AssetDisplayPageEntry assetDisplayPageEntry,
					Object createDate) {

					assetDisplayPageEntry.setCreateDate((Date)createDate);
				}

			});
		attributeGetterFunctions.put(
			"modifiedDate",
			new Function<AssetDisplayPageEntry, Object>() {

				@Override
				public Object apply(
					AssetDisplayPageEntry assetDisplayPageEntry) {

					return assetDisplayPageEntry.getModifiedDate();
				}

			});
		attributeSetterBiConsumers.put(
			"modifiedDate",
			new BiConsumer<AssetDisplayPageEntry, Object>() {

				@Override
				public void accept(
					AssetDisplayPageEntry assetDisplayPageEntry,
					Object modifiedDate) {

					assetDisplayPageEntry.setModifiedDate((Date)modifiedDate);
				}

			});
		attributeGetterFunctions.put(
			"classNameId",
			new Function<AssetDisplayPageEntry, Object>() {

				@Override
				public Object apply(
					AssetDisplayPageEntry assetDisplayPageEntry) {

					return assetDisplayPageEntry.getClassNameId();
				}

			});
		attributeSetterBiConsumers.put(
			"classNameId",
			new BiConsumer<AssetDisplayPageEntry, Object>() {

				@Override
				public void accept(
					AssetDisplayPageEntry assetDisplayPageEntry,
					Object classNameId) {

					assetDisplayPageEntry.setClassNameId((Long)classNameId);
				}

			});
		attributeGetterFunctions.put(
			"classPK",
			new Function<AssetDisplayPageEntry, Object>() {

				@Override
				public Object apply(
					AssetDisplayPageEntry assetDisplayPageEntry) {

					return assetDisplayPageEntry.getClassPK();
				}

			});
		attributeSetterBiConsumers.put(
			"classPK",
			new BiConsumer<AssetDisplayPageEntry, Object>() {

				@Override
				public void accept(
					AssetDisplayPageEntry assetDisplayPageEntry,
					Object classPK) {

					assetDisplayPageEntry.setClassPK((Long)classPK);
				}

			});
		attributeGetterFunctions.put(
			"layoutPageTemplateEntryId",
			new Function<AssetDisplayPageEntry, Object>() {

				@Override
				public Object apply(
					AssetDisplayPageEntry assetDisplayPageEntry) {

					return assetDisplayPageEntry.getLayoutPageTemplateEntryId();
				}

			});
		attributeSetterBiConsumers.put(
			"layoutPageTemplateEntryId",
			new BiConsumer<AssetDisplayPageEntry, Object>() {

				@Override
				public void accept(
					AssetDisplayPageEntry assetDisplayPageEntry,
					Object layoutPageTemplateEntryId) {

					assetDisplayPageEntry.setLayoutPageTemplateEntryId(
						(Long)layoutPageTemplateEntryId);
				}

			});
		attributeGetterFunctions.put(
			"type",
			new Function<AssetDisplayPageEntry, Object>() {

				@Override
				public Object apply(
					AssetDisplayPageEntry assetDisplayPageEntry) {

					return assetDisplayPageEntry.getType();
				}

			});
		attributeSetterBiConsumers.put(
			"type",
			new BiConsumer<AssetDisplayPageEntry, Object>() {

				@Override
				public void accept(
					AssetDisplayPageEntry assetDisplayPageEntry, Object type) {

					assetDisplayPageEntry.setType((Integer)type);
				}

			});

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@JSON
	@Override
	public String getUuid() {
		if (_uuid == null) {
			return "";
		}
		else {
			return _uuid;
		}
	}

	@Override
	public void setUuid(String uuid) {
		_columnBitmask |= UUID_COLUMN_BITMASK;

		if (_originalUuid == null) {
			_originalUuid = _uuid;
		}

		_uuid = uuid;
	}

	public String getOriginalUuid() {
		return GetterUtil.getString(_originalUuid);
	}

	@JSON
	@Override
	public long getAssetDisplayPageEntryId() {
		return _assetDisplayPageEntryId;
	}

	@Override
	public void setAssetDisplayPageEntryId(long assetDisplayPageEntryId) {
		_assetDisplayPageEntryId = assetDisplayPageEntryId;
	}

	@JSON
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

	@JSON
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

	@JSON
	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
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

	@JSON
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

	@JSON
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

	@JSON
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

	@JSON
	@Override
	public long getLayoutPageTemplateEntryId() {
		return _layoutPageTemplateEntryId;
	}

	@Override
	public void setLayoutPageTemplateEntryId(long layoutPageTemplateEntryId) {
		_columnBitmask |= LAYOUTPAGETEMPLATEENTRYID_COLUMN_BITMASK;

		if (!_setOriginalLayoutPageTemplateEntryId) {
			_setOriginalLayoutPageTemplateEntryId = true;

			_originalLayoutPageTemplateEntryId = _layoutPageTemplateEntryId;
		}

		_layoutPageTemplateEntryId = layoutPageTemplateEntryId;
	}

	public long getOriginalLayoutPageTemplateEntryId() {
		return _originalLayoutPageTemplateEntryId;
	}

	@JSON
	@Override
	public int getType() {
		return _type;
	}

	@Override
	public void setType(int type) {
		_type = type;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(
			PortalUtil.getClassNameId(AssetDisplayPageEntry.class.getName()),
			getClassNameId());
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), AssetDisplayPageEntry.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public AssetDisplayPageEntry toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, AssetDisplayPageEntry>
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
		AssetDisplayPageEntryImpl assetDisplayPageEntryImpl =
			new AssetDisplayPageEntryImpl();

		assetDisplayPageEntryImpl.setUuid(getUuid());
		assetDisplayPageEntryImpl.setAssetDisplayPageEntryId(
			getAssetDisplayPageEntryId());
		assetDisplayPageEntryImpl.setGroupId(getGroupId());
		assetDisplayPageEntryImpl.setCompanyId(getCompanyId());
		assetDisplayPageEntryImpl.setUserId(getUserId());
		assetDisplayPageEntryImpl.setUserName(getUserName());
		assetDisplayPageEntryImpl.setCreateDate(getCreateDate());
		assetDisplayPageEntryImpl.setModifiedDate(getModifiedDate());
		assetDisplayPageEntryImpl.setClassNameId(getClassNameId());
		assetDisplayPageEntryImpl.setClassPK(getClassPK());
		assetDisplayPageEntryImpl.setLayoutPageTemplateEntryId(
			getLayoutPageTemplateEntryId());
		assetDisplayPageEntryImpl.setType(getType());

		assetDisplayPageEntryImpl.resetOriginalValues();

		return assetDisplayPageEntryImpl;
	}

	@Override
	public int compareTo(AssetDisplayPageEntry assetDisplayPageEntry) {
		long primaryKey = assetDisplayPageEntry.getPrimaryKey();

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

		if (!(obj instanceof AssetDisplayPageEntry)) {
			return false;
		}

		AssetDisplayPageEntry assetDisplayPageEntry =
			(AssetDisplayPageEntry)obj;

		long primaryKey = assetDisplayPageEntry.getPrimaryKey();

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
		AssetDisplayPageEntryModelImpl assetDisplayPageEntryModelImpl = this;

		assetDisplayPageEntryModelImpl._originalUuid =
			assetDisplayPageEntryModelImpl._uuid;

		assetDisplayPageEntryModelImpl._originalGroupId =
			assetDisplayPageEntryModelImpl._groupId;

		assetDisplayPageEntryModelImpl._setOriginalGroupId = false;

		assetDisplayPageEntryModelImpl._originalCompanyId =
			assetDisplayPageEntryModelImpl._companyId;

		assetDisplayPageEntryModelImpl._setOriginalCompanyId = false;

		assetDisplayPageEntryModelImpl._setModifiedDate = false;

		assetDisplayPageEntryModelImpl._originalClassNameId =
			assetDisplayPageEntryModelImpl._classNameId;

		assetDisplayPageEntryModelImpl._setOriginalClassNameId = false;

		assetDisplayPageEntryModelImpl._originalClassPK =
			assetDisplayPageEntryModelImpl._classPK;

		assetDisplayPageEntryModelImpl._setOriginalClassPK = false;

		assetDisplayPageEntryModelImpl._originalLayoutPageTemplateEntryId =
			assetDisplayPageEntryModelImpl._layoutPageTemplateEntryId;

		assetDisplayPageEntryModelImpl._setOriginalLayoutPageTemplateEntryId =
			false;

		assetDisplayPageEntryModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<AssetDisplayPageEntry> toCacheModel() {
		AssetDisplayPageEntryCacheModel assetDisplayPageEntryCacheModel =
			new AssetDisplayPageEntryCacheModel();

		assetDisplayPageEntryCacheModel.uuid = getUuid();

		String uuid = assetDisplayPageEntryCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			assetDisplayPageEntryCacheModel.uuid = null;
		}

		assetDisplayPageEntryCacheModel.assetDisplayPageEntryId =
			getAssetDisplayPageEntryId();

		assetDisplayPageEntryCacheModel.groupId = getGroupId();

		assetDisplayPageEntryCacheModel.companyId = getCompanyId();

		assetDisplayPageEntryCacheModel.userId = getUserId();

		assetDisplayPageEntryCacheModel.userName = getUserName();

		String userName = assetDisplayPageEntryCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			assetDisplayPageEntryCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			assetDisplayPageEntryCacheModel.createDate = createDate.getTime();
		}
		else {
			assetDisplayPageEntryCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			assetDisplayPageEntryCacheModel.modifiedDate =
				modifiedDate.getTime();
		}
		else {
			assetDisplayPageEntryCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		assetDisplayPageEntryCacheModel.classNameId = getClassNameId();

		assetDisplayPageEntryCacheModel.classPK = getClassPK();

		assetDisplayPageEntryCacheModel.layoutPageTemplateEntryId =
			getLayoutPageTemplateEntryId();

		assetDisplayPageEntryCacheModel.type = getType();

		return assetDisplayPageEntryCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<AssetDisplayPageEntry, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<AssetDisplayPageEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<AssetDisplayPageEntry, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(
				attributeGetterFunction.apply((AssetDisplayPageEntry)this));
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
		Map<String, Function<AssetDisplayPageEntry, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<AssetDisplayPageEntry, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<AssetDisplayPageEntry, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(
				attributeGetterFunction.apply((AssetDisplayPageEntry)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, AssetDisplayPageEntry>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private String _uuid;
	private String _originalUuid;
	private long _assetDisplayPageEntryId;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _userId;
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
	private long _layoutPageTemplateEntryId;
	private long _originalLayoutPageTemplateEntryId;
	private boolean _setOriginalLayoutPageTemplateEntryId;
	private int _type;
	private long _columnBitmask;
	private AssetDisplayPageEntry _escapedModel;

}