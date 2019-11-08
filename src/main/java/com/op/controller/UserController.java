package com.op.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.op.pojo.User;
import com.op.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private SecurityManager securityManager;
    @RequestMapping("loginPage")
    public String loginPage(){
        return "login";
    }
    @RequestMapping("login")
    public String login(User user){
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getUname(),user.getUpwd());
        try {
            subject.login(usernamePasswordToken);
            if (subject.isAuthenticated()){
                return "redirect:index";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:loginPage";
    }
    @RequestMapping("index")
    public String index(@RequestParam(defaultValue = "1") int pageNum, Model model){
        PageHelper.startPage(pageNum,5);
        List<User> userList = userService.getUserList();
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        model.addAttribute("pageInfo",pageInfo);
        return "index";

    }
    @RequestMapping("addUser")
    public String addUser(){
        return "add";
    }

    @RequestMapping("add")
    public String add(User user){
        int i = userService.addUser(user);
        if (i>0){
            return "redirect:index";
        }
        return "redirect:addUser";
    }
    @RequestMapping("edit")
    public String edit(int uid,Model model){
    User user = userService.getUser(uid);
    model.addAttribute("user",user);
    return "update";
     }
     @RequestMapping("updateUser")
    public String updateUser(User user ){
        int i = userService.updateUser(user);
        if (i>0){
            return "redirect:index";
        }
        return "redirect:edit?uid="+user.getUid();
     }
     @RequestMapping("deleteUser")
     @ResponseBody
    public String deleteUser(int uid){
         System.out.println((uid));
        int i = userService.deleteUser(uid);
        if(i>0){
            return "success";
        }
        return "fail";
     }
     @RequestMapping("unauth")
    public String unauth(){
        return "unauth";
     }
}
