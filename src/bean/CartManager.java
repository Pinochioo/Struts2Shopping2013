package bean;

import java.util.ArrayList;
import java.util.Iterator;

import com.opensymphony.xwork2.ActionContext;

import bean.dao.GoodsDAO;
import bean.vo.GoodsVo;

public class CartManager {
	ArrayList<Item> cart = new ArrayList<Item>();
	//添加商品到购物车
	public void addToCart(String goodsId, int quantity){
		GoodsDAO goodsDAO = new GoodsDAO();
		
		GoodsVo g = goodsDAO.getGoodsById(goodsId);//通过商品id获取商品完整信息
		
		Iterator<Item> it = cart.iterator();
		boolean find = false;
		while(it.hasNext()){  //当购物车中有商品，执行此循环
			Item oneItem = it.next();
			if(oneItem.getGoods().getGoodsId().equalsIgnoreCase(goodsId)){
				oneItem.setQuantity(oneItem.getQuantity() + quantity);
				find = true;  //置find为找到该商品
			}
		}
		if(!find){   //如果没有找到该商品，将该新商品添加进购物车列表（存放进ArrayList）
				Item newItem = new Item(g,quantity);
				cart.add(newItem);
		}
	}
	
	//更新购物车
	public void update(String goodsId, int quantity){
//		System.out.println(goodsId);
		Iterator<Item> it = cart.iterator();
		while(it.hasNext()){
			Item oneItem = it.next();
			if(oneItem.getGoods().getGoodsId().equalsIgnoreCase(goodsId)){
				oneItem.setQuantity(quantity);
				break;
			}
		}
		
	}
	
	//删除某件商品
	public void delete(String goodsId){
		if(cart != null){
			Iterator it = cart.iterator();
			while(it.hasNext()){
				Item temp = (Item)it.next();
				String tGoodsId = temp.getGoods().getGoodsId();
				
				if(tGoodsId.equals(goodsId)){
					cart.remove(temp);
					break;
				}
				
			}//while
			
		}//if
	}
	
	
	public ArrayList<Item> getCart() {
		return cart;
	}

	public void setCart(ArrayList<Item> cart) {
		this.cart = cart;
	}
}
