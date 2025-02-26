package com.pr;

public class ComplexTask {
    // комплексная задача - сварить щи

    /**
     * Отварить мясо.
     */
    public void boilMeat() {
        sleep(10000);
        System.out.println("Мясо сварено");
    }

    /**
     * Сделать зажарку.
     */
    public void makeFry() {
        chopOnion();
        chopCarrots();
        sleep(2000);
        System.out.println("Зажарка готова");
    }

    /**
     * Нарезать капусту.
     */
    public void chopCabbage() {
        sleep(3000);
        System.out.println("Капуста нарезана");
    }

    /**
     * Нарезать картошку.
     */
    public void chopPotatoes() {
        peelPotatoes();
        sleep(2000);
        System.out.println("Картошка нарезана");
    }

    /**
     * Нарезать лук.
     */
    public void chopOnion() {
        sleep(2000);
        System.out.println("Лук нарезан");
    }

    /**
     * Нарезать морковь.
     */
    public void chopCarrots() {
        sleep(2000);
        System.out.println("Морковь нарезана");
    }

    /**
     * Почистить картошку.
     */
    public void peelPotatoes() {
        sleep(5000);
        System.out.println("Картошка почищена");
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
