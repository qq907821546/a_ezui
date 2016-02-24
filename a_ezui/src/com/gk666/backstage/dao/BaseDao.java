package com.gk666.backstage.dao;

import java.util.List;
import javax.annotation.Resource;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.gk666.backstage.model.entity.Article;



@Repository("baseDao")
public class BaseDao {
	@Resource
	private HibernateTemplate hibernateTemplate;
	
	private Session getOpenSession(){
		return this.hibernateTemplate.getSessionFactory().openSession();
	}
	
	private Session getCurrentSession(){
		return this.hibernateTemplate.getSessionFactory().getCurrentSession();
	}
	
	/**
	 * 关闭Session的通用方法
	 * @param session
	 */
	private void closeSession(Session session){
		if(session != null && session.isOpen()){
			session.close();
		}
	}
	
	public int executeHqlInOpenSession(String hql){
		Session session = this.getOpenSession();
		int result = 0;
		try {
			result = session.createQuery(hql).executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return result;
	}
	
	
	/**
	 * 根据主键id查询单条数据
	 * @param entityClass
	 * @param id
	 * @return
	 */
	public Object findById(String entityName,String id){
		String hql = " FROM " + entityName + " WHERE id = '" + id + "' ";
		List list = this.findByHql(hql);
		if(list != null && list.size() > 0)
			return list.get(0);
		return null;
	}

	
	/**
	 * 根据带一个参数的hql语句查询一条数据    FROM User u WHERE u.set = ?
	 * @param hql
	 * @param object	1
	 * @return object
	 */
	public Object findOneByHql(String hql,Object object){
		List list = this.findByHql(hql,object);
		if(list != null && list.size() > 0)
			return list.get(0);
		return null;
	}
	
	/**
	 * 根据hql语句查询记录集  FROM User
	 * @param hql
	 * @return
	 */
	public List findByHql(String hql){
		return this.hibernateTemplate.find(hql);
	}
	
	/**
	 * 根据带一个参数的hql语句查询记录集    FROM User u WHERE u.set = ?
	 * @param hql
	 * @param object	1
	 * @return
	 */
	public List findByHql(String hql,Object object){
		return this.hibernateTemplate.find(hql,object);
	}
	
	/**
	 * 根据带有多个参数的hql语句查询记录集   
	 * @param hql
	 * @param objects   new Object(){"abcd",1}
	 * @return
	 */
	public List findByHql(String hql,Object[] objects){
		return this.hibernateTemplate.find(hql, objects);
	}
	
	/**
	 * 根据hql语句查询符合条件的条数 SELECT COUNT(*) FROM User u WHERE u.sex = 1
	 * @param hql
	 * @return	如果没有或者null返回0
	 */
	protected int getCountByHql(String hql){
		Session session = this.getOpenSession();
		Query query = session.createQuery(hql);
		Object object = query.uniqueResult();
		closeSession(session);
		if(object == null)
			return 0;
		return Integer.parseInt(object.toString());
	}
	protected double getSumByHql(String hql){
		Session session = this.getOpenSession();
		Query query = session.createQuery(hql);
		Object object = query.uniqueResult();
		closeSession(session);
		if(object == null)
			return 0.0;
		return Double.parseDouble(object.toString());
	}
	/**
	 * 根据sql语句查询符合条件的条数 SELECT COUNT(*) FROM User u WHERE u.sex = 1
	 * @param sql
	 * @return	如果没有或者null返回0
	 */
/*	protected int getCountBySql(String sql){
		Session session = this.getOpenSession();
		Query query = session.createSQLQuery(sql);
		Object object = query.uniqueResult();
		closeSession(session);
		if(object == null)
			return 0;
		return Integer.parseInt(object.toString());
	}*/
	
	
	public List findPageByEntityName(String entityName,int firstResult,int maxResults){
		String hql = " FROM " + entityName + " ";
		return this.findPageByHql(hql, firstResult, maxResults);
	}
	
	public int getCountByEntityName(String entityName){
		String hql = " SELECT COUNT(*) FROM " + entityName + " ";
		return this.getCountByHql(hql);
	}
	
	
	/**
	 * 根据hql语句查询列表
	 * @param hql
	 * @param firstResult   开始查询的下标   从0开始
	 * @param maxResults	要查询多少条
	 * @return
	 */
	protected List findPageByHql(String hql,int firstResult,int maxResults){
		  Session session = this.getOpenSession();
		  Query q = session.createQuery(hql);
		  q.setFirstResult(firstResult);
		  q.setMaxResults(maxResults);
		  List list=q.list();
		  closeSession(session);
		  return list;
	}
	
	protected List findPageByHql(String hql,int firstResult,int maxResults,Object[] objs){
		  Session session = this.getOpenSession();
		  Query q = session.createQuery(hql);
		  
		  if(objs != null && objs.length > 0){
			  for(int i = 0;i < objs.length;i++){
				  q.setParameter(i, objs[i]);
			  }
		  }
		  
		  q.setFirstResult(firstResult);
		  q.setMaxResults(maxResults);
		  List list=q.list();
		  closeSession(session);
		  return list;
	}
	
	/**
	 *	根据sql语句和类查询记录集
	 *	使用当前事物中的Session，需要业务层有事物包裹，否则报错
	 * @param sql
	 * @param entityClass
	 * @return
	 */
	protected List findBySqlInCurrentSession(String sql,Class entityClass){
		return getCurrentSession().createSQLQuery(sql).addEntity(entityClass).list();
	}
	
	/**
	 * 根据sql语句和类查询记录集
	 * 使用openSession，不在所处事物当中，不需要事物包裹
	 * @param sql
	 * @param entityClass
	 * @return
	 */
	protected List findBySqlInOpenSession(String sql,Class entityClass){
		Session session = this.getOpenSession();
		List list = session.createSQLQuery(sql).addEntity(entityClass).list();
		closeSession(session);
		return list;
	}
	
	/**
	 * 保存
	 * @param object
	 */
	public void save(Object object){
		this.hibernateTemplate.save(object);
	}
	
	/**
	 * 修改操作  需要提供主键id
	 * @param object
	 */
	public void update(Object object){
		this.hibernateTemplate.update(object);
	}
	
	/**
	 * 根据hql语句进行修改操作
	 * @param hql update User u set u.name = 'a' where id = 1
	 * @param object
	 * @return 受影响的行数
	 */
	protected int bulkUpdateByHql(String hql) {
		return this.hibernateTemplate.bulkUpdate(hql);
	}
	
	/**
	 * 根据hql语句进行修改操作
	 * @param hql update User u set u.name = ? where id = ?
	 * @param object
	 * @return 受影响的行数
	 */
	protected int bulkUpdateByHql(String hql,Object object) {
		return this.hibernateTemplate.bulkUpdate(hql, object);
	}
	
	/**
	 * 根据hql语句进行修改操作
	 * @param hql
	 * @param objects
	 * @return 
	 */
	protected int bulkUpdateByHql(String hql,Object[] objects) {
		return hibernateTemplate.bulkUpdate(hql, objects);
	}
	
	/**
	 * 删除操作   实体类需要提供主键id
	 * @param object
	 */
	public void delete(Object object) {
		hibernateTemplate.delete(object);
	}
	
	public int deleteByEntityNameAndId(String entityName,String id){
		String hql = " DELETE FROM " + entityName + " WHERE id = '" + id + "' ";
		return this.deleteByHqlInOpenSession(hql);
	}
	
	/**
	 * 根据hql语句执行增删改操作
	 * 使用当前事物中的Session，业务层需要进行事物包裹，否则报错
	 * @param hql
	 * @return	受影响的行数
	 */
	public int executeHqlInCurrentSession(String hql){
		Session session = this.getCurrentSession();
		int result = session.createQuery(hql).executeUpdate();
		return result;
	}
	
	/**
	 * 根据hql语句执行删除操作
	 * 使用独立Session，不在当前事物中进行
	 * 自带事物
	 * @param hql
	 * @return
	 */
	protected int deleteByHqlInOpenSession(String hql){
		Session session = this.getOpenSession();
		Transaction trans=session.beginTransaction();
		int result = 0;
		try {
			result = session.createQuery(hql).executeUpdate();
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return result;
	}
	
	/**
	 * 执行sql语句   不能进行查询结果集的操作  只能进行  增删改
	 * 需要业务层包裹事物  否则报错 
	 * @param sql
	 * @return
	 */
	protected int executeBySqlInCurrentSession(String sql) {
		return this.getCurrentSession().createSQLQuery(sql).executeUpdate();
	}
	
	/**
	 * 执行增删改的sql语句   不鞥进行查询结果集的操作
	 * 独立于当前事物之外   自带事物提交
	 * @param sql
	 * @return 受影响的行数
	 */
	/*protected int executeBySqlInOpenSession(String sql){
		Session session = null; 
		Transaction ts = null; 
		Connection conn = null; 
		PreparedStatement ps = null;
		
		session = this.getOpenSession();
		ts = session.beginTransaction(); 
		conn = session.connection(); 
		int result = 0;
		try {
			ps = conn.prepareStatement(sql);
			//ps.executeQuery();
			result = ps.executeUpdate();
			ts.commit();
		} catch (Exception e1) {
			ts.rollback();
			e1.printStackTrace();
		}finally{
				try {
					if(ps!=null){
						ps.close();
					}
					if(conn!=null){
						conn.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				if(session!=null){
					session.close();
				}
		} 
		
		return result;
	}*/
}
