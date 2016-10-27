package dao;

import javax.persistence.EntityManager;

import commons.JpaUtil;
import commons.JpaUtilTest;

public abstract class DAO {

	private EntityManager entityManager = null;

	public DAO() {
		super();
	}

	public DAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	protected EntityManager getEM() {
		if (this.entityManager == null) {
			return JpaUtil.getEntityManager();
		}
		return this.entityManager;
	}

}