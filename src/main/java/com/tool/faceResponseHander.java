package com.tool;

import com.chinamobile.bcop.api.sdk.exception.ClientException;
import com.chinamobile.bcop.api.sdk.exception.ServerException;
import com.chinamobile.cmss.rpc.sdk.RpCertClient;
import com.chinamobile.cmss.rpc.sdk.constant.Region;
import com.chinamobile.cmss.rpc.sdk.request.FaceCertificationRequest;
import com.chinamobile.cmss.rpc.sdk.request.FaceComparisonRequest;
import com.chinamobile.cmss.rpc.sdk.request.FaceSilentDetectionRequest;
import com.chinamobile.cmss.rpc.sdk.request.IdCardVerificationRequest;
import com.chinamobile.cmss.rpc.sdk.response.RpCertResponse;



public class faceResponseHander {
    /**
     * 身份信息查验接口
     * @param body
     * @param AccessKey
     * @param SecretKey
     * @return
     * @throws ServerException
     * @throws ClientException
     */
    public Integer doIdCard(String body, String AccessKey, String SecretKey) throws ServerException, ClientException {
        RpCertClient client = new RpCertClient(Region.POOL_ZZ, AccessKey, SecretKey);
        IdCardVerificationRequest request = new IdCardVerificationRequest();
        String Body1 = body.substring(1, body.length() - 1);
        String[] Body2 = Body1.split(",");
        for (int i = 0; i <= Body2.length - 1; i++) {
            String[] Body3 = Body2[i].split(":");
            String key = Body3[0].substring(1, Body3[0].length() - 1);
            String value = Body3[1].substring(1, Body3[1].length() - 1);
            switch (key) {
                case "id":
                    request.setId(value);
                    continue;
                case "name":
                    request.setName(value);
                    continue;
                case "sex":
                    request.setSex(value);
                    continue;
                case "nation":
                    request.setNation(value);
                    continue;
                case "birthday":
                    request.setBirthday(value);
                    continue;
                case "address":
                    request.setAddress(value);
                    continue;
                case "issuingAuthority":
                    request.setIssuingAuthority(value);
                    continue;
                case "effectiveDate":
                    request.setEffectiveDate(value);
                    continue;
                case "expiredDate":
                    request.setExpiredDate(value);
                    continue;
                case "frontSide":
                    request.setFrontSide(value);
                    continue;
                case "rearSide":
                    request.setRearSide(value);
                    continue;
            }
        }
        RpCertResponse response = client.execute(request);
        return response.getResult();
    }


    /**
     * 人像比对接口
     * @param body
     * @param AccessKey
     * @param SecretKey
     * @return
     * @throws ServerException
     * @throws ClientException
     */
    public Integer doFaceComparisonRequest(String body, String AccessKey, String SecretKey) throws ServerException, ClientException {
        RpCertClient client = new RpCertClient(Region.POOL_ZZ, AccessKey, SecretKey);
        FaceComparisonRequest request = new FaceComparisonRequest();
        String Body1 = body.substring(1, body.length() - 1);
        String[] Body2 = Body1.split(",");
        for (int i = 0; i <= Body2.length - 1; i++) {
            String[] Body3 = Body2[i].split(":");
            String key = Body3[0].substring(1, Body3[0].length() - 1);
            String value = Body3[1].substring(1, Body3[1].length() - 1);
            switch (key) {
                case "id":
                    request.setId(value);
                    continue;
                case "name":
                    request.setName(value);
                    continue;
                case "sex":
                    request.setPicture(value);
                    continue;
            }
        }
        RpCertResponse response = client.execute(request);
        return response.getResult();
    }


    /**
     * 静默活体检测接口
     * @param body
     * @param AccessKey
     * @param SecretKey
     * @return
     * @throws ServerException
     * @throws ClientException
     */
    public Integer doFaceSilentDetectionRequest(String body, String AccessKey, String SecretKey) throws ServerException, ClientException {
        RpCertClient client = new RpCertClient(Region.POOL_ZZ, AccessKey, SecretKey);
        FaceSilentDetectionRequest request = new FaceSilentDetectionRequest();
        String Body1 = body.substring(1, body.length() - 1);
        String[] Body2 = Body1.split(",");
        for (int i = 0; i <= Body2.length - 1; i++) {
            String[] Body3 = Body2[i].split(":");
            String key = Body3[0].substring(1, Body3[0].length() - 1);
            String value = Body3[1].substring(1, Body3[1].length() - 1);
            switch (key) {
                case "id":
                    request.setId(value);
                    continue;
                case "name":
                    request.setName(value);
                    continue;
                case "sex":
                    request.setPicture(value);
                    continue;
            }
        }
        RpCertResponse response = client.execute(request);
        return response.getResult();
    }

    /**
     * 刷脸认证接口
     * @param body
     * @param AccessKey
     * @param SecretKey
     * @return
     * @throws ServerException
     * @throws ClientException
     */
    public Integer doFaceCertificationRequest(String body, String AccessKey, String SecretKey) throws ServerException, ClientException {
        RpCertClient client = new RpCertClient(Region.POOL_ZZ, AccessKey, SecretKey);
        FaceCertificationRequest request = new FaceCertificationRequest();
        String Body1 = body.substring(1, body.length() - 1);
        String[] Body2 = Body1.split(",");
        for (int i = 0; i <= Body2.length - 1; i++) {
            String[] Body3 = Body2[i].split(":");
            String key = Body3[0].substring(1, Body3[0].length() - 1);
            String value = Body3[1].substring(1, Body3[1].length() - 1);
            switch (key) {
                case "id":
                    request.setMessage(value);
                    continue;
            }
        }
        RpCertResponse response = client.execute(request);
        return response.getResult();
    }

}
