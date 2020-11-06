import com.chinamobile.bcop.api.sdk.exception.ClientException;
import com.chinamobile.bcop.api.sdk.exception.ServerException;
import com.tool.ClientWithResponseHandler;
import com.tool.ImageToBase64;
import com.tool.faceResponseHander;
import com.tool.getUrl;

public class test1 {

    public static void main(String[] args) throws ServerException, ClientException {
        String accessKey = "e38ac240e15e4fb6a572e137e4aa5bf3";
        String secretKey = "f633defdae6f4e69b2dd474a4bf35124";

        String Body = "{\"id\":\"321281199502053539\",\"name\":\"孙浩\"}";
        System.out.println(Body);
        faceResponseHander a = new faceResponseHander();
        System.out.println(a.doIdCard(Body,accessKey,secretKey));
    }
}
