package dao;

import java.util.List;

import javax.persistence.Query;

import entity.Chamada;
import entity.Cliente;

public class ChamadaDAO extends DAO {
	public void salvar(Chamada chamada) {
		getEM().merge(chamada);
	}

	public Chamada buscarPorId(Long id) {
		return getEM().find(Chamada.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Chamada> listarClientes() {
		Query query = getEM().createQuery("From Chamada", Chamada.class);//
		return query.getResultList();
	}

	public void excluir(Long id) {
		Chamada chamada = getEM().getReference(Chamada.class, id);
		getEM().remove(chamada);
	}

	@SuppressWarnings("unchecked")
	public List<Chamada> listarProfessores() {
		Query query = getEM().createQuery("From Chamada u Where u.perfil = :perfil", Cliente.class);
		query.setParameter("perfil", "ROLE_PROFESSOR");

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Chamada> listarAlunos() {
		Query query = getEM().createQuery("From Chamada u Where u.perfil = :perfil", Cliente.class);
		query.setParameter("perfil", "ROLE_ALUNO");

		return query.getResultList();
	}

	public Cliente buscarPorEmail(String email) {
		Query query = getEM().createQuery("From Cliente u Where u.email = :email", Cliente.class);
		query.setParameter("email", email);

		return (Cliente) query.getSingleResult();

	}
}
