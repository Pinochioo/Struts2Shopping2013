<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'userCart.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script>
		function clearCart(){
			targetForm = document.forms[0];
			targetForm.action="clearCart";
		}
		function modifyGoods(item){
			targetForm = document.forms[item];
			targetForm.action="modifyGoods";
		}
		function deleteGoods(item){
			targetForm = document.forms[item];
			targetForm.action="deleteGoods";
		}
	</script>
  </head>
  
  <body>
    <a href="getAllGoods">继续购物</a>&nbsp; &nbsp;
    <a href="clearCart" onclick="clearCart();">清空购物车</a>
    <table border=1>
    <tr>
    	<td>物品编号</td>
    	<td>物品价格</td>
    	<td>物品名称</td>
    	<td>物品数量</td>
    	<td>操作</td>
    </tr>
    <s:iterator var="item" value="#session.cart" status="st">
   <%--  <c:forEach var="item" items="${sessionScope.cart}"> --%>
	    <tr>
		    <form method="post"> 
				<input type="hidden" name="goodsId" value="${item.goods.goodsId}"/><!-- goods表示在Item.java中声明的GoodsVo对象，以此来调用GoodsVo中的变量 -->
		    	<td>${item.goods.goodsId}</td>
		    	<td>${item.goods.goodsName}</td>
		    	<td>${item.goods.price}</td>
		    	<td><input type="text" name="quantity" value="${item.quantity}"></td>
		    	<td>
		    		<input type="submit" value="修改" onclick="modifyGoods(<s:property value="#st.index"/>);"/>
		    		<input type="submit" value="删除" onclick="deleteGoods(<s:property value="#st.index"/>);"/>
		    	</td>
		    </form>
	    </tr>
	    </s:iterator>
    <%-- </c:forEach> --%>
    </table>
  </body>
</html>
