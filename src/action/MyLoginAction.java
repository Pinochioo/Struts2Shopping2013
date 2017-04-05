package action;

import org.omg.CORBA.PRIVATE_MEMBER;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class MyLoginAction extends ActionSupport {
	
	private String username;
	private String password;
	
	@Override
	public String execute() throws Exception {
		/*if(isValidate(username)){
			return INPUT;
		}
		if(isValidate(password)){
			return INPUT;
		}*/
		
		if("qtech".equals(username)&&"java".equals(password)){
			//登陆成功，通过ActionContext对象访问Web应用的Session
			ActionContext.getContext().getSession().put("user",getUsername());
			ActionContext.getContext().getSession().put("pass",getPassword());
			System.out.println(getUsername() + "----" + getPassword());
			return SUCCESS;
		}else{
			addActionError("用户名或密码不正确！");
			return INPUT;
		}
		
	}
	
	/*private boolean isValidate(String value){
		return (value == null || value.length() == 0);
	}*/
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
