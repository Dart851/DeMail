package ru.t_systems.demail.service.message;

import java.util.List;
import java.util.Set;

import ru.t_systems.demail.model.message.Message;
import ru.t_systems.demail.model.message.MessageStatuss;

public interface MessageStatusService {
	
	public MessageStatuss getMessageStatus(int id);
	public void addMessageStatus(MessageStatuss messageStatus);
	public void addMessageStatus(Set<MessageStatuss> messageStatus);
	public List<MessageStatuss> getMessageStatusByAccount(List<Integer> accounts);
	public List<MessageStatuss> getMessageStatusByAccount(Integer accountId);
}
