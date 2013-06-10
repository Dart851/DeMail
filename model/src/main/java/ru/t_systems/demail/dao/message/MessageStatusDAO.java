package ru.t_systems.demail.dao.message;

import java.util.List;
import java.util.Set;

import ru.t_systems.demail.model.message.Message;
import ru.t_systems.demail.model.message.MessageStatuss;

public interface MessageStatusDAO {
	
	public MessageStatuss getMessageStatus(int id);
	public void addStatusUser(MessageStatuss messageStatus);
	public void addStatusUser(Set<MessageStatuss> messageStatus);
	public List<MessageStatuss> getMessageStatussByAccount(List<Integer> accounts);
}
