package com.wupeng.demo.support.base;

import com.wupeng.demo.DemoApplication;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.stereotype.Controller;


import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

//@SpringBootApplication
@Controller
public class BaseController<T>   implements Serializable  {

    private static final long serialVersionUID = 1L;
    private final  static Log log = LogFactory.getLog(BaseController.class);


    protected  Class<T>  clazz;


  /*  @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        //System.out.println("class="+(this.getClass()!=null?this.getClass().getName():"null"));
        log.info("class="+(this.getClass()!=null?this.getClass().getName():"null"));
        if(this.getClass()!=null){
            return builder.sources(this.getClass());
        }else{
            return builder.sources(BaseController.class);
        }

    }*/


/*    public  static  void main(String[] args){
        SpringApplication.run(BaseController.class);
    }*/


  /*  public BaseController(){
        if(this.getClass()!=null) {
            //clazz = (Class<T>) getModelClass(clazz, 0);
        }
        System.out.println("泛型类构造方法获取到了第一个泛型参数具体类型 tClass="+clazz+"["+this.getClass()+"]");
    }

    //泛型类作为父类，可以获取子类的所有实际参数的类型
    @SuppressWarnings("unchecked")
    public Class<?> getModelClass(Class modelClass,int index) {
        Type genType = this.getClass().getGenericSuperclass();// 得到泛型父类
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();//一个泛型类可能有多个泛型形参，比如ClassName<T,K> 这里有两个泛型形参T和K，Class Name<T> 这里只有1个泛型形参T
        if (params.length - 1 < index) {
            modelClass = null;
        } else {
            modelClass = (Class) params[index];
        }
        return modelClass;

    }*/
}
