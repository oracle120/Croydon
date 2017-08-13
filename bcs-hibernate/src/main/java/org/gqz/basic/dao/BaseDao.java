/**
 * 
 */
package org.gqz.basic.dao;

import java.lang.reflect.ParameterizedType;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import org.gqz.basic.model.Pager;
import org.gqz.basic.model.SystemContext;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;

/**
 * @author jinhuer168
 *
 */
@SuppressWarnings("unchecked")
public class BaseDao<T> implements IBaseDao<T> {

	private SessionFactory sessionFactory;

	/**
	 * 创建一个Class的对象来获取泛型的class
	 */
	private Class<?> clz;

	public Class<?> getClz() {
		if (clz == null) {
			// 获取泛型的Class对象
			clz = ((Class<?>) (((ParameterizedType) (this.getClass().getGenericSuperclass()))
					.getActualTypeArguments()[0]));
		}
		return clz;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Inject
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.gqz.basic.dao.IBaseDao#add(java.lang.Object)
	 */
	@Override
	public T add(T t) {
		getSession().save(t);
		return t;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.gqz.basic.dao.IBaseDao#update(java.lang.Object)
	 */
	@Override
	public void update(T t) {
		getSession().update(t);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.gqz.basic.dao.IBaseDao#delete(int)
	 */
	@Override
	public void delete(int id) {
		getSession().delete(this.load(id));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.gqz.basic.dao.IBaseDao#load(int)
	 */
	@Override
	public T load(int id) {
		return (T) getSession().load(getClz(), id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.gqz.basic.dao.IBaseDao#list(java.lang.String,
	 * java.lang.Object[])
	 */
	public List<T> list(String hql, Object[] args) {

		return this.list(hql, args, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.gqz.basic.dao.IBaseDao#list(java.lang.String, java.lang.Object)
	 */
	public List<T> list(String hql, Object arg) {
		return this.list(hql, new Object[] { arg });
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.gqz.basic.dao.IBaseDao#list(java.lang.String)
	 */
	public List<T> list(String hql) {
		return this.list(hql, null);
	}

	private String initSort(String hql) {
		String order = SystemContext.getOrder();
		String sort = SystemContext.getSort();

		// 处理排序
		if (null != sort && !"".equals(sort.trim())) {
			hql += " order by " + sort;
			if (!"desc".equals(order)) {
				hql += " asc";
			} else {
				hql += " desc";
			}
		}

		return hql;
	}

	@SuppressWarnings("rawtypes")
	private void setAliasParameter(Query query, Map<String, Object> alias) {
		if (null != alias) {
			Set<String> keys = alias.keySet();
			for (String key : keys) {
				Object val = alias.get(key);
				if (val instanceof Collection) {
					// 查询条件是列表
					query.setParameterList(key, (Collection) val);
				} else {
					query.setParameter(key, val);
				}
			}
		}
	}

	private void setParameter(Query query, Object[] args) {
		if (null != args && args.length > 0) {
			int index = 0;
			for (Object arg : args) {
				query.setParameter(index++, arg);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.gqz.basic.dao.IBaseDao#list(java.lang.String,
	 * java.lang.Object[], java.util.Map)
	 */
	public List<T> list(String hql, Object[] args, Map<String, Object> alias) {

		hql = initSort(hql);

		// 处理别名
		Query query = getSession().createQuery(hql);
		setAliasParameter(query, alias);

		// 处理args
		setParameter(query, args);

		return query.list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.gqz.basic.dao.IBaseDao#list(java.lang.String, java.util.Map)
	 */
	public List<T> listByAlias(String hql, Map<String, Object> alias) {

		return this.list(hql, null, alias);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.gqz.basic.dao.IBaseDao#find(java.lang.String,
	 * java.lang.Object[])
	 */
	public Pager<T> find(String hql, Object[] args) {

		return this.find(hql, args, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.gqz.basic.dao.IBaseDao#find(java.lang.String, java.lang.Object)
	 */
	public Pager<T> find(String hql, Object arg) {

		return this.find(hql, new Object[] { arg });
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.gqz.basic.dao.IBaseDao#find(java.lang.String)
	 */
	public Pager<T> find(String hql) {

		return this.find(hql, null);
	}

	@SuppressWarnings("rawtypes")
	private void setPagers(Query query, Pager pages) {
		Integer pageSize = SystemContext.getPageSize();
		Integer pageOffSet = SystemContext.getPageOffset();

		if (pageOffSet == null || pageOffSet < 0) {
			pageOffSet = 0;
		}

		if (pageSize == null || pageSize < 0) {
			pageSize = 15;
		}

		pages.setOffset(pageOffSet);
		pages.setSize(pageSize);

		query.setFirstResult(pageOffSet).setMaxResults(pageSize);
	}

	private String getCountHql(String hql, boolean isHql) {
		String end = hql.substring(hql.indexOf("from"));
		String countsql = "select count(*) " + end;

		if (isHql) {
			countsql.replace("fetch", "");
		}
		return countsql;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.gqz.basic.dao.IBaseDao#find(java.lang.String,
	 * java.lang.Object[], java.util.Map)
	 */
	public Pager<T> find(String hql, Object[] args, Map<String, Object> alias) {
		hql = initSort(hql);
		String cq = getCountHql(hql, true);

		Query cquery = getSession().createQuery(cq);
		Query query = getSession().createQuery(hql);
		Pager<T> pages = new Pager<T>();

		setAliasParameter(query, alias);
		setAliasParameter(cquery, alias);
		setParameter(query, args);
		setParameter(cquery, args);
		setPagers(query, pages);

		List<T> datas = query.list();
		pages.setDatas(datas);

		long total = (Long)cquery.uniqueResult();
		pages.setTotal(total);

		return pages;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.gqz.basic.dao.IBaseDao#find(java.lang.String, java.util.Map)
	 */
	public Pager<T> findByAlias(String hql, Map<String, Object> alias) {

		return this.find(hql, null, alias);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.gqz.basic.dao.IBaseDao#queryObject(java.lang.String,
	 * java.lang.Object[])
	 */
	public Object queryObject(String hql, Object[] args) {

		return this.queryObject(hql, args, null);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see org.gqz.basic.dao.IBaseDao#queryObjectByAlias(String, Map)
	 */
	public Object queryObjectByAlias(String hql, Map<String, Object> alias) {
		return this.queryObject(hql, null, alias);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see org.gqz.basic.dao.IBaseDao#queryObject(String, Object[], Map)
	 */
	public Object queryObject(String hql, Object[] args, Map<String, Object> alias) {
		Query query = getSession().createQuery(hql);
		setAliasParameter(query, alias);
		setParameter(query, args);

		return query.uniqueResult();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.gqz.basic.dao.IBaseDao#queryObject(java.lang.String,
	 * java.lang.Object)
	 */
	public Object queryObject(String hql, Object args) {

		return this.queryObject(hql, new Object[] { args });
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.gqz.basic.dao.IBaseDao#queryObject(java.lang.String)
	 */
	public Object queryObject(String hql) {

		return this.queryObject(hql, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.gqz.basic.dao.IBaseDao#updateByHql(java.lang.String,
	 * java.lang.Object[])
	 */
	public void updateByHql(String hql, Object[] args) {
		Query query = getSession().createQuery(hql);
		setParameter(query, args);
		query.executeUpdate();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.gqz.basic.dao.IBaseDao#updateByHql(java.lang.String,
	 * java.lang.Object)
	 */
	public void updateByHql(String hql, Object args) {
		this.updateByHql(hql, new Object[] { args });
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.gqz.basic.dao.IBaseDao#updateByHql(java.lang.String)
	 */
	public void updateByHql(String hql) {
		this.updateByHql(hql, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.gqz.basic.dao.IBaseDao#listBySql(java.lang.String,
	 * java.lang.Object[], java.lang.Class, boolean)
	 */
	public <N extends Object>List<N> listBySql(String sql, Object[] args, Class<?> clz, boolean hasEntity) {

		return this.listBySql(sql, args, null, clz, hasEntity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.gqz.basic.dao.IBaseDao#listBySql(java.lang.String,
	 * java.lang.Object, java.lang.Class, boolean)
	 */
	public <N extends Object>List<N> listBySql(String sql, Object arg, Class<?> clz, boolean hasEntity) {

		return this.listBySql(sql, new Object[] { arg }, clz, hasEntity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.gqz.basic.dao.IBaseDao#listBySql(java.lang.String,
	 * java.lang.Class, boolean)
	 */
	public <N extends Object>List<N> listBySql(String sql, Class<?> clz, boolean hasEntity) {

		return this.listBySql(sql, null, clz, hasEntity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.gqz.basic.dao.IBaseDao#listBySql(java.lang.String,
	 * java.lang.Object[], java.util.Map, java.lang.Class, boolean)
	 */
	public <N extends Object>List<N> listBySql(String sql, Object[] args, Map<String, Object> alias, Class<?> clz,
			boolean hasEntity) {
		sql = initSort(sql);
		SQLQuery sq = getSession().createSQLQuery(sql);
		setAliasParameter(sq, alias);
		setParameter(sq, args);

		if (hasEntity) {
			sq.addEntity(clz);
		} else {
			sq.setResultTransformer(Transformers.aliasToBean(clz));
		}

		return sq.list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.gqz.basic.dao.IBaseDao#listBySql(java.lang.String,
	 * java.util.Map, java.lang.Class, boolean)
	 */
	public <N extends Object>List<N> listByAliasSql(String sql, Map<String, Object> alias, Class<?> clz, boolean hasEntity) {

		return this.listBySql(sql, null, alias, clz, hasEntity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.gqz.basic.dao.IBaseDao#findBySql(java.lang.String,
	 * java.lang.Object[], java.lang.Class, boolean)
	 */
	public <N extends Object>Pager<N> findBySql(String sql, Object[] args, Class<?> clz, boolean hasEntity) {

		return this.findBySql(sql, args, null, clz, hasEntity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.gqz.basic.dao.IBaseDao#findBySql(java.lang.String,
	 * java.lang.Object, java.lang.Class, boolean)
	 */
	public <N extends Object>Pager<N> findBySql(String sql, Object args, Class<?> clz, boolean hasEntity) {

		return this.findBySql(sql, new Object[]{args}, clz, hasEntity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.gqz.basic.dao.IBaseDao#findBySql(java.lang.String,
	 * java.lang.Class, boolean)
	 */
	public <N extends Object>Pager<N> findBySql(String sql, Class<?> clz, boolean hasEntity) {

		return this.findBySql(sql, null, clz, hasEntity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.gqz.basic.dao.IBaseDao#findBySql(java.lang.String,
	 * java.lang.Object[], java.util.Map, java.lang.Class, boolean)
	 */
	public <N extends Object>Pager<N> findBySql(String sql, Object[] args, Map<String, Object> alias, Class<?> clz,
			boolean hasEntity) {
		sql = initSort(sql);
		String cq = getCountHql(sql, false);
		
		SQLQuery squery = getSession().createSQLQuery(sql);
		SQLQuery cquery = getSession().createSQLQuery(cq);
		
		setAliasParameter(squery, alias);
		setAliasParameter(cquery, alias);
		
		setParameter(squery, args);
		setParameter(cquery, args);
		
		Pager<N> pages = new Pager<N>();
		setPagers(squery, pages);
		
		if (hasEntity) {
			squery.addEntity(clz);
		} else {
			squery.setResultTransformer(Transformers.aliasToBean(clz));
		}
		
		List<N> datas = squery.list();
		pages.setDatas(datas);
		
		Long total = ((BigInteger) cquery.uniqueResult()).longValue();
		pages.setTotal(total);
		
		return pages;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.gqz.basic.dao.IBaseDao#findBySql(java.lang.String,
	 * java.util.Map, java.lang.Class, boolean)
	 */
	public <N extends Object>Pager<N> findByAliasSql(String sql, Map<String, Object> alias, Class<?> clz, boolean hasEntity) {

		return this.findBySql(sql, null, alias, clz, hasEntity);
	}

}
