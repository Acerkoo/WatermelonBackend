package cn.watermelon.watermelonbackend.configuration;

import cn.watermelon.watermelonbackend.handler.BaseHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(baseHandler(), "commentHandler/{ID}")
                .setAllowedOrigins("*");
    }

    public BaseHandler baseHandler() {
        return new BaseHandler();
    }
}
