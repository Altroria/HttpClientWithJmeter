package com.tool;


import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;


import org.apache.http.HttpEntity;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.methods.*;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.apache.log.Logger;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;


public class ClientWithResponseHandler {
    /**
     * 绕过验证
     *
     * @return
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     */
    public static CloseableHttpClient createIgnoreVerifySSLClient() throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext sslcontext = SSLContext.getInstance("SSLv3");
        // 实现一个X509TrustManager接口，用于绕过验证，不用修改里面的方法
        X509TrustManager trustManager = new X509TrustManager() {
            @Override
            public void checkClientTrusted(
                    java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
                    String paramString) throws CertificateException {
            }

            @Override
            public void checkServerTrusted(
                    java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
                    String paramString) throws CertificateException {
            }

            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };

        sslcontext.init(null, new TrustManager[] { trustManager }, null);

        //设置协议http和https对应的处理socket链接工厂的对象
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", new SSLConnectionSocketFactory(sslcontext))
                .build();
        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);

        //创建自定义的httpclient对象
        CloseableHttpClient client = HttpClients.custom()
                .setConnectionManager(connManager)

                .setRetryHandler((exception, executionCount, context) -> {
                    if (executionCount > 2) {
                        return false;
                    }
                    if (exception instanceof NoHttpResponseException     //NoHttpResponseException 重试
                    ) {
                        return true;
                    }
                    return false;
                })
                .build();
        return client;
    }

    /**
     * Get请求
     * @param url
     * @return
     * @throws Exception
     */
    public  String doGet(String url) throws Exception {

        String result = "";

        //采用绕过验证的方式处理https请求
        CloseableHttpClient client = createIgnoreVerifySSLClient();

        try{
            //创建get方式请求对象
            HttpGet get = new HttpGet(url);

            //指定报文头Content-type、User-Agent
            get.setHeader("Content-type", "application/json");
            get.setHeader("Connection", "Keep-Alive");

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
        //采用绕过验证的方式处理https请求
        CloseableHttpClient client = createIgnoreVerifySSLClient();

        try{
            //创建post方式请求对象
            HttpPost httpPost = new HttpPost(url);
            //指定报文头Content-type、User-Agent
            httpPost.setHeader("Content-type", "application/json");
            httpPost.setHeader("Connection", "Keep-Alive");
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

    /**
     * Put请求
     * @param url
     * @param postBody
     * @return
     * @throws Exception
     */
    public String doPut(String url, String postBody) throws Exception {
        String result = "";
        //采用绕过验证的方式处理https请求
        CloseableHttpClient client = createIgnoreVerifySSLClient();

        try{
            //创建post方式请求对象
            HttpPut httpPut = new HttpPut(url);
            //指定报文头Content-type、User-Agent
            httpPut.setHeader("Content-type", "application/json");
            httpPut.setHeader("Connection", "Keep-Alive");
            //设置body
            httpPut.setEntity(new StringEntity(postBody, "utf-8"));

            //执行请求操作，并拿到结果（同步阻塞）
            CloseableHttpResponse response = client.execute(httpPut);
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

    /**
     * Delete请求
     * @param url
     * @param
     * @return
     * @throws Exception
     */
    public String doDelete(String url) throws Exception {
        String result = "";
        //采用绕过验证的方式处理https请求
        CloseableHttpClient client = createIgnoreVerifySSLClient();

        try{
            //创建post方式请求对象
            HttpDelete httpDelete = new HttpDelete(url);
            //指定报文头Content-type、User-Agent
            httpDelete.setHeader("Content-type", "application/json");
            httpDelete.setHeader("Connection", "Keep-Alive");

            //执行请求操作，并拿到结果（同步阻塞）
            CloseableHttpResponse response = client.execute(httpDelete);
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