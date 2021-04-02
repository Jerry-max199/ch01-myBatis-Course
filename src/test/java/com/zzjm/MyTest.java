package com.zzjm;


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
    }

