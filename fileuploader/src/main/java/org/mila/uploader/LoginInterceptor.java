package org.mila.uploader;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LoginInterceptor extends AbstractInterceptor {
	private static final long serialVersionUID = 3047020731801274748L;
	
	@Override
	public String intercept(final ActionInvocation invocation) throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();

		if (session.containsKey("logged-in-user"))
			return invocation.invoke();
		
		Object action = invocation.getAction();
		/* if the user is not trying to log in right now, have him log in. */
		if (!(action instanceof LoginAction)) {
			return "login";
		}

		/* Good to go. */
		return invocation.invoke();
	}
}