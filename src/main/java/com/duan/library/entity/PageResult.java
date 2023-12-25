package com.duan.library.entity;
import java.io.Serializable;
import java.util.List;
/**
 * 分页结果的实体类
 */
public  class PageResult <T> implements Serializable{
	private long total; // 总数
	private List<T> rows; // 返回的数据集合
	
	public PageResult(long total, List<T> rows) {
		super();
		this.total = total;
		this.rows = rows;
	}

	public PageResult() {

	}

	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
}
