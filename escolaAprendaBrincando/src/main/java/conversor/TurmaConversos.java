package conversor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import entity.Turma;
import rn.TurmaRN;

@FacesConverter(forClass = Turma.class)
public class TurmaConversos implements Converter {

	@Override
	public Object getAsObject(FacesContext facesContext,
			UIComponent uiComponent, String valor) {
		TurmaRN rn = new TurmaRN();
		Long id = Long.parseLong(valor);
		return rn.buscarPorId(id);
	}

	@Override
	public String getAsString(FacesContext facesContext,
			UIComponent uiComponent, Object valor) {
		Turma turma = (Turma) valor;
		return turma.getId().toString();
	}
}

