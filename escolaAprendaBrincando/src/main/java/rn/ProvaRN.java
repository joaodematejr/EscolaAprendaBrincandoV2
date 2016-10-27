package rn;

import java.util.List;

import dao.ProvaDAO;
import entity.Prova;

public class ProvaRN {
	private ProvaDAO dao;

	public ProvaRN() {
		dao = new ProvaDAO();
	}

	public List<Prova> listar() {
		return dao.listar();
	}

	public void salvar(Prova prova) throws IllegalArgumentException, Exception {
		dao.salvar(prova);
	}

	public Prova buscarPorId(Long id) {
		return dao.buscarPorId(id);
	}

	public void excluir(Long id) {
		dao.excluir(id);
	}
}
