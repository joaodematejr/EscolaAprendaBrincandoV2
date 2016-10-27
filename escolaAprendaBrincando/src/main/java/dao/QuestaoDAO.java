package dao;

import java.util.List;

import javax.persistence.Query;
import entity.Questao;

public class QuestaoDAO extends DAO {
	public void salvar(Questao questao) {
		getEM().merge(questao);
	}

	public Questao buscarPorId(Long id) {
		return getEM().find(Questao.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Questao> listarQuestoes() {
		Query query = getEM().createQuery("From Questao u Where u.perfil = :perfil", Questao.class);
		query.setParameter("perfil", "ROLE_PROFESSOR");

		return query.getResultList();

	}

	public void excluir(Long id) {
		Questao questao = getEM().getReference(Questao.class, id);
		getEM().remove(questao);
	}

}
