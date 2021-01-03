package Dao;

import Bean.Administrator;

import java.sql.ResultSet;

public class AdministratorDao extends BaseDao {
    //登录验证账号
    public boolean isRight(String aid, String password) throws Exception {
        String sql = "select * from administrator where aid=? and adminpwd=?";
        Object[] param = {aid,password};
        ResultSet rs = this.executeQuery(sql, param);
        if(rs!=null&&rs.next()){
            return true;
        }
        else
            return false;
    }
    //取得Administator,传入id
    public Administrator getAdmin(String aid) throws Exception{
        Administrator admin=null;
        String sql="select * from administrator where aid=?";
        Object param[]={aid};
        ResultSet rs=this.executeQuery(sql,param);
        if(rs!=null&&rs.next()){
            admin=new Administrator(Integer.parseInt(rs.getString(1)),rs.getString(2),rs.getString(3));
        }
        return admin;
    }
}
