package org.mila.uploader.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.mila.uploader.entities.TagRequest;
import org.mila.uploader.entities.User;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class TagRequestServiceImpl implements TagRequestService {
	private EntityManager entityManager;

	@Override
	public List<TagRequest> findAll() {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<TagRequest> query = cb.createQuery(TagRequest.class);
		return getEntityManager().createQuery(query).getResultList();
	}

	@Override
	public void save(TagRequest request) {
		if (request.getId() == null) {
			getEntityManager().persist(request);
		} else {
			getEntityManager().merge(request);
		}
	}

	@Override
	public void remove(long id) {
		TagRequest request = find(id);
		if (null != request)
			getEntityManager().remove(request);

	}

	@Override
	public TagRequest find(long id) {
		return getEntityManager().find(TagRequest.class, id);
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<TagRequest> findAllByUser(User user) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<TagRequest> q = cb.createQuery(TagRequest.class);
		Root<TagRequest> root = q.from(TagRequest.class);
		q.where(cb.equal(root.get("user"), user));
		return getEntityManager().createQuery(q).getResultList();
	}

}
