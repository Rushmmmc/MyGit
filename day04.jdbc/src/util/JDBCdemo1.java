package util;

/**
 * @author zmc
 * @version 1.0
 * @date 2020/3/27 15:37
 */

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 *登录注册小系统
 */
public class JDBCdemo1 {
    public static void main(String[] args) {
        //1.键盘录入
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名");
        String username = sc.nextLine();
        System.out.println("请输入密码");
        String password = sc.nextLine();
        //2.调用方法
        boolean flag = new JDBCdemo1().login(username, password);
        if (flag) {
            System.out.println("登录成功！");
        } else {
            System.out.println(("用户名或密码错误！"));
        }

    }
        /**
         * 登录方法
         */
        public boolean login(String username, String password){
            if (username == null || password == null) {
                return false;
            }
            //连接数据库判断是否成功
            Connection conn = null;
            Statement stmt = null;
            ResultSet rs = null;
            //1.获取连接
            try {
                conn = JDBCUtils.getConnection();
                //2.定义sql
                String sql = "select * from user where username = '" + username+"' and password = '"+password +"'  ";
                //3.获取执行sql的对象
                stmt = conn.createStatement();
                //4.执行查询
                rs = stmt.executeQuery(sql);
                //5.判断
                return rs.next();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                JDBCUtils.close(rs, stmt, conn);
            }


            return false;
        }

    }
