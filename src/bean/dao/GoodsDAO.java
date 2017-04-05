package bean.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import bean.DBBean;
import bean.vo.GoodsVo;

public class GoodsDAO {
	public ArrayList getGoodsByPage(int pageNo){
		int numPerPage = 2;//每页显示的商品的最大数量
		
		ArrayList rst = new ArrayList();
		
		String sql = "select * from goods";
		
		DBBean db = new DBBean();
		Connection con = db.getConnection();//获取数据库连接
		ResultSet rs = null;
		
		try {
			 rs = db.executeQuery(sql, null);
			 
			 if(rs != null){
				int index = 1;
				int beginIndex = (pageNo - 1)*numPerPage+1;//在此处相当于endIndex-1
				int endIndex = pageNo * 2;
						
				while(rs.next()){
					if(index < beginIndex){
						index ++;
						continue;//跳过当前循环体后面的代码，进行下一个循环体的循环
					}//直到index>=beginIndex,index<=endIndex时才能开始检索
						
					if(index > endIndex){
						index ++;
						break;
					}
						
					String goodsId = rs.getString(1);
					String goodsName = rs.getString(2);
					Float price = rs.getFloat(3);
					
					GoodsVo g = new GoodsVo();
					g.setGoodsId(goodsId);
					g.setGoodsName(goodsName);
					g.setPrice(price);
					
					rst.add(g);//将获取到的商品信息存放如ArrayList中
					index ++;
				}
			}
			 
			 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rst;//返回商品列表
	}
	
	//通过商品id获取单个商品的详细信息
	public GoodsVo getGoodsById(String goodsId){
		GoodsVo g = null;
		
		ArrayList rst = new ArrayList();
		
		String sql = "select * from goods where goodsid=?";
		ArrayList params = new ArrayList();
		params.add(goodsId);
		
		DBBean db = new DBBean();
		Connection con = db.getConnection();
		ResultSet rs = null;
		
		try {
			 rs = db.executeQuery(sql, params);//“?”表示params表中的goodsId
			 
			 if(rs != null){
						
				rs.next();
						
				String goodsId_ = rs.getString(1);
				String goodsName = rs.getString(2);
				Float price = rs.getFloat(3);
					
				g = new GoodsVo();
				g.setGoodsId(goodsId_);
				g.setGoodsName(goodsName);
				g.setPrice(price);
					
		     }
			 
			 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return g;
	}
	//获取所有商品占用的页数
	public int getPageCount(){
		int rst = 0;
		DBBean db = new DBBean();
		Connection con = db.getConnection();
		String sql = "select count(*) from goods";
		try {
			ResultSet rs = db.executeQuery(sql,null);
			
			String count = null;
			if(rs.next())
				 count = rs.getString(1);
			
			rst = Integer.parseInt(count);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return (rst-1)/2+1;
		
	}
}
