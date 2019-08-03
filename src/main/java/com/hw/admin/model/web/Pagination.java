package com.hw.admin.model.web;

import com.hw.admin.utils.NumberUtils;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class Pagination {
    /**
     * 数据量
     */
    private Integer totalRow;

    /**
     * 每页最多显示多少条数据
     */
    private Integer pageSize;

    /**
     * 当前页码
     */
    private Integer pageIndex;

    /**
     * 总页数
     */
    private Integer pageCount;

    @Builder
    public Pagination(Integer totalRow, Integer pageSize, Integer pageIndex) {
        this.totalRow = totalRow;
        this.pageSize = pageSize;
        this.pageIndex = pageIndex;
        if (NumberUtils.isPositive(totalRow) && NumberUtils.isPositive(pageSize)) {
            this.pageCount = (int) (Math.ceil(1.0 * totalRow / pageSize));
        }
    }
}
