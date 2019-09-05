package com.wupeng.demo.support.listenner;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

public class SessionApplicationContext {

    private  static HashMap<String, HttpSession> map = new HashMap<>();

    //添加一个session对象
    public static   void addSession(HttpSession session){
        synchronized (session){
            if(session!=null){
                map.put(session.getId(),session);
        }
        }
    }

    //删除一个session对象
    public  static  void  delSession(HttpSession session){
        synchronized (session){
            if(session!=null){
                map.remove(session.getId());
            }
        }
    }

    //获取session对象
    public  static  HttpSession  getSession(String sessionId){
        synchronized (sessionId){
            if (sessionId==null)return  null;
            return map.get(sessionId);
        }
    }


}
