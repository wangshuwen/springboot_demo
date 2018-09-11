package com.zkxh.demo.common.util.tree;

import io.swagger.models.auth.In;

import java.util.List;

/**
 * @ClassName Menu
 * @Description
 * @Auther lifeng
 * @DATE 2018/9/7 17:49
 * @Vserion v0.0.1
 */

public class Menu {
    // 菜单id
    private Integer id;
    // 菜单名称
    private String name;
    // 父菜单id
    private Integer parentId;
    // 菜单url
    private String url;
    // 菜单图标
    private String icon;
    // 菜单顺序
    private int order;
    // 子菜单
    private List<Menu> childMenus;

    public Menu() {
    }

    public Menu(Integer id, String name, Integer parentId, String url, String icon, int order, List<Menu> childMenus) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.url = url;
        this.icon = icon;
        this.order = order;
        this.childMenus = childMenus;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", parentId='" + parentId + '\'' +
                ", url='" + url + '\'' +
                ", icon='" + icon + '\'' +
                ", order=" + order +
                ", childMenus=" + childMenus +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public List<Menu> getChildMenus() {
        return childMenus;
    }

    public void setChildMenus(List<Menu> childMenus) {
        this.childMenus = childMenus;
    }
}
