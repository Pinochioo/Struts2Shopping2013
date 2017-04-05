package interceptor;

import java.util.Map;

import action.GoodsAction;
import action.MyLoginAction;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AuthorityInterceptor extends AbstractInterceptor {
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		//System.out.println("bbbbbbbbbbbbbbbbbbb");
		ActionContext ctx = invocation.getInvocationContext();//取得请求相关的ActionContext实例
		Map session = ctx.getSession();//通过ActionContext访问session
		String username = (String)session.get("user");//取出MyAction.java中通过ActionContext存入session中的user属性
		//如果没登陆或登陆用户不是qtech，都返回重新登陆
		if(username != null && username.equals("qtech")){
			return invocation.invoke();
		}
		//System.out.println("aaaaaaaaaaaaaaaaaaaaaaaa");
		ctx.put("tip", "您还没有登陆，请输入qtech,java登陆系统");
		return Action.INPUT;//直接返回login的逻辑视图
	}
}
