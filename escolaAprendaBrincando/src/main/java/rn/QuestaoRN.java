package rn;

import java.util.List;

import dao.QuestaoDAO;
import entity.Questao;

public class QuestaoRN {
	private QuestaoDAO dao;

	public QuestaoRN() {
		dao = new QuestaoDAO();
	}

	public void salvar(Questao questao) {
		dao.salvar(questao);
	}

	public List<Questao> listarQuestoes() {
		return dao.listarQuestoes();
	}

	public void excluir(Long id) {
		dao.excluir(id);
	}

	public Questao buscarPorId(Long id) {

		return dao.buscarPorId(id);
	}

}
