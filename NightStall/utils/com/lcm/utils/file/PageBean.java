package com.lcm.utils.file;

import java.util.List;

public class PageBean<T> {
	private List<T> list;
	private long allRow;
	private int totalPage;
	private int currentPage;
	private int pageSize;
	
	public PageBean() {
		
	}
	
	public PageBean(List<T> list, long allRow, int currentPage, int pageSize) {
		this.list = list;
		this.allRow = allRow;
		this.currentPage = currentPage;
		this.pageSize = pageSize;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public long getAllRow() {
		return allRow;
	}

	public void setAllRow(long allRow) {
		this.allRow = allRow;
	}

	public int getTotalPage() {
		totalPage = (int) ((allRow % pageSize == 0) ? (allRow / pageSize) : (allRow / pageSize + 1));
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	
	/**
	 * 是否有上一页
	 */
	public boolean hasPreviousPage() {
		return (this.currentPage > 1);
	}

	public int[] getShowPage() {

		int begPageCount = 1;// 起始页码

		int endPageCount = 5;// 结束页码---开发中设置该参数

		int maxPageLength = 5;// 页码长度---开发中设置该参数，该参数最好是endPageCount的倍数关系

		int halfPageLength = maxPageLength / 2;// 页码一半长度

		if (this.currentPage > halfPageLength) {// 当前页码>页码一半长度
			begPageCount = this.currentPage- halfPageLength;
			endPageCount = this.currentPage+ halfPageLength;
		} else if (this.currentPage <= halfPageLength
				&& this.currentPage != begPageCount) {
			endPageCount = this.currentPage + halfPageLength - 1;
		}
		// 最后再修改正回來
		if (this.getTotalPage() < endPageCount)// 总页数<结束码数
			endPageCount = this.getTotalPage();
		int[] returnVal = new int[endPageCount - begPageCount + 1];
		int index = 0;
		for (int i = begPageCount; i <= endPageCount; i++) {
			returnVal[index] = i;
			index++;
		}
		return returnVal;
	}
}