package com.panev.PanDevBot.utils;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
public class MessageUtils {

    /**
     * Создает объект SendMessage с заданным текстом и chatId.
     *
     * @param chatId идентификатор чата, в который отправляется сообщение.
     * @param text текст сообщения для отправки.
     * @return объект SendMessage с установленными полями chatId и text.
     */
    public static SendMessage createSendMessage(long chatId, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText(text);
        return sendMessage;
    }
}
