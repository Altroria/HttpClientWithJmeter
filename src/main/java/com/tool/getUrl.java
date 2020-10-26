package com.tool;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;


public class getUrl {
    /**
     *
     * @param accessKey accessKey
     * @param secretKey secretKey
     * @param url url
     * @param path path
     * @param method method
     * @return
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
   public String getRequestUrl(String accessKey,String secretKey,String url,String path,String method, String body) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {
       Map<String, String> parameters = new HashMap<>();
       SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

       if (body != null){
           //拆分字符串，获取body,put进parameters
           String Body1 = body.substring(1, body.length() - 1);
           String[] Body2 = Body1.split(",");
           for (int i = 0; i <= Body2.length - 1; i++) {
               String[] Body3 = Body2[i].split(":");
//           System.out.println(Body3[0] + Body3[1]);
               parameters.put(Body3[0].substring(1, Body3[0].length() - 1), Body3[1].substring(1, Body3[1].length() - 1));
//           parameters.put(Body3[0], Body3[1]);
           }
       }


       parameters.put("Version", "2016-12-05");
       parameters.put("AccessKey", accessKey);
       parameters.put("Timestamp", simpleDateFormat.format(new Date()));
       parameters.put("SignatureMethod", "HmacSHA1");
       parameters.put("SignatureVersion", "V2.0");
       parameters.put("SignatureNonce", UUID.randomUUID().toString().replace("-", ""));
//       Set<String> setKey = parameters.keySet();
//       Iterator<String> iterator = setKey.iterator();
//       // 从while循环中读取key
//       while(iterator.hasNext()){
//           String key = iterator.next();
//           // 此时的String类型的key就是我们需要的获取的值
//           System.out.println(key);
//       }
       List<String> parameterKeyList = new ArrayList<>();
       parameterKeyList.addAll(parameters.keySet());
       Collections.sort(parameterKeyList);
       List<String> list = new ArrayList<>();
       for (String key : parameterKeyList) {
           list.add(String.format("%s=%s", percentEncode(key), percentEncode(parameters.get(key))));
       }
       String canonicalizedQueryString = StringUtils.join(list, "&");
       String stringToSign = method + "\n" + percentEncode(path) + "\n" + sha256Encode(canonicalizedQueryString);
       String signature = hmacSha1Sign("BC_SIGNATURE&" + secretKey, stringToSign);
       return url + path + "?" + canonicalizedQueryString + "&" + "Signature" + "=" + percentEncode(signature);
   }

   private String percentEncode(String data) throws UnsupportedEncodingException {
       return data != null? URLEncoder.encode(data, "utf-8").replace("+", "%20").replace("*", "%2A").replace("%7E", "~") : null;
   }

   private String sha256Encode(String data) throws NoSuchAlgorithmException, UnsupportedEncodingException {
       MessageDigest digest = MessageDigest.getInstance("SHA-256");
       byte[] hash = digest.digest(data.getBytes("utf-8"));
       return Hex.encodeHexString(hash);
   }

   private String hmacSha1Sign(String secretKey,String data) throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException {
       Mac mac = Mac.getInstance("HmacSHA1");
       byte[] secretKeyByte = secretKey.getBytes("utf-8");
       SecretKeySpec secretKeySpec = new SecretKeySpec(secretKeyByte, "HmacSHA1");
       mac.init(secretKeySpec);
       return Hex.encodeHexString(mac.doFinal(data.getBytes("utf-8")));
   }

}