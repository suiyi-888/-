package com.changgou.util;

import com.changgou.file.FastDFSFile;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;

import org.springframework.core.io.ClassPathResource;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FastDFSClient {
    /***
     * 初始化tracker信息
     */
    static {
        try {
            //获取tracker的配置文件fdfs_client.conf的位置
            String filePath = new ClassPathResource("fdfs_client.conf").getPath();
            //加载tracker配置信息
            ClientGlobal.init(filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String[] upload(FastDFSFile file){

        NameValuePair[] meta_list = new NameValuePair[1];
        meta_list[0]=new NameValuePair(file.getAuthor());
        String [] uploadResults=null;
        try {
            TrackerClient trackerClient = new TrackerClient();
            TrackerServer trackerServer = trackerClient.getConnection();
            StorageClient storageClient = new StorageClient(trackerServer, null);
            uploadResults = storageClient.upload_file(file.getContent(), file.getExt(), meta_list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return uploadResults;
    }

    public static FileInfo getFile(String groupName,String remoteFileName){
        try {

            TrackerClient trackerClient = new TrackerClient();
            TrackerServer trackerServer = trackerClient.getConnection();
            StorageClient storageClient = new StorageClient(trackerServer, null);
            return storageClient.get_file_info(groupName,remoteFileName);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    //文件下载
    public static InputStream downFile(String groupName,String remoteFileName){
        try {
            TrackerClient trackerClient = new TrackerClient();
            TrackerServer trackerServer = trackerClient.getConnection();
            StorageClient storageClient = new StorageClient(trackerServer,null);
            byte[] fileByte=storageClient.download_file(groupName,remoteFileName);
            return new ByteArrayInputStream(fileByte);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void deleteFile(String groupName,String remoteFileName){
        try {
            TrackerClient trackerClient = new TrackerClient();
            TrackerServer trackerServer = trackerClient.getConnection();
            StorageClient storageClient = new StorageClient(trackerServer, null);
            storageClient.delete_file(groupName,remoteFileName);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //获取组信息
    public static StorageServer getStorages(String groupName){
        try {
            TrackerClient trackerClient = new TrackerClient();
            TrackerServer trackerServer = trackerClient.getConnection();
            return trackerClient.getStoreStorage(trackerServer,groupName);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //组名 存储路径 IP 端口信息
    public static ServerInfo[] getServerInfo(String groupName,String remoteFileName){
        try {
            TrackerClient trackerClient = new TrackerClient();
            TrackerServer trackerServer = trackerClient.getConnection();
            return trackerClient.getFetchStorages(trackerServer,groupName,remoteFileName);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }

    //获取Tracker服务地址
    public static String getTrackerUrl(){
        try {
            TrackerClient trackerClient = new TrackerClient();
            TrackerServer trackerServer = trackerClient.getConnection();
            return "http://"+trackerServer.getInetSocketAddress().getHostString()+":"+ClientGlobal.getG_tracker_http_port();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //优化  获取trackerserver
    public static TrackerServer getTrackerServer()throws Exception{
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        return trackerServer;
    }

    //获取storageclient 存储客户端
    public static StorageClient getStorageClient()throws Exception{
        TrackerServer trackerServer = getTrackerServer();
        StorageClient storageClient = new StorageClient(trackerServer, null);
        return storageClient;
    }
}
