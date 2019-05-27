package com.wupeng.demo.config;


import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:application-dev.properties")
public class ThymeleafProperties {
    public static  final String DEFAULT_PREFIX = "classpath:/templates/";

    public static  final String DEFAULT_SUFFIX = ".html";

    private  String  prefix = DEFAULT_PREFIX;

    private  String  suffix = DEFAULT_SUFFIX;

    private  String  mode   = "HTML5";

    private  String  encoding = "UTF-8";

    private  String contextType = "text/html";

    private  boolean cache  = false;

}
