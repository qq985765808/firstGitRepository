package com.wupeng.demo.vo;

import java.io.Serializable;

public class ServerThreadInfoVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String threadName;
    private StackTraceElement element;

    public ServerThreadInfoVO(){
        super();
    }

    public ServerThreadInfoVO(String threadName, StackTraceElement element) {
        this.threadName = threadName;
        this.element = element;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public StackTraceElement getElement() {
        return element;
    }

    public void setElement(StackTraceElement element) {
        this.element = element;
    }

    @Override
    public String toString() {
        return "ServerThreadInfoVO{" +
                "threadName='" + threadName + '\'' +
                ", element='" + element + '\'' +
                '}';
    }
}
