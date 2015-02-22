package lexicon;

import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.RandomStringUtils;

public class SecHandle extends Object implements Serializable {
	private static final long serialVersionUID = 7626790738802475916L;

	public Session session = null;

	public SecHandle() {
		Properties p = new Properties();
		/*
		 * Yeah, hard-coded. It's the bloody SMTP server, it's not going
		 * anywhere...
		 */
		p.setProperty("mail.smtp.host", "csm.cs.technion.ac.il");
		this.session = Session.getDefaultInstance(p);
	}

	public String generatePassword() {
		return RandomStringUtils.randomAlphanumeric(9);
	}

	public void sendMail(String password, String firstName, String lastName,
			String email, String userName, String country, String purpose,
			String phoneNumber, String university, int commercial) {
		System.out.println("*********************   " + commercial);
		String stCommercial = "No";
		if (commercial == 0)
			stCommercial = "Yes";

		try {
			String userData = email + " " + password;
			String commandAndPath = "htpasswd -b /data/webapps/corpus/access/";
			System.out.println("USER DATA: " + userData);

			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress("mila@cs.technion.ac.il"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					email));
			message.addRecipient(Message.RecipientType.CC, new InternetAddress(
					"mila@cs.technion.ac.il"));
			message.setSubject("Access To Mila Knowledge Center Corpora Resources");
			message.setText("Dear "
					+ firstName
					+ ",\n\n "
					+ "This is the automatic account creation confirmation e-mail from the http://www.mila.cs.technion.ac.il free Corpora service"
					+ "\n\n"
					+ "# If you have not created an account at our service, someone else has used  #\n\n"
					+ "# your e-mail-address to do so. He will not be able to use our service      #\n\n "
					+ "# without this e-mail. So you can simply ignore and delete this mail.       #\n\n"
					+ "Your user name is your email:\n\n"
					+ email
					// + userName
					+ "\n\nYour password is: \n\n"
					+ password
					+ "\n\n "
					+ "Please be aware that in order to use these resources for commerical use you need to ask for"
					+ " commercial license by applying mila@cs.technion.ac.il \n\n"
					+ "Thanks for using our free service\n\n"
					+ "Your details as saved by us are:\n\n"
					+ "\n\nFirst Name: " + firstName + "\n\nLast Name: "
					+ lastName + "\n\nUniversity/firm: " + university
					+ "\n\ncountry: " + country + "\n\nPhone: " + phoneNumber
					+ "\n\nPurpose: " + purpose + "\n\nCommercial: "
					+ stCommercial
					+ "\n\nMila Knowledge Center For Processing Hebrew ");
			Transport.send(message);

			String command = commandAndPath + ".htpasswd.all " + userData;
			try {
				Process process = Runtime.getRuntime().exec(command);
				process.waitFor();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
