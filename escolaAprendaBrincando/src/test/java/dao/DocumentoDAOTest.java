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
import entity.Documento;

public class DocumentoDAOTest {
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
		AlbumDAO dao = new AlbumDAO();
	}

	@Test
	public void listarDocumentosTest() {
		AlbumDAO dao = new AlbumDAO(entityManager);
		dao.listarDocumentoPorTurma(1l);
	}

	@Test
	public void buscarDocumentosPorId() {
		AlbumDAO dao = new AlbumDAO(entityManager);
		dao.buscarDocumentoPorId(1l);
	}

	@Test
	public void salvarDocumentoTest() {
		AlbumDAO dao = new AlbumDAO(entityManager);
		Documento documentoParaSalvar = new Documento(1l, null, null, null, null);
		JpaUtilTest.getInstancia().beginSession();
		dao.salvar(documentoParaSalvar);
		Documento retornarDocumento = dao.buscarDocumentoPorId(1l);
		JpaUtilTest.getInstancia().endSession();
	}

	@Test
	public void entityManagerDocumentosNullTest() {
		entityManager = null;
		Assert.assertNull(entityManager);
	}

	@Test@Ignore
	public void deletarDocumentoTest() {
		AlbumDAO dao = new AlbumDAO(entityManager);
		Documento documentoParaSalvar = new Documento(1l, null, null, null, null);
		JpaUtilTest.getInstancia().beginSession();
		dao.salvar(documentoParaSalvar);
		dao.excluir(documentoParaSalvar);
		JpaUtilTest.getInstancia().endSession();
	}
	
	

}
