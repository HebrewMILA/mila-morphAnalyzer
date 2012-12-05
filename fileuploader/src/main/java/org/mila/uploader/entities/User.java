package org.mila.uploader.entities;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;

@Entity
@Table(name = "userp")
public class User {
	private String mail;
	private String first;
	private String last;
	private String university;
	private String phone;
	private String purpose;
	private String country;
	private boolean commercial;
	private String password;

	@Transient
	public boolean checkPassword(String password) {
		final MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-1");
		} catch (NoSuchAlgorithmException e) {
			/* god damn it Java, this should be a compile-time error */
			return false;
		}
		md.update(password.getBytes());
		BigInteger hash = new BigInteger(1, md.digest());
		final String hashedPassword = StringUtils.leftPad(hash.toString(16),
				40, "0");
		return this.getPassword().equals(hashedPassword);
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public String getMail() {
		return mail;
	}

	public String getFirst() {
		return first;
	}

	public String getLast() {
		return last;
	}

	public String getUniversity() {
		return university;
	}

	public String getPhone() {
		return phone;
	}

	public String getPurpose() {
		return purpose;
	}

	public String getCountry() {
		return country;
	}

	public boolean isCommercial() {
		return commercial;
	}

	public String getPassword() {
		return password;
	}

	
	public void setMail(String mail) {
		this.mail = mail;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public void setLast(String last) {
		this.last = last;
	}

	public void setUniversity(String university) {
		this.university = university;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setCommercial(boolean commercial) {
		this.commercial = commercial;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
