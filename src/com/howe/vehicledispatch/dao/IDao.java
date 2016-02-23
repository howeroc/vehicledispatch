package com.howe.vehicledispatch.dao;
import java.util.List;
import org.hibernate.Query;

import com.howe.vehicledispatch.util.Pager;

@SuppressWarnings("rawtypes")
public interface IDao<T> {
	
	public void insert(T t);  
	  
    public void delete(T t);  
  
    public void update(T t);  
    
    public Query crtQuery(String hql);
  
    public T findById(Class<T> clazz, Integer id);  
  
    public List<T> findAll();
    
	public Pager pager(int start, String hql);
}
