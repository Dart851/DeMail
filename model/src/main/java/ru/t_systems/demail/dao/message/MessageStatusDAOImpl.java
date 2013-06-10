package ru.t_systems.demail.dao.message;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ru.t_systems.demail.model.user.Account;
import ru.t_systems.demail.model.message.Message;
import ru.t_systems.demail.model.message.MessageStatuss;
import ru.t_systems.demail.model.user.User;

@Repository
@Transactional
public class MessageStatusDAOImpl implements MessageStatusDAO {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Transactional(readOnly = true)
	public MessageStatuss getMessageStatus(int id) {
		MessageStatuss messageStatus = (MessageStatuss) getCurrentSession()
				.get(MessageStatuss.class, id);
		return messageStatus;
	}

	public void addStatusUser(MessageStatuss messageStatus) {
		getCurrentSession().save(messageStatus);

	}

	public void addStatusUser(Set<MessageStatuss> messageStatus) {
		for (MessageStatuss status : messageStatus) {
			getCurrentSession().save(status);
		}

	}

	public List<MessageStatuss> getMessageStatussByAccount(List<Integer> accounts) {
Criteria criteria = getCurrentSession().createCriteria(MessageStatuss.class);
Criteria criteria1 = criteria.createCriteria("account");
criteria1.add(Restrictions.in("id", accounts));
//criteria1.add(Restrictions.eq("id", 2));
//criteria.add(Restrictions.eq("account.id", accountId));
//criteria.add(Restrictions.eq("account.id", 2));
//criteria.list();
		//		List<MessageStatuss> userList = new ArrayList<MessageStatuss>();
//		Query query = getCurrentSession().createQuery(
//				"FROM MessageStatuss s WHERE s.acounts IN (:account_ids)");
//		List<Integer> list = Arrays.asList(accountId);
//		query.setParameter("account_ids", list);
//	//	query.setParameter("account_id", accountId);
//	//	query.setParameter("account_id", 2);
//		userList = query.list();
		return criteria1.list();
	}

//	public List<Message> getMessageUser(int userId) {
//		Criteria criteria = getCurrentSession().createCriteria(Message.class);
//		// criteria.add(Restrictions.eq("isDelete",false));
//		Criteria criteria1= criteria.createAlias("status_message", "status_message");
//		
//		criteria1.createAlias("status_message.user", "user").add(Restrictions.eq("userid",userId));
//		/*criteria.add(Restrictions.eq("id", manId));
//		 criteria.setFetchMode("birds", FetchMode.JOIN);
//		 criteria.setFetchMode("dogs", FetchMode.JOIN);
//		 return criteria.uniqueResult();*/
//		return criteria1.list();
//	}

}
