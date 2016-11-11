package mb;

import java.io.IOException;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import commons.Utils;
import entity.Calendario;
import rn.CalendarioRN;
import rn.JsonRN;

public class JsonMB {
	private Calendario calendario;
	private CalendarioRN calendarioRN;
	private JsonRN jsonRN;

	public void renderListarCalendarioJson() throws IOException {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		String key = externalContext.getRequestParameterMap().get("key");
		String json = "";
		if (key != null && key.equals(Utils.KEY)) {
			json = Utils.getGson().toJson(jsonRN.listarCalendarioParaJson());

		}
		externalContext.setResponseContentType("application/json");
		externalContext.setResponseCharacterEncoding("UTF-8");
		externalContext.getResponseOutputWriter().write(json);
		context.responseComplete();

	}

}
