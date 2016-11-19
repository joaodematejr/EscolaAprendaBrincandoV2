package commons;

import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import entity.Calendario;

public class MailUtil {
	private MailUtil() {
		Properties props = new Properties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.fallback", "false");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		mailSession = Session.getDefaultInstance(props);
		mailSession.setDebug(true);
	}

	private static MailUtil theService = null;
	private static Session mailSession;
	private static final String HOST = "smtp.gmail.com";
	private static final int PORT = 465;
	private static final String USER = "escolaaprendabrincandosenai@gmail.com";
	private static final String PASSWORD = "-pzYqzpj";
	private static final String FROM = "EscolaAprendaBrincando";

	@SuppressWarnings("deprecation")
	public static void sendConfirmacaoData(String inicio, String fim, String assunto, String para, String copia,
			String nomeEmail, String mensagem, List<Calendario> listarPorDatas) throws MessagingException {

		if (theService == null) {
			theService = new MailUtil();
		}

		MimeMessage mimeMessage = new MimeMessage(mailSession);
		mimeMessage.setFrom(new InternetAddress(FROM));
		mimeMessage.setSender(new InternetAddress(FROM));
		mimeMessage.setSubject(nomeEmail);
		String listarPorDatasNome = "";
		for (Calendario c : listarPorDatas) {
			listarPorDatasNome += " ____________________________________________________________" + "<br/>" + "<br/>"
					+ " Professor : " + c.getProfessor().getNome() + "<br/>" + "<br/>" + " Turma : "
					+ c.getTurma().getNomeTurma() + "<br/>" + "<br/>" + " Ambiente : " + c.getAmbiente().getNome()
					+ "<br/>" + "<br/>" + " Inicio : " + c.getInicio().toLocaleString() + "<br/>" + "<br/>" + " Fim :  "
					+ c.getFim().toLocaleString() + "<br/>" + "<br/>"
					+ " ____________________________________________________________" + "<br/>" + "<br/>";
		}
		mimeMessage.setContent("<h4> Olá,  " + "\n" + assunto + " </h4>" + "<h4> Segue sua Agenda entre o periodo de "
				+ fim + " até " + inicio + " </h4>" + "<h4> Mensagem : " + mensagem + "</h4>" + "<h4>Sua Agenda,</h4>"
				+ "<table style=\"border:1px solid black;\">" + "<tr><td><h4>" + listarPorDatasNome + "</h4></td></tr>"
				+ "</table>" +
				/* SimpleDateFormat */
				"<br/>" + "<h4></h4>"
				+ "<h4>Este e-mail foi enviado usando JavaMail, foi gerado automaticamente pelo sistema. Por favor não responder.</h4>"
				+ "<h4>Atenciosamente,</h4>" + "\n" + "<h4>Escola Aprenda Brincando</h4>"
				+ "<img src=\"http://i67.tinypic.com/bhay6h.png\">", "text/html");

		mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(para));
		mimeMessage.addRecipient(Message.RecipientType.CC, new InternetAddress(copia));
		Transport transport = mailSession.getTransport("smtps");
		transport.connect(HOST, PORT, USER, PASSWORD);

		transport.sendMessage(mimeMessage, mimeMessage.getRecipients(Message.RecipientType.TO));
		transport.close();

	}

	public static void sendConfirmacao(String nome, String email, String cpf, String telefone, String senha)
			throws MessagingException {

		if (theService == null) {
			theService = new MailUtil();
		}

		MimeMessage mimeMessage = new MimeMessage(mailSession);
		mimeMessage.setFrom(new InternetAddress(FROM));
		mimeMessage.setSender(new InternetAddress(FROM));
		mimeMessage.setSubject("Cadastro");
		mimeMessage.setContent("<h4> Olá,  " + "\n" + nome + ", </h4>"
				+ "<h4> Esse email é uma confirmação que seu cadastro foi realizado com sucesso. </h4>"
				+ "<h4> Segue Abaixo a confirmação do seus dados; </h4>" + "</h4>" + "<h4> Seu e-mail de Acesso : "
				+ email + "</h4>" + "<h4> Sua Senha : " + senha + "</h4>"
				+ "<h4>Este e-mail foi enviado usando JavaMail, foi gerado automaticamente pelo sistema. Por favor não responder.</h3>"
				+ "<h4>Atenciosamente,</h4>" + "\n" + "<h4>Escola Aprenda Brincando</h4>"
				+ "<img src=\"http://i67.tinypic.com/bhay6h.png\">", "text/html");
		mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
		Transport transport = mailSession.getTransport("smtps");
		transport.connect(HOST, PORT, USER, PASSWORD);
		transport.sendMessage(mimeMessage, mimeMessage.getRecipients(Message.RecipientType.TO));
		transport.close();

	}

}