package com.zzjm.dao;

import com.zzjm.domain.Student;

import java.util.List;

//操作student表
public interface StudentDao {
    //查询表中所有信息
    public List<Student> selectStudents();
    //插入数据
    public int insert(Student student);
}
