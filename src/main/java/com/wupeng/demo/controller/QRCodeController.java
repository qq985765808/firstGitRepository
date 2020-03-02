package com.wupeng.demo.controller;

import com.wupeng.demo.util.QRCodeUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.filechooser.FileSystemView;
import java.io.*;

/**
 * 二维码生成控制器
 * */

@Controller
@RequestMapping(value = "/qrCodeController")
public class QRCodeController implements Serializable {

    private static final long serialVersionUID = 1L;
    private final  static Log log = LogFactory.getLog(QRCodeController.class);

    @RequestMapping(value = "/getQRCodeImag")
    @ResponseBody
    public void getQRCodeImag(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "url",required = false)String url,
            @RequestParam(value = "path",required = false)String path,
            @RequestParam(value = "fileName",required = false)String fileName
    )throws ServletException,IOException {
        try {
            url = "http://wpshopping.cn";
            path = FileSystemView.getFileSystemView().getHomeDirectory() + File.separator + "testQrcode";
            fileName = "temp.jpg";
//            QRCodeUtil.createQrCode(url,path,fileName);
           File file = new File(QRCodeUtil.createQrCode(url,path,fileName));
           FileInputStream fis = new FileInputStream(file);
            long size = file.length();
            byte[] temp = new byte[(int) size];
            fis.read(temp, 0, (int) size);
            fis.close();
            byte[] data = temp;
//            data = Base64.encodeBase64(data);//如何是ajax的方式这种需要转码
            // 设置响应的类型格式为图片格式
            response.setContentType("image/jpg");
            // 禁止图像缓存。
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            OutputStream out = response.getOutputStream();
            out.write(data);
            out.flush();
            out.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
