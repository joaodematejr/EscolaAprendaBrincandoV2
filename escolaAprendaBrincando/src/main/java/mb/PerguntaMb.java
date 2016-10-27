package mb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

import entity.Pergunta;
import rn.PerguntaRN;

@ViewScoped
@ManagedBean
public class PerguntaMb {
	private Pergunta pergunta;
	private PerguntaRN perguntaRN;
	private Long editarId;
	private List<Pergunta> listaPerguntas;

	@PostConstruct
	public void depoisDeConstruir() {
		pergunta = new Pergunta();
		perguntaRN = new PerguntaRN();
	}

	public Pergunta getPergunta() {
		return pergunta;
	}

	public PerguntaRN getPerguntaRN() {
		return perguntaRN;
	}

	public void setPerguntaRN(PerguntaRN perguntaRN) {
		this.perguntaRN = perguntaRN;
	}

	public Long getEditarId() {
		return editarId;
	}

	public void setEditarId(Long editarId) {
		this.editarId = editarId;
	}

	public List<Pergunta> getListaPerguntas() {
		if (listaPerguntas == null) {
			listaPerguntas = perguntaRN.listarPerguntas();
		}
		return listaPerguntas;
	}

	public void setListaPerguntas(List<Pergunta> listaPerguntas) {
		this.listaPerguntas = listaPerguntas;
	}

	public void carregarPergunta(ComponentSystemEvent event) {
		if (editarId == null) {
			return;
		}

		pergunta = perguntaRN.buscarPorId(editarId);
	}

	public String excluir(String id) {
		Long idExcluir = Long.parseLong(id);
		perguntaRN.excluir(idExcluir);
		listaPerguntas = null;
		return "index.html";
	}

	public String salvar() {
		perguntaRN.salvar(pergunta);
		listaPerguntas = null;
		return "";
	}

	public void carregarEdicao() {
		if (editarId != null && !FacesContext.getCurrentInstance().getPartialViewContext().isAjaxRequest()) {
			pergunta = perguntaRN.buscarPorId(editarId);
		}

	}

}
