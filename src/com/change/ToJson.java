package com.change;
import com.alibaba.fastjson.JSON;
import com.model.DateCheck;
import com.model.Student;
import com.model.Teacher;
import com.model.admin;
import java.util.ArrayList;
public class ToJson {
    String AdminToJsonCorrect(ArrayList<admin> A) {
        return JSON.toJSONString(A);
    }
    String DateCheckToJsonCorrect(ArrayList<DateCheck> A) {
        return JSON.toJSONString(A);
    }
    String StudentToJsonCorrect(ArrayList<Student> A) {
        return JSON.toJSONString(A);
    }
    String TeacherToJsonCorrect(ArrayList<Teacher> A) {
        return JSON.toJSONString(A);
    }
}