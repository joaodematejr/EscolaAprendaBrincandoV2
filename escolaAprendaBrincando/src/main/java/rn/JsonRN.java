package rn;

import java.util.ArrayList;
import java.util.List;

import dao.CalendarioDAO;
import entity.Calendario;
import json.CalendarioJson;

public class JsonRN {
	private CalendarioDAO dao;

	public void JsonRN() {
		dao = new CalendarioDAO();
	}

	public List<CalendarioJson> listarCalendarioParaJson() {
		List<CalendarioJson> calendarioJson = new ArrayList<CalendarioJson>();
		List<Calendario> calendarioCarregados = dao.listarCalendarios();
		for (Calendario c : calendarioCarregados) {
			CalendarioJson cj = new CalendarioJson();
			cj.setTitulo(c.getTitulo());
			cj.setAmbiente(c.getAmbiente().toString());
			cj.setTurma(c.getTurma().toString());
			cj.setProfessor(c.getProfessor().toString());
			cj.setInicio(c.getInicio());
			cj.setFim(c.getFim());

		}
		return calendarioJson;

	}

}
