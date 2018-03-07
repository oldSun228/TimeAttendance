package com.dby.njxinch.util;

import java.util.Comparator;

import com.dby.njxinch.model.TreeNode;

public class MenuComparator implements Comparator<TreeNode>{

    @Override
    public int compare(TreeNode o1, TreeNode o2) {
	int i1 = o1.getSeq() != null ? o1.getSeq().intValue() : -1;
	int i2 = o2.getSeq() != null ? o2.getSeq().intValue() : -1;
	return i1 - i2;
    }

}
