package com.biao.weiboemotionclassing.tools;

import com.biao.weiboemotionclassing.entities.Comment;
import com.biao.weiboemotionclassing.entities.Comment_fenci_storeString;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 本类集成对txt文件进行操作的所有工具函数
 *
 */
public class TxtFileOperation {

    /**
     * 返回 Map 形式的特征词集合:Map<String, Double>
     * @param path
     * @return
     */
    public static Map<String, Double> readFeatureSetFile(String path) {
        Map<String, Double> featureList = new HashMap<>();
        List<String> list = TxtFileOperation.readAllLinesWithContent(path);
        for (int i=0;i<list.size();i++){
//            System.out.println(list.get(i));
            String[] li = list.get(i).split(":");
            featureList.put(li[0], new Double(li[1]));
        }
        return featureList;
    }

    /**
     * 返回 Map 形式的特征词集合:Map<String, Integer>
     * @param path
     * @return
     */
    public static Map<String, Integer> readFeatureSetFile_Str_Int(String path) {
        Map<String, Integer> featureList = new HashMap<>();
        List<String> list = TxtFileOperation.readAllLinesWithContent(path);
        for (int i=0;i<list.size();i++){
//            System.out.println(list.get(i));
            String[] li = list.get(i).split(":");
            featureList.put(li[0], new Integer(li[1]));
        }
        return featureList;
    }

    /**
     * 返回指定条数的评论
     * @param filename ：文件名
     * @param lines ：需要返回的评论条数
     * @return
     */
    public static List<Comment> readFileByLines(String filename, int lines){

        List<Comment> comments = new ArrayList<>();

        File file = new File(filename);

        BufferedReader bufferedReader = null;

        Comment comment;
        try{
            System.out.println("以行为单位，一次读取一行");
            bufferedReader = new BufferedReader(new FileReader(file));
            String tempString;
            int li = 1;
            while ((tempString = bufferedReader.readLine()) != null && li <= lines){
                // 显示行号
                System.out.println("li " + li + " : " + tempString);
                li++;

                //插入队列
                comment = new Comment(li, "厌恶", tempString);
                comments.add(comment);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if(bufferedReader != null){
                try {
                    bufferedReader.close();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }

        return comments;
    }

    /**
     * 返回字符串集合，每行为一个字符串元素
     * @param filename ：文件名
     * @return
     */
    public static List<String> readAllLinesWithContent(String filename){

        List<String> comments = new ArrayList<>();

        File file = new File(filename);

        BufferedReader bufferedReader = null;

        try{
//            System.out.println("以行为单位，一次读取一行");
            bufferedReader = new BufferedReader(new FileReader(file));
            String tempString;
            int line = 1;
            while ((tempString = bufferedReader.readLine()) != null) {
                // 显示行号
//                System.out.println("line " + line + " : " + tempString);
                line++;

                //插入队列
                comments.add(tempString);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if(bufferedReader != null){
                try {
                    bufferedReader.close();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }

        return comments;
    }

    /**
     * 将 Comment_fenci_storeString 对象集合的分词后的字符串存入文件
     * @param comment_fenci_storeStrings
     * @param path
     */
    public static void saveAsFileWithComment_fenci_storeString(List<Comment_fenci_storeString> comment_fenci_storeStrings, String path) {
        try {
            File file = new File(path);

            //if file do not exists, then create it
            if(!file.exists()){
                file.createNewFile();
            } else {
                //if file has been existed, delete it ,then create a new file
                file.delete();
                file.createNewFile();
            }

            for (Comment_fenci_storeString comment_fenci_storeString : comment_fenci_storeStrings){
                //FileWrite的第二个参数为true， 代表在文件后面追加而不覆盖
                FileWriter fileWriter = new FileWriter(file.getAbsoluteFile(), true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(comment_fenci_storeString.getComments_fenci().toString() + "\r\n");
                bufferedWriter.close();
            }

            System.out.println("save As File With Comment_fenci_storeString Done!");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 将传入的一组评论按行存入文件中
     * @param comments
     * @param path
     */
    public static void saveAsFileByLines(List<Comment> comments, String path){
        try {
            File file = new File(path);

            //if file do not exists, then create it
            if(!file.exists()){
                file.createNewFile();
            } else {
                //if file has been existed, delete it ,then create a new file
                file.delete();
                file.createNewFile();
            }

            for (Comment comment : comments){
                //FileWrite的第二个参数为true， 代表在文件后面追加而不覆盖
                FileWriter fileWriter = new FileWriter(file.getAbsoluteFile(), true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(comment.getContent().toString() + "\r\n");
                bufferedWriter.close();
            }

            System.out.println("Done!");

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * 存储一组字符串
     * @param strings
     * @param path
     */
    public static void saveAsFileWithContent(List<String> strings, String path){
        try {
            File file = new File(path);

            //if file do not exists, then create it
            if(!file.exists()){
                file.createNewFile();
            } else {
                //if file has been existed, delete it ,then create a new file
                file.delete();
                file.createNewFile();
            }

            for (String string : strings){
                //FileWrite的第二个参数为true， 代表在文件后面追加而不覆盖
                FileWriter fileWriter = new FileWriter(file.getAbsoluteFile(), true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(string + "\r\n");
                bufferedWriter.close();
            }

            System.out.println("String Store Done!");

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * 将传过来的map形式的特征词集合存到文件中
     * @param tops
     * @param s
     */
    public static void saveAsFileWithMaps(Map<String, Double> tops, String filepath, boolean isaddFeatureWeight) {
        List<String> features = new ArrayList<>();
        //遍历键值，即特征词
        for (String key : tops.keySet()) {
            if (isaddFeatureWeight) {
                features.add(key + ":" + tops.get(key));
            } else {
                features.add(key);
            }
        }
        saveAsFileWithContent(features, filepath);
    }

    /**
     * 将传过来的map形式的特征词集合存到文件中
     * @param tops
     * @param s
     */
    public static void saveAsFileWithMaps_Str_Int(Map<String, Integer> tops, String filepath, boolean isaddFeatureWeight) {
        List<String> features = new ArrayList<>();
        //遍历键值，即特征词
        for (String key : tops.keySet()) {
            if (isaddFeatureWeight) {
                features.add(key + ":" + tops.get(key));
            } else {
                features.add(key);
            }
        }
        saveAsFileWithContent(features, filepath);
    }
}
