package rn;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.CalendarioDAO;
import entity.Calendario;
import entity.Cliente;
import json.CalendarioJson;

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

	public Calendario buscarPorData(Date inicio) {
		return dao.buscarPorData(inicio);
	}

	public List<CalendarioJson> listaCalendariosParaJson() {
		List<CalendarioJson> calendarioJson = new ArrayList<CalendarioJson>();
		List<Calendario> calendarioCarregados = dao.listarCalendarios();
		for (Calendario c : calendarioCarregados) {
			CalendarioJson cj = new CalendarioJson();
			cj.setTitulo(c.getTitulo());
			cj.setInicio(c.getInicio());
			cj.setFim(c.getFim());
			cj.setNomeTurma(c.getTurma().getNomeTurma());
			cj.setAmbiente(c.getAmbiente().getNome());
			cj.setProfessor(c.getProfessor().getNome());
			calendarioJson.add(cj);

		}
		return calendarioJson;
	}

}
