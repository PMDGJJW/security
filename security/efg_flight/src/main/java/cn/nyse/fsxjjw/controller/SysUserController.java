package cn.nyse.fsxjjw.controller;

import cn.nyse.fsxjjw.entity.A;
import cn.nyse.fsxjjw.entity.B;
import cn.nyse.fsxjjw.entity.Test;
import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SysUserController {

    @GetMapping("/addUser")
    public String test() {
        Test t = new Test();
        A a = new A();
        B b = new B();
        List<String> list = new ArrayList<>();
        list.add("aaa");
        list.add("aaa");
        list.add("aaa");
        b.setaList(list);
        a.setB(b);
        t.setA(a);
        String s = JSON.toJSONString(t);

        Test test = JSON.parseObject(s, Test.class);
        A a1 = test.getA();
        B b1 = a1.getB();
        System.out.println(b1.getaList());
        System.out.println(a1);
        return ("模拟添加用户");
    }

    @GetMapping("/deleteRole")
    public String getRole() {
        return ("模拟删除用户");
    }

}
