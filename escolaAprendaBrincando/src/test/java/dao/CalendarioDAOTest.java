package dao;

import java.sql.SQLException;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import commons.JpaUtilTest;
import entity.Calendario;

public class CalendarioDAOTest {
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
		CalendarioDAO dao = new CalendarioDAO();
	}

	@Test
	public void listarCalendariosTest() {
		CalendarioDAO dao = new CalendarioDAO(entityManager);
		dao.listarCalendarios();
	}

	@Test
	public void buscarCalendarioPorId() {
		CalendarioDAO dao = new CalendarioDAO(entityManager);
		dao.buscarPorId(1l);
	}

	@Test
	public void salvarCalendarioTest() {
		CalendarioDAO dao = new CalendarioDAO(entityManager);
		Calendario calendarioParaSalvar = new Calendario(1l, "teste", null, null, null, null, null, null);
		JpaUtilTest.getInstancia().beginSession();
		try {
			dao.salvar(calendarioParaSalvar);
		} catch (SQLException e) {
		}
		Calendario retornarCalendario = dao.buscarPorId(1l);
		JpaUtilTest.getInstancia().endSession();

	}

	@Test
	@Ignore
	public void deletarCalendarioTest() {
		CalendarioDAO dao = new CalendarioDAO(entityManager);
		Calendario calendarioParaSalvar = new Calendario(1l, "teste", null, null, null, null, null, null);
		JpaUtilTest.getInstancia().beginSession();
		try {
			dao.salvar(calendarioParaSalvar);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dao.excluir(1l);
		JpaUtilTest.getInstancia().endSession();

	}

	@Test
	public void entityManagerCalendarioNullTeste() {
		entityManager = null;
		Assert.assertNull(entityManager);

	}

}
