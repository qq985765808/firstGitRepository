package com.wupeng.demo.support.listenner;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.concurrent.atomic.AtomicInteger;

@WebListener
public class OnLineCountListenner  implements HttpSessionListener {
    private  final  static Log log = LogFactory.getLog(OnLineCountListenner.class);

    //实例化一个原子integer
    private AtomicInteger onLineCount = new AtomicInteger(0);

    public void sessionCreated(HttpSessionEvent event) {
        log.info("创建Session");
        event.getSession().getServletContext().setAttribute("onLineCount", onLineCount.incrementAndGet());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        log.info("销毁Session");
        event.getSession().getServletContext().setAttribute("onLineCount", onLineCount.decrementAndGet());
    }

}
