package commons;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class JavaMailAutenticador extends Authenticator {
	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication("escolaaprendabrincandosenai@gmail.com", "-pzYqzpj");

	}

}
