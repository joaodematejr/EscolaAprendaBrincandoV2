package rn;

import java.util.Date;
import java.util.List;

import dao.CalendarioDAO;
import entity.Calendario;

public class CalendarioRN {

	private CalendarioDAO dao;

	public CalendarioRN() {
		dao = new CalendarioDAO();
	}

	public Calendario buscarPorId(Long id) {
		return dao.buscarPorId(id);
	}

	public List<Calendario> listaCalendarios() {
		return dao.listarCalendarios();
	}

	public void excluir(Long id) {
		dao.excluir(id);
	}

	public List<Calendario> listarCalendarioPorProfessor(Long id) {
		return dao.listarCalendarioPorProfessor(id);
	}

	public CalendarioDAO getDao() {
		return dao;
	}

	public void setDao(CalendarioDAO dao) {
		this.dao = dao;
	}

	public List<Calendario> buscarPorDatas(Date inicio, Date fim) {
		return dao.buscarPorDatas(inicio, fim);
	}

}
