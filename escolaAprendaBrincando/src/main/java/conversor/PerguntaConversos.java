package conversor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import entity.Pergunta;
import rn.PerguntaRN;

@FacesConverter(forClass = Pergunta.class)
public class PerguntaConversos implements Converter {

	@Override
	public Object getAsObject(FacesContext facesContext,
			UIComponent uiComponent, String value) {
		PerguntaRN perguntaRN = new PerguntaRN();
		Long id = Long.parseLong(value);
		return perguntaRN.buscarPorId(id);
	}

	@Override
	public String getAsString(FacesContext facesContext,
			UIComponent uiComponent, Object value) {
		Pergunta pergunta = (Pergunta) value;
		return pergunta.getId().toString();
	}
}
