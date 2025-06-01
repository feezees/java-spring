package com.example.demo.data;

import com.example.demo.entity.Note;
import com.example.demo.entity.User;
import com.example.demo.repository.NoteRepository;

import java.util.Arrays;
import java.util.List;

public class NoteDataGenerator {

    public static List<Note> generateNotes(NoteRepository noteRepository, List<User> users) {
        User user1 = users.get(0);
        User user2 = users.get(1);
        User user3 = users.get(2);
        User moderator1 = users.get(3);
        User moderator2 = users.get(4);
        User user4 = users.get(5);
        User user5 = users.get(6);
        User user6 = users.get(7);
        User user7 = users.get(8);
        User user8 = users.get(9);
        User user9 = users.get(10);
        User user10 = users.get(11);
        User user11 = users.get(12);

        Note note1 = new Note(user1.getId(), "Первая заметка пользователя 1");
        Note note2 = new Note(user1.getId(), "Вторая заметка пользователя 1");
        Note note3 = new Note(user2.getId(), "Первая заметка пользователя 2");
        Note note4 = new Note(user2.getId(), "Важная заметка пользователя 2");
        Note note5 = new Note(user3.getId(), "Единственная заметка пользователя 3");

        Note note6 = new Note(moderator1.getId(), "Планирование турнира по Dota 2");
        Note note7 = new Note(moderator1.getId(), "Обновление правил форума");
        Note note8 = new Note(moderator2.getId(), "Подготовка к чемпионату мира по киберспорту");
        Note note9 = new Note(moderator2.getId(), "Стратегии улучшения баланса героев");
        Note note10 = new Note(user4.getId(), "Тренировка ударов топором");
        Note note11 = new Note(user4.getId(), "Подбор снаряжения для Аекса");
        Note note12 = new Note(user5.getId(), "Практика стрельбы издалека");
        Note note13 = new Note(user5.getId(), "Выбор лучшего оружия для Снайпера");
        Note note14 = new Note(user6.getId(), "Советы по скрытному перемещению");
        Note note15 = new Note(user6.getId(), "Анализ метовых билдов для Дроу Рэнджер");
        Note note16 = new Note(user7.getId(), "Улучшение контроля толпы");
        Note note17 = new Note(user7.getId(), "Эффективность магического сопротивления");
        Note note18 = new Note(user8.getId(), "Управление электрическими зарядами");
        Note note19 = new Note(user8.getId(), "Оптимизация способностей Сторм Спирита");
        Note note20 = new Note(user9.getId(), "Развитие навыка луны Луны");
        Note note21 = new Note(user9.getId(), "Тактика игры против вражеских героев");
        Note note22 = new Note(user10.getId(), "Руководство по использованию питомцев Чена");
        Note note23 = new Note(user10.getId(), "Создание эффективного построения армии");
        Note note24 = new Note(user11.getId(), "Координация действий клонов Мипо");
        Note note25 = new Note(user11.getId(), "Контроль линии и управление ресурсами");

        List<Note> notes = Arrays.asList(
                note1, note2, note3, note4, note5,
                note6, note7, note8, note9, note10,
                note11, note12, note13, note14, note15,
                note16, note17, note18, note19, note20,
                note21, note22, note23, note24, note25);

        return noteRepository.saveAll(notes);
    }
} 