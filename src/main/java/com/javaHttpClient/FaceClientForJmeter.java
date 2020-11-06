package com.javaHttpClient;

import com.chinamobile.bcop.api.sdk.exception.ClientException;
import com.chinamobile.bcop.api.sdk.exception.ServerException;
import com.chinamobile.cmss.rpc.sdk.RpCertClient;
import com.chinamobile.cmss.rpc.sdk.constant.Region;
import com.chinamobile.cmss.rpc.sdk.request.IdCardVerificationRequest;
import com.chinamobile.cmss.rpc.sdk.request.RpCertRequest;
import com.chinamobile.cmss.rpc.sdk.response.RpCertResponse;
import com.tool.*;
import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.*;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by sunh on 2020/10/24.
 */
public class FaceClientForJmeter implements JavaSamplerClient{

//    private static final String URLNAME = "URL";
//    //设置界面当中默认显示的变量的值
//    private static final String DEFAULTURL = "${url}";

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
    private Integer resultData;

    public Arguments getDefaultParameters() {
        Arguments arguments = new Arguments();
//        arguments.addArgument(URLNAME,DEFAULTURL);
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
//        inputUrl = javaSamplerContext.getParameter(URLNAME,DEFAULTURL).replace(" ","");
        inputAccessKey = javaSamplerContext.getParameter(accessKeyNAME,DEFAULTaccessKey).replace(" ","");
        inputSecretKey = javaSamplerContext.getParameter(secretKeyNAME,DEFAULTsecretKey).replace(" ","");
        inputPath = javaSamplerContext.getParameter(pathNAME,DEFAULTpath).replace(" ","");
        inputmethod = javaSamplerContext.getParameter(methodNAME,DEFAULTmethod).replace(" ","");
        inputbody = javaSamplerContext.getParameter(body,DEFAULTbody).replace(" ","");

    }

    /**
     * 这个方法就是实现你具体功能逻辑的方法
     * @param javaSamplerContext
     * @return
     */
    public SampleResult runTest(JavaSamplerContext javaSamplerContext) {
        SampleResult result = new SampleResult();
        faceResponseHander face = new faceResponseHander();
        result.sampleStart();//标记事务的开始
        try {
            switch (inputPath) {
                //身份信息查验接口
                case "/api/rp-cert/v1/id-cards/verifications":
                    resultData = face.doIdCard(inputbody, inputAccessKey, inputSecretKey);
                    break;
                //人像比对接口
                case "/api/rp-cert/v1/faces/comparisons":
                    resultData = face.doFaceComparisonRequest(inputbody, inputAccessKey, inputSecretKey);
                    break;
                //静默活体检测接口
                case "/api/rp-cert/v1/faces/silent-detections":
                    resultData = face.doFaceSilentDetectionRequest(inputbody, inputAccessKey, inputSecretKey);
                    break;
                //刷脸认证接口
                case "/api/rp-cert/v1/faces/certifications":
                    resultData = face.doFaceCertificationRequest(inputbody, inputAccessKey, inputSecretKey);
                    break;
            }
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }

        result.setSuccessful(true);//告诉查看结果树访问是否成功
//        result.setResponseData(resultData,"utf-8");
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

