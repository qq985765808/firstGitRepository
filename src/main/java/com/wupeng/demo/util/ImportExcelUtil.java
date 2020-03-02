package com.wupeng.demo.util;

import com.wupeng.demo.dto.OrderDto;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONException;

import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;



public class ImportExcelUtil<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    private final static Log log = LogFactory.getLog(ImportExcelUtil.class);
    //private Class<T> entityClass;
    private T t;



    public  List  importExcel(File file)throws Exception{
        //创建一个workbook对象为空
        Workbook wb = null;
        //获取上传文件的名字，包含后缀
        String fileName = file.getName();
        //根据文件后缀的不同，获得不同的workbook对象
        Iterator<Sheet> sheets = null;
        List list = new ArrayList<>();
        System.out.println(t.getClass().getName());
        try{
            if(fileName.endsWith("xls")){
                wb = new HSSFWorkbook(new FileInputStream(file)); //2003
                sheets = wb.iterator();
            }else if(fileName.endsWith("xlsx")){
                wb = new XSSFWorkbook(new FileInputStream(file));//2007
                sheets = wb.iterator();
            }else{
                throw  new Exception("上传文件非法");
            }
            if(sheets==null){
                throw  new Exception("excel中不含有sheet工作表");
            }
            while (sheets.hasNext()){
                System.out.println("-----------遍历sheet---------");
                Sheet sheet = sheets.next();
                list = getCellValue(sheet);
                //System.out.println("集合的个数"+list.size());
                log.info("集合的个数"+list.size());
                //List<OrderDto> o = list;
            }

        }catch (JSONException e){
            log.error("文件解析失败");
            e.printStackTrace();
        }catch (Exception e){
            log.error("文件信息异常.");
            e.printStackTrace();
        }finally {
            //关闭资源
            if(wb!=null)wb.close();
        }
        return  list;
    }

       //这个方法需要用到动态反射javabean，并且把值一一对应写入属性进去
       private  List  getCellValue(Sheet sheet)throws  Exception{
         //sheet.getPhysicalNumberOfRows():获取的是物理行数，也就是不包括那些空行（隔行）的情况
           List list = new ArrayList<>();;
           Method getMethod,setMethod;
           Object value,obj;
           Field[] fields;
           Field field;
           String fieldName;
           String getMethodName,setMethodName;
         if(sheet.getPhysicalNumberOfRows()<1){
             log.error("Excel文件没有数据");
             throw new Exception("Excel文件没有数据");
         }else{
             // 利用反射，根据JavaBean属性的先后顺序，动态调用getXxx()方法得到属性值
            //fields = t.getClass().getDeclaredFields();
//             System.out.println("总行数"+sheet.getPhysicalNumberOfRows());
//             System.out.println("总列数"+sheet.getRow(3).getPhysicalNumberOfCells());
              for (int i =1;i<sheet.getPhysicalNumberOfRows();i++){
                     obj = t.getClass().newInstance();
                     Class clazz= obj.getClass();
                    //利用反射，根据JavaBean属性的先后顺序，动态调用getXxx()方法得到属性值
                     fields = obj.getClass().getDeclaredFields();
                     //Field.setAccessible(fields,true);//这个不行
                  //System.out.println("field的个数="+fields.length);
                  // 获得第i行对象
                  Row row = sheet.getRow(i);
                  //System.out.println("列数="+row.getLastCellNum());
                      for(int  j=0;j<row.getLastCellNum();j++){
                      Cell cell = row.getCell(j);
                      if(cell==null){
                          continue;
                      }
                      field = fields[j];
                      field.setAccessible(true);
                      fieldName = field.getName();
                      getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                      setMethodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                      //fields[j].setAccessible(true);//设置可以访问的权限
                      System.out.println("get方法名字="+getMethodName);
                      System.out.println("set方法名字="+setMethodName);
                      getMethod = clazz.getMethod(getMethodName, new Class[] {});
                      //setMethod = clazz.getMethod(setMethodName, new Class[] {});
                      //setMethod = clazz.getMethod(setMethodName,new Class[] {});
                      //setMethod.setAccessible(true);
                      System.out.println("方法="+getMethod.toString());
                      //value = getMethod.invoke(clazz.newInstance() ,new Object[]{});
                      //System.out.println("方法值类型="+getMethod.invoke(clazz.newInstance() ,new Object[]{}));
                      value = getMethod.getReturnType();
                      System.out.println("方法值类型="+value);
                      //System.out.println("返回值类型="+setMethod.getReturnType() );
                      if( value.toString().indexOf("String")>0  &&  cell.getCellTypeEnum().equals(CellType.STRING)){
                        // field =  t.getClass().getDeclaredField(setMethodName);
                          field.set(obj,cell.getStringCellValue());
                         //getMethod.invoke(clazz.newInstance(),cell.getStringCellValue());
                      }else if( value.toString().indexOf("Long")>0 &&
                              (cell.getCellTypeEnum().equals(CellType.STRING) ||
                                      cell.getCellTypeEnum().equals(CellType.NUMERIC) )){
                          //fields[j].set(obj,1234L);
                          field.set(obj,Long.valueOf(cell.getStringCellValue()));
                      }else if(value.toString().indexOf("Date")>0 && cell.getCellTypeEnum().equals(CellType.STRING)){
                          SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                          //sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
                          //field.set(obj,HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
                          field.set(obj,sdf.parse(cell.getStringCellValue()));
                      }

                  }
                  list.add(obj);
                  Field.setAccessible(fields,false);
              }
              return  list;
         }

       }

    public ImportExcelUtil(){
/*        Type type = this.getClass().getGenericSuperclass();
        ParameterizedType ptype=(ParameterizedType)type;
        Type[] types = ptype.getActualTypeArguments();
        entityClass=(Class<T>) types[0];*/
        /*Type typeClass = this.getClass().getGenericSuperclass();*/
       // System.out.println( ((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[ 0 ].getClass().getName());
      super();
    }
    public ImportExcelUtil(T t){
        this.t = t;
        System.out.println(t);
    }


    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
}
