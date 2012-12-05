package org.mila.uploader;

import java.util.Collections;
import java.util.List;

import org.mila.uploader.entities.TagRequest;
import org.mila.uploader.entities.User;
import org.mila.uploader.services.TagRequestService;
import org.mila.uploader.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Service
public class IndexAction extends ActionSupport {
	private static final long serialVersionUID = -196703909927325895L;
	private UserService userService;
	private TagRequestService tagRequestService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Autowired
	public void setTagRequestService(TagRequestService tagRequestService) {
		this.tagRequestService = tagRequestService;
	}

	public User getUser() {
		return userService.find((String) ActionContext.getContext()
				.getSession().get("logged-in-user"));
	}

	public String getUsername() {
		return getUser().getMail();
	}

	public List<TagRequest> getTagRequests() {
		List<TagRequest> ret = tagRequestService.findAllByUser(getUser());
		Collections.reverse(ret);
		return ret;
	}

}
