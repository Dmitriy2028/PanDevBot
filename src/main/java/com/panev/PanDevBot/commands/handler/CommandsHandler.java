package com.panev.PanDevBot.commands.handler;

import com.panev.PanDevBot.commands.*;
import com.panev.PanDevBot.utils.Consts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Map;

@Component
@Slf4j
public class CommandsHandler {

    private final Map<String, Command> commands;  // Словарь команд для хранения связей между строками команд и их реализациями

    /**
     * Конструктор для инициализации команд, поддерживаемых ботом.
     * Используется Map для сопоставления текстовых команд с их реализациями.
     */
    public CommandsHandler(StartCommand startCommand,
                           ViewTreeCommand viewTreeCommand,
                           AddElementCommand addElementCommand,
                           RemoveElementCommand removeElementCommand,
                           HelpCommand helpCommand) {

        // Map.of создаёт неизменяемый Map с командами и их обработчиками
        this.commands = Map.of(
                "/start", startCommand,
                "/viewTree", viewTreeCommand,
                "/addElement", addElementCommand,
                "/removeElement", removeElementCommand,
                "/help", helpCommand
        );
    }

    /**
     * Обрабатывает входящее сообщение и вызывает соответствующую команду, если она определена.
     *
     * @param update Объект Update, содержащий информацию о сообщении.
     * @return SendMessage ответ на команду или сообщение об ошибке, если команда неизвестна.
     */
    public SendMessage handleCommands(Update update) {
        // Получаем текст сообщения и идентификатор чата
        String messageText = update.getMessage().getText();
        String command = messageText.split(" ")[0];
        long chatId = update.getMessage().getChatId();

        // Получаем обработчик команды из Map команд
        var commandHandler = commands.get(command);
        if (commandHandler != null) {
            // Вызываем команду, если она найдена
            return commandHandler.apply(update);
        } else {
            // Возвращаем сообщение об ошибке, если команда неизвестна
            return new SendMessage(String.valueOf(chatId), Consts.UNKNOWN_COMMAND);
        }
    }
}
