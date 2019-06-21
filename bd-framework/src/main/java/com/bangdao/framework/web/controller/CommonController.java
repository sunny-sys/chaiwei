package com.bangdao.framework.web.controller;

import com.bangdao.common.utils.file.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * 通用请求处理
 * 文件上传下载
 */
@Controller
public class CommonController {
    private static final Logger log = LoggerFactory.getLogger(CommonController.class);

    @RequestMapping("common/download")
    public void fileDownload(String fileName, Boolean delete, HttpServletResponse response, HttpServletRequest request) {
        String realFileName = System.currentTimeMillis() + fileName.substring(fileName.indexOf("_") + 1);
        try {
            String filePath = ResourceUtils.getURL("classpath:").getPath() + "static/file/" + fileName;

            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
            response.setContentType(MediaType.MULTIPART_FORM_DATA_VALUE);
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;fileName=" + setFileDownloadHeader(request, realFileName));
            FileUtils.writeBytes(filePath, response.getOutputStream());
            if (delete) {
                FileUtils.deleteFile(filePath);
            }
        } catch (Exception e) {
            log.error("下载文件失败", e);
        }
    }

    public String setFileDownloadHeader(HttpServletRequest request, String fileName) throws UnsupportedEncodingException {
        final String agent = request.getHeader(HttpHeaders.USER_AGENT);
        String filename = fileName;
        if (agent.contains("MSIE")) {
            // IE浏览器
            filename = URLEncoder.encode(filename, StandardCharsets.UTF_8.name());
            filename = filename.replace("+", " ");
        } else if (agent.contains("Firefox")) {
            // 火狐浏览器
            filename = new String(fileName.getBytes(), StandardCharsets.ISO_8859_1.name());
        } else if (agent.contains("Chrome")) {
            // google浏览器
            filename = URLEncoder.encode(filename, StandardCharsets.UTF_8.name());
        } else {
            // 其它浏览器
            filename = URLEncoder.encode(filename, StandardCharsets.UTF_8.name());
        }
        return filename;

    }
}
