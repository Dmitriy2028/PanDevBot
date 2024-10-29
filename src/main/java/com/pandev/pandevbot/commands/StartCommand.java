package com.pandev.pandevbot.commands;

import com.pandev.pandevbot.utils.Consts;
import com.pandev.pandevbot.utils.MessageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@RequiredArgsConstructor
@Component
public class StartCommand implements Command {

    @Override
    public SendMessage apply(Update update) {
        String response = Consts.START_MESSAGE;
        long chatId = update.getMessage().getChatId();
        // Формируем и возвращаем сообщение для отправки
        return MessageUtils.createSendMessage(chatId, response);
    }

}
