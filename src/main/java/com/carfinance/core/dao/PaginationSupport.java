package com.carfinance.core.dao;

import java.util.HashMap;
import java.util.List;

public class PaginationSupport implements java.io.Serializable {
	
	private static final long	serialVersionUID	= -428726637561960498L;
	private static int	DEFAULT_COUNT_ON_EACH_PAGE	= 10;
	private int			totalCount;
	private int			startIndex;
	private int			countOnEachPage;
	private List<Object>		items;
	public PaginationSupport() {
		this(DEFAULT_COUNT_ON_EACH_PAGE);
	}
	public PaginationSupport(int countOnEachPage) {
		startIndex = 0;
		if (countOnEachPage < 1) {
			throw new IllegalArgumentException("Count should be greater than zero!");
		} else {
			this.countOnEachPage = countOnEachPage;
		}
	}
	public PaginationSupport(int startIndex, int totalCount, int countOnEachPage) {
		this.startIndex = startIndex;
		this.totalCount = totalCount;
		this.countOnEachPage = countOnEachPage;
		if (this.countOnEachPage <= 0) {
			this.countOnEachPage = DEFAULT_COUNT_ON_EACH_PAGE;
		}
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public void setCountOnEachPage(int countOnEachPage) {
		this.countOnEachPage = countOnEachPage;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public int getEndIndex() {
		int endIndex = getStartIndex() + countOnEachPage;
		if (endIndex > totalCount) {
			return totalCount;
		} else {
			return endIndex;
		}
	}
	public int getStartIndex() {
		if (startIndex > totalCount) {
			return totalCount;
		} else if (startIndex < 0) {
			return 0;
		} else {
			return startIndex;
		}
	}
	public int getNextIndex() {
		int[] nextStartIndexes = getNextStartIndexes();
		if (nextStartIndexes == null) {
			return getTotalCount();
		} else {
			return nextStartIndexes[0];
		}
	}
	public int getPreviousIndex() {
		int[] previousIndexes = getPreviousStartIndexes();
		if (previousIndexes == null) {
			return getStartIndex();
		} else {
			return previousIndexes[previousIndexes.length - 1];
		}
	}
	public int[] getNextStartIndexes() {
		int index = getEndIndex();
		if (index == totalCount) {
			return null;
		}
		int count = (totalCount - index) / countOnEachPage;
		if ((totalCount - index) % countOnEachPage > 0) {
			count++;
		}
		int result[] = new int[count];
		for (int i = 0; i < count; i++) {
			result[i] = index;
			index += countOnEachPage;
		}
		return result;
	}
	public int[] getPreviousStartIndexes() {
		int index = getStartIndex();
		if (index == 0) {
			return null;
		}
		int count = index / countOnEachPage;
		if (index % countOnEachPage > 0) {
			count++;
		}
		int result[] = new int[count];
		for (int i = count - 1; i > 0; i--) {
			index -= countOnEachPage;
			result[i] = index;
		}
		return result;
	}
	public int getCountOnEachPage() {
		return countOnEachPage;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		validate();
	}
	private void validate() {
		if (startIndex > totalCount) {
			int i = getTotalCount() % countOnEachPage;
			startIndex = totalCount - i;
		}
		if (startIndex < 0) {
			startIndex = 0;
		}
	}
	/*
	 * Return the number of pages for the current query.
	 */
	public int getPageCount() {
		int pages = getTotalCount() / countOnEachPage;
		int i = getTotalCount() % countOnEachPage;
		if (i > 0) {
			pages++;
		}
		if (getTotalCount() == 0) {
			pages = 1;
		}
		return pages;
	}
	/*
	 * Return the current page number. Page numbering starts with 1.
	 */
	public int getPage() {
		int page = startIndex / countOnEachPage;
		return page + 1;
	}
	public int getPagePre() {
		int page = startIndex / countOnEachPage;
		return page - 1;
	}
	public void setPage(int page) {
		if (page < 1) {
			startIndex = 0;
		} else {
			startIndex = (page - 1) * countOnEachPage;
		}
	}
	public String toString() {
		return "PaginationSupport[" + "totalCount=" + totalCount + ", startIndex=" + startIndex + ", pageCount=" + getPageCount() + ", page=" + getPage() + "]";
	}
	public void setItems(List<Object> items) {
		this.items = items;
	}
	public List<Object> getItems() {
		return items;
	}

	public HashMap<Object,Object> psToHashMap() {
		HashMap<Object,Object> map = new HashMap<Object,Object>();
		map.put("totalCount", totalCount);
		map.put("pageData", items);
		return map;
	}
}
