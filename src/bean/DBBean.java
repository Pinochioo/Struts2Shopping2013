package bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

//MySQL数据库连接文件
public class DBBean {
	private Connection con;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public Connection getConnection(){
		if(con == null){
			try {
				Class.forName("com.mysql.jdbc.Driver");
				String url="jdbc:mysql://127.0.0.1/shopping";
				String user="root";
				String password ="0325";
				
				con = DriverManager.getConnection(url, user, password);
				
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}//if
		return con;
	}
	
	//select * from goods where goodsid = ?
	public ResultSet executeQuery(String sql, ArrayList params) throws Exception{
		if(con == null){
			throw new Exception("数据库没有连接！");
		}
		
		pstmt = con.prepareStatement(sql);//先初始化SQL，先把这个SQL提交到数据库中进行预处理，多次使用可提高效率
		
		if(params != null){
			Iterator i = params.iterator();//遍历整个params列表
			
			int index = 1;
			while(i.hasNext()){
				String temp = (String)i.next();
				pstmt.setString(index, temp);//将表项再按索引顺序插入pstmt
				index ++;
			}
			
		}
		
		rs = pstmt.executeQuery();
		
		
		return rs;
	}
	
	public void close(){
		try {
			if(rs != null){
				rs.close();
			}
			if(stmt != null)
				stmt.close();
			if(pstmt!=null){
				pstmt.close();
			}
			if(con != null)
				con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}
