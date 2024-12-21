package com.whut.backend.controller;


import cn.hutool.core.util.RandomUtil;
import com.whut.backend.POJO.ChangePwdData;
import com.whut.backend.POJO.RegisterData;
import com.whut.backend.POJO.UserData;
import com.whut.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService UserService;
    @Autowired
    public UserController(com.whut.backend.service.UserService userService) {
        UserService = userService;
    }

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Value("${spring.mail.username}")
    private String sender;

    @Value("${spring.mail.nickname}")
    private String nickname;

    @ResponseBody
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @ResponseBody
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserData userData) {
        String username = userData.getUsername();
        String password = userData.getPassword();
        boolean login = UserService.login(username, password);
        if (login) {
            return ResponseEntity.ok("admin");
        } else {
            return ResponseEntity.badRequest().body("Login failed");
        }
    }

    @ResponseBody
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterData registerData) {
        String username = registerData.getUsername();
        String password = registerData.getPassword();
        String email = registerData.getEmail();
        String captcha = registerData.getCaptcha();
        String redisCaptcha = (String) stringRedisTemplate.opsForValue().get("email_code_"+email);
        log.info("redisCaptcha:{}", redisCaptcha);
        if(redisCaptcha == null) {
            log.info("验证码过期");
            // 两次传输邮箱不一致或者验证码过期
            return ResponseEntity.badRequest().body("验证码过期");
        }else if(!redisCaptcha.equals(captcha)) {
            return ResponseEntity.badRequest().body("captcha error");
        }
        boolean register = UserService.register(username, password, email);
        if (register) {
            return ResponseEntity.ok("register success");
        } else {
            log.info("register failed");
            return ResponseEntity.badRequest().body("register failed");
        }
    }

    @ResponseBody
    @GetMapping("/getemailcode")
    public ResponseEntity<String> getEmailCode(@RequestParam String email) {
        if(UserService.containsEmail(email)) {
            System.out.println("email exists");
            // 创建一个邮件
            SimpleMailMessage message = new SimpleMailMessage();
            // 设置发件人
            message.setFrom(nickname+'<'+sender+'>');
            // 设置收件人
            message.setTo(email);
            // 设置邮件主题
            message.setSubject("欢迎访问"+nickname);
            //生成六位随机数
            String code = RandomUtil.randomNumbers(6);
            //将验证码存入redis，有效期为5分钟
            stringRedisTemplate.opsForValue().set("email_code_"+email, code, 300000, TimeUnit.MILLISECONDS);
            String content = "【验证码】您的验证码为：" + code +
                    " 。 验证码五分钟内有效，逾期作废。\n\n\n" +
                    "------------------------------\n\n\n" ;
            message.setText(content);
            // 发送邮件
            javaMailSender.send(message);
            return ResponseEntity.ok("Send email success");
        } else {
            return ResponseEntity.badRequest().body("Send email failed");
        }
    }
    @ResponseBody
    @GetMapping("/getregisteremailcode")
    public ResponseEntity<String> getRegisterEmailCode(@RequestParam String email) {
        if(!UserService.containsEmail(email)) {
            // 创建一个邮件
            SimpleMailMessage message = new SimpleMailMessage();
            // 设置发件人
            message.setFrom(nickname+'<'+sender+'>');
            // 设置收件人
            message.setTo(email);
            // 设置邮件主题
            message.setSubject("欢迎访问"+nickname);
            //生成六位随机数
            String code = RandomUtil.randomNumbers(6);
            //将验证码存入redis，有效期为5分钟
            stringRedisTemplate.opsForValue().set("email_code_"+email, code, 300000, TimeUnit.MILLISECONDS);
            String content = "【验证码】您的验证码为：" + code +
                    " 。 验证码五分钟内有效，逾期作废。\n\n\n" +
                    "------------------------------\n\n\n" ;
            message.setText(content);
            // 发送邮件
            javaMailSender.send(message);
            return ResponseEntity.ok("Send email success");
        } else {
            return ResponseEntity.badRequest().body("Send email failed");
        }
    }

    @ResponseBody
    @PostMapping("/changepwd")
    public ResponseEntity<String> changePwd(@RequestBody ChangePwdData changePwdData) {
        String email = changePwdData.getEmail();
        String code = changePwdData.getCaptcha();
        String password = changePwdData.getPassword();
        String redisCode = stringRedisTemplate.opsForValue().get("email_code_"+email);
        if(redisCode != null && redisCode.equals(code)) {
            boolean changePwd = UserService.changePwd(email, password);
            if (changePwd) {
                return ResponseEntity.ok("Change password success");
            } else {
                return ResponseEntity.badRequest().body("Change password failed");
            }
        } else {
            return ResponseEntity.badRequest().body("Change password failed");
        }
    }

}
