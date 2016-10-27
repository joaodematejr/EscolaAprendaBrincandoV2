package mb;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

import entity.Chamada;
import rn.ChamadaRN;

@ViewScoped
@ManagedBean
public class ChamadaMb {
	private Chamada chamada;
	private ChamadaRN chamadaRN;
	private Long editarId;

	@PostConstruct
	public void depoisDeConstruir() {
		chamada = new Chamada();
		chamadaRN = new ChamadaRN();
	}

	public Chamada getChamada() {
		return chamada;
	}

	public void setChamada(Chamada chamada) {
		this.chamada = chamada;
	}

	public ChamadaRN getChamadaRN() {
		return chamadaRN;
	}

	public void setChamadaRN(ChamadaRN chamadaRN) {
		this.chamadaRN = chamadaRN;
	}

	public Long getEditarId() {
		return editarId;
	}

	public void setEditarId(Long editarId) {
		this.editarId = editarId;
	}

	public void carregarUsuario(ComponentSystemEvent event) {
		if (editarId == null) {
			return;
		}

		chamada = chamadaRN.buscarPorId(editarId);
	}

	public String excluir(String id) {
		Long idExcluir = Long.parseLong(id);
		chamadaRN.excluir(idExcluir);
		return "index.html";
	}

	public String salvar() {
		chamadaRN.salvar(chamada);
		return "index.html";
	}

	public void carregarEdicao() {
		if (editarId != null && !FacesContext.getCurrentInstance().getPartialViewContext().isAjaxRequest()) {
			chamada = chamadaRN.buscarPorId(editarId);
		}

	}

}
