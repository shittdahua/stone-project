package com.stone.stoneproject.utils.http;

import com.stone.stoneproject.base.Base;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HttpUtil extends Base {
    private static final CloseableHttpClient httpclient = HttpClients.createDefault();

    /**
     * 发送HttpGet请求
     *
     * @param url
     * @return
     */
    public static String sendGet(String url) {
        HttpGet httpget = new HttpGet(url);
        String result = null;
        try (CloseableHttpResponse response = httpclient.execute(httpget)) {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                result = EntityUtils.toString(entity);
            }
        } catch (Throwable e) {
            error(e);
        }
        return result;
    }

    /**
     * 发送HttpPost请求，参数为map
     *
     * @param url
     * @param map
     * @return
     */
    public static String sendPost(String url, Map<String, String> map) {
        List<BasicNameValuePair> forearms = map.entrySet()
                .stream()
                .map(o -> new BasicNameValuePair(o.getKey(), o.getValue()))
                .collect(Collectors.toList());
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(forearms, Consts.UTF_8);
        HttpPost httppost = new HttpPost(url);
        httppost.setEntity(entity);

        try (CloseableHttpResponse response = httpclient.execute(httppost)) {
            HttpEntity entity1 = response.getEntity();
            return EntityUtils.toString(entity1);
        } catch (ParseException | IOException e) {
            error(e);
        }
        return null;
    }

    /**
     * 发送不带参数的HttpPost请求
     *
     * @param url
     * @return
     */
    public static String sendPost(String url) {
        HttpPost httppost = new HttpPost(url);
        try (CloseableHttpResponse response = httpclient.execute(httppost)) {
            return EntityUtils.toString(response.getEntity());
        } catch (ParseException | IOException e) {
            error(e);
        }
        return null;
    }


}
