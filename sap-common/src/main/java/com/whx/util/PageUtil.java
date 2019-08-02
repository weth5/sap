package com.whx.util;

import com.whx.pojo.vo.PageObject;

import java.util.List;

public class PageUtil {
	private static int pageSize=3;
	public static int getPageSize() {
		return pageSize;
	}
	public static int getstartIndex(Integer pageCurrent) {
		return (pageCurrent-1)*pageSize;
	}
	public static <T> PageObject<T> newPageObject(Integer pageCurrent, int rowCount, int pageSize, List<T> records) {
		PageObject<T> pageObject = new PageObject<>();
		//4:对查询结果进行计算和封装
		pageObject.setPageSize(pageSize);
		pageObject.setPageCount((rowCount-1)/pageSize+1);
		/*
		 * double pageCount=rowCount/(pageObject.getPageSize());
		 * pageObject.setPageCount((int)Math.ceil(pageCount));
		 * 不能这么算当2/3余0Math.ceil还是0
		 */
		pageObject.setRowCount(rowCount);
		pageObject.setRecords(records);
		pageObject.setPageCurrent(pageCurrent);
		return pageObject;
	}
}
