package com.pandev.pandevbot.service;

import com.pandev.pandevbot.entity.Category;
import com.pandev.pandevbot.entity.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.*;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    // Метод, возвращающий текстовое представление дерева категорий для определенного чата
    @Transactional
    public String viewTree(Long chatId) {
        List<Category> rootCategories = categoryRepository.findByParentIsNullAndChatId(chatId);
        StringBuilder treeRepresentation = new StringBuilder();

        int rootCount = rootCategories.size();
        for (int i = 0; i < rootCount; i++) {
            boolean isLastRoot = (i == rootCount - 1);
            appendCategory(treeRepresentation, rootCategories.get(i), "", isLastRoot);
        }

        return treeRepresentation.toString();
    }

    // Рекурсивный метод для добавления категорий в дерево с правильными отступами и символами
    private void appendCategory(StringBuilder builder, Category category, String prefix, boolean isLast) {
        // Добавляем префикс и проверяем, является ли категория последней в текущем уровне
        builder.append(prefix);
        if (isLast) {
            builder.append("└─ ");
            prefix += "    ";  // Добавляем пустое пространство вместо │ для последнего элемента
        } else {
            builder.append("├─ ");
            prefix += "│   ";  // Добавляем │ для промежуточных элементов
        }
        builder.append(category.getName()).append("\n");

        // Обрабатываем дочерние элементы рекурсивно
        List<Category> children = new ArrayList<>(category.getChildren());
        int childCount = children.size();
        for (int i = 0; i < childCount; i++) {
            boolean isLastChild = (i == childCount - 1);
            appendCategory(builder, children.get(i), prefix, isLastChild);
        }
    }

    // Метод для добавления дочернего элемента к родителю
    public String addElement(String parentName, String childName, Long chatId) {
        Optional<Category> parentCategory = categoryRepository.findByNameAndChatId(parentName, chatId);
        if (parentCategory.isPresent()) {
            Category childCategory = new Category(childName, parentCategory.get(), chatId);
            categoryRepository.save(childCategory);
            return "Элемент " + childName + " добавлен к родителю " + parentName;
        } else {
            return "Родительский элемент " + parentName + " не найден.";
        }
    }

    // Метод для добавления корневого элемента в дерево
    public String addElement(String rootName, Long chatId) {
        Category rootCategory = new Category(rootName, chatId);
        categoryRepository.save(rootCategory);
        return "Элемент " + rootName + " добавлен как корневой.";
    }

    // Метод для удаления элемента и его дочерних элементов
    @Transactional
    public String removeElement(String elementName, Long chatId) {
        // Получаем ID родительской категории
        Long parentId = categoryRepository.findIdByName(elementName, chatId);

        if (parentId != null) {
            // Удаляем дочерние категории по ID родительской категории
            categoryRepository.deleteChildrenByParentId(parentId, chatId);

            // Удаляем родительскую категорию
            categoryRepository.deleteByName(elementName, chatId);

            return "Элемент " + elementName + " и его дочерние элементы удалены.";
        } else {
            return "Элемент " + elementName + " не найден.";
        }
    }
}
