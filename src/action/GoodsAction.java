package action;

import java.util.List;
import com.opensymphony.xwork2.ActionSupport;
import bean.vo.*;
import bean.dao.*;

public class GoodsAction extends ActionSupport {
	private int pageNo = 1;
	private int pageCount;
	private List<GoodsVo> goodsList;//货物列表
	
	public String findAllGoods() throws Exception {
		GoodsDAO goodsDAO = new GoodsDAO();
		goodsList = goodsDAO.getGoodsByPage(pageNo);//商品列表
		pageCount = goodsDAO.getPageCount();//商品总页数
		return SUCCESS;
	}//自定义的业务方法最好不要用get打头，否则struts2会自动调用该方法
	
	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public List<GoodsVo> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<GoodsVo> goodsList) {
		this.goodsList = goodsList;
	}
	
	
}
