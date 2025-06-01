package com.example.demo.data;

import com.example.demo.entity.Post;
import com.example.demo.entity.User;
import com.example.demo.model.PostBody;
import com.example.demo.repository.PostRepository;

import java.util.Arrays;
import java.util.List;

public class PostDataGenerator {

    public static List<Post> generatePosts(PostRepository postRepository, List<User> users) {
        User user1 = users.get(0);
        User user2 = users.get(1);
        User user3 = users.get(2);
        User user4 = users.get(5);
        User user5 = users.get(6);
        User user6 = users.get(7);
        User user7 = users.get(8);
        User user8 = users.get(9);
        User user9 = users.get(10);
        User user10 = users.get(11);
        User user11 = users.get(12);

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

        // Additional posts to ensure each user has several
        Post post11 = new Post(user3.getId(), List.of(pb1, pb2));
        Post post12 = new Post(user3.getId(), List.of(pb3, pb4));

        Post post13 = new Post(user4.getId(), List.of(pb5, pb6));
        Post post14 = new Post(user4.getId(), List.of(pb1, pb3));

        Post post15 = new Post(user5.getId(), List.of(pb2, pb4));
        Post post16 = new Post(user5.getId(), List.of(pb6, pb2));

        Post post17 = new Post(user6.getId(), List.of(pb3, pb5));
        Post post18 = new Post(user6.getId(), List.of(pb1, pb4));

        Post post19 = new Post(user7.getId(), List.of(pb1, pb2, pb3));
        Post post20 = new Post(user7.getId(), List.of(pb4, pb5, pb6));
        Post post21 = new Post(user7.getId(), List.of(pb1, pb6));

        Post post22 = new Post(user8.getId(), List.of(pb2, pb3, pb4));
        Post post23 = new Post(user8.getId(), List.of(pb5, pb1, pb3));
        Post post24 = new Post(user8.getId(), List.of(pb2, pb6));

        Post post25 = new Post(user9.getId(), List.of(pb3, pb4, pb5));
        Post post26 = new Post(user9.getId(), List.of(pb6, pb2, pb4));
        Post post27 = new Post(user9.getId(), List.of(pb3, pb1));

        Post post28 = new Post(user10.getId(), List.of(pb4, pb5, pb6));
        Post post29 = new Post(user10.getId(), List.of(pb1, pb3, pb5));
        Post post30 = new Post(user10.getId(), List.of(pb4, pb2));

        Post post31 = new Post(user11.getId(), List.of(pb5, pb6, pb1));
        Post post32 = new Post(user11.getId(), List.of(pb2, pb4, pb6));
        Post post33 = new Post(user11.getId(), List.of(pb5, pb3));

        List<Post> allPosts = Arrays.asList(
                post1, post2, post3, post4, post5, post6, post7, post8, post9, post10,
                post11, post12, post13, post14, post15, post16, post17, post18, post19,
                post20, post21, post22, post23, post24, post25, post26, post27, post28,
                post29, post30, post31, post32, post33);

        return postRepository.saveAll(allPosts);
    }
} 