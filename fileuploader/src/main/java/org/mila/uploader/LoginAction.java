package org.mila.uploader;

import java.util.Map;

import org.mila.uploader.entities.User;
import org.mila.uploader.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Service
public class LoginAction extends ActionSupport {
	private static final long serialVersionUID = -5295102202348533467L;

	private String username;
	private String password;
	private UserService userService;

	@Override
	public String execute() {
		User user = userService.find(username);
		if (user == null)
			return ERROR;
		if (user.checkPassword(this.getPassword())) {
			Map<String, Object> session = ActionContext.getContext().getSession();
			session.put("logged-in-user", username);
			return SUCCESS;
		}
		return ERROR;
	}
	
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
