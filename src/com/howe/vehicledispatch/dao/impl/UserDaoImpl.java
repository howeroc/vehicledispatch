package com.howe.vehicledispatch.dao.impl;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.howe.vehicledispatch.bean.User;
import com.howe.vehicledispatch.dao.IUserDao;

@SuppressWarnings("unchecked")
@Repository("IUserDao")
public class UserDaoImpl extends DaoImpl<User> implements IUserDao{
	
	public UserDaoImpl() {
		super(User.class); 
	}
	
	public User findById(Class<User> clazz, Integer id){
		return (User) sessionFactory.getCurrentSession().get(clazz, id);
	}
	

	@Override
	public User findByName(String name) {
		
		List<User> user = crtQuery("SELECT u FROM User u WHERE u.name =:name").setParameter("name", name).list();
		
		if (user.size() > 0)
			return user.get(0);
		
		return null;
	}
	
	public User loginSearch(Integer id, String password ){
		
		List<User> list = crtQuery("SELECT u FROM User u WHERE u.id =:id "
				
				+ "and u.password =:password").setParameter("id", id).setParameter("password", password).list();
		
		if (list.size() > 0){
			
			return list.get(0);
		}
		
		return null;
	
	}

}
