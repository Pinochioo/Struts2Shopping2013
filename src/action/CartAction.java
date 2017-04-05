package action;

import java.util.ArrayList;
import java.util.Map;

import javax.jms.Session;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import bean.*;
import bean.vo.GoodsVo;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class CartAction extends ActionSupport {
	private String goodsId;
	private int quantity;
	
	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	ActionContext ctx = ActionContext.getContext();
	CartManager cartManager = new CartManager();
	ArrayList<Item> cart;
	
	public ArrayList<Item> getCart() {
		return cart;
	}

	public void setCart(ArrayList<Item> cart) {
		this.cart = cart;
	}

	public String addToCart() throws Exception{
		//ArrayList<Item> cart = (ArrayList<Item>)ctx.getSession().get("cart");//得到会话,将Map类型的ctx.getSession强制转换为ArrayList
		cart = (ArrayList<Item>)ctx.getSession().get("cart");
		if(cart != null){//如果session中cart对象为空
			cartManager.setCart(cart);
			cartManager.addToCart(getGoodsId(), 1);//将一件id为goodsId的商品添加进购物车
		}else{
			cart = new ArrayList<Item>();
			cartManager.setCart(cart);
			cartManager.addToCart(getGoodsId(), 1);
		}
		//System.out.println(getGoodsId());
		//ctx.getSession().put("cart", cartManager.getCart());//在session里存入cart，以后可以通过get("cart")获取
		
		Map session=ctx.getSession();
		session.put("cart", cart);
		ctx.setSession(session);//当用<c:forEach>标签获取session时需要setSession
		
		System.out.println(ctx.getSession().get("cart"));
		return "usercart";
	}
	
	public String modifyGoods() throws Exception{
		cart = (ArrayList<Item>)ctx.getSession().get("cart");
		//int quantity = Integer.parseInt(ServletActionContext.getRequest().getParameter("quantity"));
		cartManager.setCart(cart);
		cartManager.update(getGoodsId(), getQuantity());
		ctx.getSession().put("cart", cartManager.getCart());
		
		System.out.println(this.getQuantity());
		return "usercart";
	}
	
	public String deleteGoods() throws Exception{
		cart = (ArrayList<Item>)ctx.getSession().get("cart");
		cartManager.setCart(cart);
		cartManager.delete(getGoodsId());
		ctx.getSession().put("cart", cartManager.getCart());
		
		System.out.println("已删除该商品");
		return "usercart";
	}
	
	public String clearCart() throws Exception{
		cart = (ArrayList<Item>)ctx.getSession().get("cart");
		ctx.getSession().remove("cart");
		System.out.println("购物车已清空");
		return "usercart";
	}
}
