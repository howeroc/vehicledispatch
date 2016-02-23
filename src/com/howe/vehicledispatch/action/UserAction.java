package com.howe.vehicledispatch.action;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.struts2.ServletActionContext;
import com.howe.vehicledispatch.bean.User;
import com.howe.vehicledispatch.service.IUserService;
import com.howe.vehicledispatch.util.StringUtil;
import com.opensymphony.xwork2.ActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@SuppressWarnings("serial")
public class UserAction extends IAction {
	
	@Autowired
	private IUserService userService;
	private User user;
	private String confirmPassword;
	private Date startDate;
	private Date endDate;

    
	public String execute(){
		
		if ("add".equals(action))
			
			return add();
		
		if ("login".equals(action))
			
			return login();

		if ("delete".equals(action))
			
			return delete();
		if ("edit".equals(action))
			
			return edit();
		
		if ("pager".equals(action))
			
			return pager();
		if ("logout".equals(action))
			
			return logout();
		setMessage("请求错误，没有可执行操作！");
		
		return "search";
	}

	public String login() {
		
		setTitle("用户登录");
		
		if (user.getId() == null || StringUtil.isNull(user.getPassword())){
			
			setMessage("请正确输入账号和密码！");
			
			return "login";	
		}
		
		try{
			
			User user = this.userService.loginSearch(this.user.getId(), this.user.getPassword());
			
			if (user == null){
				
				setMessage("用户名或密码错误");
				
				return "login";
				
			}else{
				if(user.getType()==1){
					ActionContext.getContext().getSession().put("user", user);
					setMessage("普通管理员登录成功！");
					return "nav2";
					
				}
				
				ActionContext.getContext().getSession().put("user", user);
				
				setMessage("登录成功");
				
				return "nav";
			}
			
		}catch (Exception e){
			
			setMessage("发生错误" + e.getMessage());			
		}
		
		return "login";
	}
	
	public String add(){
		
		setTitle("添加管理员");
		
		Integer param = Integer.parseInt(getParam("param"));
		
		if (param == 0){
			
			return "add";
		}
		
		if(param == 1){
			
			if (StringUtil.isNull(user.getName()) ||StringUtil.isNull(user.getPassword()) ){
				
				setMessage("请填写信息!");
				
				return "add";
			}
			if (!user.getPassword().equals(confirmPassword)) {
				
				setMessage("密码不一致!");
	
				return "add";
			}
	
			try {
				
				if(this.userService.findUserByName(user.getName()) !=null){
					
					setMessage("用户\""+user.getName()+"\"已存在");
					
					return "add";
					
				}
				
				this.userService.create(user);
	
				setMessage("成功添加管理员\""+user.getName()+"\"!");
	
				return "search";
	
			} catch (Exception e) {
				
				setMessage("发生错误：" + e.getMessage());
				
				return "add";
				
			}
		
		}
		
		setMessage("发生错误！");
		
		return "fail";

	}

	public String edit(){
		
		setTitle("修改管理员信息");
		
		try {
			
			Integer param = Integer.parseInt(getParam("param"));
			
			if(param == 0){
				
				Integer userId = Integer.parseInt(getParam("userId"));
				
				setUser(this.userService.findUserById(User.class, userId));
				
				return "edit";
				
			}else if(param == 1){
				
				userService.update(user);
				
				setMessage("修改成功！");
				
				return "search";
			}
		} catch (Exception e) {

			e.printStackTrace();
			
			return "edit";
		}
		
		return "search";
	}
	
	public String delete(){
		
		Integer userId = Integer.parseInt(getParam("userId"));
		
		if(null == this.userService.findUserById(User.class, userId)){
			
			setMessage("不存在此管理员");
			
			return "search";
		}
		
		this.userService.delete(userId);
		
		setMessage("删除成功");
		return "search";
	}

	public String pager(){
		
		if(user==null && startDate==null && endDate==null&&pno==null){
			
			hql = "FROM User";
			
			page = this.getUserService().userPager(1, hql);
			
			setMessage("搜索条件为空，默认显示所有信息！");
			
			ServletActionContext.getRequest().setAttribute("page", page);
			
			ServletActionContext.getRequest().setAttribute("searchInfo",hql);
			
			return "search";
		} else
			
		if( pno == null ){
			
			StringBuffer buffer = new StringBuffer(" SELECT  u FROM User u WHERE 1=1");
			
			if(!StringUtil.isNull(user.getId())){
				
				buffer.append(" AND u.id="+ user.getId());
			}

			if(!StringUtil.isNull(user.getName())){
				
				buffer.append(" AND u.name Like '%"+ user.getName()+"%'");
			}
			
			if(!StringUtil.isNull(user.getBirthdate())){
				
				String minDate = new SimpleDateFormat("yyyy-mm-dd").format(user.getBirthdate());

				buffer.append(" AND u.birthdate >= '"+ minDate +"'");
			}
			
			if(!StringUtil.isNull(user.getWorkdate())){
				
				String maxDate = new SimpleDateFormat("yyyy-mm-dd").format(user.getWorkdate());				
				
				buffer.append(" AND u.birthdate <= '"+ maxDate +"'");
			}
			
			if(!StringUtil.isNull(startDate)){
				
				String minDate = new SimpleDateFormat("yyyy-mm-dd").format(startDate);
				
				buffer.append(" AND u.workdate >= '"+ minDate +"'");
			}
			
			if(!StringUtil.isNull(endDate)){
				
				String maxDate = new SimpleDateFormat("yyyy-mm-dd").format(endDate);
		
				buffer.append(" AND u.workdate <= '"+ maxDate +"'");
			}
			
			if(!StringUtil.isNull(user.getGender())){
				
				buffer.append(" AND u.gender="+ user.getGender());
			}
			
			if(!StringUtil.isNull(user.getType())){
				
				buffer.append(" AND u.type="+ user.getType());
			}
			
			hql = buffer.toString();
			
			System.out.println(hql);
			
			System.out.println("搜索"+hql);
			
			page = this.getUserService().userPager(1, hql);
			
			setMessage("成功查找到相关信息！");
			
			ServletActionContext.getRequest().setAttribute("searchInfo", hql);
			
			ServletActionContext.getRequest().setAttribute("page", page);
			
			return "search";
		}else{
		
		Integer pno = Integer.parseInt(getParam("pno"));
			
		String searchInfo = getParam("searchInfo");
		
		System.out.println("分页"+hql);
		
		page = this.getUserService().userPager(pno, searchInfo);
		
		ServletActionContext.getRequest().setAttribute("searchInfo", searchInfo);
		
		ServletActionContext.getRequest().setAttribute("page", page);
		
		return "search";
		}
		
	}
	
	protected String getParam(String key){
		return ServletActionContext.getRequest().getParameter(key);
	}


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	public static String getLogin() {
		return LOGIN;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String logout(){
		ServletActionContext.getRequest().getSession().removeAttribute("user");
		return "login";
	}
}
