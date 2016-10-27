package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;//ACESSAR O PERSISTENCE

import entity.Cliente;//ACESSAR A CLASSE CLIENTE

public class ClienteDAO extends DAO {

	public ClienteDAO() {
		
	}

	public ClienteDAO(EntityManager entityManager) {
		super(entityManager);
	}

	public void salvar(Cliente cliente) {
		getEM().merge(cliente);
	}

	public Cliente buscarPorId(Long id) {
		return getEM().find(Cliente.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> listarClientes() {
		Query query = getEM().createQuery("From Cliente", Cliente.class);//
		return query.getResultList();
	}

	public void excluir(Long id) {
		Cliente cliente = getEM().getReference(Cliente.class, id);
		getEM().remove(cliente);
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> listarProfessores() {
		Query query = getEM().createQuery("From Cliente u Where u.perfil = :perfil", Cliente.class);
		query.setParameter("perfil", "ROLE_PROFESSOR");

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> listarAlunos() {
		Query query = getEM().createQuery("From Cliente u Where u.perfil = :perfil", Cliente.class);
		query.setParameter("perfil", "ROLE_ALUNO");

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> listarClientesQuantidade() {
		Query query = getEM().createQuery("SELECT COUNT(id) FROM cliente;", Cliente.class);//
		return query.getResultList();
	}

	public Cliente buscarPorEmail(String email) {
		Query query = getEM().createQuery("From Cliente u Where u.email = :email", Cliente.class);
		query.setParameter("email", email);

		return (Cliente) query.getSingleResult();

	}

}
