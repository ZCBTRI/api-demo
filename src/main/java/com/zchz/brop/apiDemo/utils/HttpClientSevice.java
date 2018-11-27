package com.zchz.brop.apiDemo.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.text.ParseException;

public class HttpClientSevice {

    /**
     * get
     *
     * @param url
     * @return
     * @throws IOException
     * @throws HttpException
     * @throws org.apache.http.ParseException
     */
    public static String get(String url) throws org.apache.http.ParseException, HttpException, IOException, ParseException {
        HttpGet get = new HttpGet(url);
        try (CloseableHttpClient client = HttpClients.createSystem()) {
            HttpResponse response = client.execute(get);
            return dealResponse(response);
        }
    }

    /**
     * Post JSON对象
     *
     * @param url
     * @return
     * @throws IOException
     * @throws ParseException
     */
    public static String postJsonObject(String url, Object jsonObj) throws ParseException, HttpException, IOException {
        String json = new ObjectMapper().writeValueAsString(jsonObj);
        return postJsonString(url, json);
    }

    /**
     * post JOSN字符串
     *
     * @param url
     * @param jsonStr
     * @return
     * @throws IOException
     * @throws ParseException
     */
    public static String postJsonString(String url, String jsonStr) throws ParseException, HttpException, IOException {
        HttpPost httpPost = new HttpPost(url);
        StringEntity params = new StringEntity(jsonStr, "UTF-8");
        params.setContentType("application/json");
        params.setContentEncoding("UTF-8");
        httpPost.setEntity(params);
        return post(httpPost);
    }

    /**
     * post HttpPost
     *
     * @param httpPost
     * @return
     * @throws IOException
     * @throws ParseException
     */
    private static String post(HttpPost httpPost) throws ParseException, HttpException, IOException {
        try (CloseableHttpClient client = HttpClients.createSystem()) {
            HttpResponse response = client.execute(httpPost);
            return dealResponse(response);
        }
    }

    /**
     * 解析数据 HttpResponse
     *
     * @param response
     * @return
     * @throws IOException
     * @throws ParseException
     */
    private static String dealResponse(HttpResponse response) throws HttpException, ParseException, IOException {
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            HttpEntity entity = response.getEntity();
            String content = EntityUtils.toString(entity);
            return content;
        } else {
            throw new HttpException(response.getStatusLine().toString());
        }
    }
}
