package dao;

import java.util.Date;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import commons.JpaUtilTest;
import entity.Ambiente;

public class AmbienteDAOTest {

	private EntityManager entityManager;

	@BeforeClass
	public static void initClass() {
		JpaUtilTest.getInstancia().initEntityManagerFactory();
	}

	@AfterClass
	public static void finishClass() {
		JpaUtilTest.getInstancia().closeEntityManagerFactory();
	}

	@Before
	public void initTest() {
		entityManager = JpaUtilTest.getInstancia().getEntityManager();
	}

	@After
	public void finishTest() {
		JpaUtilTest.getInstancia().closeEntityManager();
	}

	@Test
	public void entityManagerByUsuarioDaoIsNotNullTest() {
		Assert.assertNotNull(entityManager);
	}

	@Test
	public void entityManagerByUsuarioDaoIsNullTest() {
		entityManager = null;
		Assert.assertNull(entityManager);
	}

	@Test@Ignore
	public void salvaUsuarioTest() {
		AmbienteDAO dao = new AmbienteDAO(entityManager);
		Ambiente ambienteSave = new Ambiente();

		JpaUtilTest.getInstancia().beginSession();
		dao.salvar(ambienteSave);
		JpaUtilTest.getInstancia().endSession();

		Ambiente ambienteRecuperado = dao.buscarPorId(1l);

		Assert.assertTrue(ambienteSave.equals(ambienteRecuperado));
	}

}
