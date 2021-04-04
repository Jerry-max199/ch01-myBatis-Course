package com.zzjm;


import com.zzjm.Utils.Myutil;
import com.zzjm.domain.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyTest {
    @Test
    public void mybatisTest() throws IOException {
        //mybatis读取student数据
        //1.定义mybatis主配置文件的名称，从类路径中开始加载
        String config="mybatis.xml";
        //2.读取这个congfig文件
        InputStream in=Resources.getResourceAsStream(config);
        //3.创建sqlsessionFactoryBuilder对象
        SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
        //4.创建sqlSessionFactory对象
        SqlSessionFactory factory=builder.build(in);
        //5.【重要】获取SqlSessionFactory对象，从SqlSessionFactory获取sqlSession
        SqlSession sqlSession=factory.openSession();
        //6.【重要】制定要执行的sql语句表示，sql文件中的namespace+“.”+标签的id
        String sqlid="com.zzjm.dao.StudentDao"+"."+"selectStudents";
        //7.要执行的sql，通过sqlid找到要执行语句
        List<Student> studentList=sqlSession.selectList(sqlid);
        //8.输出结果
        for (Student student:studentList){
            System.out.println(student);
        }
        //关闭sqlSession对象
        sqlSession.close();
        }
        @Test
    public void insertStudent() throws IOException {
       String config="mybatis.xml";
       InputStream inputStream=Resources.getResourceAsStream(config);
       SqlSessionFactoryBuilder sqlSessionFactoryBuilder=new SqlSessionFactoryBuilder();
       SqlSessionFactory sqlSessionFactory=sqlSessionFactoryBuilder.build(inputStream);
       SqlSession sqlSession=sqlSessionFactory.openSession();
       String insertsql="com.zzjm.dao.StudentDao"+"."+"insertStudents";
       Student student=new Student();
       student.setId(1005);
       student.setName("关羽");
       student.setEmail("guanyu@163.com");
       student.setAge(50);
      int numbers= sqlSession.insert(insertsql,student);
      sqlSession.commit();
        System.out.println("插入数据数量"+numbers);
        sqlSession.close();
    }
    @Test
    public void selectStudentUtil(){
        SqlSession sqlSession=Myutil.getSqlSession();
        String selectStudent="com.zzjm.dao.StudentDao"+"."+"selectStudents";
       List<Student> studentList=sqlSession.selectList(selectStudent);
    for (Student student:studentList){
        System.out.println(student);
    }
    sqlSession.close();
    }
    @Test
    public void insertStudentUtil(){
        SqlSession sqlSession=Myutil.getSqlSession();
        String insertStudent="com.zzjm.dao.StudentDao"+"."+"insertStudents";
        Student student=new Student();
        student.setId(1005);
        student.setName("诸葛亮");
        student.setEmail("zhugeliang@163.com");
        student.setAge(60);
        int numbers=sqlSession.insert(insertStudent,student);
        System.out.println("提交数据的数量"+numbers);
        sqlSession.close();
    }
    }

