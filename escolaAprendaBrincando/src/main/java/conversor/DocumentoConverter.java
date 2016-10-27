package conversor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import entity.Documento;
import rn.AlbumRN;

@FacesConverter(forClass = Documento.class)
public class DocumentoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String valor) {
		try {
			Long id = Long.parseLong(valor);
			AlbumRN albumRN = new AlbumRN();
			Documento imagem = albumRN.buscarDocumentoPorId(id);
			return imagem;
		} catch (NumberFormatException e) {
			return new Documento();
		}
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object valor) {
		Documento imagem = (Documento) valor;
		return imagem.getId() == null ? "" : imagem.getId().toString();
	}

}
