/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2019  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: HttpsClient.java
 : Author: janloongdoo@gmail.com
 : Date: 19-3-29 下午5:41
 : LastModify: 18-11-1 下午5:19
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.janloong.springsecurity.common.utils;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class HttpsClient {

//public class HttpsClient extends DefaultHttpClient {
//    public HttpsClient() throws Exception {
//        SSLContext ctx = SSLContext.getInstance("TLS");
//        X509TrustManager tm = new X509TrustManager() {
//            public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
//            }
//
//            public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
//            }
//
//            public X509Certificate[] getAcceptedIssuers() {
//                return null;
//            }
//        };
//        ctx.init((KeyManager[])null, new TrustManager[]{tm}, (SecureRandom)null);
//        SSLSocketFactory ssf = new SSLSocketFactory(ctx, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
//        ClientConnectionManager ccm = this.getConnectionManager();
//        SchemeRegistry sr = ccm.getSchemeRegistry();
//        sr.register(new Scheme("https", 443, ssf));
//    }


    public static HttpClient initHttpsClient() throws NoSuchAlgorithmException, KeyManagementException {
        HttpClientBuilder clientBuilder = HttpClientBuilder.create();
        SSLContext tls = SSLContext.getInstance("TLS");
        X509TrustManager xm = new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

            }

            @Override
            public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };
        TrustManager[] tm = {xm};
        tls.init(null, tm, null);
        //SSLContext tls2 = SSLContext.getDefault();
        //SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(tls,SSLConnectionSocketFactory.getDefaultHostnameVerifier());
        CloseableHttpClient build = clientBuilder.setSSLContext(tls)
                .setSSLSocketFactory(new SSLConnectionSocketFactory(tls, SSLConnectionSocketFactory
                        .getDefaultHostnameVerifier()))
                .build();
        return build;
    }
}
