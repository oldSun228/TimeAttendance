package com.dby.njxinch.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Guoch
 * @describe 树形节点模型
 * 
 */

public class TreeNode {

    private String id;
    private String text;// 树节点名称
    private String iconCls;// 前面的小图标样式
    private Boolean checked = false;// 是否勾选状态
    private Map<String, Object> attributes;// 其他参数
    private List<TreeNode> children;// 子节点
    private String state = "open";// 是否展开(open,closed)
    private int level;// 深度
    private boolean leaf;// 是否是叶子节点
    private BigDecimal seq;

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public String getText() {
	return text;
    }

    public void setText(String text) {
	this.text = text;
    }

    public String getIconCls() {
	return iconCls;
    }

    public void setIconCls(String iconCls) {
	this.iconCls = iconCls;
    }

    public Boolean getChecked() {
	return checked;
    }

    public void setChecked(Boolean checked) {
	this.checked = checked;
    }

    public Map<String, Object> getAttributes() {
	return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
	this.attributes = attributes;
    }

    public List<TreeNode> getChildren() {
	return children;
    }

    public void setChildren(List<TreeNode> children) {
	this.children = children;
    }

    public String getState() {
	return state;
    }

    public void setState(String state) {
	this.state = state;
    }

    public int getLevel() {
	return level;
    }

    public void setLevel(int level) {
	this.level = level;
    }

    public boolean isLeaf() {
	return leaf;
    }

    public void setLeaf(boolean leaf) {
	this.leaf = leaf;
    }

    public BigDecimal getSeq() {
        return seq;
    }

    public void setSeq(BigDecimal seq) {
        this.seq = seq;
    }

}
