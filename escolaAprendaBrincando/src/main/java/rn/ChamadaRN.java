package rn;

import java.util.List;

import dao.ChamadaDAO;
import entity.Chamada;
import entity.Cliente;

public class ChamadaRN {
	private ChamadaDAO dao;

	public ChamadaRN() {
		dao = new ChamadaDAO();
	}

	public void salvar(Chamada chamada) {
		dao.salvar(chamada);
	}

	public Chamada buscarPorId(Long id) {
		return dao.buscarPorId(id);
	}

	public void excluir(Long id) {
		dao.excluir(id);
	}

	public List<Chamada> listarProfessores() {
		return dao.listarProfessores();
	}

	public List<Chamada> listarAlunos() {
		return dao.listarAlunos();
	}

	public Cliente buscarPorEmail(String email) {
		return dao.buscarPorEmail(email);
	}

}
