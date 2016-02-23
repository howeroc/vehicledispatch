package com.howe.vehicledispatch.service;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.howe.vehicledispatch.bean.Car;
import com.howe.vehicledispatch.bean.Fix;
import com.howe.vehicledispatch.util.Pager;

@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
@Scope("prototype")
public interface IFixService {
	
	public void create(Fix fix);
	
	public void delete(Integer id);
	
	public void update(Fix fix);
	
	public Fix findFixById(Class<Fix> clazz,Integer id);
	
	public List<Fix> findAllFix();
	
	public List<Fix> searchFix(Fix fix);
	
	public List<Car> findUseableCar();
	
	@SuppressWarnings("rawtypes")
	public Pager fixPager(int start, String hql);
	

}
