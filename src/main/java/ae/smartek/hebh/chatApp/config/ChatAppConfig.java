/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ae.smartek.hebh.chatApp.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author houssem
 */
@Configuration
public class ChatAppConfig {
public static String USERS_MANAGEMENT_APP_ENDPOINT;
public static String USERS_CHAT_REST_APP_ENDPOINT;

    public ChatAppConfig() {
        getApplicationsEndpointsFromConfigFile();
    }



    private static void getApplicationsEndpointsFromConfigFile() {
        InputStream input = ChatAppConfig.class.getClassLoader().getResourceAsStream("config.properties");
        Properties config = new Properties();
        try {
            config.load(input);
            USERS_MANAGEMENT_APP_ENDPOINT=(String) "http://"+config.get("users_management_app_endpoint")+"/ws";
            USERS_CHAT_REST_APP_ENDPOINT=(String) "http://"+config.get("rest_chat_app_endpoint")+"/ws";
        } catch (IOException ex) {
            Logger.getLogger(ChatAppConfig.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
