package com.time.utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpClientUtils {

    /**
     * 请求参数为XML格式的POST请求
     * @param url
     * @param requestDataXml
     * @return
     */
    public static String  doPostByXml(String url,String requestDataXml) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse httpResponse = null;
        // 创建httpClient连接对象
        httpClient = HttpClients.createDefault();
        // 创建post请求对象
        HttpPost httpPost = new HttpPost(url);
        // 创建连接请求参数对象，并设置连接参数
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(15000)
                .setConnectTimeout(60000)
                .setSocketTimeout(60000)
                .build();

        // 为httpPost请求设置参数
        httpPost.setConfig(requestConfig);
        // 将上传参数存放到entiry
        httpPost.setEntity(new StringEntity(requestDataXml,"UTF-8"));
        // 添加头信息
        httpPost.addHeader("Content-Type", "text/xml");
        String result = "";
        try {
            // 发送请求
            httpResponse = httpClient.execute(httpPost);
            // 从响应对象中获取返回内容
            HttpEntity entity = httpResponse.getEntity();
            result = EntityUtils.toString(entity, "UTF-8");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;

    }



}
