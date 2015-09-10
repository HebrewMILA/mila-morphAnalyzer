package lexicon;

import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.mail.*;
import javax.mail.internet.*;

import com.Ostermiller.util.RandPass;

public class SecHandle implements Serializable {

	private static final long serialVersionUID = 2990047772520252177L;
	private static final List<String> corpusList = Arrays.asList("tapuz",
			"infomed", "doctors", "natureHilling", "yanshuf", "bari",
			"knesset", "a7corpus", "theMarker", "haaretz", "shaar");

	private void tryExec(String userName, String password, String corpus) {
		String command = "/usr/bin/htpasswd -b /home/gtabajah/access/.htpasswd."
				+ corpus + " " + userName + " " + password;
		try {
			Runtime.getRuntime().exec(command);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String generatePassword() {
		return new RandPass(new char[] { '0', '1', '2', '3', '4', '5', '6',
				'7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
				'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u',
				'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G',
				'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
				'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '`', '@', '%', '*', '-',
				'+', ']', ';', ':', '"', '<', '.', '>', '?' }).getPass(40);
	}

	public void sendMail(String password, String firstName, String lastName,
			String email, String userName, String country, String purpose,
			String phoneNumber, String university, int commercial) {
		System.out.println("*********************   " + commercial);
		String stCommercial = "No";
		if (commercial == 0)
			stCommercial = "Yes";

		try {
			for (String corpus : corpusList)
				tryExec(userName, password, corpus);

			MimeMessage message = new MimeMessage((Session)null);
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
			message.setFrom(new InternetAddress("daliabo@cs.technion.ac.il"));
			message.setRecipients(Message.RecipientType.CC, "mila@cs.technion.ac.il");
			message.setSubject("Access To Mila Knowledge Center Corpora Resources");
			message.setText("Dear "
					+ firstName
					+ ",\n\n "
					+ "This is the automatic account creation confirmation e-mail from the http://www.mila.cs.technion.ac.il free Corpora service"
					+ "\n\n"
					+ "# If you have not created an account at our service, someone else has used  #\n\n"
					+ "# your e-mail-address to do so. He will not be able to use our service      #\n\n "
					+ "# without this e-mail. So you can simply ignore and delete this mail.       #\n\n"
					+ "Your user name is:\n\n"
					+ userName
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
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
	public static void releaseConnection() {
	}
}
