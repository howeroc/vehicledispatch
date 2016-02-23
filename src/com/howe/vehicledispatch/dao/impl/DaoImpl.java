package com.howe.vehicledispatch.dao.impl;

import java.util.List;
import com.howe.vehicledispatch.dao.IDao;
import com.howe.vehicledispatch.util.Pager;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

@SuppressWarnings({"unchecked","rawtypes"})
public class DaoImpl<T>  implements IDao<T> {
	
	private Class<T> entityClass;  
	  
    public DaoImpl(Class<T> clazz) {  
        this.entityClass = clazz;  
    }

    @Autowired
	protected SessionFactory sessionFactory;
	
	public void insert(T t){
		
		sessionFactory.getCurrentSession().save(t);
	}
	
	public void delete(T t) {
		// 把脱管实例的状态复制到新实例，否则有时异常：illegally attempted to associate a proxy with
		// two open Sessions
		Object obj = sessionFactory.getCurrentSession().merge(t);
		sessionFactory.getCurrentSession().delete(obj);
	}

	public void update(T t) {
		sessionFactory.getCurrentSession().update(t);
	}
	
	public Query crtQuery(String hql){
		return sessionFactory.getCurrentSession().createQuery(hql);
	}
	
	public T findById(Class<T> clazz, Integer id){
		return (T) sessionFactory.getCurrentSession().get(clazz, id);
	}
	

	public List<T> findAll(){

		return sessionFactory.getCurrentSession().createQuery("FROM " + entityClass.getSimpleName()).list();
	}
	
	public List<T> qryInfo(String hql) {
		return sessionFactory.getCurrentSession().createQuery(hql).list();
	}

	public SessionFactory getSessionFactory() {  
        return sessionFactory;  
    } 
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


/*	public Page<T> pageFind(Page<T> page, String hql) {
		
		Query query = this.getSessionFactory().getCurrentSession().createQuery(hql);
		Query cQuery = this.getSessionFactory().getCurrentSession().createQuery(this.getCountHql(hql));
		Long count = (Long) cQuery.uniqueResult();
		page.setTotalCount(count);
		System.out.print(count);
		query.setFirstResult((int)page.getStartNum());
		query.setMaxResults((int)page.getPageSize());
		page.setList(query.list());
		return page;
	}*/

/*-----------------------------------分页方法的实现----------------------------------------*/
	public Pager page;

    public Integer start;
    
    public String hql;
    
    public Pager pager(int start, String hql){
    	
    	this.init(start, hql);
    	return this.getPage();
    }

    public void init(int start, String hql){

       page = new Pager();
       
       this.hql = hql;

       this.start = start;

       setTotalRecord();

       setTotalPage();

       setCurrentPage();

       setPrePage();

       setNextPage();

       setPreOrNextBoolean();

    }


    public Pager getPage(){
    	
    	Query query = this.getSessionFactory().getCurrentSession().createQuery(hql);
		query.setFirstResult(getStartIndex());
		query.setMaxResults(page.getPageSize());
		page.setList(query.list());
		return page;

    }

    public long getTotalRecord(){
    	
    	String search = this.getCountHql(hql);
		Query cQuery = this.getSessionFactory().getCurrentSession().createQuery(search);
		Long count = (Long) cQuery.uniqueResult();
		
		System.out.println("count");
		
       if(count == null){

        return 0;
        
       }

       return count;

    }
    
    public void setTotalRecord() {

        page.setTotalRecord(getTotalRecord());

    }

    public void setPreOrNextBoolean() {

        if (page.getCurrentPage() <= 1) {

            page.setHasPrePage(false);

        } else {

            page.setHasPrePage(true);

        }

        if (page.getCurrentPage() >= page.getTotalPage()) {

            page.setHasNextPage(false);

        } else {

            page.setHasNextPage(true);

        }

    }



    public void setCurrentPage() {

        if (start < 1) {

            page.setCurrentPage(1);

        }

        if (start > page.getTotalPage()) {

            page.setCurrentPage(page.getTotalPage());

        }

        page.setCurrentPage(start);

    }



    public void setPrePage() {

        page.setPrePage(page.getCurrentPage() - 1);

    }



    public void setNextPage() {

        page.setNextPage(page.getCurrentPage() + 1);

    }



    public void setTotalPage() {

        long totalRecord = getTotalRecord();

        int pageSize = page.getPageSize();

        if (totalRecord > pageSize) {

            if (totalRecord % pageSize == 0) {

                page.setTotalPage(totalRecord / pageSize);

            } else {

                page.setTotalPage(1 + (totalRecord / pageSize));

            }

        } else {

            page.setTotalPage(1);

        }

    }
    
    public int getStartIndex() {

        int startIndex = 0;

        if (start < 0) {

            startIndex = 0;

        } else {

            if (start > page.getTotalPage()) {

                startIndex = (int) (page.getPageSize() * (page.getTotalPage() - 1));

            } else {

                startIndex = page.getPageSize() * (start - 1);

            }

        }

        return startIndex;

    }
	private String getCountHql(String hql){
		
		String f = hql.substring(0, hql.indexOf("FROM"));
		if(f.equals("")){
			hql = "SELECT COUNT(*) " + hql;
		} else{
			hql = hql.replace(f,"SELECT COUNT(*) ");
		}
		System.out.println(hql);
		return hql;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public String getHql() {
		return hql;
	}

	public void setHql(String hql) {
		this.hql = hql;
	}

	public void setPage(Pager page) {
		this.page = page;
	}

}
