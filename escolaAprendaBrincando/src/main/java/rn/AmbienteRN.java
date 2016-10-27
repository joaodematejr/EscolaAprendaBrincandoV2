package rn;

import java.util.List;

import dao.AmbienteDAO;
import entity.Ambiente;

public class AmbienteRN {

	private AmbienteDAO dao;

	public AmbienteRN() {
		dao = new AmbienteDAO();
	}

	public void salvar(Ambiente ambiente) {
		dao.salvar(ambiente);
	}

	public Ambiente buscarPorId(Long id) {
		return dao.buscarPorId(id);
	}

	public List<Ambiente> listarAmbientes() {
		return dao.listarAmbientes();
	}

	public void excluir(Long id) {
		dao.excluir(id);
	}

}
