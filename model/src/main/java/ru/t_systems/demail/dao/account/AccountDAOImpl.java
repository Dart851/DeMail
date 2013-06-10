package ru.t_systems.demail.dao.account;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ru.t_systems.demail.model.user.Account;


@Repository
@Transactional
public class AccountDAOImpl implements AccountDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	@Transactional(readOnly = true)
	public Account getAccount(int id) {
		Account role = (Account) getCurrentSession().get(Account.class, id);
		return role;
	}

}
