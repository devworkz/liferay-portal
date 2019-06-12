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

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.LocaleException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.LayoutPrototype;
import com.liferay.portal.kernel.model.LayoutPrototypeModel;
import com.liferay.portal.kernel.model.LayoutPrototypeSoap;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
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
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the LayoutPrototype service. Represents a row in the &quot;LayoutPrototype&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface </code>LayoutPrototypeModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link LayoutPrototypeImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LayoutPrototypeImpl
 * @generated
 */
@JSON(strict = true)
@ProviderType
public class LayoutPrototypeModelImpl
	extends BaseModelImpl<LayoutPrototype> implements LayoutPrototypeModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a layout prototype model instance should use the <code>LayoutPrototype</code> interface instead.
	 */
	public static final String TABLE_NAME = "LayoutPrototype";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"uuid_", Types.VARCHAR},
		{"layoutPrototypeId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"name", Types.CLOB}, {"description", Types.CLOB},
		{"settings_", Types.VARCHAR}, {"active_", Types.BOOLEAN}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("layoutPrototypeId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("name", Types.CLOB);
		TABLE_COLUMNS_MAP.put("description", Types.CLOB);
		TABLE_COLUMNS_MAP.put("settings_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("active_", Types.BOOLEAN);
	}

	public static final String TABLE_SQL_CREATE =
		"create table LayoutPrototype (mvccVersion LONG default 0 not null,uuid_ VARCHAR(75) null,layoutPrototypeId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,name TEXT null,description TEXT null,settings_ STRING null,active_ BOOLEAN)";

	public static final String TABLE_SQL_DROP = "drop table LayoutPrototype";

	public static final String ORDER_BY_JPQL =
		" ORDER BY layoutPrototype.layoutPrototypeId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY LayoutPrototype.layoutPrototypeId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.portal.util.PropsUtil.get(
			"value.object.entity.cache.enabled.com.liferay.portal.kernel.model.LayoutPrototype"),
		true);

	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.portal.util.PropsUtil.get(
			"value.object.finder.cache.enabled.com.liferay.portal.kernel.model.LayoutPrototype"),
		true);

	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(
		com.liferay.portal.util.PropsUtil.get(
			"value.object.column.bitmask.enabled.com.liferay.portal.kernel.model.LayoutPrototype"),
		true);

	public static final long ACTIVE_COLUMN_BITMASK = 1L;

	public static final long COMPANYID_COLUMN_BITMASK = 2L;

	public static final long UUID_COLUMN_BITMASK = 4L;

	public static final long LAYOUTPROTOTYPEID_COLUMN_BITMASK = 8L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static LayoutPrototype toModel(LayoutPrototypeSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		LayoutPrototype model = new LayoutPrototypeImpl();

		model.setMvccVersion(soapModel.getMvccVersion());
		model.setUuid(soapModel.getUuid());
		model.setLayoutPrototypeId(soapModel.getLayoutPrototypeId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setName(soapModel.getName());
		model.setDescription(soapModel.getDescription());
		model.setSettings(soapModel.getSettings());
		model.setActive(soapModel.isActive());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<LayoutPrototype> toModels(
		LayoutPrototypeSoap[] soapModels) {

		if (soapModels == null) {
			return null;
		}

		List<LayoutPrototype> models = new ArrayList<LayoutPrototype>(
			soapModels.length);

		for (LayoutPrototypeSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.portal.util.PropsUtil.get(
			"lock.expiration.time.com.liferay.portal.kernel.model.LayoutPrototype"));

	public LayoutPrototypeModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _layoutPrototypeId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setLayoutPrototypeId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _layoutPrototypeId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return LayoutPrototype.class;
	}

	@Override
	public String getModelClassName() {
		return LayoutPrototype.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<LayoutPrototype, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<LayoutPrototype, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<LayoutPrototype, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((LayoutPrototype)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<LayoutPrototype, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<LayoutPrototype, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(LayoutPrototype)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<LayoutPrototype, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<LayoutPrototype, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, LayoutPrototype>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			LayoutPrototype.class.getClassLoader(), LayoutPrototype.class,
			ModelWrapper.class);

		try {
			Constructor<LayoutPrototype> constructor =
				(Constructor<LayoutPrototype>)proxyClass.getConstructor(
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

	private static final Map<String, Function<LayoutPrototype, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<LayoutPrototype, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<LayoutPrototype, Object>>
			attributeGetterFunctions =
				new LinkedHashMap<String, Function<LayoutPrototype, Object>>();
		Map<String, BiConsumer<LayoutPrototype, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<LayoutPrototype, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion",
			new Function<LayoutPrototype, Object>() {

				@Override
				public Object apply(LayoutPrototype layoutPrototype) {
					return layoutPrototype.getMvccVersion();
				}

			});
		attributeSetterBiConsumers.put(
			"mvccVersion",
			new BiConsumer<LayoutPrototype, Object>() {

				@Override
				public void accept(
					LayoutPrototype layoutPrototype, Object mvccVersion) {

					layoutPrototype.setMvccVersion((Long)mvccVersion);
				}

			});
		attributeGetterFunctions.put(
			"uuid",
			new Function<LayoutPrototype, Object>() {

				@Override
				public Object apply(LayoutPrototype layoutPrototype) {
					return layoutPrototype.getUuid();
				}

			});
		attributeSetterBiConsumers.put(
			"uuid",
			new BiConsumer<LayoutPrototype, Object>() {

				@Override
				public void accept(
					LayoutPrototype layoutPrototype, Object uuid) {

					layoutPrototype.setUuid((String)uuid);
				}

			});
		attributeGetterFunctions.put(
			"layoutPrototypeId",
			new Function<LayoutPrototype, Object>() {

				@Override
				public Object apply(LayoutPrototype layoutPrototype) {
					return layoutPrototype.getLayoutPrototypeId();
				}

			});
		attributeSetterBiConsumers.put(
			"layoutPrototypeId",
			new BiConsumer<LayoutPrototype, Object>() {

				@Override
				public void accept(
					LayoutPrototype layoutPrototype, Object layoutPrototypeId) {

					layoutPrototype.setLayoutPrototypeId(
						(Long)layoutPrototypeId);
				}

			});
		attributeGetterFunctions.put(
			"companyId",
			new Function<LayoutPrototype, Object>() {

				@Override
				public Object apply(LayoutPrototype layoutPrototype) {
					return layoutPrototype.getCompanyId();
				}

			});
		attributeSetterBiConsumers.put(
			"companyId",
			new BiConsumer<LayoutPrototype, Object>() {

				@Override
				public void accept(
					LayoutPrototype layoutPrototype, Object companyId) {

					layoutPrototype.setCompanyId((Long)companyId);
				}

			});
		attributeGetterFunctions.put(
			"userId",
			new Function<LayoutPrototype, Object>() {

				@Override
				public Object apply(LayoutPrototype layoutPrototype) {
					return layoutPrototype.getUserId();
				}

			});
		attributeSetterBiConsumers.put(
			"userId",
			new BiConsumer<LayoutPrototype, Object>() {

				@Override
				public void accept(
					LayoutPrototype layoutPrototype, Object userId) {

					layoutPrototype.setUserId((Long)userId);
				}

			});
		attributeGetterFunctions.put(
			"userName",
			new Function<LayoutPrototype, Object>() {

				@Override
				public Object apply(LayoutPrototype layoutPrototype) {
					return layoutPrototype.getUserName();
				}

			});
		attributeSetterBiConsumers.put(
			"userName",
			new BiConsumer<LayoutPrototype, Object>() {

				@Override
				public void accept(
					LayoutPrototype layoutPrototype, Object userName) {

					layoutPrototype.setUserName((String)userName);
				}

			});
		attributeGetterFunctions.put(
			"createDate",
			new Function<LayoutPrototype, Object>() {

				@Override
				public Object apply(LayoutPrototype layoutPrototype) {
					return layoutPrototype.getCreateDate();
				}

			});
		attributeSetterBiConsumers.put(
			"createDate",
			new BiConsumer<LayoutPrototype, Object>() {

				@Override
				public void accept(
					LayoutPrototype layoutPrototype, Object createDate) {

					layoutPrototype.setCreateDate((Date)createDate);
				}

			});
		attributeGetterFunctions.put(
			"modifiedDate",
			new Function<LayoutPrototype, Object>() {

				@Override
				public Object apply(LayoutPrototype layoutPrototype) {
					return layoutPrototype.getModifiedDate();
				}

			});
		attributeSetterBiConsumers.put(
			"modifiedDate",
			new BiConsumer<LayoutPrototype, Object>() {

				@Override
				public void accept(
					LayoutPrototype layoutPrototype, Object modifiedDate) {

					layoutPrototype.setModifiedDate((Date)modifiedDate);
				}

			});
		attributeGetterFunctions.put(
			"name",
			new Function<LayoutPrototype, Object>() {

				@Override
				public Object apply(LayoutPrototype layoutPrototype) {
					return layoutPrototype.getName();
				}

			});
		attributeSetterBiConsumers.put(
			"name",
			new BiConsumer<LayoutPrototype, Object>() {

				@Override
				public void accept(
					LayoutPrototype layoutPrototype, Object name) {

					layoutPrototype.setName((String)name);
				}

			});
		attributeGetterFunctions.put(
			"description",
			new Function<LayoutPrototype, Object>() {

				@Override
				public Object apply(LayoutPrototype layoutPrototype) {
					return layoutPrototype.getDescription();
				}

			});
		attributeSetterBiConsumers.put(
			"description",
			new BiConsumer<LayoutPrototype, Object>() {

				@Override
				public void accept(
					LayoutPrototype layoutPrototype, Object description) {

					layoutPrototype.setDescription((String)description);
				}

			});
		attributeGetterFunctions.put(
			"settings",
			new Function<LayoutPrototype, Object>() {

				@Override
				public Object apply(LayoutPrototype layoutPrototype) {
					return layoutPrototype.getSettings();
				}

			});
		attributeSetterBiConsumers.put(
			"settings",
			new BiConsumer<LayoutPrototype, Object>() {

				@Override
				public void accept(
					LayoutPrototype layoutPrototype, Object settings) {

					layoutPrototype.setSettings((String)settings);
				}

			});
		attributeGetterFunctions.put(
			"active",
			new Function<LayoutPrototype, Object>() {

				@Override
				public Object apply(LayoutPrototype layoutPrototype) {
					return layoutPrototype.getActive();
				}

			});
		attributeSetterBiConsumers.put(
			"active",
			new BiConsumer<LayoutPrototype, Object>() {

				@Override
				public void accept(
					LayoutPrototype layoutPrototype, Object active) {

					layoutPrototype.setActive((Boolean)active);
				}

			});

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@JSON
	@Override
	public long getMvccVersion() {
		return _mvccVersion;
	}

	@Override
	public void setMvccVersion(long mvccVersion) {
		_mvccVersion = mvccVersion;
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
	public long getLayoutPrototypeId() {
		return _layoutPrototypeId;
	}

	@Override
	public void setLayoutPrototypeId(long layoutPrototypeId) {
		_layoutPrototypeId = layoutPrototypeId;
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

	@JSON
	@Override
	public String getName() {
		if (_name == null) {
			return "";
		}
		else {
			return _name;
		}
	}

	@Override
	public String getName(Locale locale) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getName(languageId);
	}

	@Override
	public String getName(Locale locale, boolean useDefault) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getName(languageId, useDefault);
	}

	@Override
	public String getName(String languageId) {
		return LocalizationUtil.getLocalization(getName(), languageId);
	}

	@Override
	public String getName(String languageId, boolean useDefault) {
		return LocalizationUtil.getLocalization(
			getName(), languageId, useDefault);
	}

	@Override
	public String getNameCurrentLanguageId() {
		return _nameCurrentLanguageId;
	}

	@JSON
	@Override
	public String getNameCurrentValue() {
		Locale locale = getLocale(_nameCurrentLanguageId);

		return getName(locale);
	}

	@Override
	public Map<Locale, String> getNameMap() {
		return LocalizationUtil.getLocalizationMap(getName());
	}

	@Override
	public void setName(String name) {
		_name = name;
	}

	@Override
	public void setName(String name, Locale locale) {
		setName(name, locale, LocaleUtil.getDefault());
	}

	@Override
	public void setName(String name, Locale locale, Locale defaultLocale) {
		String languageId = LocaleUtil.toLanguageId(locale);
		String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

		if (Validator.isNotNull(name)) {
			setName(
				LocalizationUtil.updateLocalization(
					getName(), "Name", name, languageId, defaultLanguageId));
		}
		else {
			setName(
				LocalizationUtil.removeLocalization(
					getName(), "Name", languageId));
		}
	}

	@Override
	public void setNameCurrentLanguageId(String languageId) {
		_nameCurrentLanguageId = languageId;
	}

	@Override
	public void setNameMap(Map<Locale, String> nameMap) {
		setNameMap(nameMap, LocaleUtil.getDefault());
	}

	@Override
	public void setNameMap(Map<Locale, String> nameMap, Locale defaultLocale) {
		if (nameMap == null) {
			return;
		}

		setName(
			LocalizationUtil.updateLocalization(
				nameMap, getName(), "Name",
				LocaleUtil.toLanguageId(defaultLocale)));
	}

	@JSON
	@Override
	public String getDescription() {
		if (_description == null) {
			return "";
		}
		else {
			return _description;
		}
	}

	@Override
	public String getDescription(Locale locale) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getDescription(languageId);
	}

	@Override
	public String getDescription(Locale locale, boolean useDefault) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getDescription(languageId, useDefault);
	}

	@Override
	public String getDescription(String languageId) {
		return LocalizationUtil.getLocalization(getDescription(), languageId);
	}

	@Override
	public String getDescription(String languageId, boolean useDefault) {
		return LocalizationUtil.getLocalization(
			getDescription(), languageId, useDefault);
	}

	@Override
	public String getDescriptionCurrentLanguageId() {
		return _descriptionCurrentLanguageId;
	}

	@JSON
	@Override
	public String getDescriptionCurrentValue() {
		Locale locale = getLocale(_descriptionCurrentLanguageId);

		return getDescription(locale);
	}

	@Override
	public Map<Locale, String> getDescriptionMap() {
		return LocalizationUtil.getLocalizationMap(getDescription());
	}

	@Override
	public void setDescription(String description) {
		_description = description;
	}

	@Override
	public void setDescription(String description, Locale locale) {
		setDescription(description, locale, LocaleUtil.getDefault());
	}

	@Override
	public void setDescription(
		String description, Locale locale, Locale defaultLocale) {

		String languageId = LocaleUtil.toLanguageId(locale);
		String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

		if (Validator.isNotNull(description)) {
			setDescription(
				LocalizationUtil.updateLocalization(
					getDescription(), "Description", description, languageId,
					defaultLanguageId));
		}
		else {
			setDescription(
				LocalizationUtil.removeLocalization(
					getDescription(), "Description", languageId));
		}
	}

	@Override
	public void setDescriptionCurrentLanguageId(String languageId) {
		_descriptionCurrentLanguageId = languageId;
	}

	@Override
	public void setDescriptionMap(Map<Locale, String> descriptionMap) {
		setDescriptionMap(descriptionMap, LocaleUtil.getDefault());
	}

	@Override
	public void setDescriptionMap(
		Map<Locale, String> descriptionMap, Locale defaultLocale) {

		if (descriptionMap == null) {
			return;
		}

		setDescription(
			LocalizationUtil.updateLocalization(
				descriptionMap, getDescription(), "Description",
				LocaleUtil.toLanguageId(defaultLocale)));
	}

	@JSON
	@Override
	public String getSettings() {
		if (_settings == null) {
			return "";
		}
		else {
			return _settings;
		}
	}

	@Override
	public void setSettings(String settings) {
		_settings = settings;
	}

	@JSON
	@Override
	public boolean getActive() {
		return _active;
	}

	@JSON
	@Override
	public boolean isActive() {
		return _active;
	}

	@Override
	public void setActive(boolean active) {
		_columnBitmask |= ACTIVE_COLUMN_BITMASK;

		if (!_setOriginalActive) {
			_setOriginalActive = true;

			_originalActive = _active;
		}

		_active = active;
	}

	public boolean getOriginalActive() {
		return _originalActive;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(
			PortalUtil.getClassNameId(LayoutPrototype.class.getName()));
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), LayoutPrototype.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public String[] getAvailableLanguageIds() {
		Set<String> availableLanguageIds = new TreeSet<String>();

		Map<Locale, String> nameMap = getNameMap();

		for (Map.Entry<Locale, String> entry : nameMap.entrySet()) {
			Locale locale = entry.getKey();
			String value = entry.getValue();

			if (Validator.isNotNull(value)) {
				availableLanguageIds.add(LocaleUtil.toLanguageId(locale));
			}
		}

		Map<Locale, String> descriptionMap = getDescriptionMap();

		for (Map.Entry<Locale, String> entry : descriptionMap.entrySet()) {
			Locale locale = entry.getKey();
			String value = entry.getValue();

			if (Validator.isNotNull(value)) {
				availableLanguageIds.add(LocaleUtil.toLanguageId(locale));
			}
		}

		return availableLanguageIds.toArray(
			new String[availableLanguageIds.size()]);
	}

	@Override
	public String getDefaultLanguageId() {
		String xml = getName();

		if (xml == null) {
			return "";
		}

		Locale defaultLocale = LocaleUtil.getDefault();

		return LocalizationUtil.getDefaultLanguageId(xml, defaultLocale);
	}

	@Override
	public void prepareLocalizedFieldsForImport() throws LocaleException {
		Locale defaultLocale = LocaleUtil.fromLanguageId(
			getDefaultLanguageId());

		Locale[] availableLocales = LocaleUtil.fromLanguageIds(
			getAvailableLanguageIds());

		Locale defaultImportLocale = LocalizationUtil.getDefaultImportLocale(
			LayoutPrototype.class.getName(), getPrimaryKey(), defaultLocale,
			availableLocales);

		prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	@Override
	@SuppressWarnings("unused")
	public void prepareLocalizedFieldsForImport(Locale defaultImportLocale)
		throws LocaleException {

		Locale defaultLocale = LocaleUtil.getDefault();

		String modelDefaultLanguageId = getDefaultLanguageId();

		String name = getName(defaultLocale);

		if (Validator.isNull(name)) {
			setName(getName(modelDefaultLanguageId), defaultLocale);
		}
		else {
			setName(getName(defaultLocale), defaultLocale, defaultLocale);
		}

		String description = getDescription(defaultLocale);

		if (Validator.isNull(description)) {
			setDescription(
				getDescription(modelDefaultLanguageId), defaultLocale);
		}
		else {
			setDescription(
				getDescription(defaultLocale), defaultLocale, defaultLocale);
		}
	}

	@Override
	public LayoutPrototype toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, LayoutPrototype>
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
		LayoutPrototypeImpl layoutPrototypeImpl = new LayoutPrototypeImpl();

		layoutPrototypeImpl.setMvccVersion(getMvccVersion());
		layoutPrototypeImpl.setUuid(getUuid());
		layoutPrototypeImpl.setLayoutPrototypeId(getLayoutPrototypeId());
		layoutPrototypeImpl.setCompanyId(getCompanyId());
		layoutPrototypeImpl.setUserId(getUserId());
		layoutPrototypeImpl.setUserName(getUserName());
		layoutPrototypeImpl.setCreateDate(getCreateDate());
		layoutPrototypeImpl.setModifiedDate(getModifiedDate());
		layoutPrototypeImpl.setName(getName());
		layoutPrototypeImpl.setDescription(getDescription());
		layoutPrototypeImpl.setSettings(getSettings());
		layoutPrototypeImpl.setActive(isActive());

		layoutPrototypeImpl.resetOriginalValues();

		return layoutPrototypeImpl;
	}

	@Override
	public int compareTo(LayoutPrototype layoutPrototype) {
		long primaryKey = layoutPrototype.getPrimaryKey();

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

		if (!(obj instanceof LayoutPrototype)) {
			return false;
		}

		LayoutPrototype layoutPrototype = (LayoutPrototype)obj;

		long primaryKey = layoutPrototype.getPrimaryKey();

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
		LayoutPrototypeModelImpl layoutPrototypeModelImpl = this;

		layoutPrototypeModelImpl._originalUuid = layoutPrototypeModelImpl._uuid;

		layoutPrototypeModelImpl._originalCompanyId =
			layoutPrototypeModelImpl._companyId;

		layoutPrototypeModelImpl._setOriginalCompanyId = false;

		layoutPrototypeModelImpl._setModifiedDate = false;

		layoutPrototypeModelImpl._originalActive =
			layoutPrototypeModelImpl._active;

		layoutPrototypeModelImpl._setOriginalActive = false;

		layoutPrototypeModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<LayoutPrototype> toCacheModel() {
		LayoutPrototypeCacheModel layoutPrototypeCacheModel =
			new LayoutPrototypeCacheModel();

		layoutPrototypeCacheModel.mvccVersion = getMvccVersion();

		layoutPrototypeCacheModel.uuid = getUuid();

		String uuid = layoutPrototypeCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			layoutPrototypeCacheModel.uuid = null;
		}

		layoutPrototypeCacheModel.layoutPrototypeId = getLayoutPrototypeId();

		layoutPrototypeCacheModel.companyId = getCompanyId();

		layoutPrototypeCacheModel.userId = getUserId();

		layoutPrototypeCacheModel.userName = getUserName();

		String userName = layoutPrototypeCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			layoutPrototypeCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			layoutPrototypeCacheModel.createDate = createDate.getTime();
		}
		else {
			layoutPrototypeCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			layoutPrototypeCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			layoutPrototypeCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		layoutPrototypeCacheModel.name = getName();

		String name = layoutPrototypeCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			layoutPrototypeCacheModel.name = null;
		}

		layoutPrototypeCacheModel.description = getDescription();

		String description = layoutPrototypeCacheModel.description;

		if ((description != null) && (description.length() == 0)) {
			layoutPrototypeCacheModel.description = null;
		}

		layoutPrototypeCacheModel.settings = getSettings();

		String settings = layoutPrototypeCacheModel.settings;

		if ((settings != null) && (settings.length() == 0)) {
			layoutPrototypeCacheModel.settings = null;
		}

		layoutPrototypeCacheModel.active = isActive();

		return layoutPrototypeCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<LayoutPrototype, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<LayoutPrototype, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<LayoutPrototype, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((LayoutPrototype)this));
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
		Map<String, Function<LayoutPrototype, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<LayoutPrototype, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<LayoutPrototype, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((LayoutPrototype)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, LayoutPrototype>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private long _mvccVersion;
	private String _uuid;
	private String _originalUuid;
	private long _layoutPrototypeId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private String _name;
	private String _nameCurrentLanguageId;
	private String _description;
	private String _descriptionCurrentLanguageId;
	private String _settings;
	private boolean _active;
	private boolean _originalActive;
	private boolean _setOriginalActive;
	private long _columnBitmask;
	private LayoutPrototype _escapedModel;

}