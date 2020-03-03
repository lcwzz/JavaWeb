package bean;

import java.util.List;

/**
 * 分页对象
 * @param <T>
 */
public class Page<T> {
    private int count; //总记录数
    private int pageSize; //每页显示的条数
    private int pages; //页数
    private List<T> list; //当前页面的数据
    private int currentPage; //当前页号

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    @Override
    public String toString() {
        return "Page{" +
                "count=" + count +
                ", pageSize=" + pageSize +
                ", pages=" + pages +
                ", list=" + list +
                ", currentPage=" + currentPage +
                '}';
    }
}
