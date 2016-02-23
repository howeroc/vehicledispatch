package com.howe.vehicledispatch.service;

import java.util.List;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import com.howe.vehicledispatch.bean.User;
import com.howe.vehicledispatch.util.Pager;

@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
@Scope("prototype")
public interface IUserService{
	
	public User findUserByName(String name);
	
	public void create(User user);
	
	public void delete(Integer id);
	
	public void update(User user);
	
	public User loginSearch(Integer id, String password );
	
	public User findUserById(Class<User> clazz,Integer id);
	
	public List<User> findAllUser();
	
	/*public Page<User> userPager(Page<User> page, User user, Date startDate, Date endDate);*/
	
	@SuppressWarnings("rawtypes")
	public Pager userPager(int start, String hql);
	
}
