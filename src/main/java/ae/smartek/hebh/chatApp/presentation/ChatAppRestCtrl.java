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
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author houssem
 */
@RestController
@RequestMapping("ws")
public class ChatAppRestCtrl {

    @Autowired
    ChatAppModel appModel;
    @Autowired
    ChatAppService service;
//OK
    @PostMapping("/receiveMessage")
    public void receiveMessage(@RequestBody Message message) {
        appModel.getMessages().putIfAbsent(message.getFrom(), new LinkedList<>());
        appModel.getMessages().get(message.getFrom()).add(message);
        if (!message.getFrom().equals(message.getTo())) {
            appModel.getMessages().putIfAbsent(message.getTo(), new LinkedList<>());
            appModel.getMessages().get(message.getTo()).add(message);
        }
    }
//OK
    @PostMapping("/addUser")
    public void addUser(@RequestBody User user) {
        service.sendUser(user);
    }
//ok
    @PostMapping("/sendMessage")
    public void sendMessage(@RequestBody Message message) {
        service.sendMessage(message.getFrom(), message.getTo(), message.getMessageText());
    }
//ok
    @GetMapping("/getUserByUserName")
    public User getUserByUserName(@RequestParam String userName) {
        return service.getUserByUserName(userName);
    }
//OK
    @GetMapping("/receiveAllUsers")
    public List<User> receiveAllUsers() {
        return service.getAllUsers();
    }
//OK
    @GetMapping("/getAllMessages")
    public List<Message> getAllMessages(@RequestParam String user) {
//        return appModel.getMessages().get(user);
        return service.getAllMessages(user);
    }

}
