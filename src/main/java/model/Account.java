package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the accounts database table.
 * 
 */
@Entity
@Table(name="accounts")
@NamedQuery(name="Account.findAll", query="SELECT a FROM Account a")
public class Account implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int iduser;

	@Column(name="account_number")
	private String accountNumber;

	private float balance;

	private String name;

	private String pin;

	public Account() {
	}

	public Account(String accountNumber, String name, String pin, float balance) {
		super();
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.name = name;
		this.pin = pin;
	}

	public int getIduser() {
		return this.iduser;
	}

	public void setIduser(int iduser) {
		this.iduser = iduser;
	}

	public String getAccountNumber() {
		return this.accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public float getBalance() {
		return this.balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPin() {
		return this.pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	@Override
	public String toString() {
		return "Account [iduser=" + iduser + ", accountNumber=" + accountNumber + ", balance=" + balance + ", name="
				+ name + ", pin=" + pin + "]";
	}

	
}