/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ae.smartek.hebh.chatApp;

import ae.smartek.hebh.chatApp.dao.ChatAppDAO;
import ae.smartek.hebh.chatApp.model.ChatAppModel;
import ae.smartek.hebh.chatApp.service.ChatAppService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author houssem
 */
@Configuration
@ComponentScan("ae.smartek")
public class AppConfig {

    @Bean
    public ChatAppService chatAppService() {
        return new ChatAppService();
    }

    @Bean
    public ChatAppModel chatAppModel() {
        return new ChatAppModel();
    }

    @Bean
    public ChatAppDAO chatAppDAO() {
        return new ChatAppDAO();
    }


}
