package org.mila.uploader.entities;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tagrequests")
public class TagRequest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private User user;
	private String filename;
	private TagRequestState state;
	private Timestamp timestamp;
	private String uploadedFilename;
	private String localCorpusFilename;
	private String tempDirectoryPath;

	public String getFilename() {
		return filename;
	}

	public Long getId() {
		return id;
	}

	public String getLocalCorpusFilename() {
		return localCorpusFilename;
	}

	public TagRequestState getState() {
		return state;
	}

	public String getTempDirectoryPath() {
		return tempDirectoryPath;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public String getUploadedFilename() {
		return uploadedFilename;
	}

	public User getUser() {
		return user;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setLocalCorpusFilename(String localCorpusFilename) {
		this.localCorpusFilename = localCorpusFilename;
	}

	public void setState(TagRequestState state) {
		this.state = state;
	}

	public void setTempDirectoryPath(String tempDirectoryPath) {
		this.tempDirectoryPath = tempDirectoryPath;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public void setUploadedFilename(String uploadedFilename) {
		this.uploadedFilename = uploadedFilename;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
