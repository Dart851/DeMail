package ru.t_systems.demail.service.message;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.t_systems.demail.dao.message.MessageStatusDAO;
import ru.t_systems.demail.model.message.MessageStatuss;

@Service
@Transactional
public class MessageStatusServiceImpl implements MessageStatusService {

	@Autowired
	private MessageStatusDAO messageStatusDAO;

	public MessageStatuss getMessageStatus(int id) {
		return messageStatusDAO.getMessageStatus(id);
	}

	public void addMessageStatus(MessageStatuss messageStatus) {
		messageStatusDAO.addStatusUser(messageStatus);

	}

	public void addMessageStatus(Set<MessageStatuss> messageStatus) {
		messageStatusDAO.addStatusUser(messageStatus);

	}

	public List<MessageStatuss> getMessageStatusByAccount(List<Integer> accounts) {
		messageStatusDAO.getMessageStatussByAccount(accounts);
		return null;
	}

	public List<MessageStatuss> getMessageStatusByAccount(Integer accountId) {
		return messageStatusDAO.getMessageStatussByAccount(Arrays
				.asList(accountId));

	}

}
