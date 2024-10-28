package com.panev.PanDevBot.utils;

public class Consts {
    public static final String UNKNOWN_COMMAND = "Извините, я не знаю такой команды";
    public static final String CANT_UNDERSTAND = "Извините, я не понял, что вы имеете ввиду";
    public static final String ADD_ELEMENT_WRONG_FORMAT = "Неверный формат команды. Используйте /addElement <название элемента> или /addElement <родитель> <дочерний элемент>";
    public static final String REMOVE_ELEMENT_WRONG_FORMAT = "Неверный формат команды. Используйте /removeElement <название элемента>";
    public static final String START_MESSAGE = "Welcome to the bot!";
    public static final String HELP_MESSAGE = "/viewTree - Показать дерево категорий\n" +
            "/addElement <название элемента> - Добавить корневой элемент\n" +
            "/addElement <родитель> <дочерний элемент> - Добавить дочерний элемент к существующему\n" +
            "/removeElement <название элемента> - Удалить элемент и все дочерние\n" +
            "/help - Показать список команд";
}
