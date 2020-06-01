package com.soldier.http;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * HTTP & HTTPS 工具类
 *
 */

@Slf4j
public class HttpUtil {

    public static final String HTTP = "com/tongdun/aries/http";
    public static final String HTTPS = "https";
    public static final String UTF8 = "utf-8";

    /**
     * @param url     请求地址
     * @param headers 请求头
     * @param params  请求参数
     * @param body    请求体
     * @return
     */

    public static HttpResponse doPost(String url, Map<String, String> headers, Map<String, String> params, String body) {
        HttpResponse response = null;
        if (StringUtils.isNotBlank(url) && url.startsWith("http")) {
            String httpType = url.startsWith("https") ? HTTPS : HTTP;
            CloseableHttpClient httpClient = getHttpClient(httpType);
            // url 后添加参数
            String appendParams = "";
            if (params != null) {
                StringBuffer paramsUrl = new StringBuffer();
                for (Map.Entry<String, String> param : params.entrySet()) {
                    if (StringUtils.isNotBlank(param.getKey()) && StringUtils.isNotBlank(param.getValue())) {
                        paramsUrl.append(param.getKey() + "=" + param.getValue() + "&");
                    }
                }
                //剔除掉最后的&符号
                appendParams = paramsUrl.toString().substring(0, paramsUrl.length() - 1);
            }
            if (StringUtils.isNotBlank(appendParams)) {
                if (url.contains("?")) {
                    //包含参数，继续追加
                    url = url + "&" + appendParams;
                } else {
                    //无参数，添加参数
                    url = url + "?" + appendParams;
                }
            }
            HttpPost post = new HttpPost(url);
            if (headers != null) {
                // 设置请求 header
                for (Map.Entry<String, String> header : headers.entrySet()) {
                    post.setHeader(header.getKey(), header.getValue());
                }
            }
            // 设置请求 Body
            StringEntity stringEntity = new StringEntity(body, UTF8);
            post.setEntity(stringEntity);
            try {
                response = httpClient.execute(post);
            } catch (IOException e) {
                log.error("执行 http 请求出错", e);
            }
        } else {
            log.error("HttpClient URL ERR:{}", url);
        }
        return response;
    }

    public static HttpResponse doPatch(String url, Map<String, String> headers, Map<String, String> params, String body) {
        HttpResponse response = null;
        if (StringUtils.isNotBlank(url) && url.startsWith("http")) {
            String httpType = url.startsWith("https") ? HTTPS : HTTP;
            CloseableHttpClient httpClient = getHttpClient(httpType);
            // url 后添加参数
            String appendParams = "";
            if (params != null) {
                StringBuffer paramsUrl = new StringBuffer();
                for (Map.Entry<String, String> param : params.entrySet()) {
                    if (StringUtils.isNotBlank(param.getKey()) && StringUtils.isNotBlank(param.getValue())) {
                        paramsUrl.append(param.getKey() + "=" + param.getValue() + "&");
                    }
                }
                //剔除掉最后的&符号
                appendParams = paramsUrl.toString().substring(0, paramsUrl.length() - 1);
            }
            if (StringUtils.isNotBlank(appendParams)) {
                if (url.contains("?")) {
                    //包含参数，继续追加
                    url = url + "&" + appendParams;
                } else {
                    //无参数，添加参数
                    url = url + "?" + appendParams;
                }
            }
            HttpPatch patch = new HttpPatch(url);
            if (headers != null) {
                // 设置请求 header
                for (Map.Entry<String, String> header : headers.entrySet()) {
                    patch.setHeader(header.getKey(), header.getValue());
                }
            }
            // 设置请求 Body
            StringEntity stringEntity = new StringEntity(body, UTF8);
            patch.setEntity(stringEntity);
            try {
                response = httpClient.execute(patch);
            } catch (IOException e) {
                log.error("执行 http 请求出错", e);
            }
        } else {
            log.error("HttpClient URL ERR:{}", url);
        }
        return response;
    }

    public static HttpResponse doGet(String url, Map<String, String> headers, Map<String, String> params) {
        HttpResponse response = null;
        if (StringUtils.isNotBlank(url) && url.startsWith("http")) {
            String httpType = url.startsWith("https") ? HTTPS : HTTP;
            CloseableHttpClient httpClient = getHttpClient(httpType);
            // url 后添加参数
            String appendParams = "";
            if (params != null) {
                StringBuffer paramsUrl = new StringBuffer();
                for (Map.Entry<String, String> param : params.entrySet()) {
                    if (StringUtils.isNotBlank(param.getKey()) && StringUtils.isNotBlank(param.getValue())) {
                        paramsUrl.append(param.getKey() + "=" + param.getValue() + "&");
                    }
                }
                //剔除掉最后的&符号
                appendParams = paramsUrl.toString().substring(0, paramsUrl.length() - 1);
            }
            if (StringUtils.isNotBlank(appendParams)) {
                if (url.contains("?")) {
                    //包含参数，继续追加
                    url = url + "&" + appendParams;
                } else {
                    //无参数，添加参数
                    url = url + "?" + appendParams;
                }
            }
            HttpGet get = new HttpGet(url);
            if (headers != null) {
                // 设置请求 header
                for (Map.Entry<String, String> header : headers.entrySet()) {
                    get.setHeader(header.getKey(), header.getValue());
                }
            }
            try {
                response = httpClient.execute(get);
            } catch (IOException e) {
                log.error("执行 com.tongdun.aries.http 请求出错", e);
            }
        } else {
            log.error("HttpClient URL ERR:{}", url);
        }
        return response;
    }

    /**
     * @param httpType
     * @return
     */
    public static CloseableHttpClient getHttpClient(String httpType) {
        CloseableHttpClient httpClient = null;
        if (HTTPS.equals(httpType)) {
            try {
                //相信所有的签名证书
                SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, new TrustSelfSignedStrategy()).build();
                SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
                httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (KeyManagementException e) {
                e.printStackTrace();
            } catch (KeyStoreException e) {
                e.printStackTrace();
            }
        } else {
            httpClient = HttpClients.createDefault();
        }
        return httpClient;
    }
    public static void main(String[] args) {
        System.out.println(HttpUtil.doGet("http://www.baidu.com", null, null));
    }
}
