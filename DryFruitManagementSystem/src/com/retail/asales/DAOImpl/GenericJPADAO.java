package com.retail.asales.DAOImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public abstract class GenericJPADAO<K, T> {

	private final Class<T> entityClass;

	protected static EntityManagerFactory emfactory = Persistence
			.createEntityManagerFactory("DryFruitManagementSystem");

	@PersistenceContext(unitName = "DryFruitManagementSystem")
	protected static EntityManager entityManager;

	private String findAllQueryString;

	protected GenericJPADAO(Class<T> entityClass) {
		this.entityClass = entityClass;
		findAllQueryString = "select c from " + entityClass.getSimpleName() + " c";
		System.out.println(entityManager);
	}

	public void persist(T entity) {
		entityManager.persist(entity);
	}

	public T read(K id) {
		return entityManager.find(entityClass, id);
	}

	public T update(T entity) {
		entityManager.merge(entity);
		return entity;

	}

	public void delete(K id, T entity) {
		T entityToRemove = entity;
		if (!entityManager.contains(entity)) {
			entityToRemove = entityManager.find(entityClass, id);
		}
		entityManager.remove(entityToRemove);

	}

	public void delete(K id) {
		T entityToRemove = entityManager.find(entityClass, id);
		entityManager.remove(entityToRemove);
		entityManager.getTransaction().commit();
	}

	public List<T> findAll() {

		return entityManager.createQuery(findAllQueryString, entityClass).getResultList();
	}

	public List<T> findAll(int maxResults) {
		TypedQuery<T> query = entityManager.createQuery(findAllQueryString, entityClass);
		query.setMaxResults(maxResults);
		return query.getResultList();
	}

	protected <R> List<R> findByNamedQuery(String namedQueryName, Class<R> resultClass) {
		return findByNamedQuery(namedQueryName, resultClass, -1);
	}

	protected <R> List<R> findByNamedQuery(String namedQueryName, Class<R> resultClass, int maxResults) {
		TypedQuery<R> query = entityManager.createNamedQuery(namedQueryName, resultClass);
		if (maxResults >= 0) {
			query.setMaxResults(maxResults);
		}
		List<R> resultList = query.getResultList();
		if (resultList == null) {
			return new ArrayList<R>();
		}
		return resultList;

	}

	protected int executeNamedQuery(String namedQueryName, Map<String, Object> parameters) {
		Set<Entry<String, Object>> rawParameters = parameters.entrySet();
		Query query = entityManager.createNamedQuery(namedQueryName);
		for (Entry<String, Object> entry : rawParameters) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
		return query.executeUpdate();
	}

	protected List<T> findByNamedQuery(String namedQueryName, Map<String, Object> parameters) {
		return findByNamedQuery(namedQueryName, parameters, -1);

	}

	protected List<T> findByNamedQuery(String namedQueryName, Map<String, Object> parameters, int maxResults) {
		Set<Entry<String, Object>> rawParameters = parameters.entrySet();
		TypedQuery<T> query = entityManager.createNamedQuery(namedQueryName, entityClass);

		for (Entry<String, Object> entry : rawParameters) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
		if (maxResults >= 0) {
			query.setMaxResults(maxResults);
		}
		List<T> resultList = query.getResultList();
		if (resultList == null) {
			return new ArrayList<T>();
		}
		return resultList;
	}

	protected T findSingleResultFromNamedQuery(String namedQueryName, Map<String, Object> parameters) {
		List<T> result = findByNamedQuery(namedQueryName, parameters);
		if (result.size() < 1) {
			return null;
		} else if (result.size() == 1) {
			return result.get(0);
		} else {
			throw new IllegalStateException("The query " + namedQueryName + " with the parameters " + parameters
					+ " returned more than one result " + result);
		}
	}

	public void flushCacheToDatabase() {
		entityManager.flush();
	}

	public void evictClass(Class<T> entityClass) {
		entityManager.getEntityManagerFactory().getCache().evict(entityClass);
	}

	public void clearPersistenceContext() {
		entityManager.clear();
	}

	public void beginTransaction() {
		entityManager = emfactory.createEntityManager();
		entityManager.getTransaction().begin();
	}

	public void commitTransaction() {

		entityManager.getTransaction().commit();

	}

	public void joinTransaction() {
		entityManager = emfactory.createEntityManager();
		entityManager.joinTransaction();
	}

	public boolean isTransactionActive(EntityTransaction tx) {
		return tx.isActive();
	}

	public void rollbackTransaction() {
		entityManager.getTransaction().rollback();
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void closeTransaction() {
		entityManager.close();
	}

	public void commitAndCloseTransaction() {
		commitTransaction();
		closeTransaction();
	}

	protected String getEntityName() {
		return entityClass.getSimpleName();
	}

	protected Map<String, Object> createParameterMap(String parameterName, Object value) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put(parameterName, value);
		return parameters;
	}

}