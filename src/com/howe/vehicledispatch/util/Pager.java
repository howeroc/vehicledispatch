package com.howe.vehicledispatch.util;

import java.util.List;
@SuppressWarnings("rawtypes")
public class Pager<T> {
	
    private int pageSize;/*构造函数来赋值*/

    private long totalPage; 

    private long totalRecord;/*总记录数*/

    private long currentPage;/*由Dao层来设置*/

    private long prePage; 

    private long nextPage;

    private boolean hasNextPage;

    private boolean hasPrePage;

	private List list;

    public Pager() {

        this.pageSize=10;
    }

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(long totalPage) {
		this.totalPage = totalPage;
	}

	public long getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(long totalRecord) {
		this.totalRecord = totalRecord;
	}

	public long getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(long currentPage) {
		this.currentPage = currentPage;
	}

	public long getPrePage() {
		return prePage;
	}

	public void setPrePage(long prePage) {
		this.prePage = prePage;
	}

	public long getNextPage() {
		return nextPage;
	}

	public void setNextPage(long nextPage) {
		this.nextPage = nextPage;
	}

	public boolean isHasNextPage() {
		return hasNextPage;
	}

	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}

	public boolean isHasPrePage() {
		return hasPrePage;
	}

	public void setHasPrePage(boolean hasPrePage) {
		this.hasPrePage = hasPrePage;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}



}