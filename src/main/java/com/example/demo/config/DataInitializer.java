package com.example.demo.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.example.demo.entity.Note;
import com.example.demo.entity.Post;
// import com.example.demo.entity.Post;
import com.example.demo.entity.User;
import com.example.demo.model.PostBody;
import com.example.demo.repository.NoteRepository;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Component
@Order(1)
public class DataInitializer implements CommandLineRunner {

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private NoteRepository noteRepository;

        @Autowired
        private PostRepository postRepository;

        @PersistenceContext
        private EntityManager entityManager;

        @Override
        @Transactional
        public void run(String... args) {
                try {
                        // root
                        User user1 = new User("admin", "admin", "admin");
                        User user2 = new User("moderator", "moderator", "moderator");
                        User user3 = new User("user", "user", "user");

                        // other
                        User moderator1 = new User("SpectreGuardian", "guardian123", "moderator");
                        User moderator2 = new User("InvokerMaster", "invoke456", "moderator");

                        User user4 = new User("AxeCrusher", "crusher789", "user");
                        User user5 = new User("SniperSharpshooter", "sharp101", "user");
                        User user6 = new User("DrowRangerHuntress", "huntress202", "user");
                        User user7 = new User("TinyBoulder", "boulder303", "user");
                        User user8 = new User("StormSpiritShockwave", "shockwave404", "user");
                        User user9 = new User("LunaMoonfang", "moonfang505", "user");
                        User user10 = new User("ChenHeavenlyGuidance", "guidance606", "user");
                        User user11 = new User("MeepoGeminateAttack", "geminate707", "user");

                        // Save users individually to ensure IDs are generated and available
                        user1 = userRepository.save(user1);
                        user2 = userRepository.save(user2);
                        user3 = userRepository.save(user3);

                        moderator1 = userRepository.save(moderator1);
                        moderator2 = userRepository.save(moderator2);

                        user4 = userRepository.save(user4);
                        user5 = userRepository.save(user5);
                        user6 = userRepository.save(user6);
                        user7 = userRepository.save(user7);
                        user8 = userRepository.save(user8);
                        user9 = userRepository.save(user9);
                        user10 = userRepository.save(user10);
                        user11 = userRepository.save(user11);

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

                        noteRepository.saveAll(Arrays.asList(
                                        note1, note2, note3, note4, note5,
                                        note6, note7, note8, note9, note10,
                                        note11, note12, note13, note14, note15,
                                        note16, note17, note18, note19, note20,
                                        note21, note22, note23, note24, note25));

                        PostBody pb1 = new PostBody("text", "lorem1");
                        PostBody pb2 = new PostBody("image",
                                        "https://upload.wikimedia.org/wikipedia/commons/b/b6/Image_created_with_a_mobile_phone.png");
                        PostBody pb3 = new PostBody("text", "lorem2");
                        PostBody pb4 = new PostBody("image",
                                        "https://tsx.x5static.net/i/800x800-fit/xdelivery/files/4c/a7/d55bc529fbedcc465f790fcc7a8d.jpg");

                        PostBody pb5 = new PostBody("image",
                                        "https://avatars.mds.yandex.net/get-lpc/1520633/dd1796d9-d6e9-410b-8011-d8dbe7be1446/orig");

                        PostBody pb6 = new PostBody("image",
                                        "https://i0.wp.com/www.vsyasol.com/wp-content/uploads/2021/04/image5.jpg?fit=1507%2C1303&ssl=1");

                        // Use the IDs from the saved user objects
                        Post post1 = new Post(user1.getId(), List.of(pb1, pb2, pb3));
                        Post post2 = new Post(user2.getId(), List.of(pb3, pb2, pb1));
                        Post post3 = new Post(user2.getId(), List.of(pb3, pb4, pb5));
                        Post post4 = new Post(user2.getId(), List.of(pb1, pb6, pb5));

                        postRepository.saveAll(Arrays.asList(post1, post2, post3, post4));

                        PostBody textPart = new PostBody("text",
                                        "Недавно играли тимой в Dota 2, попробовали новый тактический подход, решили собирать курьера уровня Godlike. Это было весело и полезно для команды.");
                        PostBody catImage = new PostBody("image",
                                        "https://image.lexica.art/sm2_webp/c95c5f8a-bea1-40e8-a2ad-bb749122e6f2");
                        Post post5 = new Post(user1.getId(), List.of(textPart, catImage));

                        PostBody textPart6 = new PostBody("text",
                                        "Сегодня наконец-то прокачал своего любимого Anti-Mage до максимального уровня. Чувствуется разница!");
                        PostBody catImage6 = new PostBody("image",
                                        "https://image.lexica.art/sm2_webp/1e7964f5-d8b9-4889-85c8-b688b1f5907c");
                        Post post6 = new Post(user2.getId(), List.of(textPart6, catImage6));

                        PostBody textPart7 = new PostBody("text",
                                        "Прошёл крутой челлендж в Dota 2: победил на одиночной линии с Phantom Assassin. Ощущения непередаваемые!");
                        PostBody catImage7 = new PostBody("image",
                                        "https://image.lexica.art/sm2_webp/461136f8-b730-4fbc-a4b6-4c8cfa053372");
                        Post post7 = new Post(user3.getId(), List.of(textPart7, catImage7));

                        PostBody textPart8 = new PostBody("text",
                                        "Всегда мечтал сыграть идеальным Broodmother. Сегодня получилось впервые забанить врагов, спрятавшись в паучьих сетях.");
                        PostBody catImage8 = new PostBody("image",
                                        "https://image.lexica.art/sm2_webp/778fbfec-6502-43da-b51d-89adc6edca46");
                        Post post8 = new Post(user4.getId(), List.of(textPart8, catImage8));

                        PostBody textPart9 = new PostBody("text",
                                        "Решили провести турнир по Dota 2 среди друзей. Главный приз — пицца и сладкий чай. Очень увлекательно прошло мероприятие!");
                        PostBody catImage9 = new PostBody("image",
                                        "https://image.lexica.art/sm2_webp/9036b49a-f310-433a-9348-a08f31d56d7d");
                        Post post9 = new Post(user5.getId(), List.of(textPart9, catImage9));

                        PostBody textPart10 = new PostBody("text",
                                        "Провели ночь в тренировочном режиме с командой. Разобрались с механикой нового героя Arc Warden.");
                        PostBody catImage10 = new PostBody("image",
                                        "https://image.lexica.art/sm2_webp/a49be0f6-10df-4ccf-97e5-0a9a0d990eee");
                        Post post10 = new Post(user6.getId(), List.of(textPart10, catImage10));

                        List<Post> aiPosts = Arrays.asList(post5, post6, post7, post8, post9, post10);

                        postRepository.saveAll(aiPosts);

                        System.out.println("Sample data initialized successfully!");
                } catch (Exception e) {
                        System.err.println("Error initializing sample data: " + e.getMessage());
                        e.printStackTrace();
                }
        }
}