package com.dby.njxinch.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fgs on 2016/7/3.
 */
public class FunctionMenus extends ClassCommon implements Serializable {
    private Integer menuId;
    private Integer menuSn;
    private Integer parentMenuId;
    private String menuPic;
    private String menuName;
    private String menuUrl;

    //判断是否有下级菜单的标识
    private String nextFlag;
    private List<FunctionMenus> functionMenusList;

    public Integer getMenuSn() {
        return menuSn;
    }

    public void setMenuSn(Integer menuSn) {
        this.menuSn = menuSn;
    }

    public String getNextFlag() {
        return nextFlag;
    }

    public void setNextFlag(String nextFlag) {
        this.nextFlag = nextFlag;
    }

    public String getMenuPic() {
        return menuPic;
    }

    public void setMenuPic(String menuPic) {
        this.menuPic = menuPic;
    }

    public List<FunctionMenus> getFunctionMenusList() {
        return functionMenusList;
    }

    public void setFunctionMenusList(List<FunctionMenus> functionMenusList) {
        this.functionMenusList = functionMenusList;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public Integer getParentMenuId() {
        return parentMenuId;
    }

    public void setParentMenuId(Integer parentMenuId) {
        this.parentMenuId = parentMenuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }


}
