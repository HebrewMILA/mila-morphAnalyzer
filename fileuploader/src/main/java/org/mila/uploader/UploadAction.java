package org.mila.uploader;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.mila.uploader.entities.TagRequest;
import org.mila.uploader.entities.TagRequestState;
import org.mila.uploader.entities.User;
import org.mila.uploader.services.TagRequestService;
import org.mila.uploader.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Service
public class UploadAction extends ActionSupport {
	private static final long serialVersionUID = 1441970163536952783L;
	private static Logger logger = Logger.getLogger(UploadAction.class);

	private File file;
	private String contentType;
	private String filename;
	private String tempDir;
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

	public String getTempDir() {
		return tempDir;
	}

	public void setTempDir(String tempDir) {
		this.tempDir = tempDir;
	}

	private AsyncBean asyncBean;

	public void setUpload(File file) {
		this.file = file;
	}

	public void setAsyncBean(AsyncBean bean) {
		asyncBean = bean;
	}

	public void setUploadFileName(String filename) {
		this.filename = filename;
	}

	public void setUploadContentType(String contentType) {
		this.contentType = contentType;
	}

	public String execute() {
		User user = userService.find((String) ActionContext.getContext()
				.getSession().get("logged-in-user"));
		TagRequest tagreq = new TagRequest();
		tagreq.setUser(user);
		tagreq.setState(TagRequestState.WAITING);
		tagreq.setUploadedFilename(filename);
		tagreq.setTimestamp(new Timestamp(System.currentTimeMillis()));
		tagRequestService.save(tagreq);

		logger.debug("Adding a tag request, the content-type was: "
				+ contentType);

		try {
			assert tempDir != null;
			logger.info("Processing upload, tempDir is: " + tempDir);
			File copy = File.createTempFile("input", "", new File(tempDir));
			FileUtils.copyFile(file, copy);
			copy.deleteOnExit();
			asyncBean.handle(copy, tagreq);
		} catch (IOException e) {
			logger.error("Can't save uploaded file, giving up tagging", e);
			return ERROR;
		}

		return SUCCESS;
	}
}
