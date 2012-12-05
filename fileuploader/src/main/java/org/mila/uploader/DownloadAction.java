package org.mila.uploader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.apache.log4j.Logger;
import org.mila.uploader.entities.TagRequest;
import org.mila.uploader.entities.TagRequestState;
import org.mila.uploader.services.TagRequestService;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class DownloadAction extends ActionSupport {
	private static final long serialVersionUID = -2054767182565358683L;
	private static final Logger logger = Logger.getLogger(DownloadAction.class);

	private Long tagRequestID;
	private TagRequestService tagRequestService;
	private InputStream inputStream;

	@Override
	public String execute() {
		TagRequest tagRequest = tagRequestService.find(getTagRequestID());
		if (tagRequest == null
				|| tagRequest.getState() != TagRequestState.FINISHED)
			return ERROR;

		/* don't let people download other people's files */
		if (!tagRequest.getUser().getMail().equals(getUsername()))
			return ERROR;
		
		try {
			inputStream = new FileInputStream(
					new File(tagRequest.getFilename()));
		} catch (FileNotFoundException e) {
			logger.error(
					"Can't find tagged file named " + tagRequest.getFilename(),
					e);
			tagRequest.setState(TagRequestState.ERROR);
			tagRequestService.save(tagRequest);
			return ERROR;
		}
		return SUCCESS;
	}

	public Long getTagRequestID() {
		return tagRequestID;
	}

	public void setTagRequestID(long tagRequestID) {
		this.tagRequestID = tagRequestID;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	@Autowired
	public void setTagRequestService(TagRequestService tagRequestService) {
		this.tagRequestService = tagRequestService;
	}

	public String getUsername() {
		return (String) ActionContext.getContext().getSession()
				.get("logged-in-user");
	}
}
