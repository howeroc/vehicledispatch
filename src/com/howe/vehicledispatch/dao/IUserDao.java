package com.howe.vehicledispatch.dao;

import com.howe.vehicledispatch.bean.User;

public interface IUserDao extends IDao<User>{
	
	 public User findByName(String userName);
	 
	 public User findById(Class<User> clazz, Integer id);
	 
	 public User loginSearch(Integer id, String password );
	 
	/* public Page<User> userPager(Page<User> page,User user, Date startDate, Date endDate);*/

}
