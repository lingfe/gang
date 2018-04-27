package com.gang.pojo;

/**
 * 查詢條件
 * @author 13068	lingfe
 * @date 2018.04.26
 *
 */
public class SelectWhere {
	
	//開始時間
	private String startTime;
	//結束時間
	private String endTime;
	//搜索key ,id编号,用户名,姓名，地区，电话号码等
	private String searchKey;
	//当前页
	private Integer pageIndex;
	//页容量
	private Integer pageNum;
	//操作参数,delete ，update
	private String param;
	
	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}
	public Integer getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	public String getSearchKey() {
		return searchKey;
	}
	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
}
