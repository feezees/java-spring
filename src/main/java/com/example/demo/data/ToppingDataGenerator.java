package com.example.demo.data;

import com.example.demo.entity.Topping;
import com.example.demo.repository.ToppingRepository;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class ToppingDataGenerator {

    public static List<Topping> generateToppings(ToppingRepository toppingRepository) {
        Topping tropicalPulp = new Topping(
                "Тропическое пюре с маракуйей и кокосом",
                new BigDecimal("15.99"),
                100,
                Topping.Category.ADDITIONALS);

        Topping vanillaMilk = new Topping("Молоко со вкусом ванили", new BigDecimal("12.99"), 50,
                Topping.Category.LIQUIDS);
        Topping almondMilk = new Topping("Органическое миндальное молоко", new BigDecimal("10.99"), 75,
                Topping.Category.LIQUIDS);
        Topping coconutChocolateMilk = new Topping("Шоколадное кокосовое молоко",
                new BigDecimal("14.99"), 40, Topping.Category.LIQUIDS);
        Topping oatsCinnamonMilk = new Topping("Овсяное молоко с корицей", new BigDecimal("9.99"), 60,
                Topping.Category.LIQUIDS);
        Topping orangeJuice = new Topping("Свежевыжатый апельсиновый сок", new BigDecimal("8.99"), 80,
                Topping.Category.LIQUIDS);
        Topping skimMilk = new Topping("Натуральное обезжиренное молоко", new BigDecimal("7.99"), 90,
                Topping.Category.LIQUIDS);
        Topping strawberryPuree = new Topping("Клубничное пюре", new BigDecimal("11.99"), 30,
                Topping.Category.FRUITS);
        Topping mangoChunks = new Topping("Кусочки свежего манго", new BigDecimal("13.99"), 25,
                Topping.Category.FRUITS);
        Topping berryMix = new Topping("Микс лесных ягод", new BigDecimal("15.99"), 20,
                Topping.Category.FRUITS);
        Topping granolaNutsSeeds = new Topping("Хрустящий гранола с орехами и семенами подсолнечника",
                new BigDecimal("18.99"), 15, Topping.Category.ADDITIONALS);

        List<Topping> allToppings = Arrays.asList(
                vanillaMilk,
                almondMilk,
                coconutChocolateMilk,
                oatsCinnamonMilk,
                orangeJuice,
                skimMilk,
                strawberryPuree,
                mangoChunks,
                berryMix,
                granolaNutsSeeds,
                tropicalPulp
        );

        return toppingRepository.saveAll(allToppings);
    }
} 