package Dao;


import Bean.LeaveTable;
import Bean.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LeaveTableDao extends BaseDao {
    //保存请假表
    public int saveLeaveTable(LeaveTable lt){
        int result=0;
        String sql="insert into leavetable(uid,username,email,date_begin,date_end,reason,state) values(?,?,?,?,?,?,?)";
        Object []params=lt.getSaveParaments();
        result=this.executeUpdate(sql,params);
        return result;
    }
    //修改请假表，传入一个lid和表
    public int updateLeaveTable(int lid, LeaveTable lt) throws SQLException {
        int result=0;
        if(!isExist(lid)){
            //确认要修改的表存在
            return 0;
        }
        String sql="update leavetable set uid=?,username=?,email=?,date_begin=?,date_end=?,reason=?,state=?,message=? where lid=?";
        Object[] params={lt.getUid(),lt.getUserName(),lt.getEmail(),lt.getBegin(),lt.getEnd(),lt.getReason(),lt.getState(),lt.getMessage(),lid};
        result=this.executeUpdate(sql,params);//返回受影响行数，1为成功，0为失败
        return result;
    }
    /*查询uid用户的请假表，传入state筛选,
    为筛选到的时，返回Arraylist集合*/
    public ArrayList<LeaveTable> getLeaveTablesByUid(int uid,String state) throws SQLException {
        //初始化，list为待返回的数组集合
        ArrayList<LeaveTable> list=new ArrayList<LeaveTable>();
        String sql="select * from leavetable where uid=?";
        Object[] params={uid};
        String userName=null;
        String email=null;
        ResultSet rs=this.executeQuery(sql,params);
        if(rs!=null&&rs.next()){
            //如果查询有结果则往list添加取得的元素
             userName=rs.getString(3);
             email=rs.getString(4);
             while (rs!=null){
                String state_temp=rs.getString(8);
                 //优先判断是不是要找的类型,既不是要求类型，且要求类型也不是全部，则下一个
                if(state_temp.equals(state)||state.equals("全部")||state.equals("已完成")&&!state_temp.equals("待审批")){
                    //取得字段生成对象
                    int lid=Integer.parseInt(rs.getString(1));
                    String begin=rs.getString(5);
                    String end=rs.getString(6);
                    String reason=rs.getString(7);
                    String message=rs.getString(8);
                    LeaveTable lt=new LeaveTable(lid,uid,userName,email,begin,end,reason,state_temp,message);
                    list.add(lt);//添加到list
                }
                //下一个，为空退出
                if(!rs.next())
                    break;
                else
                    continue;
            }
        }
        return list;
    }
    //查询所有用户的请假表，管理员用
    public ArrayList<LeaveTable> getLeaveTables(String state) throws SQLException {
        //初始化，list为待返回的数组集合
        ArrayList<LeaveTable> list=new ArrayList<LeaveTable>();
        String sql="select * from leavetable";
        ResultSet rs=this.executeQuery(sql);
        while (rs!=null&&rs.next()){
            String state_temp=rs.getString(8);
            if(!state_temp.equals(state)&&!state.equals("全部")&&!(!state_temp.equals("待审批")&&state.equals("已完成"))){
               //如果不符合状态筛选，就跳过
               continue;
           }
           int lid=Integer.parseInt(rs.getString(1));
           int uid=Integer.parseInt(rs.getString(2));
           String userName=rs.getString(3);
           String email=rs.getString(4);
           String begin=rs.getString(5);
           String end=rs.getString(6);
           String reason=rs.getString(7);
           String message=rs.getString(9);
           list.add(new LeaveTable(lid,uid,userName,email,begin,end,reason,state_temp,message));
       }
        return list;
    }

    //根据lid获取请假表
    public  LeaveTable getLeaveTableByLid(int lid) throws SQLException {
        LeaveTable result=null;
        String sql="select * from leavetable where lid=?";
        Object[] params={lid};
        ResultSet rs=this.executeQuery(sql,params);
        if(rs!=null&&rs.next()){
            int uid=Integer.parseInt(rs.getString(2));
            String userName=rs.getString(3);
            String email=rs.getString(4);
            String begin=rs.getString(5);
            String end=rs.getString(6);
            String reason=rs.getString(7);
            String state_temp=rs.getString(8);
            String message=rs.getString(9);
            result=new LeaveTable(lid,uid,userName,email,begin,end,reason,state_temp,message);
        }
        return result;
    }

    //确认请假表是否存在
    public boolean isExist(int lid) throws SQLException {
        String sql="select uid from leavetable where lid=?";
        Object[] params={lid};
        ResultSet rs=this.executeQuery(sql,params);
        if(rs!=null&&rs.next()){
            return true;
        }
        return false;
    }

}
