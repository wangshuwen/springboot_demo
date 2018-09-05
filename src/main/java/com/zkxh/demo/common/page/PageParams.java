package com.zkxh.demo.common.page;

/**
 * @ClassName PageParams
 * @Description 进行分页插件的 参数设置
 * @Auther lifeng
 * @DATE 2018/8/16 18:46
 * @Vserion v0.0.1
 */

public class PageParams {

    /**
     * 当前页
     */
    private Integer page;

    /**
     * 每页限制条数
     */
    private Integer pageSize;
    /**
     * 是否启动插件，如果不启动，则不作分页
     */
    private Boolean useFlag;
    /**
     * 是否检测页码的有效性，如果为true，而页码大于最大页数，则抛出异常 默认为false
     */
    private Boolean checkFlag;
    /**
     * 是否清除最后order by 后面的语句
     */
    private Boolean cleanOrderBy;
    /**
     * 总条数
     */
    private Integer total;
    /**
     * 总页数
     */
    private Integer totalPage;

    /**
     * 创建auther:   lifeng
     * date:   2018-8-16 下午11:05:56
     * desc:   当前页
     * :   @return
     *
     * @return Integer
     */
    public Integer getPage() {
        return page;
    }

    /**
     * 创建auther:   lifeng
     * date:   2018-8-16 下午11:06:07
     * desc:   当前页
     * :   @param page
     *
     * @return void
     */
    public void setPage(Integer page) {
        this.page = page;
    }

    /**
     * 创建auther:   lifeng
     * date:   2018-8-16 下午11:06:15
     * desc:   每页限制条数
     * :   @return
     *
     * @return Integer
     */
    public Integer getPageSize() {
        return pageSize;
    }

    /**
     * 创建auther:   lifeng
     * date:   2018-8-16 下午11:06:27
     * desc:   每页限制条数
     * :   @param pageSize
     *
     * @return void
     */
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 创建auther:   lifeng
     * date:   2018-8-16 下午11:06:37
     * desc:   是否启动插件，如果不启动，则不作分页
     * :   @return
     *
     * @return Boolean
     */
    public Boolean getUseFlag() {
        return useFlag;
    }

    /**
     * 创建auther:   lifeng
     * date:   2018-8-16 下午11:06:48
     * desc:   是否启动插件，如果不启动，则不作分页
     * :   @param useFlag
     *
     * @return void
     */
    public void setUseFlag(Boolean useFlag) {
        this.useFlag = useFlag;
    }

    /**
     * 创建auther:   lifeng
     * date:   2018-8-16 下午11:06:57
     * desc:   是否检测页码的有效性，如果为true，而页码大于最大页数，则抛出异常
     * :   @return
     *
     * @return Boolean
     */
    public Boolean getCheckFlag() {
        return checkFlag;
    }

    /**
     * 是否检测页码的有效性，如果为true，而页码大于最大页数，则抛出异常
     * 创建auther:   lifeng
     * date:   2018-8-16 下午11:07:09
     * desc:
     * :   @param checkFlag
     *
     * @return void
     */
    public void setCheckFlag(Boolean checkFlag) {
        if (checkFlag == null) {
            this.checkFlag = false;
        } else {
            this.checkFlag = checkFlag;
        }
    }

    /**
     * 创建auther:   lifeng
     * date:   2018-8-16 下午11:07:22
     * desc:   是否清除最后order by 后面的语句
     * :   @return
     *
     * @return Boolean
     */
    public Boolean getCleanOrderBy() {
        return cleanOrderBy;
    }

    /**
     * 创建auther:   lifeng
     * date:   2018-8-16 下午11:07:31
     * desc:   是否清除最后order by 后面的语句
     * :   @param cleanOrderBy
     *
     * @return void
     */
    public void setCleanOrderBy(Boolean cleanOrderBy) {
        this.cleanOrderBy = cleanOrderBy;
    }

    /**
     * 创建auther:   lifeng
     * date:   2018-8-16 下午11:07:41
     * desc:   总条数
     * :   @return
     *
     * @return Integer
     */
    public Integer getTotal() {
        return total;
    }

    /**
     * 创建auther:   lifeng
     * date:   2018-8-16 下午11:07:50
     * desc:   总条数
     * :   @param total
     *
     * @return void
     */
    public void setTotal(Integer total) {
        this.total = total;
    }

    /**
     * 创建auther:   lifeng
     * date:   2018-8-16 下午11:07:59
     * desc:   总页数
     * :   @return
     *
     * @return Integer
     */
    public Integer getTotalPage() {
        return totalPage;
    }

    /**
     * 创建auther:   lifeng
     * date:   2018-8-16 下午11:08:08
     * desc:   总页数
     * :   @param totalPage
     *
     * @return void
     */
    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

}
