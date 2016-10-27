package mb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

import entity.Ambiente;
import rn.AmbienteRN;

@ViewScoped
@ManagedBean
public class AmbienteMb {
	private Ambiente ambiente;
	private AmbienteRN ambienteRN;
	private Long editarId;
	private List<Ambiente> listaAmbientes;

	@PostConstruct
	public void depoisDeConstruir() {
		ambiente = new Ambiente();
		ambienteRN = new AmbienteRN();
	}

	public Ambiente getAmbiente() {
		return ambiente;
	}

	public void setAmbiente(Ambiente ambiente) {
		this.ambiente = ambiente;
	}

	public AmbienteRN getAmbienteRN() {
		return ambienteRN;
	}

	public void setAmbienteRN(AmbienteRN ambienteRN) {
		this.ambienteRN = ambienteRN;
	}

	public Long getEditarId() {
		return editarId;
	}

	public void setEditarId(Long editarId) {
		this.editarId = editarId;
	}

	public List<Ambiente> getListaAmbientes() {
		if (listaAmbientes == null) {
			listaAmbientes = ambienteRN.listarAmbientes();
		}

		return listaAmbientes;
	}

	public void setListaAmbientes(List<Ambiente> listaAmbientes) {
		this.listaAmbientes = listaAmbientes;
	}

	public void carregarUsuario(ComponentSystemEvent event) {
		if (editarId == null) {
			return;
		}

		ambiente = ambienteRN.buscarPorId(editarId);
	}

	public String excluir(String id) throws Throwable {
		try {
			Long idExcluir = Long.parseLong(id);
			ambienteRN.excluir(idExcluir);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Deletado Com Sucesso", "Deletado Com Sucesso."));
			return "menuadministrativo";
		} catch (IllegalArgumentException exception) {
			exception.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", exception.getMessage()));
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", e.getMessage()));
		}
		return "menuadministrativo.xhtml";
	}

	public String salvar() throws Throwable {
		try {
			ambienteRN.salvar(ambiente);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Salvo com Sucesso", "Salvo com sucesso."));
			return "admincadastroambiente";
		} catch (IllegalArgumentException exception) {
			exception.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao Salvar", exception.getMessage()));
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao Salvar", e.getMessage()));
		}
		return "admincadastroambiente.xhtml";
	}

	public void carregarEdicao() {
		if (editarId != null && !FacesContext.getCurrentInstance().getPartialViewContext().isAjaxRequest()) {
			ambiente = ambienteRN.buscarPorId(editarId);
		}
	}
}