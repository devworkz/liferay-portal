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

package com.liferay.portal.search.elasticsearch7.internal;

import com.liferay.portal.kernel.messaging.DestinationFactory;
import com.liferay.portal.kernel.messaging.MessageBus;
import com.liferay.portal.kernel.search.IndexSearcher;
import com.liferay.portal.kernel.search.IndexWriter;
import com.liferay.portal.kernel.search.SearchEngine;
import com.liferay.portal.kernel.search.SearchEngineConfigurator;
import com.liferay.portal.kernel.search.SearchEngineHelper;

import java.util.Collections;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Michael C. Han
 */
@Component(
	immediate = true, property = "search.engine.impl=Elasticsearch",
	service = SearchEngineConfigurator.class
)
public class ElasticsearchEngineConfigurator
	extends BaseSearchEngineConfigurator {

	@Activate
	protected void activate(BundleContext bundleContext) {
		_bundleContext = bundleContext;

		setSearchEngines(
			Collections.singletonMap(
				SearchEngineHelper.SYSTEM_ENGINE_ID, _searchEngine));

		initialize();
	}

	@Override
	protected BundleContext getBundleContext() {
		return _bundleContext;
	}

	@Override
	protected DestinationFactory getDestinationFactory() {
		return _destinationFactory;
	}

	@Override
	protected IndexSearcher getIndexSearcher() {
		return _indexSearcher;
	}

	@Override
	protected IndexWriter getIndexWriter() {
		return _indexWriter;
	}

	@Override
	protected MessageBus getMessageBus() {
		return _messageBus;
	}

	@Override
	protected ClassLoader getOperatingClassLoader() {
		Class<?> clazz = getClass();

		return clazz.getClassLoader();
	}

	@Override
	protected SearchEngineHelper getSearchEngineHelper() {
		return _searchEngineHelper;
	}

	private BundleContext _bundleContext;

	@Reference
	private DestinationFactory _destinationFactory;

	@Reference(target = "(!(search.engine.impl=*))")
	private IndexSearcher _indexSearcher;

	@Reference(target = "(!(search.engine.impl=*))")
	private IndexWriter _indexWriter;

	@Reference
	private MessageBus _messageBus;

	@Reference(
		target = "(&(search.engine.id=SYSTEM_ENGINE)(search.engine.impl=Elasticsearch))"
	)
	private SearchEngine _searchEngine;

	@Reference
	private SearchEngineHelper _searchEngineHelper;

}