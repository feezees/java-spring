package com.example.demo.data;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

import java.util.Arrays;
import java.util.List;

public class UserDataGenerator {

    public static List<User> generateUsers(UserRepository userRepository) {
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

        return Arrays.asList(user1, user2, user3, moderator1, moderator2, user4, user5, user6, user7, user8, user9, user10, user11);
    }
} 