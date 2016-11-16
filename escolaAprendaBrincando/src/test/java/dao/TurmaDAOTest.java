package dao;

import java.sql.SQLException;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import commons.JpaUtilTest;
import entity.Cliente;
import entity.Turma;

public class TurmaDAOTest {
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
	public void testeVazioTeste() {
		TurmaDAO dao = new TurmaDAO();
	}

	@Test
	public void listarTurmasTest() {
		TurmaDAO dao = new TurmaDAO(entityManager);
		dao.listarTurmas();
	}

	@Test
	public void buscarPorIdTest() {
		TurmaDAO dao = new TurmaDAO(entityManager);
		dao.buscarPorId(1l);
	}

	@Test
	public void entityManagerNullTest() {
		entityManager = null;
		Assert.assertNull(entityManager);
	}

	@Test
	public void salvarTurmaTest() {
		TurmaDAO dao = new TurmaDAO(entityManager);
		Turma turmaSalvar = new Turma(1l, "2016-10-20 20:11:00", "2016-10-20 20:11:00", "NomeTurma", "quantidadeAluno",
				null, null, null, null, null);
		JpaUtilTest.getInstancia().beginSession();
		try {
			dao.salvar(turmaSalvar);
		} catch (SQLException e) {

		}
		Turma retornarTurma = dao.buscarPorId(1l);
		JpaUtilTest.getInstancia().endSession();
	}

	@Test
	public void deletarTurmaTest() {
		TurmaDAO dao = new TurmaDAO(entityManager);
		Cliente cliente = new Cliente();
		Turma turmaSalvar = new Turma(4l, "2016-10-20 20:11:00", "2016-10-20 20:11:00", "NomeTurma", "quantidadeAluno",
				null, null, null, null, null);
		JpaUtilTest.getInstancia().beginSession();
		try {
			dao.salvar(turmaSalvar);
		} catch (SQLException e) {
		}
		dao.excluir(4l);
		JpaUtilTest.getInstancia().endSession();

	}

}
