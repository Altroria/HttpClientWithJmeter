package com.tool;


import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;


public class ResponseHandler{
    public static CloseableHttpClient creatSSLClient() throws Exception {
        String storPath = new File(".").getCanonicalPath()+"\\my.keystore";
        KeyStore keyStore = KeyStore.getInstance("JKS");
        keyStore.load(new FileInputStream(new File(storPath)), "123456".toCharArray());
        SSLContext sslcontext = SSLContexts.custom()
                //忽略掉对服务器端证书的校验
                .loadTrustMaterial(new TrustStrategy() {
                    @Override
                    public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                        return true;
                    }
                })
                .loadKeyMaterial(keyStore, "cmcc".toCharArray())
                .build();
        SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(
                sslcontext,
                new String[]{"TLSv1"},
                null,
                SSLConnectionSocketFactory.getDefaultHostnameVerifier());
        CloseableHttpClient client = HttpClients.custom()
                .setSSLSocketFactory(sslConnectionSocketFactory)
                .build();

        return client;

    }
    /**
     * Get请求
     * @param url
     * @return
     * @throws Exception
     */

    public  String doGet(String url) throws Exception  {
        String result = "";
        CloseableHttpClient client = null;
        client = creatSSLClient();

        try{
            //创建get方式请求对象
            HttpGet get = new HttpGet(url);

            //指定报文头Content-type、User-Agent
            get.setHeader("Content-type", "application/json");

            //执行请求操作，并拿到结果（同步阻塞）
            CloseableHttpResponse response = client.execute(get);

            //获取结果实体
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                //按指定编码转换结果实体为String类型
                result = EntityUtils.toString(entity, "UTF-8");
            }
            EntityUtils.consume(entity);
            //释放链接
            response.close();
        } catch (Exception e){
            e.printStackTrace();
            result = "请求失败，再试一遍";
        }
        finally{
            client.close();
        }
        return result;
    }

    /**
     * Post请求
     * @param url
     * @param postBody
     * @return
     * @throws Exception
     */
    public String doPost(String url, String postBody) throws Exception {
        String result = "";
        CloseableHttpClient client = null;
        client = creatSSLClient();
//        CloseableHttpClient client = HttpClients.createDefault();
        try{
            //创建post方式请求对象
            HttpPost httpPost = new HttpPost(url);
            //指定报文头Content-type、User-Agent
            httpPost.setHeader("Content-type", "application/json");
            //设置body
            httpPost.setEntity(new StringEntity(postBody, "utf-8"));

            //执行请求操作，并拿到结果（同步阻塞）
            CloseableHttpResponse response = client.execute(httpPost);
            //获取结果实体
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                //按指定编码转换结果实体为String类型
                result = EntityUtils.toString(entity, "UTF-8");
            }
            EntityUtils.consume(entity);
            //释放链接
            response.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        finally{
            client.close();
        }
        return result;
    }

}