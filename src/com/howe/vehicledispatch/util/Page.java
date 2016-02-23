package com.howe.vehicledispatch.util;



import java.util.ArrayList;
import java.util.List;

public class Page<T> {

	private static final int PAGE_SIZE = 10;
	private long currentPage;
	private long totalPage;
	private long totalCount;
	
	private long minPage;
	private long maxPage;
	private long startNum;
	private List<T> list = new ArrayList<T>();
	public Page(){
		
	}
	/*
	 * 设置当前第几页
	 */
	public Page(long currentPage){
		this.currentPage = currentPage;
	}

	public long getCurrentPage() {
		return currentPage;
	}

	public long getTotalPage() {
		return totalPage;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public long getMinPage() {
		return minPage;
	}

	public long getMaxPage() {
		return maxPage;
	}

	public List<T> getList() {
		return list;
	}

	
	public long getStartNum() {
		return startNum;
	}

	public void setStartNum(long startNum) {
		this.startNum = startNum;
	}

	public void setCurrentPage(long currentPage) {
		this.currentPage = currentPage;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
		startNum = (currentPage - 1) * PAGE_SIZE;
		totalPage = (totalCount + PAGE_SIZE-1)/PAGE_SIZE;
		if(totalPage <= 5){
			minPage = 1;
			maxPage = totalPage;
		}else{
			minPage = currentPage-2;
			maxPage = currentPage+2;
			
			if(minPage < 1){
				minPage = 1;
				maxPage = 5;
			}
			if(maxPage > totalPage){
				maxPage = totalPage;
				minPage = maxPage - 4;
			}
		}
	}

	
	public static long getPageSize() {
		return PAGE_SIZE;
	}

	
	@Override
	public String toString() {
		return "Page [currentPage=" + currentPage + ", totalPage=" + totalPage
				+ ", totalCount=" + totalCount + ", minPage=" + minPage
				+ ", maxPage=" + maxPage + ", startNum=" + startNum + ", list="
				+ list + "]";
	}
	
	
	
}
