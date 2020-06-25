/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ae.smartek.hebh.chatApp.model;

import java.util.HashMap;
import java.util.List;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author houssem
 */
@Component
@Scope(value = "singleton")
public class ChatAppModel {

    private HashMap<String, List<Message>> messages;
    

    public ChatAppModel() {
        this.messages = new HashMap<>();
    }

    public HashMap<String, List<Message>> getMessages() {
        return messages;
    }

    public void setMessages(HashMap<String, List<Message>> messages) {
        this.messages = messages;
    }

}
