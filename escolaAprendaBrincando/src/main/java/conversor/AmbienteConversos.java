package conversor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import entity.Ambiente;
import rn.AmbienteRN;

@FacesConverter(forClass = Ambiente.class)
public class AmbienteConversos implements Converter {

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
		AmbienteRN ambienteRN = new AmbienteRN();
		Long id = Long.parseLong(value);
		return ambienteRN.buscarPorId(id);
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
		Ambiente ambiente = (Ambiente) value;
		return String.valueOf(ambiente.getId());

	}

}
