package org.mila.uploader;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.MagicNumberFileFilter;
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
	private static byte[] ZIP_MAGIC = new byte[] { 0x50, 0x4B, 0x03, 0x04 };
	private static MagicNumberFileFilter zipfilter = new MagicNumberFileFilter(
			ZIP_MAGIC);

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

		if (file == null || filename == null)
			return ERROR;
		
		if (zipfilter.accept(file)) {
			/* This is a zip file */
			logger.info("Processing zipfile upload, tempDir is: " + tempDir);
			ZipFile zipfile;
			try {
				zipfile = new ZipFile(file);
			} catch (ZipException e) {
				logger.error("Some sort of zip file problem, giving up", e);
				return ERROR;
			} catch (IOException e) {
				logger.error("Some sort of zip file IOException, giving up", e);
				return ERROR;
			}

			/* If we got this far in the code, we don't actually fail anymore... */
			for (ZipEntry entry : Collections.list(zipfile.entries())) {
				String uploadedFilename = filename + ":" + entry.getName();
				try {
					assert tempDir != null;
					File copy = File.createTempFile("input", "", new File(
							tempDir));
					FileUtils.copyInputStreamToFile(
							zipfile.getInputStream(entry), copy);
					copy.deleteOnExit();
					createTagRequest(user, copy, uploadedFilename);
				} catch (IOException e) {
					logger.warn(
							"Couldn't create tag request for a zip entry, continuing...",
							e);
				}
			}
			return SUCCESS;
		} else {
			/* This is just a normal file */
			logger.info("Processing upload, tempDir is: " + tempDir);
			try {
				assert tempDir != null;
				File copy = File.createTempFile("input", "", new File(tempDir));
				FileUtils.copyFile(file, copy);
				copy.deleteOnExit();
				return createTagRequest(user, copy, filename);
			} catch (IOException e) {
				logger.error("Can't save uploaded file, giving up tagging", e);
				return ERROR;
			}
		}
	}

	private String createTagRequest(User user, File workfile,
			String uploadedFilename) {
		TagRequest tagreq = new TagRequest();
		tagreq.setUser(user);
		tagreq.setState(TagRequestState.WAITING);
		tagreq.setUploadedFilename(uploadedFilename);
		tagreq.setTimestamp(new Timestamp(System.currentTimeMillis()));
		tagRequestService.save(tagreq);

		logger.debug("Adding a tag request, the content-type was: "
				+ contentType);

		asyncBean.handle(workfile, tagreq);

		return SUCCESS;
	}
}
