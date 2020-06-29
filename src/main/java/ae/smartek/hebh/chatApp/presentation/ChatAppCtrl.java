/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ae.smartek.hebh.chatApp.presentation;

import ae.smartek.hebh.chatApp.model.ChatAppModel;
import ae.smartek.hebh.chatApp.model.Message;
import ae.smartek.hebh.chatApp.model.User;
import ae.smartek.hebh.chatApp.service.ChatAppService;
import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author houssem
 */
@Controller
@Scope("session")
public class ChatAppCtrl implements Serializable {

//    private static ChatAppCtrl bean;
    @Autowired
    ChatAppService service;
    @Autowired
    ChatAppModel appModel;
    public static Logger logger = Logger.getLogger(ChatAppCtrl.class.getName());

    private Model model = new Model();

    @PostConstruct
    private void init() {
        getModel().setMessagesList(new LinkedList<>());
        getModel().setUsersList(new LinkedList<>());
        getModel().setUser(new User());
    }

    public Model getModel() {
        return model;
    }

    public void addUser() throws IOException {
        service.sendUser(getModel().getUser());
        getModel().setUser(new User());
        redirectToIndex();
    }

    public void sendMessage() {
        logger.log(Level.INFO,getModel().getUser().getUserName());
        logger.log(Level.INFO,getModel().getTo().getUserName());
        logger.log(Level.INFO,getModel().getSentMessage());
        service.sendMessage(getModel().getUser().getUserName(), getModel().getTo().getUserName(), getModel().getSentMessage());
        getModel().setSentMessage("");
    }

    public boolean isUserExists() {
        getModel().setUser(getUserByUserName(getModel().getUserName()));
        return (getModel().getUser() != null);
    }

    public void submitUser() throws IOException {
        if (!isUserExists()) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error!", "Username " + getModel().getUserName() + " does not exist!!!"));
            return;
        }
        receiveAllUsers();
        redirectToChat();
    }

    public String createUserLabel(String firstName, String lastName) {
        return firstName + " " + lastName;
    }

    public User getUserByLabel(String label) {
        String[] ch = label.split(" ");
        for (User user : getModel().getUsersList()) {
            if ((user.getFirstName().equals(ch[0])) && (user.getLastName().equals(ch[1]))) {
                return user;
            }
        }
        return null;
    }

    public void redirectToChat() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("chat.xhtml");
    }

    public void redirectToIndex() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
    }
    public void redirectToAddUser() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("addUser.xhtml");
    }

    public User getUserByUserName(String userName) {
        return service.getUserByUserName(userName);
    }

    public void receiveMessagesByUsers() {
        if ((getModel().getUser() != null) && (getModel().getTo() != null)) {
            List<Message> l = service.getMessagesByUsers(getModel().getUser().getUserName(), getModel().getTo().getUserName());
            if (l != null) {
                getModel().setMessagesList(l);
            }
        }
    }

    public void receiveAllUsers() {
        getModel().setUsersList(service.getAllUsers());
    }

    public void onUserChange() {
        if (getModel().getTo() != null) {
            receiveMessagesByUsers();
        } else {
            getModel().setMessagesList(new LinkedList<>());
        }
    }

    public class Model {

        private List<Message> messagesList;
        private List<User> usersList;
        private String sentMessage;
        private User to;
        private String userName;
        private User user;

        public List<Message> getMessagesList() {
            return messagesList;
        }

        public void setMessagesList(List<Message> messagesList) {
            this.messagesList = messagesList;
        }

        public String getSentMessage() {
            return sentMessage;
        }

        public void setSentMessage(String sentMessage) {
            this.sentMessage = sentMessage;
        }

        public User getTo() {
            return to;
        }

        public void setTo(User to) {
            this.to = to;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public List<User> getUsersList() {
            return usersList;
        }

        public void setUsersList(List<User> usersList) {
            this.usersList = usersList;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

    }

}
