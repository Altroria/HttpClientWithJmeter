package com.tool;

import com.chinamobile.bcop.api.sdk.exception.ClientException;
import com.chinamobile.bcop.api.sdk.exception.ServerException;
import com.chinamobile.cmss.rpc.sdk.RpCertClient;
import com.chinamobile.cmss.rpc.sdk.constant.Region;
import com.chinamobile.cmss.rpc.sdk.request.*;
import com.chinamobile.cmss.rpc.sdk.response.FaceCertificationResponse;
import com.chinamobile.cmss.rpc.sdk.response.RpCertResponse;



public class faceResponseHander {
    public String result = null;

    public String doFace(RpCertRequest Request, String body, String AccessKey, String SecretKey) throws ServerException, ClientException {
        RpCertClient client = new RpCertClient(Region.POOL_ZZ, AccessKey, SecretKey);

        if (Request != null) {
            RpCertResponse response = null;
            if (Request instanceof IdCardVerificationRequest) {
                IdCardVerificationRequest request = (IdCardVerificationRequest) Request;
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
                response = client.execute(request);
            }
            else if (Request instanceof FaceComparisonRequest) {
                FaceComparisonRequest request = (FaceComparisonRequest) Request;
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
                        case "picture":
                            request.setPicture(value);
                            continue;
                    }

                }
                response = client.execute(request);
            }
            else if (Request instanceof FaceSilentDetectionRequest) {
                FaceSilentDetectionRequest request = (FaceSilentDetectionRequest) Request;
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
                        case "picture":
                            request.setPicture(value);
                            continue;
                    }
                }
                response = client.execute(request);
            }
            result = response.getResult() + response.getState() + response.getErrorCode() + response.getErrorCode();
        } else {
            FaceCertificationRequest request = new FaceCertificationRequest();
            FaceCertificationResponse response = null;
            String Body1 = body.substring(1, body.length() - 1);
            String[] Body2 = Body1.split(",");
            for (int i = 0; i <= Body2.length - 1; i++) {
                String[] Body3 = Body2[i].split(":");
                String key = Body3[0].substring(1, Body3[0].length() - 1);
                String value = Body3[1].substring(1, Body3[1].length() - 1);
                switch (key) {
                    case "message":
                        request.setMessage(value);
                        continue;
                }
            }
            response = client.execute(request);
            result = response.getState() + response.getErrorCode() + response.getErrorCode();
        }
        return result;
    }
}
