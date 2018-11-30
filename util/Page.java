package com.onlineordering.util;


import java.util.ArrayList;
import java.util.List;

/**
 * 分页类，封装分页基本信息。首页默�?
 * @author Administrator
 * @version v1.0
 */
public class Page {
	//当前�?
	private int curPage = 1;
	//总页�?
	private int totalPage;
	//数据库记录数
	private int rows;
	//每页数据�?
	private int pageNumber = 5;
	//要展示的List数据
	private List date = new ArrayList();
	
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public List getDate() {
		return date;
	}
	public void setDate(List date) {
		this.date = date;
	}
	
}
