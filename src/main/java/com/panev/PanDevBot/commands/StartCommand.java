package com.panev.PanDevBot.commands;

import com.panev.PanDevBot.utils.Consts;
import com.panev.PanDevBot.utils.MessageUtils;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

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
