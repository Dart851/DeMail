package ru.t_systems.demail.dao.message;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ru.t_systems.demail.model.message.Message;
import ru.t_systems.demail.model.message.MessageStatuss;

@Repository
@Transactional
public class MessageDAOImpl implements MessageDAO {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public Message getMessage(int id) {
		Message message = (Message) getCurrentSession().get(Message.class, id);
		return message;
	}

	public void addMessage(Message message) {
		getCurrentSession().save(message);

	}

	public List<Message> getMessageByStatus(List<MessageStatuss> status) {
		Query query = getCurrentSession().createQuery("from Message m join m.status s where s.id in (:list)");
		List<Integer> a = new ArrayList<Integer>();
		for(MessageStatuss s :status){
			a.add(s.getId());
			System.out.println("----BODY = "+s.getMessage().getBody());
		}
		query.setParameterList("list", a);
		return query.list();
	}

}
