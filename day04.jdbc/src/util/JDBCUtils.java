package util;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

import static java.sql.DriverManager.getConnection;

/**
 * @author zmc
 * @version 1.0
 * @date 2020/3/27 13:44
 */
public class JDBCUtils {
    private static String url;
    private  static String user;
    private  static String password;//只有静态变量才能被静态代码、静态方法所访问.
    private static String driver;


    /**
     *
     *文件的读取,只需要读取一次即可拿到这些值.使用静态代码块. 静态代码块随着类的加载而加载,只会执行一次,效率高.
     */
    static {
        //读取资源文件,获取值.
        //1.创建Properties集合类
        Properties pro = new Properties();
        //2.加载文件
        try {
            pro.load(new FileReader("src/jdbc"));

            //3.获取数据、赋值
            url = pro.getProperty("url");
            user = pro.getProperty("user");
            password = pro.getProperty("password");
            driver = pro.getProperty("driver");
            //4.注册驱动
            try {
                Class.forName(driver);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





    /**
     *获取连接
     * @return 连接对象
     * @param url
     * @param user
     * @param password
     */

    public static Connection getConnection() throws SQLException{

        return DriverManager.getConnection(url,user,password);

    }


    /**
     *释放资源
     * @param stmt
     * @param conn
     */
    public static  void close(ResultSet rs, Statement stmt, Connection conn){
        if(rs !=null){
            try{
                rs.close();
            }   catch(SQLException e){
                e.printStackTrace();
            }
        }
        if(stmt !=null){
            try{
                stmt.close();
            }   catch(SQLException e){
                e.printStackTrace();
            }
        }

        if(conn !=null){
            try{
                conn.close();
            }   catch(SQLException e){
                e.printStackTrace();
            }
        }



    }

}
