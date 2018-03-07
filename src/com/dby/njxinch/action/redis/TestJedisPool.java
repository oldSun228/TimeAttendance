package com.dby.njxinch.action.redis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dby.njxinch.model.User;
import com.dby.njxinch.util.RedisUtil;
import com.dby.njxinch.util.SerializeUtil;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by fgs on 2016/10/21.
 */
public class TestJedisPool {
    public static void main(String[] args) {
//        JSONArray array = new JSONArray();
//        for(int i=0;i<5;i++){
//            JSONObject object = new JSONObject();
//            object.put("a","a>>>"+i);
//            object.put("b","b>>>"+i);
//            array.add(object);
//        }

        Jedis jedis = RedisUtil.getJedis();
//        String key = "testSetJsonArray";
//        jedis.set(key.getBytes(), SerializeUtil.serialize(array));

        byte[] in = jedis.get("testSetJsonArray".getBytes());
       JSONArray list = (JSONArray) SerializeUtil.deserialize(in);
        for(Object o:list){
            JSONObject object = JSON.parseObject(o.toString());
            System.out.println(object.getString("a")+"------"+object.getString("b"));
        }




//        byte[] in = jedis.get("testSetEnsemble".getBytes());
//        List<User> list = (List<User>) SerializeUtil.deserialize(in);
//        for(User user : list){
//            System.out.println(">>>>>>>" + user.getAccount()+","+user.getPassword());
//        }
    }

    public static List<User> buildTestData() {
        User a = new User();
        a.setAccount("a");
        a.setPassword("123");
        User b = new User();
        b.setAccount("b");
        b.setPassword("456");
        List<User> list = new ArrayList<User>();
        list.add(a);
        list.add(b);
        return list;
    }

    public static void testSetEnsemble(){
        List<User> testData = buildTestData();
        Jedis jedis = RedisUtil.getJedis();
        String key = "testSetEnsemble";
        jedis.set(key.getBytes(), SerializeUtil.serialize(testData));

        //验证
        byte[] in = jedis.get("testSetEnsemble".getBytes());
        List<User> list = (List<User>) SerializeUtil.deserialize(in);
        for(User user : list){
            System.out.println(">>>>>>>" + user.getAccount());
        }
    }
}
