package com.dby.njxinch.util;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import com.thoughtworks.xstream.io.path.Path;


public class CommonUtil {
	/**
	 * 功能：根据当前时间和文件名称获取新文件名
	 * 
	 * @return 新文件名
	 */
	public static String getFileName() {
		SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		java.util.Date date = new java.util.Date();
		String datename = String.valueOf(bartDateFormat.format(date));
		/*
		 * String nameFile = datename + s.substring(s.indexOf("."),
		 * s.length()).toLowerCase();
		 */
		return datename;
	}

	/**
	 * 日期
	 */
	public static String getDate() {

		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = format.format(new Date());
		return date;
	}
	

	/**
	 * 日期
	 */
	public static String getDateForDate(Date formatdate) {

		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String date = format.format(formatdate);
		return date;
	}

	/**
	 * 日期
	 */
	public static String getDateForDate2(Date formatdate) {

		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String date = format.format(formatdate);
		return date;
	}

    /**
     * 日期
     */
    public static String getDate14() {

        DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String date = format.format(new Date());
        return date;
    }

	/**
	 * 删除文件
	 * 
	 * @param delpath
	 */
	public void deleteFile(String delpath) {
		File file = new File(delpath);
		if (file.exists()) {
			file.delete();
		}
	}
	/**
	 * 创建文件夹
	 * 
	 * @param path
	 */
	public void createFile(String path) {
		File file = new File(path);
		// 如果文件夹不存在则创建
		if (!file.exists() && !file.isDirectory()) {
			System.out.println("//不存在");

			file.mkdir();
		} else {
			System.out.println("//目录存在");
		}

	}


    /**
     * 上传图片文件
     * @param path 工程运行目录
     * @param type 图片类型
     * @param fileName 图片名称
     * @param file 图片文件
     * @throws IOException
     */
    public static void  createImgFile(String path,String type,String fileName,MultipartFile file)throws IOException{

//        Path realPath = Paths.get(path, type);
//
//        if (!Files.exists(realPath)){
//            Files.createDirectories(realPath);
//        }
//        realPath = realPath.resolve(Paths.get(fileName));
//        if (Files.exists(realPath)){
//            Files.delete(realPath);
//        }
//        file.transferTo(realPath.toFile());
    }

    /**
     * 修改文件名称
     * @param path 工程运行目录
     * @param type 图片类型
     * @param fileName 图片名称
     * @param oldName 原文件名
     * @throws IOException
     */
    public static void  changImgFile(String path,String type,String fileName, String oldName)throws IOException{

//        Path realPath = Paths.get(path, type);
//
//        if (!Files.exists(realPath)){
//            Files.createDirectories(realPath);
//        }
//        realPath = realPath.resolve(Paths.get(oldName+".jpg"));
//        if (Files.exists(realPath)){
//            Path newPath =  Paths.get(path, type,fileName+".jpg");
//            Files.copy(realPath, newPath);
//            Files.delete(realPath);
//        }
    }
	public static boolean isBlank(String str){
		return (str==null|| str.trim().length()==0);
	}

    public static boolean isNumber(String str){
        try {
            Integer.parseInt(str);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }
    
    public static boolean isDouble(String str){
        try {
            Double.parseDouble(str);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }
    
    /**
     * 日期
     */
    public static String getDate17() {

        DateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String date = format.format(new Date());
        return date;
    }
    
    public static String getRandom(){
		String str = "";
		for (int i = 0; i < 10; i++) {
			str  = str + String.valueOf((int) (10*Math.random()));
		}
		return str;
		
	}
}
