package mb;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

import commons.MailUtil;
import commons.Utils;
import entity.Cliente;
import json.LoginJson;
import rn.ClienteRN;

@ViewScoped
@ManagedBean
public class ClienteMb {
	private Cliente cliente;
	private ClienteRN clienteRN;
	private Long editarId;
	private List<Cliente> listaClientes;
	private List<Cliente> listaProfessores;
	private List<Cliente> listaAlunos;
	private List<Cliente> renderListClientesJson;

	@PostConstruct
	public void depoisDeConstruir() {
		cliente = new Cliente();
		clienteRN = new ClienteRN();
	}

	public void setListaProfessores(List<Cliente> listaProfessores) {
		this.listaProfessores = listaProfessores;
	}

	public void setListaAlunos(List<Cliente> listaAlunos) {
		this.listaAlunos = listaAlunos;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public List<Cliente> getListaProfessores() {
		if (listaProfessores == null) {
			listaProfessores = clienteRN.listarProfessores();
		}
		return listaProfessores;
	}

	public List<Cliente> getListaAlunos() {
		if (listaAlunos == null) {
			listaAlunos = clienteRN.listarAlunos();
		}
		return listaAlunos;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public ClienteRN getClienteRN() {
		return clienteRN;
	}

	public void setClienteRN(ClienteRN clienteRN) {
		this.clienteRN = clienteRN;
	}

	public Long getEditarId() {
		return editarId;
	}

	public void setEditarId(Long editarId) {
		this.editarId = editarId;
	}

	public List<Cliente> getListaClientes() {
		if (listaClientes == null) {
			listaClientes = clienteRN.listarClientes();
		}
		return listaClientes;
	}

	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public void carregarUsuario(ComponentSystemEvent event) {
		if (editarId == null) {
			return;
		}

		cliente = clienteRN.buscarPorId(editarId);
	}

	public String excluir(String id) throws Throwable {
		try {
			Long idExcluir = Long.parseLong(id);
			clienteRN.excluir(idExcluir);
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
			MailUtil.sendConfirmacao(cliente.getNome(), cliente.getEmail(), cliente.getCpf(), cliente.getTelefone(),
					cliente.getSenha());
			String hash = Utils.senhaToSha256(cliente.getSenha());
			cliente.setSenha(hash);
			clienteRN.salvar(cliente);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Salvo com sucesso", "Salvo com sucesso."));
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

	public void carregarEdicao() {
		if (editarId != null && !FacesContext.getCurrentInstance().getPartialViewContext().isAjaxRequest()) {
			cliente = clienteRN.buscarPorId(editarId);
		}

	}

	public void renderListClientesJson() throws IOException {

		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();

		String key = externalContext.getRequestParameterMap().get("key");
		String json = "";

		if (key != null && key.equals(Utils.KEY)) {
			json = Utils.getGson().toJson(clienteRN.listarClienteParaJson());

		}
		externalContext.setResponse("application/json");
		externalContext.setResponseCharacterEncoding("UTF-8");
		externalContext.getResponseOutputWriter().write(json);
		context.responseComplete();
	}

	public void renderLoginJson() throws IOException {

		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		String email = externalContext.getRequestParameterMap().get("email");
		String senha = externalContext.getRequestParameterMap().get("senha");
		String key = externalContext.getRequestParameterMap().get("key");
		String json = "";
		if (key != null && key.equals(Utils.KEY)) {
			Cliente u = clienteRN.loginParaJson(email, senha);
			if (u != null) {
				LoginJson lj = new LoginJson();
				lj.setNome(u.getNome());
				lj.setPerfil("ROLE_PROFESSOR");
				json = Utils.getGson().toJson(lj);

			}

		}
		externalContext.setResponseContentType("application/json");
		externalContext.setResponseCharacterEncoding("UTF-8");
		externalContext.getResponseOutputWriter().write(json);
		context.responseComplete();

	}

}
