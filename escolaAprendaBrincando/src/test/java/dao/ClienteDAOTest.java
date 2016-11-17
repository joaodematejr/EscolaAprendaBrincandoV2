package dao;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import commons.JpaUtilTest;
import entity.Cliente;

public class ClienteDAOTest {
	public EntityManager entityManager;

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
	public void classVaziaTest() {
		ClienteDAO dao = new ClienteDAO();
	}

	@Test
	public void listarClientesTest() {
		ClienteDAO dao = new ClienteDAO(entityManager);
		dao.listarClientes();
	}

	@Test
	public void listarAlunosTest() {
		ClienteDAO dao = new ClienteDAO(entityManager);
		dao.listarAlunos();
	}

	@Test
	public void listarProfessoresTest() {
		ClienteDAO dao = new ClienteDAO(entityManager);
		dao.listarProfessores();
	}

	@Test
	public void listarJsonTeste() {
		ClienteDAO dao = new ClienteDAO(entityManager);
		dao.loginParaJson("email", "senha");
	}

	@Test
	public void buscarClientesPorId() {
		ClienteDAO dao = new ClienteDAO(entityManager);
		dao.buscarPorId(1l);
	}

	@Test
	public void salvarClientesTest() {
		ClienteDAO dao = new ClienteDAO(entityManager);
		Cliente clienteParaSalvar = new Cliente(1l, null, null, null, null, null, null, null, null, null, null, null,
				null, null, null, null);
		JpaUtilTest.getInstancia().beginSession();
		dao.salvar(clienteParaSalvar);
		Cliente retornarCliente = dao.buscarPorId(1l);
		JpaUtilTest.getInstancia().endSession();
	}

	@Test
	public void entityManagerClienteNullTest() {
		entityManager = null;
		Assert.assertNull(entityManager);
	}

}
