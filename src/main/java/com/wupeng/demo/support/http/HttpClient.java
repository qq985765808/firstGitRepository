package com.wupeng.demo.support.http;


import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.io.Serializable;

public class HttpClient implements Serializable {

    private static final long serialVersionUID = 1L;


    private  final  static Log log = LogFactory.getLog(HttpClient.class);
    private static final String GZIP_CONTENT_TYPE = "application/x-gzip";
    private static final String DEFUALT_CONTENT_TYPE = "application/x-www-form-urlencoded; charset=UTF-8";
    public static final String USER_AGENT = "Mozilla/5.0 (Linux; Android 4.4.4;  en-us; Nexus 4 Build/JOP40D) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2307.2 Mobile Safari/537.36";

    public static final int default_socketTimeout = 10000;

    private  HttpClient(){}

    /**
     * 发送HTTP_POST请求  时默认采用UTF-8解码
     * 该方法会自动对<code>params</code>中的[中文][|][ ]等特殊字符进行<code>URLEncoder.encode(string,encodeCharset)</code>
     * @param reqURL  请求的url
     * @param params 请求参数
     * @return
     */
    public static String sendPostRequest(String reqURL, Map<String,String> params){
        return  sendPostRequest(reqURL,params,false,null,null);
    }

    /**
     * 发送HTTP_GET请求
     * @param reqURL 请求的url地址(含参数),默认采用utf-8编码
     * @return
     */
    public static String sendGetRequest(String reqURL){
        return sendGetRequest(reqURL,null,false);
    }

    public static String sendGetRequest(String reqURL, String encoding, Boolean inGZIP) {
        long responseLength = 0;//响应长度
        String responseContent = null; //响应内容
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(reqURL);

        if(inGZIP){
            httpGet.setHeader(HTTP.CONTENT_TYPE,GZIP_CONTENT_TYPE);
        }
        httpGet.setHeader(HTTP.USER_AGENT, USER_AGENT);
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(default_socketTimeout).setConnectTimeout(default_socketTimeout).build();
        httpGet.setConfig(requestConfig);

        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);
            try {
                HttpEntity entity = response.getEntity();
                if(null != entity){
                    responseLength = entity.getContentLength();

                    if(inGZIP) {
                        responseContent = unGZipContent(entity, encoding == null ? "UTF-8" : encoding);
                    } else {
                        responseContent = EntityUtils.toString(entity, encoding == null ? "UTF-8" : encoding);
                    }

                    close(entity);
                }
                log.debug("请求地址: " + httpGet.getURI());
                log.debug("响应状态: " + response.getStatusLine());
                log.debug("响应长度: " + responseLength);
                log.debug("响应内容: " + responseContent);
            } finally {
                response.close();//关闭连接,释放资源
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseContent;
    }

    public static String sendPostRequest(String reqURL, Map<String, String> params, Boolean gzip, String encodeCharset, String decodeCharset) {
        String responseContent = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(reqURL);
        if(gzip){
            httpPost.setHeader(HTTP.CONTENT_TYPE,GZIP_CONTENT_TYPE);
        }
        httpPost.setHeader(HTTP.USER_AGENT, USER_AGENT);

        List<NameValuePair> formParams = new ArrayList<NameValuePair>(); //创建参数队列
        Set<Map.Entry<String, String>>  paramSet = params.entrySet();

        if(null != paramSet && paramSet.size() > 0){
            for(Map.Entry<String,String> entry : paramSet){
                formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }
        log.debug("send params:"+formParams.toString());

        try {
            // 设置参数
            httpPost.setEntity(new UrlEncodedFormEntity(formParams, encodeCharset==null ? "UTF-8" : encodeCharset));

            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(default_socketTimeout).setConnectTimeout(default_socketTimeout).build();//设置请求和传输超时时间
            httpPost.setConfig(requestConfig);
            CloseableHttpResponse response = httpClient.execute(httpPost);
            try{
                HttpEntity entity = response.getEntity();
                if (null != entity) {
                    String contentType = "";
                    Header[] headers = httpPost.getHeaders(HTTP.CONTENT_TYPE);
                    if(headers != null && headers.length>0){
                        contentType = headers[0].getValue();
                    }

                    if(contentType.equalsIgnoreCase(GZIP_CONTENT_TYPE)){
                        responseContent = unGZipContent(entity,decodeCharset==null ? "UTF-8" : decodeCharset);
                    }else{
                        responseContent = EntityUtils.toString(entity, decodeCharset==null ? "UTF-8" : decodeCharset);
                    }
                    close(entity);
                }

                log.debug("请求地址: " + httpPost.getURI());
                log.debug("响应状态: " + response.getStatusLine());
                log.debug("响应内容: " + responseContent);
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseContent;
    }
    /**
     * 解压
     * @param entity
     * @param encoding
     * @return
     * @throws IOException
     */
    public static String unGZipContent(HttpEntity entity,String encoding) throws IOException {
        String responseContent = "";
        GZIPInputStream gis = new GZIPInputStream(entity.getContent());
        int count = 0;
        byte data[] = new byte[1024];
        while ((count = gis.read(data, 0, 1024)) != -1) {
            String str = new String(data, 0, count,encoding);
            responseContent += str;
        }
        return responseContent;
    }

    /**
     * 压缩
     * @param sendData
     * @return
     * @throws IOException
     */
    public static ByteArrayOutputStream gZipContent(String sendData) throws IOException{
        if (StringUtils.isBlank(sendData)) {
            return null;
        }

        ByteArrayOutputStream originalContent = new ByteArrayOutputStream();
        originalContent.write(sendData.getBytes("UTF-8"));

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        GZIPOutputStream gzipOut = new GZIPOutputStream(baos);
        originalContent.writeTo(gzipOut);
        gzipOut.close();
        return baos;
    }

    public static void close(HttpEntity entity) throws IOException {
        if (entity == null) {
            return;
        }
        if (entity.isStreaming()) {
            final InputStream instream = entity.getContent();
            if (instream != null) {
                instream.close();
            }
        }
    }



}
