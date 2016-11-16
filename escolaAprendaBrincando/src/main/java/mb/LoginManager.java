package mb;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import entity.Cliente;
import rn.ClienteRN;

@ManagedBean
@SessionScoped
public class LoginManager {

	private Cliente cliente;

	public void login() throws ServletException, IOException {
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		RequestDispatcher dispatcher = ((ServletRequest) context.getRequest()).getRequestDispatcher("/login");
		dispatcher.forward((ServletRequest) context.getRequest(), (ServletResponse) context.getResponse());
		FacesContext.getCurrentInstance().responseComplete();

		SecurityContext contextSecurity = SecurityContextHolder.getContext();
		Authentication authentication = contextSecurity.getAuthentication();
		if (authentication != null && authentication.getPrincipal() != null) {
			String email = ((User) authentication.getPrincipal()).getUsername();
			ClienteRN clienteRN = new ClienteRN();
			cliente = clienteRN.buscarPorEmail(email);
		} else {
			cliente = null;
		}

	}

	public void logout() throws ServletException, IOException {
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		RequestDispatcher dispatcher = ((ServletRequest) context.getRequest()).getRequestDispatcher("/logout");
		dispatcher.forward((ServletRequest) context.getRequest(), (ServletResponse) context.getResponse());
		FacesContext.getCurrentInstance().responseComplete();
		cliente = null;
	}

	public String goLogin() {
		return "/login";
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
