package cn.itcast.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import static java.time.zone.ZoneOffsetTransitionRule.TimeDefinition.UTC;

/**
 * @author zmc
 * @version 1.0
 * @date 2020/3/23 22:46
 */
public class JdbcDemo1 {
    public static void main(String[] args)throws Exception  {

        //1.导入驱动java包
        //2.注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        //3.获取数据库连接对象
    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db3?serverTimezone=UTC","root","root");
        //4.定义sql语句
        String sql  = "update student set age = 500 ";
        //5.获取执行sql的对象 Statement
        Statement stmt = conn.createStatement();
        //6.执行sql
        int count = stmt.executeUpdate(sql);
        //7.处理结果
        System.out.println(count);
        //8.释放资源
        stmt.close();
        conn.close();
    }
}
