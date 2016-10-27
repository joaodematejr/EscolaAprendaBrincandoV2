package dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.Query;
import entity.Prova;

public class ProvaDAO extends DAO {

	@SuppressWarnings("unchecked")
	public List<Prova> listar() {
		Query query = getEM().createQuery("From Prova", Prova.class);
		return query.getResultList();
	}

	public void salvar(Prova prova) throws SQLException {
		getEM().merge(prova);
	}

	public Prova buscarPorId(Long id) {
		return getEM().find(Prova.class, id);
	}

	public void excluir(Long id) {
		Prova prova = getEM().getReference(Prova.class, id);
		getEM().remove(prova);
	}
}