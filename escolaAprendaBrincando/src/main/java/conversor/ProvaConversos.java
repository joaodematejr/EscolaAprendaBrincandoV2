package conversor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import entity.Prova;
import rn.ProvaRN;

@FacesConverter(forClass = Prova.class)
public class ProvaConversos implements Converter {

	@Override
	public Object getAsObject(FacesContext facesContext,
			UIComponent uiComponent, String valor) {
		ProvaRN rn = new ProvaRN();
		Long id = Long.parseLong(valor);
		return rn.buscarPorId(id);
	}

	@Override
	public String getAsString(FacesContext facesContext,
			UIComponent uiComponent, Object valor) {
		Prova prova = (Prova) valor;
		return prova.getId().toString();
	}
}

