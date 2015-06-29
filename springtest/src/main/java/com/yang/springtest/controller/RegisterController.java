package com.yang.springtest.controller;

import com.yang.springtest.dao.UserDao;
import com.yang.springtest.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @Resource
    private UserDao userDao;

    /**
     * 注册页面
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, params = "new")
    public String registerShow(Model model) {

        model.addAttribute(new User());
        return "register/register_show";
    }

    /**
     * 把用户插入到数据库
     * @param user
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public String addUser(@Validated User user,BindingResult bindingResult) {

        if (bindingResult.hasErrors()){

            System.out.println("出现错误啦。");
            return "register/register_show";
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
