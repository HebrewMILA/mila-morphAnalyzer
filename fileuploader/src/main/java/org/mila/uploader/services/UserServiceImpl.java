package org.mila.uploader.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.mila.uploader.entities.User;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class UserServiceImpl implements UserService {
	private EntityManager entityManager;

	@Override
	public List<User> findAll() {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<User> query = cb.createQuery(User.class);
		return getEntityManager().createQuery(query).getResultList();
	}

	@Override
	public void save(User request) {
		getEntityManager().persist(request);
	}

	@Override
	public void remove(String mail) {
		User request = find(mail);
		if (null != request)
			getEntityManager().remove(request);

	}

	@Override
	public User find(String mail) {
		return getEntityManager().find(User.class, mail);
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
