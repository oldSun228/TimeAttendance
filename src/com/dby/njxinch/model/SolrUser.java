package com.dby.njxinch.model;

import org.apache.solr.client.solrj.beans.Field;

/**
 * Created by fgs on 2016/6/26.
 */
public class SolrUser {
    @Field
    private String id;//用户id

    @Field
    private String userName;//用户名

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
