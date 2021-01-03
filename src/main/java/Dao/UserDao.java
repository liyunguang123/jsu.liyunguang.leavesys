package Dao;

import Bean.User;

import java.sql.ResultSet;

public class UserDao extends BaseDao {
    //验证邮箱是否存在
    public int isExist(String email) throws Exception {
        String sql = "select * from user where email=?";
        Object[] param = {email};
        ResultSet rs = this.executeQuery(sql, param);
        if(rs!=null&&rs.next()){
            return Integer.parseInt(rs.getString(1));
        }
        else
            return 0;
    }
    //登录验证账号
    public boolean isRight(String email, String password) throws Exception {
        String sql = "select * from user where email=? and userpwd=?";
        Object[] param = {email,password};
        ResultSet rs = this.executeQuery(sql, param);
        if(rs!=null&&rs.next()){
            return true;
        }
        else
            return false;
    }

    //保存用户,注册用
    public int saveUser(User user){
        int result=0;
        String sql="insert into user (userName,email,phone,userpwd,sex) values(?,?,?,?,?) ";
        Object param[]=user.getParaments();
        result=this.executeUpdate(sql,param);
        return result;
    }
    //删除用户，管理员操作
    public int deleteUser(String email){
        int result=0;
        String sql="delete from user where email=?";
        Object param[]={email};
        result=this.executeUpdate(sql,param);
        return result;
    }
    //取得User,传入email
    public User getUser(String email) throws Exception{
        User user=null;
        String sql="select * from user where email=?";
        Object param[]={email};
        ResultSet rs=this.executeQuery(sql,param);
        if(rs!=null&&rs.next()){
            user=new User(Integer.parseInt(rs.getString(1)),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),Integer.parseInt(rs.getString(6)));
        }
        return user;
    }
    //取得User,传入uid
    public User getUser(int uid) throws Exception{
        User user=null;
        String sql="select * from user where uid=?";
        Object param[]={uid};
        ResultSet rs=this.executeQuery(sql,param);
        if(rs!=null&&rs.next()){
            user=new User(Integer.parseInt(rs.getString(1)),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),Integer.parseInt(rs.getString(6)));
        }
        return user;
    }
    //修改用户
    public int updateUser(User user){
        String sql="update user set username=?,email=?,phone=?,userpwd=?,sex=? where uid=?";
        Object[] params={user.getUserName(),user.getEmail(),user.getPhone(),user.getUserPwd(),user.getSex(),user.getUid()};
        return this.executeUpdate(sql,params);
    }
}