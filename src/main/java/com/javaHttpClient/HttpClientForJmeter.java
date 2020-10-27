package com.javaHttpClient;
import com.tool.*;
import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;

import java.security.NoSuchAlgorithmException;


/**
 * Created by sunh on 2020/10/24.
 */
public class HttpClientForJmeter implements JavaSamplerClient{
    //URLNAME 就是在图形化界面当中显示的变量名称
    private static final String isGateway = "isGateway";
    //是否过网关，0是过，1是不过
    private static final String DEFAULTisGateway = "0";

    private static final String URLNAME = "URL";
    //设置界面当中默认显示的变量的值
    private static final String DEFAULTURL = "${url}";

    //用户AccessKey
    private static final String accessKeyNAME = "accessKey";
    private static final String DEFAULTaccessKey = "${accessKey}";

    //用户SecretKey
    private static final String secretKeyNAME = "secretKey";
    private static final String DEFAULTsecretKey = "${secretKey}";

    //API服务的路径
    private static final String pathNAME = "path";
    private static final String DEFAULTpath = "/api/rp-cert/v1/id-cards/verifications";

    //接口请求方式，请注意字母全部为大写
    private static final String methodNAME = "method";
    private static final String DEFAULTmethod = "POST";

    //请求参数
    private static final String body = "body";
    private static final String DEFAULTbody= "null";


    /**
     * 这个方法决定了在jmeter当中要显示哪些属性
     * @return arguments
     */
    //用来存储响应的数据，目的是将响应结果放到察看结果树当中
    private String resultData;

    public Arguments getDefaultParameters() {
        Arguments arguments = new Arguments();
        arguments.addArgument(isGateway,DEFAULTisGateway);
        arguments.addArgument(URLNAME,DEFAULTURL);
        arguments.addArgument(accessKeyNAME,DEFAULTaccessKey);
        arguments.addArgument(secretKeyNAME,DEFAULTsecretKey);
        arguments.addArgument(pathNAME,DEFAULTpath);
        arguments.addArgument(methodNAME,DEFAULTmethod);
        arguments.addArgument(body,DEFAULTbody);
        return arguments;
    }

    private String inputGateway;
    private String inputUrl;
    private String inputAccessKey;
    private String inputSecretKey;
    private String inputPath;
    private String inputmethod;
    private String inputbody;

    /**
     * 这个方法就是一个初始化方法，我们所有的初始化的动作都可以在这里写
     * @param javaSamplerContext
     */
    public void setupTest(JavaSamplerContext javaSamplerContext) {
        inputGateway = javaSamplerContext.getParameter(isGateway,DEFAULTisGateway);
        inputUrl = javaSamplerContext.getParameter(URLNAME,DEFAULTURL);
        inputAccessKey = javaSamplerContext.getParameter(accessKeyNAME,DEFAULTaccessKey);
        inputSecretKey = javaSamplerContext.getParameter(secretKeyNAME,DEFAULTsecretKey);
        inputPath = javaSamplerContext.getParameter(pathNAME,DEFAULTpath);
        inputmethod = javaSamplerContext.getParameter(methodNAME,DEFAULTmethod);
        inputbody = javaSamplerContext.getParameter(body,DEFAULTbody);
    }

    /**
     * 这个方法就是实现你具体功能逻辑的方法
     * @param javaSamplerContext
     * @return
     */
    public SampleResult runTest(JavaSamplerContext javaSamplerContext) {
        SampleResult result = new SampleResult();
//        ClientWithResponseHandler Client = new ClientWithResponseHandler();
        ClientWithResponseHandler Client = new ClientWithResponseHandler();
        result.sampleStart();//标记事务的开始
        getUrl testurl = new getUrl();
        String url = null;
        try {
            switch (inputmethod){
                case "GET":
                    switch (inputGateway){
                        case "0":
                            url = testurl.getRequestUrl(inputAccessKey, inputSecretKey, inputUrl, inputPath, inputmethod,inputbody);
                            break;
                        case "1":
                            url = inputUrl+inputPath;
                            break;
                    }
                    resultData = Client.doGet(url);
                    break;
                case "POST":
                    switch (inputGateway){
                        case "0":
                            url = testurl.getRequestUrl(inputAccessKey, inputSecretKey, inputUrl, inputPath, inputmethod,null);
                            break;
                        case "1":
                            url = inputUrl+inputPath;
                            break;
                    }
                    resultData = Client.doPost(url, inputbody);
                    break;
                default:
                    resultData = "请求方法有误,目前支持GET,POST,注意大写";
                    break;
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (resultData == "请求失败，再试一遍" || resultData == "请求方法有误,目前支持GET,POST,注意大写"){
            result.setSuccessful(false);
        }else {
            result.setSuccessful(true);//告诉查看结果树访问是否成功
        }
//        result.setSampleLabel("自定义java请求访问");
        result.setResponseData(resultData,"utf-8");
        result.setDataType(SampleResult.TEXT);
        return result;
    }


    /**
     * 这个方法就是来做一些收尾的工作的。
     * @param javaSamplerContext
     */
    public void teardownTest(JavaSamplerContext javaSamplerContext) {

    }

}
