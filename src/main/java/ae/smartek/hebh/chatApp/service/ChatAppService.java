/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ae.smartek.hebh.chatApp.service;

import ae.smartek.hebh.chatApp.dao.ChatAppDAO;
import ae.smartek.hebh.chatApp.model.ChatAppModel;
import ae.smartek.hebh.chatApp.model.Message;
import ae.smartek.hebh.chatApp.model.User;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 *
 * @author houssem
 */
@Service
@Scope(value = "singleton")
public class ChatAppService {

    @Autowired
    ChatAppDAO dto;
    @Autowired
    ChatAppModel appModel;

    public List<Message> getMessagesByUsers(String user, String to) {
        List<Message> l = appModel.getMessages().get(user);
        List<Message> rl = new LinkedList<>();
        if ((l != null) && (!l.isEmpty())) {
            if (user.equals(to)) {
                for (Message message : l) {
                    if ((message.getTo().equals(to)) && (message.getFrom().equals(to))) {
                        rl.add(message);
                    }
                }
            } else {
                for (Message message : l) {
                    if ((message.getTo().equals(to)) || (message.getFrom().equals(to))) {
                        rl.add(message);
                    }
                }
            }
        }
        if (!rl.isEmpty()) {
            rl.sort((Message o1, Message o2) -> (int) (o1.getTimeStamp() - o2.getTimeStamp()));
        }
        return rl;
    }

    public void sendUser(User user) {
        dto.sendUser(user);
    }

    public void sendMessage(String from, String to, String message) {
        Message m = new Message();
        m.setFrom(from);
        m.setTo(to);
        m.setMessageText(message);
        dto.sendMessage(m);
    }
    
    public List<User> getAllUsers(){
        return dto.getAllUsers();
    }
    
     public User getUserByUserName(String userName){
        return dto.getUserByUserName(userName);
    }

    public List<Message> getAllMessages(String user) {
        List<Message> l= new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            Message m= new Message();
            m.setFrom(user);
            m.setTo("test"+i);
            m.setMessageText("test"+i);
            l.add(m);
        }
        return l;
    }

}
