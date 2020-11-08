import com.chinamobile.bcop.api.sdk.exception.ClientException;
import com.chinamobile.bcop.api.sdk.exception.ServerException;
import com.chinamobile.cmss.rpc.sdk.RpCertClient;
import com.chinamobile.cmss.rpc.sdk.constant.Region;
import com.chinamobile.cmss.rpc.sdk.request.FaceCertificationRequest;
import com.chinamobile.cmss.rpc.sdk.request.FaceComparisonRequest;
import com.chinamobile.cmss.rpc.sdk.request.FaceSilentDetectionRequest;
import com.chinamobile.cmss.rpc.sdk.request.IdCardVerificationRequest;
import com.chinamobile.cmss.rpc.sdk.response.FaceCertificationResponse;
import com.chinamobile.cmss.rpc.sdk.response.RpCertResponse;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class test {
    public static void main(String[] args) throws ServerException, ClientException {
        String apiGatewayAccessKey = "e38ac240e15e4fb6a572e137e4aa5bf3";
        String apiGatewaySecretKey = "f633defdae6f4e69b2dd474a4bf35124";
        RpCertClient client = new RpCertClient(Region.POOL_ZZ, apiGatewayAccessKey, apiGatewaySecretKey);
        //刷脸认证接口
        FaceCertificationRequest request1 = new FaceCertificationRequest();
        //人像比对接口
        FaceComparisonRequest request2 = new FaceComparisonRequest();
        //静默活体检测接口  /api/rp-cert/v1/faces/silent-detections
        FaceSilentDetectionRequest request3 = new FaceSilentDetectionRequest();
        //身份信息查验接口 /api/rp-cert/v1/id-cards/verifications
        IdCardVerificationRequest request = new IdCardVerificationRequest();

//        request.setId("321281199502053539");
//        request.setName("孙浩");
        request1.setMessage("mess");
        FaceCertificationResponse response = client.execute(request1);
        System.out.println(response.getMessage());
        System.out.println(response.getErrorCode());
        System.out.println(response.getErrorMessage());
    }
}

