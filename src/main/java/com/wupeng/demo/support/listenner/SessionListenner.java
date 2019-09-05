package com.wupeng.demo.support.listenner;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.*;
import java.util.HashSet;

@WebListener
public class SessionListenner implements HttpSessionListener,HttpSessionAttributeListener {

    private  final  static Log log = LogFactory.getLog(SessionListenner.class);

    @SuppressWarnings("all")
    @Override
    public  void  attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent){
        log.info("---------attrributeAdded---------");
        HttpSession session = httpSessionBindingEvent.getSession();
        log.info("key="+httpSessionBindingEvent.getName());
        log.info("value="+httpSessionBindingEvent.getValue());
    }

    @Override
    public  void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent){
        log.info("---------attributeRemoved---------");
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {
        log.info("---------attributeReplaced---------");
    }


    @SuppressWarnings("all")
    @Override
    public  void sessionCreated(HttpSessionEvent event){
        log.info("-----------sessionCreated-----------");
        HttpSession session = event.getSession();
        //获取一个servlet上下文对象
        ServletContext context  = session.getServletContext();
        HashSet<HttpSession> sessions  = (HashSet<HttpSession>)context.getAttribute("sessions");
        if(sessions==null){
            sessions = new HashSet<HttpSession>();
            context.setAttribute("sessions",sessions);
        }
        //新创建的session放到hashset集合里面
        sessions.add(session);
        // 可以在别处从application范围中取出sessions集合
        // 然后使用sessions.size()获取当前活动的session数，即为“在线人数”
        log.info("在线人数为："+sessions.size());
        //添加新建的session到SessionApplicationContext中;
        SessionApplicationContext.addSession(event.getSession());
    }

    public  void sessionDestroyed(HttpSessionEvent event)throws  ClassCastException{
        log.info("------sessionDestroyed---------");
        HttpSession session = event.getSession();
        log.info("删除的sessionId为："+session.getId());
        log.info("session创建时间为："+session.getCreationTime());
        log.info("session最后进入时间为："+session.getLastAccessedTime());
        ServletContext context = session.getServletContext();
        HashSet<?> sessions = (HashSet<?>) context.getAttribute("sessions");
        // 销毁的session均从HashSet集中移除
        sessions.remove(session);
        //删除session到SessionApplicationContext中;
        SessionApplicationContext.delSession(session);
    }

}
