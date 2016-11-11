package rn;

import java.util.ArrayList;
import java.util.List;

import dao.ClienteDAO;
import entity.Cliente;
import json.ClienteJson;

public class ClienteRN {
	private ClienteDAO dao;

	public ClienteRN() {
		dao = new ClienteDAO();
	}

	public void salvar(Cliente cliente) {
		dao.salvar(cliente);
	}

	public Cliente buscarPorId(Long id) {
		return dao.buscarPorId(id);
	}

	public List<Cliente> listarClientes() {
		return dao.listarClientes();
	}

	public void excluir(Long id) {
		dao.excluir(id);
	}

	public List<Cliente> listarProfessores() {
		return dao.listarProfessores();
	}

	public List<Cliente> listarAlunos() {
		return dao.listarAlunos();
	}

	public Cliente buscarPorEmail(String email) {
		return dao.buscarPorEmail(email);
	}

	public List<Cliente> listarClientesQuantidade() {
		return dao.listarClientesQuantidade();
	}

	public List<ClienteJson> listarClienteParaJson() {
		List<ClienteJson> clientesJson = new ArrayList<ClienteJson>();
		List<Cliente> clientesCarregados = dao.listarClientes();
		for (Cliente u : clientesCarregados) {
			ClienteJson cj = new ClienteJson();
			cj.setNome(u.getNome());
			cj.setEmail(u.getEmail());
			clientesJson.add(cj);
		}
		return clientesJson;
	}

	public Cliente loginParaJson(String email, String senha) {
		return dao.loginParaJson(email, senha);
	}

}
