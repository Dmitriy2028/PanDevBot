package com.panev.PanDevBot.commands;

import com.panev.PanDevBot.service.CategoryService;
import com.panev.PanDevBot.utils.Consts;
import com.panev.PanDevBot.utils.MessageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@RequiredArgsConstructor
@Component
public class HelpCommand implements Command {

    /**
     * Метод обрабатывает команду /help, возвращая сообщение с описанием доступных команд.
     *
     * @param update Объект Update, содержащий информацию о сообщении.
     * @return SendMessage объект с текстом справки для пользователя.
     */
    @Override
    public SendMessage apply(Update update) {
        String response = Consts.HELP_MESSAGE; // Получение текста справки из CategoryService
        long chatId = update.getMessage().getChatId(); // Получение ID чата, чтобы отправить сообщение пользователю
        return MessageUtils.createSendMessage(chatId, response);
    }
}
