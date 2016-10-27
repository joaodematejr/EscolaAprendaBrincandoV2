package rn;

import java.util.List;

import dao.AlbumDAO;
import entity.Documento;

public class AlbumRN {

	private AlbumDAO dao;

	public AlbumRN() {
		dao = new AlbumDAO();
	}

	public void adicionar(Documento foto) {
		dao.salvar(foto);
	}

	public List<Documento> listarDocumentoPorTurma(Long idTurma) {
		return dao.listarDocumentoPorTurma(idTurma);
	}

	public Documento buscarDocumentoPorId(Long idDocumento) {
		return dao.buscarDocumentoPorId(idDocumento);
	}

	public void excluir(Documento documento) {
		dao.excluir(documento);
	}

}
