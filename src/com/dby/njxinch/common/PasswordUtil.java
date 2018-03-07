package com.dby.njxinch.common;

import java.util.HashMap;
import java.util.Map;

import com.dby.njxinch.util.StringUtils;


public class PasswordUtil {

    public enum EncryptType {
        /**
         * 不加密
         */
        NONT((short)0),
        /**
         * md5加密
         */
        MD5((short)1),
        /**
         * md5密钥在前加密
         */
        MD5_AFTER_KEY((short)11),
        /**
         * md5密钥在后加密
         */
        MD5_LOST_KEY((short)12),
        /**
         * des加密
         */
        DES((short)2),
        /**
         * 自我分配
         */
        AUTO((short)-1),
        /**
         * 随机分配
         */
        RANDOM_AUTO((short)-11);

        // 定义私有变量
        private short nCode;

        // 构造函数，枚举类型只能为私有
        private EncryptType( short _nCode) {
            this . nCode = _nCode;
        }


        public short getValue() {
            return this.nCode;
        }
    };
    public enum key{
        TYPE,PASSWORD,KEY;
    }


    public static boolean decryptPassword(String pwd, String vpwd, short type, String key) throws Exception{
        String pwd2=null;
        if(EncryptType.NONT.getValue()==type){
            pwd2=(String)parsePassowrdNONT(vpwd).get(PasswordUtil.key.PASSWORD);
        }else if(EncryptType.MD5.getValue()==type){
            pwd2=(String)parsePassowrdMD5(vpwd).get(PasswordUtil.key.PASSWORD);
        }else if(EncryptType.MD5_AFTER_KEY.getValue()==type){
            pwd2=(String)parsePassowrdAfterKEY(vpwd,key).get(PasswordUtil.key.PASSWORD);
        }else if(EncryptType.MD5_LOST_KEY.getValue()==type){
            pwd2=(String)parsePassowrdLostKEY(vpwd,key).get(PasswordUtil.key.PASSWORD);
        }else if(EncryptType.DES.getValue()==type){
            pwd2=(String)parsePassowrdDES(vpwd,key).get(PasswordUtil.key.PASSWORD);
        }else if(EncryptType.AUTO.getValue()==type){
            pwd2=(String)parsePassowrdAfterKEY(vpwd,key).get(PasswordUtil.key.PASSWORD);
        }

        if(pwd.equals(pwd2)){
            return true;
        }
        return false;
    }



    public static Map<key,Object> encryptPassword(String password, EncryptType ty)  throws Exception{
        return encryptPassword(password,ty,null);
    }

    public static Map<key,Object> encryptPassword(String password, EncryptType ty,String key)  throws Exception{
        switch (ty) {
            case NONT:
                return parsePassowrdNONT(password);
            case MD5:
                return parsePassowrdMD5(password);
            case MD5_AFTER_KEY:
                return parsePassowrdAfterKEY(password);
            case MD5_LOST_KEY:
                return parsePassowrdLostKEY(password);
            case DES:
                return parsePassowrdDES(password,key);
            case AUTO:
                return parsePassowrdAfterKEY(password);
            case RANDOM_AUTO:
                return parsePassowrdRandomKEY(password);
            default:
                return null;
        }
    }

    private static Map<key, Object> parsePassowrdRandomKEY(String password)  throws Exception{
        EncryptType[] et=new EncryptType[]{ EncryptType.NONT, EncryptType.MD5,
                EncryptType.MD5_AFTER_KEY,
                EncryptType.MD5_LOST_KEY,
                EncryptType.DES};
        int index=(int)(Math.random()*et.length);
        switch(et[index]){
            case NONT:
                return parsePassowrdNONT(password);
            case MD5:
                return parsePassowrdMD5(password);
            case MD5_AFTER_KEY:
                return parsePassowrdAfterKEY(password);
            case MD5_LOST_KEY:
                return parsePassowrdLostKEY(password);
            case DES:
                return parsePassowrdDES(password,null);
            default:
                break;
        }
        return null;
    }

    private static Map<key,Object> parsePassowrdDES(String password,String k) throws Exception{
        Map<key,Object> map=new HashMap<key,Object>();
        map.put(key.TYPE, EncryptType.DES);
        if(StringUtils.isEmpty(k)){
            k=RandomUtil.genRandomNum(12,RandomUtil.PWD_TYPE_NUM_AND_CHAR);
        }
        map.put(key.PASSWORD, new Des(k).encrypt(password));
        map.put(key.KEY,k);
        return map;
    }

    private static Map<key,Object> parsePassowrdLostKEY(String password) {
        return parsePassowrdLostKEY(password,RandomUtil.genRandomNum(10,RandomUtil.PWD_TYPE_NUM_AND_CHAR));
    }

    private static Map<key,Object> parsePassowrdLostKEY(String password,String k) {
        Map<key,Object> map=new HashMap<key,Object>();
        map.put(key.TYPE, EncryptType.MD5_LOST_KEY);
        map.put(key.PASSWORD,MD5Util.getMD5(password+k));
        map.put(key.KEY,k);
        return map;
    }

    private static Map<key,Object> parsePassowrdAfterKEY(String password) {
        return parsePassowrdAfterKEY(password,RandomUtil.genRandomNum(10,RandomUtil.PWD_TYPE_NUM_AND_CHAR));
    }

    private static Map<key,Object> parsePassowrdAfterKEY(String password,String k) {
        Map<key,Object> map=new HashMap<key,Object>();
        map.put(key.TYPE, EncryptType.MD5_AFTER_KEY);
        map.put(key.PASSWORD,MD5Util.getMD5(k+password));
        map.put(key.KEY,k);
        return map;
    }

    public static Map<key,Object> parsePassowrdMD5(String password) {
        Map<key,Object> map=new HashMap<key,Object>();
        map.put(key.TYPE, EncryptType.MD5);
        map.put(key.PASSWORD,MD5Util.getMD5(password));
        return map;
    }

    private static Map<key,Object> parsePassowrdNONT(String password) {
        Map<key,Object> map=new HashMap<key,Object>();
        map.put(key.TYPE, EncryptType.NONT);
        map.put(key.PASSWORD,password);
        return map;
    }


    public static EncryptType getEncrypt(short ty)
    {
        switch (ty) {
            case 0:
                return EncryptType.NONT;
            case 1:
                return EncryptType.MD5;
            case 11:
                return EncryptType.MD5_AFTER_KEY;
            case 12:
                return EncryptType.MD5_LOST_KEY;
            case 2:
                return EncryptType.DES;
            case -1:
                return EncryptType.AUTO;
            case -11:
                return EncryptType.RANDOM_AUTO;
            default:
                return null;
        }
    }


    public static void main(String[] args){
        System.out.println(PasswordUtil.parsePassowrdAfterKEY("1","qpecsp0zjc").get(PasswordUtil.key.PASSWORD));
    }

}
