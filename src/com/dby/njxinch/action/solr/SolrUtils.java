package com.dby.njxinch.action.solr;

import com.dby.njxinch.model.SolrUser;
import com.dby.njxinch.model.User;
import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.beans.DocumentObjectBinder;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.util.ClientUtils;
import org.apache.solr.common.SolrDocumentList;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SolrUtils {

    private static Logger logger = Logger.getLogger(User.class);

    /**
     * solr url
     */
    public static String URL;

    public static String SERVER;

    private static SolrClient solrClient;

    static {
        URL = "http://192.168.1.128:18001/solr/";
        SERVER = "test_core";
    }

    /**
     * solr应用
     */
    private static SolrClient getSolrClient() {
        if (null == solrClient) {
            solrClient = new HttpSolrClient(URL + "/" + SERVER);
        }
        return solrClient;
    }

    /**
     * 新增
     *
     * @param solrUser
     */
    public static void add(SolrUser solrUser) {
        SolrClient client = getSolrClient();
        try {
            client.addBean(solrUser);
            client.commit();
        } catch (SolrServerException | IOException e) {
            logger.error("添加单位索引失败", e);
        }
    }

    /**
     * 删除
     *
     * @param id
     */

    public static void delete(String id) {
        SolrClient client = getSolrClient();
        try {
            client.deleteById(id);
            client.commit();
        } catch (SolrServerException | IOException e) {
            logger.error("删除单位索引失败", e);
        }
    }

    public static void update(SolrUser solrUser) {
        SolrClient client = getSolrClient();
        try {
            client.deleteById(String.valueOf(solrUser.getId()));
            client.addBean(solrUser);
            client.commit();
        } catch (SolrServerException | IOException e) {
            logger.error("更新单位索引失败", e);
        }
    }


    /**
     * 获取详情 通过 id
     *
     * @param id
     * @return
     */
    public static SolrUser getById(String id) {
        SolrClient client = getSolrClient();
        SolrUser solrUser = null;
        try {
            DocumentObjectBinder binder = new DocumentObjectBinder();
            solrUser = binder.getBean(SolrUser.class, client.getById(id));
        } catch (SolrServerException | IOException e) {
            logger.error("根据单位ID获取单位失败", e);
        }

        return solrUser;
    }

    /**
     * 搜索单位
     *
     * @param matchField 匹配列
     * @param matchValue 匹配值
     * @return
     * @throws SolrServerException
     * @throws IOException
     * @author zhangjinglei
     * @since 2016年1月31日
     */
    public static Map<String, Object> search(String[] matchField, String[] matchValue, int start, int count)
            throws SolrServerException, IOException {
        Map<String, Object> resMap = new HashMap<>();

        if (null == matchField || null == matchValue
                || matchField.length != matchValue.length) {
            return null;
        }

        SolrQuery query;

        if (matchField[0].equals("userName")) {
            query = new SolrQuery(matchField[0] + ":"
                    + ("*".equals(matchValue[0]) ? matchValue[0]
                    : ClientUtils.escapeQueryChars(matchValue[0])));
        } else {
            query = new SolrQuery(matchField[0] + ":"
                    + ClientUtils.escapeQueryChars(matchValue[0]));
        }

        for (int i = 1; i < matchField.length; i++) {
            query.addFilterQuery(matchField[i] + ":"
                    + ClientUtils.escapeQueryChars(matchValue[i]));
        }

        query.setStart(start);
        query.setRows(count);

        logger.info(">>>>>>>>>>>>>>>>>>>>>" + query.toString());

        List<SolrUser> companyList;

        QueryResponse response = getSolrClient().query(query);
        SolrDocumentList docs = response.getResults();

        DocumentObjectBinder binder = new DocumentObjectBinder();
        companyList = binder.getBeans(SolrUser.class, docs);

        resMap.put("total", docs.getNumFound());
        resMap.put("dataList", companyList);
        return resMap;
    }

    /**
     * 查询
     *
     * @param matchField
     * @param matchValue
     * @param filterField
     * @param filterValue
     * @param sortfield
     * @param flag
     * @param start
     * @param count
     * @param hightlight
     * @return
     * @throws SolrServerException
     * @throws IOException
     */
    public static List<SolrUser> search(String[] matchField, String[] matchValue,
                                        String[] filterField, String[] filterValue, String[] sortfield,
                                        Boolean[] flag, int start, int count, String hightlight)
            throws SolrServerException, IOException {
        if (null == matchField || null == matchValue
                || matchField.length != matchValue.length) {
            return null;
        }

        SolrQuery query = new SolrQuery();

        if (matchField[0].equals("companyName")) {
            query = new SolrQuery(matchField[0] + ":"
                    + ("*".equals(matchValue[0]) ? matchValue[0]
                    : ClientUtils.escapeQueryChars(matchValue[0])));
        } else {
            query = new SolrQuery(matchField[0] + ":"
                    + ClientUtils.escapeQueryChars(matchValue[0]));
        }

        for (int i = 1; i < matchField.length; i++) {
            query.addFilterQuery(matchField[i] + ":"
                    + ClientUtils.escapeQueryChars(matchValue[i]));
        }

        if (null != filterField && null != filterValue
                && filterField.length == filterValue.length) {
            for (int i = 0; i < filterField.length; i++) {
                query.addFilterQuery("-" + filterField[i] + ":"
                        + ClientUtils.escapeQueryChars(filterValue[i]));
            }
        }
        query.setStart(start);
        query.setRows(count);
        if (null != sortfield && null != flag
                && sortfield.length == flag.length) {
            for (int i = 0; i < sortfield.length; i++) {
                if (flag[i]) {
                    query.addSort(sortfield[i], SolrQuery.ORDER.asc);
                } else {
                    query.addSort(sortfield[i], SolrQuery.ORDER.desc);
                }
            }
        }
        if (null != hightlight) {
            query.setHighlight(true); // 开启高亮组件
            query.addHighlightField(hightlight);// 高亮字段
            query.setHighlightSimplePre("<font color=\"red\">");// 标记
            query.setHighlightSimplePost("</font>");
            query.setHighlightSnippets(1);
            query.setHighlightFragsize(1000);
        }
        List<SolrUser> companyList;
        QueryResponse response = getSolrClient().query(query);
        SolrDocumentList docs = response.getResults();
        DocumentObjectBinder binder = new DocumentObjectBinder();
        companyList = binder.getBeans(SolrUser.class, docs);
        return companyList;
    }


    public static void main(String[] args) throws Exception {

        SolrUser user = new SolrUser();
        user.setId("2");
        user.setUserName("张金磊");
        add(user);

        String[] a = new String[]{"userName"};
        String[] b = new String[]{"范国顺"};
        Map<String, Object> result = search(a, b, 0, 10);
        System.out.println(result);
    }
}
