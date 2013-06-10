package ru.t_systems.demail.dao.user;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ru.t_systems.demail.model.user.Country;


@Repository
@Transactional
public class CountryDAOImpl implements CountryDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession() {
		
		return sessionFactory.getCurrentSession();
		
		}
	@Transactional(readOnly = true)
	public Country getCountry(int id) {
		Country country = (Country) getCurrentSession().get(Country.class, id);
		return country;
	}
	@Transactional(readOnly = true)
	public List<Country> getAllCountry() {
		
				return getCurrentSession().createQuery("from Country").list();
		
	}
	@Transactional(readOnly = true)
	public Country getCountry(String name) {
		List<Country> userList = new ArrayList<Country>();
		
		Query query = getCurrentSession().createQuery(
				"from Country u where u.id = :name");
		query.setParameter("name", Integer.valueOf(name));
		userList = query.list();
		if (userList.size() > 0)
			return userList.get(0);
		else
			return null;
	}
	
	

}
