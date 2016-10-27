package rn;

import java.util.List;

import dao.PerguntaDAO;
import entity.Pergunta;

public class PerguntaRN {
	private PerguntaDAO dao;

	public PerguntaRN() {
		dao = new PerguntaDAO();
	}

	public void salvar(Pergunta pergunta) {
		dao.salvar(pergunta);
	}

	public Pergunta buscarPorId(Long id) {
		return dao.buscarPorId(id);
	}

	public List<Pergunta> listarPerguntas() {
		return dao.listarPerguntas();
	}

	public void excluir(Long id) {
		dao.excluir(id);
	}

	public Pergunta buscarPorEmail(String email) {
		return dao.buscarPorEmail(email);
	}

}
