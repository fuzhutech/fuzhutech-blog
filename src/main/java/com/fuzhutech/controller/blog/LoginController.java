package com.fuzhutech.controller.blog;

import com.fuzhutech.common.ResponseResult;
import com.fuzhutech.pojo.blog.User;
import com.fuzhutech.service.blog.UserService;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

// 登录退出
@RestController
//@RequestMapping("/login")
public class LoginController {

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService uerService;

    //登录
    @RequestMapping(value = "/login", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseResult login(@RequestBody User model/*,HttpServletRequest request*/) {

        try {
            if (model.getLoginName().isEmpty())
                return new ResponseResult(ResponseResult.FAILURE, null, "用户名不正确");
            if (model.getPassword().isEmpty())
                return new ResponseResult(ResponseResult.FAILURE, null, "密码不正确");

            model.setPassword(EncoderByMd5(model.getPassword()));
            logger.info("加密后密码：" + model.getPassword());

            List<User> userList = uerService.queryListByWhere(model);
            if (userList.size() == 0) {
                logger.info("userList.size() == 0");
                return new ResponseResult(ResponseResult.FAILURE, null, "用户名或密码不正确");
            }
            User user = userList.get(0);
            user.setLastLoginTime(new Date());

            //将最后登录时间更新到数据库
            User record = new User();
            record.setId(user.getId());
            record.setLastLoginTime(user.getLastLoginTime());
            this.uerService.updateSelective(record);

            //创建token，保存在password字段供前台获取
            String token = uerService.createToken(user);
            user.setPassword(token);

            return new ResponseResult(ResponseResult.SUCCESS, user);

        } catch (Exception ex) {
            logger.info("发生错误：{}",ex.getMessage());
            return new ResponseResult(ResponseResult.FAILURE, null, ex.getMessage());
        }
    }

    //对字符串md5加密
    private String EncoderByMd5(String value) throws Exception {
        try {
            //确定计算方法,生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            return Base64.encodeBase64String(md.digest(value.getBytes()));
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("MD5加密出现错误");
        }
    }

    //退出
    @RequestMapping(value = "/logout", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseResult logout(@RequestBody User model/*,HttpServletRequest request*/) {
        try {
            //如果已经登录，则退出
            //uerService.update(model);
            return new ResponseResult(ResponseResult.SUCCESS);
        } catch (RuntimeException ex) {
            return new ResponseResult(ResponseResult.FAILURE, null, ex.getMessage());
        }
    }

    //修改密码
    @RequestMapping(value = "/password", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseResult modifyPassword(@RequestBody User model/*,HttpServletRequest request*/) {
        try {
            //如果已经登录，则允许修改

            model.setPassword(EncoderByMd5(model.getPassword()));
            logger.info("修改后加密密码：" + model.getPassword());

            uerService.update(model);
            return new ResponseResult(ResponseResult.SUCCESS);
        } catch (Exception ex) {
            return new ResponseResult(ResponseResult.FAILURE, null, ex.getMessage());
        }
    }

}
