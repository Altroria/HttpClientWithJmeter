import com.tool.*;



public class clientTest {

    public static void main(String[] args) throws Exception {
        String accessKey = "c4bf2bce0bd4468c92adbdcf6dde48ab";
        String secretKey = "3ff3cb1cc81f4e1e9b32ffbd248ee637";
        String url1 = "https://api-zhengzhou-1.cmecloud.cn:8443";
        String path = "/api/rp-cert/v1/id-cards/verifications";
        String method = "POST";

        ClientWithResponseHandler test1 = new ClientWithResponseHandler();
        ImageToBase64 BS = new ImageToBase64();
        getUrl testurl = new getUrl();
        String Body = "{\"id\":\"321281199502053539\",\"name\":\"孙浩\",\"sex\":\"0\"  }";
        System.out.println(Body.getClass().toString());
        String a = "1";
        System.out.println( a == "1");
//        String Body1 = "";
//        String Body1 = Body.substring(1, Body.length() - 1);
//        String[] Body2 = Body1.split(",");
//        for (int i = 0; i <= Body2.length - 1; i++) {
//            String[] Body3 = Body2[i].split(":");
//            System.out.println(Body3[0] + Body3[1]);
//        }

//           for(int j=0;j<=Body2.length-1;j++){
//               System.out.println("开始add");
//               System.out.println(Body3[j]);
//            }


        String url = testurl.getRequestUrl(accessKey,secretKey,url1,path,method,null);
//        System.out.println(Body1);s
//        System.out.println(url);

        String bs64 = BS.ImageToBase64("/Users/sunh/ZL.png");
//        System.out.println(bs64);
        String Body1 = "{ \"id\": \"321281199502053539\", \"name\": \"孙浩\", \"pictures\": {\"frontSide\":\""+bs64+"\"}}";
//        String Body1 = "{ \"id\": \"321281199502053539\", \"name\": \"孙浩\", \"pictures\": {\"frontSide\":\" \"} }";
//
//
        System.out.println(test1.doPost(url,Body));
//        System.out.println(test1.doPost(url,Body).getClass().getName().toString());
        }
    }


