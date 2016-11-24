package dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.mysql.jdbc.PreparedStatement;

import entity.Calendario;
import entity.Cliente;

public class CalendarioDAO extends DAO {

	PreparedStatement pstm;

	public void salvar(Calendario calendario) throws SQLException {
		getEM().merge(calendario);

	}

	public Calendario buscarPorId(Long id) {
		return getEM().find(Calendario.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Calendario> listarCalendarios() {
		Query query = getEM().createQuery("From Calendario", Calendario.class);//
		return query.getResultList();

	}

	public List<Calendario> listarCalendarioPorProfessor(Long id) {
		Query query = getEM().createQuery("From Calendario i Where i.professor.id = :idProfessor ", Calendario.class);
		query.setParameter("idProfessor", id);
		return query.getResultList();
	}

	public void excluir(Long id) {
		Calendario calendario = getEM().getReference(Calendario.class, id);
		getEM().remove(calendario);

	}

	@SuppressWarnings("unchecked")
	public List<Calendario> buscarPorDatas(Date inicio, Date fim) {
		Query query = getEM().createQuery("SELECT c FROM Calendario c WHERE inicio BETWEEN (:inicio) AND (:fim)",
				Calendario.class);
		query.setParameter("inicio", inicio);
		query.setParameter("fim", fim);
		return query.getResultList();
	}

	public Calendario buscarPorData(Date inicio) {
		Query query = getEM().createQuery("SELECT c FROM Calendario c WHERE c.inicio = :inicio",Calendario.class);
		query.setParameter("inicio", inicio);
		try {
			return (Calendario) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	public CalendarioDAO() {

	}

	public CalendarioDAO(EntityManager entityManager) {
		super(entityManager);
	}

}
