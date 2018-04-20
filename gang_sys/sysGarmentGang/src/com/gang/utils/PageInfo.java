package com.gang.utils;

import java.util.List;

/**
 * 分页工具
 * @author dongk
 *
 */
@SuppressWarnings("rawtypes")
public class PageInfo {

	private int pageNum = 1; // 当前页,默认1
	private int pageSize = 10; // 每页显示多少条数据,默认10
	private int total = 0; // 总数
	private List list = null; //数据列表
	
	public PageInfo() {
		
	}
	
	public PageInfo(int pageNum, int pageSize) {
		this.pageNum = pageNum;
		this.pageSize = pageSize;
	}
	
	/**
	 * 参数初始化构造方法
	 * @param pageNum
	 * @param pageSize
	 * @param total
	 */
	public PageInfo(int pageNum, int pageSize, int total) {
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.total = total;
	}
	
	/**
	 * 上一页
	 * @return
	 */
	public int getPrevPage() {
		if(pageNum <= 0) return 1;
		return pageNum == 1 ? 1 : pageNum - 1;
	}
	
	/**
	 * 下一页
	 * @return
	 */
	public int getNextPage() {
		return pageNum == getPageCount() ? getPageCount() : pageNum + 1;
	}
	
	/**
	 * 第一页
	 * @return
	 */
	public int getFirstPage() {
		return 1;
	}
	
	/**
	 * 最后一页
	 * @return
	 */
	public int getLastPage() {
		return getPageCount();
	}
	
	/**
	 * 总数
	 * @return
	 */
	public int getTotal() {
		return total;
	}
	
	/**
	 * 总页数
	 * @return
	 */
	public int getPageCount() {
		return total % pageSize == 0 ? total / pageSize : (total / pageSize) + 1;
	}
	
	
	public int getPageNum() {
		return pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public List getList() {
		return list;
	}
	
	public void setList(List list) {
		this.list = list;
	}
	
	public void setTotal(int total) {
		this.total = total;
	}
}
