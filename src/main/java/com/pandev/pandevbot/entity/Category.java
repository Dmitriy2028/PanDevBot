package com.pandev.pandevbot.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Уникальный идентификатор категории

    private String name;  // Название категории

    // Связь с родительской категорией, если такая имеется
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category parent;

    // Связь с дочерними категориями с каскадным удалением и немедленной загрузкой
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Category> children = new HashSet<>();

    // Поле для идентификации чата, к которому относится категория
    private Long chatId;

    // Конструктор без параметров для JPA
    public Category() {}

    // Конструктор для корневой категории
    public Category(String name, Long chatId) {
        this.name = name;
        this.chatId = chatId;
    }

    // Конструктор для дочерней категории
    public Category(String name, Category parent, Long chatId) {
        this.name = name;
        this.parent = parent;
        this.chatId = chatId;
    }

    // Переопределение hashCode с использованием только id
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // Переопределение equals с использованием только id для корректного сравнения сущностей
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Category category = (Category) obj;
        return Objects.equals(id, category.id);
    }
}