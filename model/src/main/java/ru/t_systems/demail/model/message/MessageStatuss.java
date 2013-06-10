package ru.t_systems.demail.model.message;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ru.t_systems.demail.model.user.Account;

@Entity
@Table(name = "status")
public class MessageStatuss {

	@Id
	@GeneratedValue
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "account_id")
	private Account account;

	@ManyToOne
	@JoinColumn(name = "account_sender_id")
	private Account acountsSender;

	@ManyToOne(cascade=CascadeType.ALL)
	private Message message;
	
	private Boolean isRead;

	private Boolean isDeleted;
	
	
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public Account getAcountsSender() {
		return acountsSender;
	}

	public void setAcountsSender(Account acountsSender) {
		this.acountsSender = acountsSender;
	}

	
	public Account getAcounts() {
		return account;
	}

	public void setAcounts(Account acounts) {
		this.account = acounts;
	}

	public MessageStatuss() {
		this.isRead = false;
		this.isDeleted = false;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getIsRead() {
		return isRead;
	}

	public void setIsRead(Boolean isRead) {
		this.isRead = isRead;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

}
