package com.lile.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utils.ImageCaptchaUtil;




@Api(value = "/open", description = "公共部分")
@RequestMapping("open")
@RestController
@Slf4j
public class OpenController {
    private Logger logger = LoggerFactory.getLogger(OpenController.class);

    @ApiOperation("获取图形验证码")
    @GetMapping("/captcha/img/{macCode}")
    public byte[] getByte(@PathVariable String macCode){

         ImageCaptchaUtil.createImage();
        Object[] objs = ImageCaptchaUtil.createImage();
        String randomStr = (String) objs[0];
        logger.info("randomStr  result: " + randomStr);
        /**   //  保存randomStr 到 redis中
        redisService.delete(RedisKeys.USER_CAPTCHA_MACADDRESS + macCode);
        redisService.save(RedisKeys.USER_CAPTCHA_MACADDRESS + macCode, randomStr, 60L);
         **/
        return (byte[]) objs[1];
    }
 }
