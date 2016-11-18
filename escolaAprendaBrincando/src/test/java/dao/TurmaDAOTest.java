package dao;

import java.sql.SQLException;
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
		Cliente clienteProfessor = new Cliente();
		Cliente clienteAluno = new Cliente();
		Turma turmaSave = new Turma(2l, "NomeTurmaTeste", "Qalunos", "TurnoTeste", "SemetreTeste", new Date(),
				new Date(), clienteProfessor, null, null);
		JpaUtilTest.getInstancia().beginSession();
		try {
			dao.salvar(turmaSave);
		} catch (SQLException e) {
		}
		Turma retornarTurma = dao.buscarPorId(1l);
		JpaUtilTest.getInstancia().endSession();
	}

	@Test
	@Ignore
	public void excluirUsuarioTest() throws SQLException {
		TurmaDAO dao = new TurmaDAO(entityManager);
		Turma turmaSave = new Turma(5l, null, null, null, null, null, null, null, null, null);
		JpaUtilTest.getInstancia().beginSession();
		dao.salvar(turmaSave);
		dao.excluir(5l);

	}

	@Test
	public void entityManagerTurmaNullTeste() {
		entityManager = null;
		Assert.assertNull(entityManager);
	}

}
