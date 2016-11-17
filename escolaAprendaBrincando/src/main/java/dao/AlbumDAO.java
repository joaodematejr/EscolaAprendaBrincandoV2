package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entity.Documento;

public class AlbumDAO extends DAO {

	public void salvar(Documento foto) {
		getEM().merge(foto);
	}

	@SuppressWarnings("unchecked")
	public List<Documento> listarDocumentoPorTurma(Long idTurma) {
		Query query = getEM().createQuery("From Documento i Where i.turma.id = :idTurma ", Documento.class);
		query.setParameter("idTurma", idTurma);
		return query.getResultList();
	}

	public Documento buscarDocumentoPorId(Long idDocumento) {
		return getEM().find(Documento.class, idDocumento);
	}

	public void excluir(Documento documento) {
		getEM().remove(documento);
	}

	public AlbumDAO() {

	}

	public AlbumDAO(EntityManager entityManager) {
		super(entityManager);
	}

}
