package com.wupeng.demo.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



import java.lang.reflect.Type;
import java.lang.reflect.ParameterizedType;

public class GenericsUtil {

    private  final static Log log = LogFactory.getLog(GenericsUtil.class);

    /**
           * 通过反射,获得定义Class时声明的父类的范型参数的类型.
           * 如public BookManager extends GenricManager<Book>
           *
           * @param clazz The class to introspect
           * @return the first generic declaration, or <code>Object.class</code> if cannot be determined
           */
     public static Class getSuperClassGenricType(Class clazz) {
                 return getSuperClassGenricType(clazz, 0);
             }

             /**
       * 通过反射,获得定义Class时声明的父类的范型参数的类型.
       * 如public BookManager extends GenricManager<Book>
       *
       * @param clazz clazz The class to introspect
       * @param index the Index of the generic ddeclaration,start from 0.
       */
             public static Class getSuperClassGenricType(Class clazz, int index) throws IndexOutOfBoundsException {

                Type genType = clazz.getGenericSuperclass();

                 if (!(genType instanceof ParameterizedType)) {
                         return Object.class;
                     }

                 Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

                 if (index >= params.length || index < 0) {
                         return Object.class;
                     }
                 if (!(params[index] instanceof Class)) {
                         return Object.class;
                     }
                     log.debug("className="+params[index].getClass().getName());
                 return (Class) params[index];
             }

}
