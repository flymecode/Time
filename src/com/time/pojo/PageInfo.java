package com.time.pojo;

import java.util.List;

/**
 * 分页数据包装
 */
public class PageInfo {
    /**
     * 总页数
     */
    private Integer total;
    /**
     * 结果集
     */
    private List<?> rows;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }
}
