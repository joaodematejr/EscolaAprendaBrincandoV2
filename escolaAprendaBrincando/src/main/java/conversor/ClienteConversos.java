package conversor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import entity.Cliente;
import rn.ClienteRN;

@FacesConverter(forClass = Cliente.class)
public class ClienteConversos implements Converter {

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
		ClienteRN clienteRN = new ClienteRN();
		Long id = Long.parseLong(value);
		return clienteRN.buscarPorId(id);
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
		Cliente cliente = (Cliente) value;
		return cliente.getId().toString();
	}
}
