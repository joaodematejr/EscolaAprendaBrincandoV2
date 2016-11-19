package mb;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.context.RequestContext;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import commons.MailUtil;
import commons.Utils;
import dao.CalendarioDAO;
import entity.Ambiente;
import entity.Calendario;
import entity.Cliente;
import entity.Turma;
import rn.CalendarioRN;
import rn.TurmaRN;

@SessionScoped
@ManagedBean(name = "calendarioMb")
public class CalendarioMb {
	private Calendario calendario;
	private CalendarioRN calendarioRN;
	private TurmaRN turmaRN;
	@SuppressWarnings("unused")
	private Turma turma;
	private Long editarId;
	private List<Calendario> listaCalendarios;
	private List<Calendario> listarCalendarioProfessor;
	private List<Calendario> listarPorDatas;
	/* Envio de Mail */
	private Date fim;
	private Date inicio;
	private String para;
	private String copia;
	private String nomeEmail;
	private String assunto;
	private String mensagem;
	private String statusMessagem = "";
	private Long idProfessor;

	public String getNomeEmail() {
		return nomeEmail;
	}

	public void setNomeEmail(String nomeEmail) {
		this.nomeEmail = nomeEmail;
	}

	public String getStatusMessagem() {
		return statusMessagem;
	}

	public void setStatusMessagem(String statusMessagem) {
		this.statusMessagem = statusMessagem;
	}

	public String getCopia() {
		return copia;
	}

	public void setCopia(String copia) {
		this.copia = copia;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getPara() {
		return para;
	}

	public void setPara(String para) {
		this.para = para;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String subject) {
		this.assunto = subject;
	}

	public String getStatusMessage() {
		return statusMessagem;
	}

	public Date getFim() {
		return fim;
	}

	public void setFim(Date fim) {
		this.fim = fim;
	}

	public Date getInicio() {
		return inicio;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	public List<Calendario> getListarPorDatas() throws SQLException {
		return listarPorDatas;
	}

	public void setListarPorDatas(List<Calendario> listarPorDatas) {
		this.listarPorDatas = listarPorDatas;
	}

	public String buscar() throws MessagingException {
		listarPorDatas = calendarioRN.buscarPorDatas(inicio, fim);

		return "";
	}

	@SuppressWarnings("deprecation")
	public String enviar() throws MessagingException, SQLException {
		listarPorDatas = calendarioRN.buscarPorDatas(inicio, fim);
		MailUtil.sendConfirmacaoData(getFim().toLocaleString(), getInicio().toLocaleString(), nomeEmail, para, copia,
				assunto, mensagem, listarPorDatas);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
				"E-mail enviado com sucesso", "E-mail enviado com sucesso"));
		return "enviaragenda.xhtml";
	}

	public String atualizar() {
		listarPorDatas = calendarioRN.buscarPorDatas(null, null);
		return "";
	}

	public List<Calendario> getListarCalendarioProfessor() {
		if (listarCalendarioProfessor == null) {
			HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
					.getRequest();
			LoginManager loginManager = (LoginManager) request.getSession().getAttribute("loginManager");
			listarCalendarioProfessor = calendarioRN.listarCalendarioPorProfessor(loginManager.getCliente().getId());

		}
		return listarCalendarioProfessor;
	}

	public void setListarCalendarioProfessor(List<Calendario> listarCalendarioProfessor) {
		this.listarCalendarioProfessor = listarCalendarioProfessor;
	}

	private ScheduleModel calendarioModel;
	private CalendarioDAO eDao;
	private Turma turmaSelecionado;
	private Ambiente ambienteSelecionado;
	private Calendario selectedCalendario;

	@PostConstruct
	public void depoisDeConstruir() {
		calendario = new Calendario();
		calendarioRN = new CalendarioRN();
		eDao = new CalendarioDAO();

		atualizarAgenda();

	}

	public void atualizarAgenda() {
		calendarioModel = new DefaultScheduleModel();

		listaCalendarios = eDao.listarCalendarios();
		for (Calendario ca : listaCalendarios) {
			DefaultScheduleEvent evt = new DefaultScheduleEvent();
			evt.setEndDate(ca.getFim());
			evt.setStartDate(ca.getInicio());
			evt.setTitle(ca.getTitulo());
			evt.setData(ca.getId());
			evt.setDescription(ca.getDescricao());
			evt.setAllDay(true);
			evt.setEditable(true);
			calendarioModel.addEvent(evt);
		}
	}

	public Calendario getCalendario() {
		return calendario;
	}

	public void setCalendario(Calendario calendario) {
		this.calendario = calendario;
	}

	public CalendarioRN getCalendarioRN() {
		return calendarioRN;
	}

	public void setAmbienteRN(CalendarioRN calendarioRN) {
		this.calendarioRN = calendarioRN;
	}

	public Long getEditarId() {
		return editarId;
	}

	public void setEditarId(Long editarId) {
		this.editarId = editarId;
	}

	public List<Calendario> getlistaCalendarios() {
		if (listaCalendarios == null) {
			listaCalendarios = calendarioRN.listaCalendarios();
		}

		return listaCalendarios;
	}

	public void setlistarCalendarioPorProfessor(List<Calendario> listarCalendarioPorProfessor) {
	}

	public void setListaCalendarios(List<Calendario> listaCalendarios) {
		this.listaCalendarios = listaCalendarios;
	}

	public void carregarUsuario(ComponentSystemEvent event) {
		if (editarId == null) {
			return;
		}

		calendario = calendarioRN.buscarPorId(editarId);
	}

