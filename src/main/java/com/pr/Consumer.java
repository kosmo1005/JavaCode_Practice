package com.pr;

import com.pr.Animal.Animal;
import com.pr.Animal.Bobr;
import com.pr.Animal.Cat;
import com.pr.Animal.Dog;

public class Consumer {
    public void consume(Animal animal) {
        try {
            Thread.sleep(2000 + (int) (Math.random() * 3000));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        if (animal instanceof Dog) {
            System.out.println(Thread.currentThread().getName() + " лечит собаку.");
        } else if (animal instanceof Cat) {
            System.out.println(Thread.currentThread().getName() + " лечит кошку.");
        } else if (animal instanceof Bobr) {
            System.out.println(Thread.currentThread().getName() + " лечит бобера.");
        } else {
            System.out.println("Неизвестное животное мимо пробегало");
        }
    }
}
