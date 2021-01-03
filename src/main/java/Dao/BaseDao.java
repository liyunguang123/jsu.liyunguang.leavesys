package Dao;

import java.sql.*;

//基层DAO
public class BaseDao {
	String driverName = "com.mysql.cj.jdbc.Driver";
	String dbURL = "jdbc:MySQL://localhost:3306/leavesys? &serverTimezone=Asia/Shanghai";
	String userName = "root";
	String userPwd = "123456";
	Connection conn = null;
	//取得数据库连接
	public Connection getConnection() throws Exception { 
		 Class.forName(driverName);
		 conn=DriverManager.getConnection(dbURL,userName,userPwd);
		 return conn;
	}
	// 关闭数据库连接
	public void closeAll(Connection conn, Statement stmt, ResultSet rs) throws Exception {
		if (rs != null) {
			rs.close();
		}
		if (stmt != null) {
			stmt.close();
		}
		if (conn != null) {
			conn.close();
		}
	}

	// 带参数的查询
	public ResultSet executeQuery(String sql, Object[] param) {
		//带参数的查询
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		if (conn == null) {
			try {
				conn = getConnection();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		//
		try {
			pstmt = conn.prepareStatement(sql);
			if (pstmt != null&&param!=null) {
				for (int i = 0; i < param.length; i++) {
					pstmt.setObject(i + 1, param[i]);
				}
			}
			rs = pstmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	//不带参数的查询
	public ResultSet executeQuery(String sql) throws SQLException {
		ResultSet rs = null;
		if (conn == null) {
			try {
				conn = getConnection();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		Statement st=conn.createStatement();
		rs=st.executeQuery(sql);
		return rs;
	}
	// 更新，传入查询语句和参数，返回更新数量
	public int executeUpdate(String sql, Object[] param) {
		PreparedStatement pstmt = null;
		int num = 0;
		try {
			if (conn == null)
				conn = getConnection();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			pstmt = conn.prepareStatement(sql);
			//导入参数
			if (pstmt != null) {
				for (int i = 0; i < param.length; i++) {
					pstmt.setObject(i + 1, param[i]);
				}
			}
			//执行更新语句
			num = pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return num;
	}
}
