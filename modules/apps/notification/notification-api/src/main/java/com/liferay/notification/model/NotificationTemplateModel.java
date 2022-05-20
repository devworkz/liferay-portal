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

package com.liferay.notification.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.exception.LocaleException;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.LocalizedModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.StagedAuditedModel;

import java.util.Date;
import java.util.Locale;
import java.util.Map;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the NotificationTemplate service. Represents a row in the &quot;NotificationTemplate&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.notification.model.impl.NotificationTemplateModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.notification.model.impl.NotificationTemplateImpl</code>.
 * </p>
 *
 * @author Gabriel Albuquerque
 * @see NotificationTemplate
 * @generated
 */
@ProviderType
public interface NotificationTemplateModel
	extends BaseModel<NotificationTemplate>, LocalizedModel, MVCCModel,
			ShardedModel, StagedAuditedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a notification template model instance should use the {@link NotificationTemplate} interface instead.
	 */

	/**
	 * Returns the primary key of this notification template.
	 *
	 * @return the primary key of this notification template
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this notification template.
	 *
	 * @param primaryKey the primary key of this notification template
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this notification template.
	 *
	 * @return the mvcc version of this notification template
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this notification template.
	 *
	 * @param mvccVersion the mvcc version of this notification template
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the uuid of this notification template.
	 *
	 * @return the uuid of this notification template
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this notification template.
	 *
	 * @param uuid the uuid of this notification template
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the notification template ID of this notification template.
	 *
	 * @return the notification template ID of this notification template
	 */
	public long getNotificationTemplateId();

	/**
	 * Sets the notification template ID of this notification template.
	 *
	 * @param notificationTemplateId the notification template ID of this notification template
	 */
	public void setNotificationTemplateId(long notificationTemplateId);

	/**
	 * Returns the company ID of this notification template.
	 *
	 * @return the company ID of this notification template
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this notification template.
	 *
	 * @param companyId the company ID of this notification template
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this notification template.
	 *
	 * @return the user ID of this notification template
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this notification template.
	 *
	 * @param userId the user ID of this notification template
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this notification template.
	 *
	 * @return the user uuid of this notification template
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this notification template.
	 *
	 * @param userUuid the user uuid of this notification template
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this notification template.
	 *
	 * @return the user name of this notification template
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this notification template.
	 *
	 * @param userName the user name of this notification template
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this notification template.
	 *
	 * @return the create date of this notification template
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this notification template.
	 *
	 * @param createDate the create date of this notification template
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this notification template.
	 *
	 * @return the modified date of this notification template
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this notification template.
	 *
	 * @param modifiedDate the modified date of this notification template
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the bcc of this notification template.
	 *
	 * @return the bcc of this notification template
	 */
	@AutoEscape
	public String getBcc();

	/**
	 * Sets the bcc of this notification template.
	 *
	 * @param bcc the bcc of this notification template
	 */
	public void setBcc(String bcc);

	/**
	 * Returns the body of this notification template.
	 *
	 * @return the body of this notification template
	 */
	public String getBody();

	/**
	 * Returns the localized body of this notification template in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized body of this notification template
	 */
	@AutoEscape
	public String getBody(Locale locale);

	/**
	 * Returns the localized body of this notification template in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized body of this notification template. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@AutoEscape
	public String getBody(Locale locale, boolean useDefault);

	/**
	 * Returns the localized body of this notification template in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized body of this notification template
	 */
	@AutoEscape
	public String getBody(String languageId);

	/**
	 * Returns the localized body of this notification template in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized body of this notification template
	 */
	@AutoEscape
	public String getBody(String languageId, boolean useDefault);

	@AutoEscape
	public String getBodyCurrentLanguageId();

	@AutoEscape
	public String getBodyCurrentValue();

	/**
	 * Returns a map of the locales and localized bodies of this notification template.
	 *
	 * @return the locales and localized bodies of this notification template
	 */
	public Map<Locale, String> getBodyMap();

	/**
	 * Sets the body of this notification template.
	 *
	 * @param body the body of this notification template
	 */
	public void setBody(String body);

	/**
	 * Sets the localized body of this notification template in the language.
	 *
	 * @param body the localized body of this notification template
	 * @param locale the locale of the language
	 */
	public void setBody(String body, Locale locale);

	/**
	 * Sets the localized body of this notification template in the language, and sets the default locale.
	 *
	 * @param body the localized body of this notification template
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	public void setBody(String body, Locale locale, Locale defaultLocale);

	public void setBodyCurrentLanguageId(String languageId);

	/**
	 * Sets the localized bodies of this notification template from the map of locales and localized bodies.
	 *
	 * @param bodyMap the locales and localized bodies of this notification template
	 */
	public void setBodyMap(Map<Locale, String> bodyMap);

	/**
	 * Sets the localized bodies of this notification template from the map of locales and localized bodies, and sets the default locale.
	 *
	 * @param bodyMap the locales and localized bodies of this notification template
	 * @param defaultLocale the default locale
	 */
	public void setBodyMap(Map<Locale, String> bodyMap, Locale defaultLocale);

	/**
	 * Returns the cc of this notification template.
	 *
	 * @return the cc of this notification template
	 */
	@AutoEscape
	public String getCc();

	/**
	 * Sets the cc of this notification template.
	 *
	 * @param cc the cc of this notification template
	 */
	public void setCc(String cc);

	/**
	 * Returns the description of this notification template.
	 *
	 * @return the description of this notification template
	 */
	@AutoEscape
	public String getDescription();

	/**
	 * Sets the description of this notification template.
	 *
	 * @param description the description of this notification template
	 */
	public void setDescription(String description);

	/**
	 * Returns the enabled of this notification template.
	 *
	 * @return the enabled of this notification template
	 */
	public boolean getEnabled();

	/**
	 * Returns <code>true</code> if this notification template is enabled.
	 *
	 * @return <code>true</code> if this notification template is enabled; <code>false</code> otherwise
	 */
	public boolean isEnabled();

	/**
	 * Sets whether this notification template is enabled.
	 *
	 * @param enabled the enabled of this notification template
	 */
	public void setEnabled(boolean enabled);

	/**
	 * Returns the from of this notification template.
	 *
	 * @return the from of this notification template
	 */
	@AutoEscape
	public String getFrom();

	/**
	 * Sets the from of this notification template.
	 *
	 * @param from the from of this notification template
	 */
	public void setFrom(String from);

	/**
	 * Returns the from name of this notification template.
	 *
	 * @return the from name of this notification template
	 */
	public String getFromName();

	/**
	 * Returns the localized from name of this notification template in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized from name of this notification template
	 */
	@AutoEscape
	public String getFromName(Locale locale);

	/**
	 * Returns the localized from name of this notification template in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized from name of this notification template. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@AutoEscape
	public String getFromName(Locale locale, boolean useDefault);

	/**
	 * Returns the localized from name of this notification template in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized from name of this notification template
	 */
	@AutoEscape
	public String getFromName(String languageId);

	/**
	 * Returns the localized from name of this notification template in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized from name of this notification template
	 */
	@AutoEscape
	public String getFromName(String languageId, boolean useDefault);

	@AutoEscape
	public String getFromNameCurrentLanguageId();

	@AutoEscape
	public String getFromNameCurrentValue();

	/**
	 * Returns a map of the locales and localized from names of this notification template.
	 *
	 * @return the locales and localized from names of this notification template
	 */
	public Map<Locale, String> getFromNameMap();

	/**
	 * Sets the from name of this notification template.
	 *
	 * @param fromName the from name of this notification template
	 */
	public void setFromName(String fromName);

	/**
	 * Sets the localized from name of this notification template in the language.
	 *
	 * @param fromName the localized from name of this notification template
	 * @param locale the locale of the language
	 */
	public void setFromName(String fromName, Locale locale);

	/**
	 * Sets the localized from name of this notification template in the language, and sets the default locale.
	 *
	 * @param fromName the localized from name of this notification template
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	public void setFromName(
		String fromName, Locale locale, Locale defaultLocale);

	public void setFromNameCurrentLanguageId(String languageId);

	/**
	 * Sets the localized from names of this notification template from the map of locales and localized from names.
	 *
	 * @param fromNameMap the locales and localized from names of this notification template
	 */
	public void setFromNameMap(Map<Locale, String> fromNameMap);

	/**
	 * Sets the localized from names of this notification template from the map of locales and localized from names, and sets the default locale.
	 *
	 * @param fromNameMap the locales and localized from names of this notification template
	 * @param defaultLocale the default locale
	 */
	public void setFromNameMap(
		Map<Locale, String> fromNameMap, Locale defaultLocale);

	/**
	 * Returns the name of this notification template.
	 *
	 * @return the name of this notification template
	 */
	public String getName();

	/**
	 * Returns the localized name of this notification template in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized name of this notification template
	 */
	@AutoEscape
	public String getName(Locale locale);

	/**
	 * Returns the localized name of this notification template in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized name of this notification template. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@AutoEscape
	public String getName(Locale locale, boolean useDefault);

	/**
	 * Returns the localized name of this notification template in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized name of this notification template
	 */
	@AutoEscape
	public String getName(String languageId);

	/**
	 * Returns the localized name of this notification template in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized name of this notification template
	 */
	@AutoEscape
	public String getName(String languageId, boolean useDefault);

	@AutoEscape
	public String getNameCurrentLanguageId();

	@AutoEscape
	public String getNameCurrentValue();

	/**
	 * Returns a map of the locales and localized names of this notification template.
	 *
	 * @return the locales and localized names of this notification template
	 */
	public Map<Locale, String> getNameMap();

	/**
	 * Sets the name of this notification template.
	 *
	 * @param name the name of this notification template
	 */
	public void setName(String name);

	/**
	 * Sets the localized name of this notification template in the language.
	 *
	 * @param name the localized name of this notification template
	 * @param locale the locale of the language
	 */
	public void setName(String name, Locale locale);

	/**
	 * Sets the localized name of this notification template in the language, and sets the default locale.
	 *
	 * @param name the localized name of this notification template
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	public void setName(String name, Locale locale, Locale defaultLocale);

	public void setNameCurrentLanguageId(String languageId);

	/**
	 * Sets the localized names of this notification template from the map of locales and localized names.
	 *
	 * @param nameMap the locales and localized names of this notification template
	 */
	public void setNameMap(Map<Locale, String> nameMap);

	/**
	 * Sets the localized names of this notification template from the map of locales and localized names, and sets the default locale.
	 *
	 * @param nameMap the locales and localized names of this notification template
	 * @param defaultLocale the default locale
	 */
	public void setNameMap(Map<Locale, String> nameMap, Locale defaultLocale);

	/**
	 * Returns the subject of this notification template.
	 *
	 * @return the subject of this notification template
	 */
	public String getSubject();

	/**
	 * Returns the localized subject of this notification template in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized subject of this notification template
	 */
	@AutoEscape
	public String getSubject(Locale locale);

	/**
	 * Returns the localized subject of this notification template in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized subject of this notification template. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@AutoEscape
	public String getSubject(Locale locale, boolean useDefault);

	/**
	 * Returns the localized subject of this notification template in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized subject of this notification template
	 */
	@AutoEscape
	public String getSubject(String languageId);

	/**
	 * Returns the localized subject of this notification template in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized subject of this notification template
	 */
	@AutoEscape
	public String getSubject(String languageId, boolean useDefault);

	@AutoEscape
	public String getSubjectCurrentLanguageId();

	@AutoEscape
	public String getSubjectCurrentValue();

	/**
	 * Returns a map of the locales and localized subjects of this notification template.
	 *
	 * @return the locales and localized subjects of this notification template
	 */
	public Map<Locale, String> getSubjectMap();

	/**
	 * Sets the subject of this notification template.
	 *
	 * @param subject the subject of this notification template
	 */
	public void setSubject(String subject);

	/**
	 * Sets the localized subject of this notification template in the language.
	 *
	 * @param subject the localized subject of this notification template
	 * @param locale the locale of the language
	 */
	public void setSubject(String subject, Locale locale);

	/**
	 * Sets the localized subject of this notification template in the language, and sets the default locale.
	 *
	 * @param subject the localized subject of this notification template
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	public void setSubject(String subject, Locale locale, Locale defaultLocale);

	public void setSubjectCurrentLanguageId(String languageId);

	/**
	 * Sets the localized subjects of this notification template from the map of locales and localized subjects.
	 *
	 * @param subjectMap the locales and localized subjects of this notification template
	 */
	public void setSubjectMap(Map<Locale, String> subjectMap);

	/**
	 * Sets the localized subjects of this notification template from the map of locales and localized subjects, and sets the default locale.
	 *
	 * @param subjectMap the locales and localized subjects of this notification template
	 * @param defaultLocale the default locale
	 */
	public void setSubjectMap(
		Map<Locale, String> subjectMap, Locale defaultLocale);

	/**
	 * Returns the to of this notification template.
	 *
	 * @return the to of this notification template
	 */
	public String getTo();

	/**
	 * Returns the localized to of this notification template in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized to of this notification template
	 */
	@AutoEscape
	public String getTo(Locale locale);

	/**
	 * Returns the localized to of this notification template in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized to of this notification template. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@AutoEscape
	public String getTo(Locale locale, boolean useDefault);

	/**
	 * Returns the localized to of this notification template in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized to of this notification template
	 */
	@AutoEscape
	public String getTo(String languageId);

	/**
	 * Returns the localized to of this notification template in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized to of this notification template
	 */
	@AutoEscape
	public String getTo(String languageId, boolean useDefault);

	@AutoEscape
	public String getToCurrentLanguageId();

	@AutoEscape
	public String getToCurrentValue();

	/**
	 * Returns a map of the locales and localized tos of this notification template.
	 *
	 * @return the locales and localized tos of this notification template
	 */
	public Map<Locale, String> getToMap();

	/**
	 * Sets the to of this notification template.
	 *
	 * @param to the to of this notification template
	 */
	public void setTo(String to);

	/**
	 * Sets the localized to of this notification template in the language.
	 *
	 * @param to the localized to of this notification template
	 * @param locale the locale of the language
	 */
	public void setTo(String to, Locale locale);

	/**
	 * Sets the localized to of this notification template in the language, and sets the default locale.
	 *
	 * @param to the localized to of this notification template
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	public void setTo(String to, Locale locale, Locale defaultLocale);

	public void setToCurrentLanguageId(String languageId);

	/**
	 * Sets the localized tos of this notification template from the map of locales and localized tos.
	 *
	 * @param toMap the locales and localized tos of this notification template
	 */
	public void setToMap(Map<Locale, String> toMap);

	/**
	 * Sets the localized tos of this notification template from the map of locales and localized tos, and sets the default locale.
	 *
	 * @param toMap the locales and localized tos of this notification template
	 * @param defaultLocale the default locale
	 */
	public void setToMap(Map<Locale, String> toMap, Locale defaultLocale);

	@Override
	public String[] getAvailableLanguageIds();

	@Override
	public String getDefaultLanguageId();

	@Override
	public void prepareLocalizedFieldsForImport() throws LocaleException;

	@Override
	public void prepareLocalizedFieldsForImport(Locale defaultImportLocale)
		throws LocaleException;

	@Override
	public NotificationTemplate cloneWithOriginalValues();

}