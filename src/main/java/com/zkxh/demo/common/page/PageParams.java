package com.zkxh.demo.common.page;

/**
 * @ClassName PageParams
 * @Description
 * @Auther lifeng
 * @DATE 2018/11/23 16:29
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
     * @param []
     * @return java.lang.Integer
     * @description 当前页
     * @date 16:32 2018/11/23
     * @auther lifeng
     **/
    public Integer getPage() {
        return page;
    }

    /**
     * @param [page]
     * @return void
     * @description 当前页
     * @date 16:31 2018/11/23
     * @auther lifeng
     **/
    public void setPage(Integer page) {
        this.page = page;
    }

    /**
     * @param []
     * @return java.lang.Integer
     * @description 每页限制条数
     * @date 16:32 2018/11/23
     * @auther lifeng
     **/
    public Integer getPageSize() {
        return pageSize;
    }

    /**
     * @param [pageSize]
     * @return void
     * @description 每页限制条数
     * @date 16:32 2018/11/23
     * @auther lifeng
     **/
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * @param []
     * @return java.lang.Boolean
     * @description 是否启动插件，如果不启动，则不作分页
     * @date 16:40 2018/11/23
     * @auther lifeng
     **/
    public Boolean getUseFlag() {
        return useFlag;
    }

    /**
     * @param [useFlag]
     * @return void
     * @description 是否启动插件，如果不启动，则不作分页
     * @date 16:40 2018/11/23
     * @auther lifeng
     **/
    public void setUseFlag(Boolean useFlag) {
        this.useFlag = useFlag;
    }

    /**
     * @param []
     * @return java.lang.Boolean
     * @description 是否检测页码的有效性，如果为true，而页码大于最大页数，则抛出异常
     * @date 16:40 2018/11/23
     * @auther lifeng
     **/
    public Boolean getCheckFlag() {
        return checkFlag;
    }

    /**
     * @param [checkFlag]
     * @return void
     * @description 是否检测页码的有效性，如果为true，而页码大于最大页数，则抛出异常
     * @date 16:41 2018/11/23
     * @auther lifeng
     **/
    public void setCheckFlag(Boolean checkFlag) {
        if (checkFlag == null) {
            this.checkFlag = false;
        } else {
            this.checkFlag = checkFlag;
        }
    }

    /**
     * @param []
     * @return java.lang.Boolean
     * @description 是否清除最后order by 后面的语句
     * @date 16:41 2018/11/23
     * @auther lifeng
     **/
    public Boolean getCleanOrderBy() {
        return cleanOrderBy;
    }

    /**
     * @param [cleanOrderBy]
     * @return void
     * @description 是否清除最后order by 后面的语句
     * @date 16:41 2018/11/23
     * @auther lifeng
     **/
    public void setCleanOrderBy(Boolean cleanOrderBy) {
        this.cleanOrderBy = cleanOrderBy;
    }

    /**
     * @param []
     * @return java.lang.Integer
     * @description 总条数
     * @date 16:41 2018/11/23
     * @auther lifeng
     **/
    public Integer getTotal() {
        return total;
    }

    /**
     * @param [total]
     * @return void
     * @description 总条数
     * @date 16:41 2018/11/23
     * @auther lifeng
     **/
    public void setTotal(Integer total) {
        this.total = total;
    }

    /**
     * @param []
     * @return java.lang.Integer
     * @description 总页数
     * @date 16:42 2018/11/23
     * @auther lifeng
     **/
    public Integer getTotalPage() {
        return totalPage;
    }

    /**
     * @param [totalPage]
     * @return void
     * @description 总页数
     * @date 16:42 2018/11/23
     * @auther lifeng
     **/
    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }


}
