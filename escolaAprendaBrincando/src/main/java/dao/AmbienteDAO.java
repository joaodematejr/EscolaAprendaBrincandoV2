package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entity.Ambiente;

public class AmbienteDAO extends DAO {

	public void salvar(Ambiente ambiente) {
		getEM().merge(ambiente);

	}

	public Ambiente buscarPorId(Long id) {

		return getEM().find(Ambiente.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Ambiente> listarAmbientes() {
		Query query = getEM().createQuery("From Ambiente", Ambiente.class);
		return query.getResultList();

	}

	public void excluir(Long id) {
		Ambiente ambiente = getEM().getReference(Ambiente.class, id);
		getEM().remove(ambiente);

	}

	public AmbienteDAO() {

	}

	public AmbienteDAO(EntityManager entityManager) {
		super(entityManager);
	}

}