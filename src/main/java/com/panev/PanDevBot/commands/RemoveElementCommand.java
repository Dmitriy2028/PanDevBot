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
public class RemoveElementCommand implements Command {

    private final CategoryService categoryService; // Сервис для работы с категориями

    /**
     * Метод обрабатывает команду /removeElement и удаляет указанную категорию.
     * Ожидается формат команды "/removeElement <название элемента>".
     *
     * @param update Объект Update, содержащий информацию о сообщении.
     * @return SendMessage объект с ответом о результате удаления.
     */
    @Override
    public SendMessage apply(Update update) {
        String[] parts = update.getMessage().getText().split(" ");
        String response;
        long chatId = update.getMessage().getChatId();

        if (parts.length == 2) {
            // Если введен правильный формат, удаляем элемент
            String elementName = parts[1];
            response = categoryService.removeElement(elementName, chatId);
        } else {
            // Если формат команды неверен, отправляем сообщение с инструкцией
            response = Consts.REMOVE_ELEMENT_WRONG_FORMAT;
        }
        // Формируем и возвращаем сообщение для отправки
        return MessageUtils.createSendMessage(chatId, response);
    }
}
