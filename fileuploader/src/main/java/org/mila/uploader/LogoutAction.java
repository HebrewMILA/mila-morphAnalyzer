package org.mila.uploader;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LogoutAction extends ActionSupport {
	private static final long serialVersionUID = -8668862680746334161L;

	@Override
	public String execute() {
		ActionContext.getContext().getSession().remove("logged-in-user");
		return SUCCESS;
	}

}
