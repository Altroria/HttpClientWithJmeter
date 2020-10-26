import com.tool.*;

import java.io.File;


public class clientTest {

    public static void main(String[] args) throws Exception {
        String accessKey = "c4bf2bce0bd4468c92adbdcf6dde48ab";
        String secretKey = "3ff3cb1cc81f4e1e9b32ffbd248ee637";
        String url1 = "https://api-zhengzhou-1.cmecloud.cn:8443";
        String path = "/api/rp-cert/v1/id-cards/verifications";
        String method = "POST";

        ClientWithResponseHandler test1 = new ClientWithResponseHandler();
        ResponseHandler test2 = new ResponseHandler();
        ImageToBase64 BS = new ImageToBase64();
        getUrl testurl = new getUrl();
        String Body = "{\"id\":\"321281199502053539\",\"name\":\"孙浩\",\"sex\":\"0\"  }";
        String url = testurl.getRequestUrl(accessKey,secretKey,url1,path,method,null);
        String url3 = "https://www.baidu.com/";

        path = new File(".").getCanonicalPath();
        System.out.println("根路径" + path +"\\my.keystore");

//        String bs64 = BS.ImageToBase64("/Users/sunh/ZL.png");
//        String Body1 = "{ \"id\": \"321281199502053539\", \"name\": \"孙浩\", \"pictures\": {\"frontSide\":\""+bs64+"\"}}";
        System.out.println(test2.doPost(url,Body));
        }
    }


