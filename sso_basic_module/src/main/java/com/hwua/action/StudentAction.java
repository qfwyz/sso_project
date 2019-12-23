package com.hwua.action;

import com.hwua.annotation.UseLogin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentAction {

    @GetMapping("addStudent")
    @UseLogin(required = true)
    public String addStudent() throws Exception{
        return "添加成功";
    }

    @GetMapping("updateStudent")
    @UseLogin(required = true)
    public String updateStudent() throws Exception{
        return "更新成功";
    }

    @GetMapping("deleteStudent")
    @UseLogin(required = true)
    public String deleteStudent() throws Exception{
        return "删除成功";
    }

    @GetMapping("getStudentByNumber")
    public String deleteStudent(String number) throws Exception{
        return "查询成功";
    }
}
