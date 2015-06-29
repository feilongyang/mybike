package com.yang.springtest.controller;

import com.yang.springtest.dao.UserDao;
import com.yang.springtest.domain.User;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @Resource
    private UserDao userDao;

    /**
     * 注册页面
     * @param user
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String registerShow(@ModelAttribute("user") User user) {

        return "register/register_show";
    }

    /**
     * 把用户插入到数据库
     * @param user
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public String addUser(@Validated User user,BindingResult bindingResult,
                          @RequestParam(value = "image",required = false)MultipartFile image) {

        if (bindingResult.hasErrors()){
            System.out.println("出现错误啦。");
            return "register/register_show";
        }

        if (!image.isEmpty()){
            try {
                File file = new File("/Users/yang/Documents/images/" + user.getUsername() + ".jpeg");
                FileUtils.writeByteArrayToFile(file, image.getBytes());
            }catch (Exception e){
                throw new RuntimeException("保存图片失败");
            }
        }

        boolean add = userDao.add(user);
        return "redirect:/register/" + user.getUsername();
    }

    /**
     * 用户注册成功以后，把用户信息显示出来
     * @param username
     * @param model
     * @return
     */
    @RequestMapping(value ="/{username}",method = RequestMethod.GET)
    public String showUserProfile(@PathVariable String username,Model model){

        model.addAttribute(userDao.getUser(username));
        return "register/register_success";
    }
}
