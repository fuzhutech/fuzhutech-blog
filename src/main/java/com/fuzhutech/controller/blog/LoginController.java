package com.fuzhutech.controller.blog;

import com.fuzhutech.common.ResponseResult;
import com.fuzhutech.entity.blog.User;
import com.fuzhutech.service.blog.UserService;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;
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
    public ResponseResult login(@RequestBody User model) {

        try {
            if (model.getLoginName().isEmpty())
                return new ResponseResult(ResponseResult.FAILURE, null, "用户名不正确");
            if (model.getPassword().isEmpty())
                return new ResponseResult(ResponseResult.FAILURE, null, "密码不正确");

            model.setPassword(encoderByMd5(model.getPassword()));
            logger.info("加密后密码：" + model.getPassword());

            List<User> userList = uerService.queryListByWhere(model);
            if (userList.isEmpty()) {
                logger.warn("登录过程中用户名或密码错误,LoginName:{},Password:{}", model.getLoginName(), model.getPassword());
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
            logger.warn("登录过程中发生异常：{}", ex.getMessage());
            return new ResponseResult(ResponseResult.FAILURE, null, ex.getMessage());
        }
    }

    //对字符串md5加密
    private String encoderByMd5(String value) throws Exception {
        //确定计算方法,生成一个MD5加密计算摘要
        MessageDigest md = MessageDigest.getInstance("MD5");
        return Base64.encodeBase64String(md.digest(value.getBytes()));
    }

    //退出
    @RequestMapping(value = "/logout", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseResult logout(@RequestBody User model) {
        logger.info("用户登出:LoginName:{},NickName:{}",model.getLoginName(),model.getNickName());
        return new ResponseResult(ResponseResult.SUCCESS);
    }

    //修改密码
    @RequestMapping(value = "/password", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseResult modifyPassword(@RequestBody User model) {
        try {
            //TODO:改为验证原始密码成功后，才允许修改成新密码
            model.setPassword(encoderByMd5(model.getPassword()));
            uerService.update(model);
            return new ResponseResult(ResponseResult.SUCCESS);
        } catch (Exception ex) {
            logger.warn("LoginName:{},NickName:{},修改密码过程中发生异常：{}", model.getLoginName(),model.getNickName(),ex.getMessage());
            return new ResponseResult(ResponseResult.FAILURE, null, ex.getMessage());
        }
    }

}
