/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ae.smartek.hebh.chatApp.dto;

import ae.smartek.hebh.chatApp.config.ChatAppConfig;
import ae.smartek.hebh.chatApp.model.Message;
import ae.smartek.hebh.chatApp.model.User;
import java.util.List;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author houssem
 */
@Component
public class ChatAppDTO {

    RestTemplate restTemplate;

    public ChatAppDTO() {
        restTemplate = new RestTemplate();
    }

    public void sendUser(User user) {
        restTemplate.postForObject(ChatAppConfig.USERS_MANAGEMENT_APP_ENDPOINT + "/addUser",
                user, User.class);
    }

    public void sendMessage(Message message) {
        restTemplate.postForObject(ChatAppConfig.USERS_CHAT_REST_APP_ENDPOINT + "/sendMessage",
                message, Message.class);
    }

    public List<User> getAllUsers() {
        ResponseEntity<List<User>> rateResponse
                = restTemplate.exchange(ChatAppConfig.USERS_MANAGEMENT_APP_ENDPOINT + "/getAllUsers",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {
                });
        return rateResponse.getBody();
//        return restTemplate.getForObject(ChatAppConfig.USERS_MANAGEMENT_APP_ENDPOINT + "/getAllUsers", List.class);
    }

    public User getUserByUserName(String userName) {
        return restTemplate.getForObject(ChatAppConfig.USERS_MANAGEMENT_APP_ENDPOINT
                + "/getUserByUserName?userName=" + userName, User.class);
    }

}
