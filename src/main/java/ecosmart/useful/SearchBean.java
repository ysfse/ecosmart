package ecosmart.useful;

/*
 * Copyright 2009 The Revere Group Licensed under the Apache License, Version 2.0 (the "License"); you may not use this
 * file except in compliance with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions and limitations under the
 * License.
 */


import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

/**
 * Implementation of <code>GeneralDAO</code> using Hibernate. The SessionFactory
 * property is annotated for automatic resource injection.
 * 
 * @author dwolverton
 */
@Component
public class SearchBean implements SearchLocal {
 
	private static final long serialVersionUID = 2414088018169832643L;

	@PersistenceContext
	EntityManager entityManager;

	JPASearchProcessor processor;

	@PostConstruct
	public void init() {
		MetadataUtil metadataUtil = new JPAAnnotationMetadataUtil();
		processor = new JPASearchProcessor(metadataUtil);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List search(ISearch search) {
		return processor.search(entityManager, search);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List search(Class<?> searchClass, ISearch search) {
		return processor.search(entityManager, searchClass, search);
	}

	@Override
	public int count(ISearch search) {
		return processor.count(entityManager, search);
	}

	@Override
	public int count(Class<?> searchClass, ISearch search) {
		return processor.count(entityManager, searchClass, search);
	}

	@Override
	@SuppressWarnings("rawtypes")
	public SearchResult searchAndCount(ISearch search) {
		return processor.searchAndCount(entityManager, search);
	}

	@Override
	@SuppressWarnings("rawtypes")
	public SearchResult searchAndCount(Class<?> searchClass, ISearch search) {
		return processor.searchAndCount(entityManager, searchClass, search);
	}

	@Override
	public Object searchUnique(ISearch search) {
		return processor.searchUnique(entityManager, search);
	}

	@Override
	public Object searchUnique(Class<?> searchClass, ISearch search) {
		return processor.searchUnique(entityManager, searchClass, search);
	}

	@Override
	public Filter getFilterFromExample(Object example) {
		return processor.getFilterFromExample(example);
	}

	@Override
	public Filter getFilterFromExample(Object example, ExampleOptions options) {
		return processor.getFilterFromExample(example, options);
	}
}