package com.wupeng.demo.sms;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONException;

import javax.xml.ws.http.HTTPException;
import java.io.IOException;
import java.io.Serializable;
import java.util.Map;
import java.util.UUID;

public class QCloudSMS  implements Serializable {

    private static final long serialVersionUID = 1L;
    private  final  static Log log = LogFactory.getLog(QCloudSMS.class);

    private  final  static int RESULT_STATUS_SUCCESS=0;
    private  final  static String RESULT_SUCCESS ="SUCCESS",RESULT_ERROR="ERROR",RESULT_EXCEPTION="EXCEPTION";

    private final static int AppID = 14002;
    private final static String AppKey = "f";
    private final static  int templateId = 420425,templateNoticeId=420535;
    private final static String smsSign = "wpshoppingcn";
    private String Random = UUID.randomUUID()+"";
    private String Time = System.currentTimeMillis()+"";


    /**
     * 发送腾讯云短信(验证码)
     * @param phoneNum,code
     * @return string
     */
    public static  String sendVerificationCodeSMS(String phoneNum,String code) {
        try {
            //参数，一定要对应短信模板中的参数顺序和个数，
            String [] parans = {code,"5"};
            //创建ssender对象
            SmsSingleSender ssender = new SmsSingleSender(AppID, AppKey);
            //发送
            SmsSingleSenderResult result = ssender.sendWithParam("86",phoneNum,templateId,parans,smsSign,"","");
            //签名参数未提供或者为空时，会使用默认签名发送短信
            //System.out.println(result.toString());
            log.info(result.toString());
            if(result.result==QCloudSMS.RESULT_STATUS_SUCCESS){
                log.info("手机号码为："+phoneNum+"的短信发送成功");
                return  QCloudSMS.RESULT_SUCCESS;
            }else{
                log.info("手机号码为："+phoneNum+"的短信发送失败");
                return  QCloudSMS.RESULT_ERROR;
            }
        }catch (HTTPException e) {
           //HTTP响应码错误
            log.error("HTTP响应码错误");
            e.printStackTrace();
        }catch (JSONException e){
            //json解析错误
            log.error("json解析错误");
            e.printStackTrace();
        }catch (Exception e){
            //未知异常
            log.error("详细看错误代码");
            e.printStackTrace();
        }
        return  QCloudSMS.RESULT_EXCEPTION;
    }

    /**
     * 发送腾讯云短信(通知类)
     * @param phoneNum,message
     * @return string
     */
      public  static  String  sendNoticeSMS(String phoneNum,String message){
          try {
              //参数，一定要对应短信模板中的参数顺序和个数，
              String [] parans = {message};
              //创建ssender对象
              SmsSingleSender ssender = new SmsSingleSender(AppID, AppKey);
              //发送
              SmsSingleSenderResult result = ssender.sendWithParam("86",phoneNum,templateNoticeId,parans,smsSign,"","");
              //签名参数未提供或者为空时，会使用默认签名发送短信
              //System.out.println(result.toString());
              log.info(result.toString());
              if(result.result==QCloudSMS.RESULT_STATUS_SUCCESS){
                  log.info("手机号码为："+phoneNum+"的短信发送成功");
                  return  QCloudSMS.RESULT_SUCCESS;
              }else{
                  log.info("手机号码为："+phoneNum+"的短信发送失败");
                  return  QCloudSMS.RESULT_ERROR;
              }
          }catch (HTTPException e) {
              //HTTP响应码错误
              log.error("HTTP响应码错误");
              e.printStackTrace();
          }catch (JSONException e){
              //json解析错误
              log.error("json解析错误");
              e.printStackTrace();
          }catch (Exception e){
              //未知异常
              log.error("详细看错误代码");
              e.printStackTrace();
          }
          return  QCloudSMS.RESULT_EXCEPTION;
      }

}
