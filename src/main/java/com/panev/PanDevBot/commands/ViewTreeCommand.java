package com.panev.PanDevBot.commands;

import com.panev.PanDevBot.service.CategoryService;
import com.panev.PanDevBot.utils.MessageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;


@RequiredArgsConstructor
@Component
public class ViewTreeCommand implements Command {

    private final CategoryService categoryService; // Сервис для работы с категориями

    @Override
    public SendMessage apply(Update update) {
        long chatId = update.getMessage().getChatId();
        String response = categoryService.viewTree(chatId);
        // Формируем и возвращаем сообщение для отправки
        return MessageUtils.createSendMessage(chatId, response);
    }

}
