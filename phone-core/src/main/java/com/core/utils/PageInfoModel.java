package com.core.utils;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Miracle on 2020/8/14.
 */
@ApiModel(value = "分页信息")
public class PageInfoModel {

    public static final int DEFAULT_PAGE_SIZE = 15;
    public static final int MAX_PAGE_SIZE = 200;

    @ApiModelProperty(value = "分页页码")
    @TableField(exist = false)
    private Integer pageNum;

    @ApiModelProperty(value = "分页条数")
    @TableField(exist = false)
    private Integer pageSize;

    public void setDefault(Integer pageNum, Integer pageSize) {
        if (pageNum == null) {
            this.pageNum = 1;
        } else {
            this.pageNum = pageNum;
        }
        if (pageSize == null) {
            this.pageSize = DEFAULT_PAGE_SIZE;
        } else {
            if (pageSize > MAX_PAGE_SIZE) {
                this.pageSize = MAX_PAGE_SIZE;
            } else {
                this.pageSize = pageSize;
            }
        }
    }

    /**
     * 获取分页起始下标
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    public static int getStartIndex(int pageNum, int pageSize) {
        return (pageNum - 1) * pageSize;
    }

    /**
     * 获取分页结束下标
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    public static int getEndIndex(int pageNum, int pageSize) {
        return getStartIndex(pageNum, pageSize) + pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getOffset() {
        if (pageNum != null && pageSize != null) {
            return (pageNum - 1) * getPageSize();
        }
        return null;
    }
}
