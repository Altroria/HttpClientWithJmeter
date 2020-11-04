import com.tool.*;

import java.io.File;


public class clientTest {

    public static void main(String[] args) throws Exception {
        String accessKey = "1a65dddb209743399f5052ca6c4c7239";
        String secretKey = "e549f1d42fd442e59500bfd6c843b8e3";
        String url1 = "http://112.35.22.82:18080";
        String path = "/api/rp-cert/v1/faces/comparisons";
        String method = "POST";

        ClientWithResponseHandler test1 = new ClientWithResponseHandler();

        ImageToBase64 BS = new ImageToBase64();
        getUrl testurl = new getUrl();
        String Body = "{\"id\":\"321281199502053539\",\"name\":\"孙浩\"}";
        String url = testurl.getRequestUrl(accessKey,secretKey,url1,path,method,Body);
        String url3 = "https://www.baidu.com/";
        System.out.println(url);
//        String bs64 = BS.ImageToBase64("/Users/sunh/ZL.png");
//        String Body1 = "{ \"id\": \"321281199502053539\", \"name\": \"孙浩\", \"pictures\": {\"frontSide\":\""+bs64+"\"}}";
        System.out.println(test1.doPost(url,Body));
        }
    }


