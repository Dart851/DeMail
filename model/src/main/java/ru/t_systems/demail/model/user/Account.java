package ru.t_systems.demail.model.user;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import ru.t_systems.demail.model.message.MessageStatuss;

@Entity
@Table(name = "account")
public class Account {

	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(name = "account_name")
	private String accountName;
	
	@ManyToOne(cascade=CascadeType.ALL)
  //  @JoinColumn(name="user_id")
	private User user;
	
	@OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="account_id")
    private Set<MessageStatuss> status;
	
	@OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="account_sender_id")
    private Set<MessageStatuss> statusSender;

	public Set<MessageStatuss> getStatusSender() {
		return statusSender;
	}

	public void setStatusSender(Set<MessageStatuss> statusSender) {
		this.statusSender = statusSender;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<MessageStatuss> getStatus() {
		return status;
	}

	public void setStatus(Set<MessageStatuss> status) {
		this.status = status;
	}
}
