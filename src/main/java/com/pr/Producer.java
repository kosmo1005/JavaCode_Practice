package com.pr;

import com.pr.Animal.Animal;
import com.pr.Animal.Bobr;
import com.pr.Animal.Cat;
import com.pr.Animal.Dog;

import java.util.Random;

public class Producer {
    public Animal produce (){
        try {
            Thread.sleep(2000 + (int) (Math.random() * 3000));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return switch (new Random().nextInt(3)) {
            case 0 -> {
                System.out.println("Собака заболела");
                yield new Dog();
            }
            case 1 -> {
                System.out.println("Кошка заболела");
                yield new Cat();
            }
            case 2 -> {
                System.out.println("Бобр заболел");
                yield new Bobr();
            }
            default -> throw new IllegalStateException("Unexpected value:");
        };
    }
}
