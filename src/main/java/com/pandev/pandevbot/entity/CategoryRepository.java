package com.pandev.pandevbot.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    // Поиск категории по имени и chatId
    Optional<Category> findByNameAndChatId(String name, Long chatId);

    // Поиск корневых категорий для конкретного chatId
    List<Category> findByParentIsNullAndChatId(Long chatId);

    // Поиск всех категорий с подкатегориями для конкретного chatId
    @Query("SELECT c FROM Category c LEFT JOIN FETCH c.children WHERE c.chatId = :chatId")
    List<Category> findAllWithChildren(Long chatId);

    // Получение ID категории по имени и chatId
    @Query("SELECT c.id FROM Category c WHERE c.name = :name AND c.chatId = :chatId")
    Long findIdByName(String name, Long chatId);

    // Удаление дочерних категорий по parentId для конкретного chatId
    @Modifying
    @Transactional
    @Query("DELETE FROM Category c WHERE c.parent.id = :parentId AND c.chatId = :chatId")
    void deleteChildrenByParentId(Long parentId, Long chatId);

    // Удаление категории по имени и chatId
    @Modifying
    @Transactional
    @Query("DELETE FROM Category c WHERE c.name = :name AND c.chatId = :chatId")
    void deleteByName(String name, Long chatId);
}
