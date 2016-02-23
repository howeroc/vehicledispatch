package com.howe.vehicledispatch.action;
import com.howe.vehicledispatch.util.Pager;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings({"serial","rawtypes"})
public class IAction extends ActionSupport{

	protected String action;
	protected String title;
	protected String message;
	protected String hql;
	protected Integer pno;
	protected Pager page = null;
	
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getHql() {
		return hql;
	}
	public void setHql(String hql) {
		this.hql = hql;
	}
	public Integer getPno() {
		return pno;
	}
	public void setPno(Integer pno) {
		this.pno = pno;
	}
	public Pager getPage() {
		return page;
	}
	public void setPage(Pager page) {
		this.page = page;
	}

}
