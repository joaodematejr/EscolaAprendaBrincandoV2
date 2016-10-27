package conversor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import entity.Calendario;
import rn.CalendarioRN;

@FacesConverter(forClass = Calendario.class)
public class CalendarioConversos implements Converter {

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
		CalendarioRN calendarioRN = new CalendarioRN();
		Long id = Long.parseLong(value);
		return calendarioRN.buscarPorId(id);
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
		Calendario calendario = (Calendario) value;
		return String.valueOf(calendario.getId());

	}

}
