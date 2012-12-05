package org.mila.uploader.services;

import java.util.List;

import org.mila.uploader.entities.TagRequest;
import org.mila.uploader.entities.User;

public interface TagRequestService {
	public List<TagRequest> findAll();
	public List<TagRequest> findAllByUser(User user);
	public void save(TagRequest request);
	public void remove(long id);
	public TagRequest find(long id);
}