	public String excluir(String id) throws Throwable {
		try {
			Long idExcluir = Long.parseLong(id);
			calendarioRN.excluir(idExcluir);
			atualizarAgenda();
			calendario = new Calendario();
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
		List<Calendario> calendarioLoad = calendarioRN.buscarPorDatas(getInicio(), getFim());
		if (calendario.getTitulo() == null | calendario.getTitulo() == "") {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Titulo : Vazio", ""));
			if (calendario.getAmbiente() == null) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Ambiente : Vazio", ""));
				if (calendario.getTurma() == null) {
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_INFO, "Turma : Vazia", ""));
					if (calendario.getProfessor() == null) {
						FacesContext.getCurrentInstance().addMessage(null,
								new FacesMessage(FacesMessage.SEVERITY_INFO, "Professor : Vazio", ""));
						if (calendario.getDescricao() == null | calendario.getDescricao() == "") {
							FacesContext.getCurrentInstance().addMessage(null,
									new FacesMessage(FacesMessage.SEVERITY_INFO, "Descrição : Vazio", ""));
							FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
									FacesMessage.SEVERITY_INFO, "Por favor Preencher os Campos acima !", ""));
							if (calendario.getInicio().equals(inicio) == calendario.getFim().equals(fim)) {
								FacesContext.getCurrentInstance().addMessage(null,
										new FacesMessage(FacesMessage.SEVERITY_INFO,
												"Horario iguais, Por favor Selecionar Horarios Diferentes", ""));

							}
						}
					}
				}
			}
		} else {
			eDao = new CalendarioDAO();
			eDao.salvar(calendario);
			atualizarAgenda();
			calendario = new Calendario();
			RequestContext.getCurrentInstance().execute("PF('caixaCalendario').hide();");
			RequestContext.getCurrentInstance().update("formCalendario:idCalendario");
		}

		return "";
	}

	public void carregarEdicao() {
		if (editarId != null && !FacesContext.getCurrentInstance().getPartialViewContext().isAjaxRequest()) {
			calendario = calendarioRN.buscarPorId(editarId);
		}
	}

	public ScheduleModel getCalendarioModel() {
		return calendarioModel;
	}

	public void setCalendarioModel(ScheduleModel calendarioModel) {
		this.calendarioModel = calendarioModel;
	}

	public CalendarioDAO getEdao() {
		return eDao;
	}

	public void setEdao(CalendarioDAO edao) {
		this.eDao = edao;
	}

	public List<Calendario> getListaCalendarios() {
		return listaCalendarios;
	}

	public void setCalendarioRN(CalendarioRN calendarioRN) {
		this.calendarioRN = calendarioRN;
	}

	public void dataSelecionado(SelectEvent selectEvent) {

		ScheduleEvent calendari = (ScheduleEvent) selectEvent.getObject();
		for (Calendario ca : listaCalendarios) {
			if (ca.getId() == (Long) calendari.getData()) {
				calendario = ca;
				break;

			}

		}
	}

	public void novaData(SelectEvent selectEvent) {
		ScheduleEvent calendari = new DefaultScheduleEvent("", (Date) selectEvent.getObject(),
				(Date) selectEvent.getObject());
		calendario = new Calendario();
		calendario.setInicio(new java.sql.Date(calendari.getStartDate().getTime()));
		calendario.setFim(new java.sql.Date(calendari.getEndDate().getTime()));
	}

	public void moverData(ScheduleEntryMoveEvent calendari) throws SQLException {
		for (Calendario ca : listaCalendarios) {
			if (ca.getId() == (Long) calendari.getScheduleEvent().getData()) {
				calendario = ca;
				eDao = new CalendarioDAO();
				eDao.salvar(calendario);
				atualizarAgenda();
				// inicializar();
			}
		}
	}

	public void redimensinarData(ScheduleEntryResizeEvent calendari) throws SQLException {
		for (Calendario ca : listaCalendarios) {
			if (ca.getId() == (Long) calendari.getScheduleEvent().getData()) {
				calendario = ca;
				eDao = new CalendarioDAO();
				eDao.salvar(calendario);
				atualizarAgenda();
				// inicializar();
			}
		}
	}

	public TurmaRN getTurmaRN() {
		return turmaRN;
	}

	public void setTurmaRN(TurmaRN turmaRN) {
		this.turmaRN = turmaRN;
	}

	public CalendarioDAO geteDao() {
		return eDao;
	}

	public void seteDao(CalendarioDAO eDao) {
		this.eDao = eDao;
	}

	public Turma getTurmaSelecionado() {
		return turmaSelecionado;
	}

	public void setTurmaSelecionado(Turma turmaSelecionado) {
		this.turmaSelecionado = turmaSelecionado;
	}

	public Ambiente getAmbienteSelecionado() {
		return ambienteSelecionado;
	}

	public void setAmbienteSelecionado(Ambiente ambienteSelecionado) {
		this.ambienteSelecionado = ambienteSelecionado;
	}

	public void onRowSelect(SelectEvent event) {
		FacesMessage msg = new FacesMessage("Calendario Selected", ((Calendario) event.getObject()).getDescricao());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowUnselect(UnselectEvent event) {
		FacesMessage msg = new FacesMessage("Calendario Selected", ((Calendario) event.getObject()).getDescricao());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public Calendario getSelectedCalendario() {
		return selectedCalendario;
	}

	public void setSelectedCalendario(Calendario selectedCalendario) {
		this.selectedCalendario = selectedCalendario;
	}

	/* JSON */
	public void rendeCalendarioJson() throws IOException {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		String key = externalContext.getRequestParameterMap().get("key");
		String json = "";
		if (key != null && key.equals(Utils.KEY)) {
			json = Utils.getGson().toJson(calendarioRN.listaCalendariosParaJson());

		}
		externalContext.setResponseContentType("application/json");
		externalContext.setResponseCharacterEncoding("UTF-8");
		externalContext.getResponseOutputWriter().write(json);
		context.responseComplete();
	}

}