package com.howe.vehicledispatch.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.howe.vehicledispatch.bean.User;
import com.howe.vehicledispatch.dao.IUserDao;
import com.howe.vehicledispatch.service.IUserService;
import com.howe.vehicledispatch.util.Pager;

@SuppressWarnings("rawtypes")
@Service("IUserService")
public class UserServiceImpl implements IUserService{
	
	@Autowired
	private IUserDao userDao;
	
	private User user;
	
	@Override
	public User findUserByName(String name) {
		return this.getUserDao().findByName(name);
	}

	@Override
	public void create(User user) {
		if(findUserByName(user.getName()) !=null){
			throw new RuntimeException("用户" + user.getName() + "已存在。");
		}
		this.getUserDao().insert(user);
		
	}
	
	@Override
	public void delete(Integer id) {
		
		user = findUserById(User.class, id);
		
		this.getUserDao().delete(user);
	}
	
	@Override
	public void update(User user) {

		this.userDao.update(user);
		
	}
	
	@Override
	public User loginSearch(Integer id, String password ){
		
		return this.getUserDao().loginSearch(id, password);
	}
	
	@Override
	public User findUserById(Class<User> clazz,Integer id) {
			
			return this.getUserDao().findById( clazz, id);

	}

	@Override
	public List<User> findAllUser(){
		
		List<User> list = this.getUserDao().findAll();
		
		if (list.size() > 0)
			
			return list;
		
		return null;
	}
	
	public IUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

/*	@Override
	public Page<User> userPager(Page<User> page, User user, Date startDate, Date endDate) {

		Page<User> userPage = this.getUserDao().userPager(page, user, startDate, endDate);
		
		if(userPage != null)
			return userPage;
		
		return null;
	}*/

	@Override
	public Pager userPager(int start, String hql) {

	return this.getUserDao().pager(start, hql);
	}
}
