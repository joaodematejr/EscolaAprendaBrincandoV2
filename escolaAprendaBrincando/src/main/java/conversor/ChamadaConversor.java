package conversor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import entity.Chamada;
import rn.ChamadaRN;

@FacesConverter(forClass = Chamada.class)
public class ChamadaConversor implements Converter {

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
		ChamadaRN chamadaRN = new ChamadaRN();
		Long id = Long.parseLong(value);
		return chamadaRN.buscarPorId(id);
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
		Chamada chamada = (Chamada) value;
		return chamada.getId().toString();
	}

}
