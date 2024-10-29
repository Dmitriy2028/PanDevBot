package com.pandev.pandevbot.commands;

import com.pandev.pandevbot.service.CategoryService;
import com.pandev.pandevbot.utils.Consts;
import com.pandev.pandevbot.utils.MessageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@RequiredArgsConstructor
@Component
public class AddElementCommand implements Command {

    private final CategoryService categoryService; // Сервис для работы с категориями

    /**
     * Метод обрабатывает команду /addElement, добавляя категорию в базу данных.
     *
     * @param update Объект Update, содержащий информацию о сообщении.
     * @return SendMessage объект с результатом выполнения команды.
     */
    public SendMessage apply(Update update) {
        String[] parts = update.getMessage().getText().split(" "); // Разделение текста команды
        String response; // Ответ, который будет отправлен пользователю
        long chatId = update.getMessage().getChatId(); // ID чата для сохранения категории

        if (parts.length == 2) {
            // Если передано два аргумента, добавляется корневая категория
            String rootName = parts[1];
            response = categoryService.addElement(rootName, chatId);
        } else if (parts.length == 3) {
            // Если передано три аргумента, добавляется дочерняя категория к указанной родительской
            String parentName = parts[1];
            String childName = parts[2];
            response = categoryService.addElement(parentName, childName, chatId);
        } else {
            // Если передано неправильное количество аргументов, возвращается сообщение об ошибке
            response = Consts.ADD_ELEMENT_WRONG_FORMAT;
        }
        // Формируем и возвращаем сообщение для отправки
        return MessageUtils.createSendMessage(chatId, response);
    }
}
