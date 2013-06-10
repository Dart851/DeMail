package ru.t_systems.demail.dao.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import ru.t_systems.demail.model.user.Role;


@Repository
@Transactional
public class RoleDAOImpl implements RoleDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	@Transactional(readOnly = true)
	public Role getRole(int id) {
		Role role = (Role) getCurrentSession().get(Role.class, id);
		return role;
	}

}
