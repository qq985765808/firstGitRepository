package com.wupeng.demo.support.service;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import com.wupeng.demo.support.http.HttpClient;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONException;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

@Service
@Transactional
public class GeocodingService implements Serializable {

    private static final long serialVersionUID = 1L;
    private final  static Log log = LogFactory.getLog(GeocodingService.class);

    private  final  static  Double PI =  Math.PI;
    private  final  static  Double PK =  180 / PI;
    static final String MAP_URL= "http://api.map.baidu.com/geocoder/v2/?ak=cXtebEbDNksN4siNgkApX9QjWTGFrsl2&output=json&address=";

    /**
     * 根据地址获取经纬度
     * @param address
     * @return
     */
    private Map<String,Double> getLatAndLngByAddress(String address){
        Map<String,Double> poiMap = null;
        String result = null;
        try {
            result = HttpClient.sendGetRequest(MAP_URL);
        } catch (Exception e) {
            log.error("调用百度地图API获取{}的经纬度，抛错{}");
        }
        if (StringUtils.isNotBlank(result) && "0".equals(new JSONObject().getJSONObject(result).getString("status").toString())){
            String lat = result.substring(result.indexOf("\"lat\":")
                    + ("\"lat\":").length(), result.indexOf("},\"precise\""));
            String lng = result.substring(result.indexOf("\"lng\":")
                    + ("\"lng\":").length(), result.indexOf(",\"lat\""));
            poiMap = ImmutableMap.of("lat",Double.parseDouble(lat),"lng",Double.parseDouble(lng));
        }
        return poiMap;
    }

    /**
     * 计算两个地址的距离（米）
     * @param address
     * @param otherAddress
     * @return
     */
    public Double getDistanceFromTwoPlaces(String address,String otherAddress){
        Double distance = null;
        if (StringUtils.isNotBlank(address) && StringUtils.isNotBlank(otherAddress)){
            address = address.trim();
            otherAddress = otherAddress.trim();
            if (address.equals(otherAddress)){
                return 0.0d;
            }
            Map pointA = getLatAndLngByAddress(address);
            Map pointB = getLatAndLngByAddress(otherAddress);
            distance = getDistanceFromTwoPoints(pointA,pointB);
        }
        return distance;
    }

    /**
     * 获取两个经纬度之间的距离（单位：米）
     * @param pointA
     * @param pointB
     * @return
     */
    private Double getDistanceFromTwoPoints(Map pointA, Map pointB) {
        Double distance = null;
        if (pointA != null && !pointA.isEmpty() && pointB != null && !pointB.isEmpty()){
            double lat_a = (double) pointA.get("lat");
            double lng_a = (double) pointA.get("lng");
            double lat_b = (double) pointB.get("lat");
            double lng_b = (double) pointB.get("lng");

            if (lat_a == lat_b && lng_a == lng_b){
                return 0.0d;
            }

            double t1 = Math.cos(lat_a / PK) * Math.cos(lng_a / PK) * Math.cos(lat_b / PK) * Math.cos(lng_b / PK);
            double t2 = Math.cos(lat_a / PK) * Math.sin(lng_a / PK) * Math.cos(lat_b / PK) * Math.sin(lng_b / PK);
            double t3 = Math.sin(lat_a / PK) * Math.sin(lat_b / PK);

            double tt = Math.acos(t1 + t2 + t3);
            distance = 6366000 * tt;
        }
        return distance;
    }

    /**
     * 获取地图的返回结果
     * */
    public  String  getMapResult(){
        String result = null;
        try{
            result = HttpClient.sendGetRequest("http://api.map.baidu.com/location/ip?ak=cXtebEbDNksN4siNgkApX9QjWTGFrsl2&coor=gcj02");
            log.info("返回数据："+result);
            Map<String,Object> map = (Map)JSONObject.parse(result);
           log.info("数据："+map);
            if("0".equals(map.get("status").toString())){
                log.info("请求地理位置信息成功");
            }else{
                log.info("请求地理位置信息失败");
            }
        }catch (JSONException e){
            log.error("JSON解析失败");
            e.printStackTrace();
        }catch (Exception e){
            log.error("服务器异常");
          e.printStackTrace();
        }
        return  result;
    }

}
