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
import entity.Cliente;

public class ClienteDAOTest {

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
		ClienteDAO dao = new ClienteDAO(entityManager);
		Cliente clienteSave = new Cliente();
		JpaUtilTest.getInstancia().beginSession();
		dao.salvar(clienteSave);
		JpaUtilTest.getInstancia().endSession();
		Cliente usuarioRecuperado = dao.buscarPorEmail("joao@email.com");
		Assert.assertTrue(clienteSave.equals(usuarioRecuperado));
	}

}
