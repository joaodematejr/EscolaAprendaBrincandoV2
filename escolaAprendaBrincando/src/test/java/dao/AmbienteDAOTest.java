package dao;

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
		JpaUtilTest.getInstancia().closeEntityManager();
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
	public void testeVazioTest() {
		AmbienteDAO dao = new AmbienteDAO();
	}

	@Test
	public void listarAmbientesTest() {
		AmbienteDAO dao = new AmbienteDAO(entityManager);
		dao.listarAmbientes();
	}

	@Test
	public void buscarPorIdTest() {
		AmbienteDAO dao = new AmbienteDAO(entityManager);
		dao.buscarPorId(1l);
	}

	@Test
	public void salvarAmbienteTest() {
		AmbienteDAO dao = new AmbienteDAO(entityManager);
		Ambiente ambienteSalvar = new Ambiente(1l, "descrição", "nome", "quantidade");
		JpaUtilTest.getInstancia().beginSession();
		dao.salvar(ambienteSalvar);
		Ambiente retornarAmbiente = dao.buscarPorId(1l);
		JpaUtilTest.getInstancia().endSession();

	}

	@Test
	@Ignore
	public void deletarAmbienteTest() {
		AmbienteDAO dao = new AmbienteDAO(entityManager);
		Ambiente ambienteSalvar = new Ambiente(3l, null, null, null);
		JpaUtilTest.getInstancia().beginSession();
		dao.salvar(ambienteSalvar);
		dao.excluir(3l);
		JpaUtilTest.getInstancia().endSession();

	}

	@Test
	public void entityManagerAmbienteNullTeste() {
		entityManager = null;
		Assert.assertNull(entityManager);
	}

}
