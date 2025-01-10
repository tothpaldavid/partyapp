package com.realtimeweb.partyapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic"); // Üzenetek fogadása
        config.setApplicationDestinationPrefixes("/app"); // Üzenetek küldése
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws") // WebSocket végpont
                .setAllowedOriginPatterns("*") // Minden eredet engedélyezése (biztonságosabb mód)
                .withSockJS(); // Alternatíva régebbi böngészők támogatására
    }
}

