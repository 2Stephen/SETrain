package com.whut.backend.controller;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;

import com.whut.backend.common.Result;
import com.whut.backend.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;


/**
 * 文件上传接口
 */
@RestController
@RequestMapping("/files")
@Slf4j
public class FileController {

    @Autowired
    private QuestionService questionService;

    // 文件上传存储路径
    private static final String filePath = "/Users/hanlelin/Desktop/SETrain/back/backend/src/main/resources/static";

    /**
     * 文件上传
     */
    @PostMapping("/upload")
    public Result upload(MultipartFile file) {
        synchronized (FileController.class) {
            String flag = String.valueOf(System.currentTimeMillis());
            String fileName = file.getOriginalFilename();
            //如果不以csv结尾，就报错
            if (!fileName.endsWith(".csv")) {
                return Result.error("文件格式错误");
            }

            try {
                //不进行判误了
                //应该都有 filePath
//                if (!FileUtil.isDirectory(filePath)) {
//                    FileUtil.mkdir(filePath);
//                }

                // 文件存储形式：时间戳-文件名
                FileUtil.writeBytes(file.getBytes(), filePath + flag +"-"+fileName);
                System.out.println(fileName + "--上传成功");
                Thread.sleep(1000L);
            } catch (Exception e) {
                System.err.println(fileName + "--文件上传失败");
                return Result.error("文件上传失败");
            }

            //开始进行恢复数据库
            String fileRealName=filePath + flag +"-"+fileName;
            log.info("文件路径："+fileRealName);

            questionService.refreshQuestionCSV(fileRealName);

            return Result.success(flag);
        }
    }
//
//    /**
//     * 获取文件
//     */
//    @GetMapping("/{flag}")
//    public void avatarPath(@PathVariable String flag, HttpServletResponse response) {
//        if (!FileUtil.isDirectory(filePath)) {
//            FileUtil.mkdir(filePath);
//        }
//        OutputStream os;
//        List<String> fileNames = FileUtil.listFileNames(filePath);
//        String avatar = fileNames.stream().filter(name -> name.contains(flag)).findAny().orElse(null);
//        try {
//            if (StrUtil.isNotEmpty(avatar)) {
//                response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(avatar, "UTF-8"));
//                response.setContentType("application/octet-stream");
//                byte[] bytes = FileUtil.readBytes(filePath + avatar);
//                os = response.getOutputStream();
//                os.write(bytes);
//                os.flush();
//                os.close();
//            }
//        } catch (Exception e) {
//            System.out.println("文件下载失败");
//        }
//    }

    /**
     * 获取题库文件.csv  sql先算了
     * @param response
     */

    @GetMapping("/questionCSV")
    public void questionCSV(HttpServletResponse response) {
        //若存在先删除
        //FileUtil.del(filePath+"question.csv");

        //保存更新的csv
        questionService.generateQuestionCSV();
        log.info("生成题库文件成功");
        //连表查询 附带 题目类型的id 和 题目类型title
        String flag="question.csv";

        //不进行判误了
        //应该都有 filePath
        OutputStream os;
        List<String> fileNames = FileUtil.listFileNames(filePath);
        String avatar = fileNames.stream().filter(name -> name.equals(flag)).findAny().orElse(null);
        try {
            if (StrUtil.isNotEmpty(avatar)) {
                response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                response.setHeader("Pragma", "no-cache");
                response.setHeader("Expires", "0");
                response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(avatar , "UTF-8"));
                response.setContentType("application/octet-stream");
                byte[] bytes = FileUtil.readBytes(filePath + avatar);
                os = response.getOutputStream();
                os.write(bytes);
                os.flush();
                os.close();
            }
        } catch (Exception e) {
            System.out.println("文件下载失败");
        }
    }

}
