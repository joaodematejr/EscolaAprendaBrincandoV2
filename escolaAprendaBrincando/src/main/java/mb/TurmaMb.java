package mb;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ComponentSystemEvent;

import entity.Cliente;
import entity.Turma;
import rn.TurmaRN;

@ViewScoped
@ManagedBean(name = "turmaMb")
public class TurmaMb {
	private TurmaRN turmaRN;
	private Turma turma;
	private Long editarId;
	private Cliente clienteSelecionado;
	private List<Turma> listarTurmas;

	@PostConstruct
	public void init() {
		turmaRN = new TurmaRN();
		turma = new Turma();
		turma.setClienteTurma(new ArrayList<Cliente>());
	}

	public void setListarTurmas(List<Turma> listarTurmas) {
		this.listarTurmas = listarTurmas;
	}

	public TurmaRN getTurmaRN() {
		return turmaRN;
	}

	public void setTurmaRN(TurmaRN turmaRN) {
		this.turmaRN = turmaRN;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public void setClienteSelecionado(Cliente clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}

	public Long getEditarId() {
		return editarId;
	}

	public void setEditarId(Long editarId) {
		this.editarId = editarId;
	}

	public void carregarEdicao() {
		if (editarId != null && !FacesContext.getCurrentInstance().getPartialViewContext().isAjaxRequest()) {
			turma = turmaRN.buscarPorId(editarId);
		}
	}

	public Cliente getClienteSelecionado() {
		return clienteSelecionado;
	}

	public void adicionarCliente(AjaxBehaviorEvent event) {
		if (turma.getClienteTurma().contains(clienteSelecionado)) {
			return;
		}
		turma.getClienteTurma().add(clienteSelecionado);
		clienteSelecionado = null;
	}

	public void excluirCliente(AjaxBehaviorEvent event) {
		Cliente cliente = (Cliente) event.getComponent().getAttributes().get("idCliente");
		turma.getClienteTurma().remove(cliente);
	}

	public String excluir(String idParam) {
		System.out.println("Excluindo: " + idParam);
		Long id = Long.parseLong(idParam);
		turmaRN.excluir(id);
		listarTurmas = null;

		return "";
	}

	public String salvar() throws Throwable {
		try {
			turmaRN.salvar(turma);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Salvo com Sucesso", "Salvo com sucesso."));
			return "admincadastroturmas";
		} catch (IllegalArgumentException exception) {
			exception.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao Salvar", exception.getMessage()));
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao Salvar", e.getMessage()));
		}
		return "admincadastroturmas.html";
	}

	public List<Turma> getListarTurmas() {
		if (listarTurmas == null) {
			listarTurmas = turmaRN.listarTurmas();
		}

		return listarTurmas;
	}

	public void carregarUsuario(ComponentSystemEvent event) {
		if (editarId == null) {
			return;
		}

		turma = turmaRN.buscarPorId(editarId);
	}

}
