package com.pr;

public class ComplexTask {
    // комплексная задача - сварить щи

    /**
     * Отварить мясо.
     */
    public String boilMeat() {
        sleep(10000);
        return "Мясо сварено";
    }

    /**
     * Сделать зажарку.
     */
    public String makeFry() {
        sleep(2000);
        return chopOnion() + "\n" + chopCarrots() + "\n" + "Зажарка готова";
    }

    /**
     * Нарезать капусту.
     */
    public String chopCabbage() {
        sleep(3000);
        return "Капуста нарезана";
    }

    /**
     * Нарезать картошку.
     */
    public String chopPotatoes() {
        sleep(2000);
        return peelPotatoes() + "\n" + "Картошка нарезана";
    }

    /**
     * Нарезать лук.
     */
    public String chopOnion() {
        sleep(2000);
        return "Лук нарезан";
    }

    /**
     * Нарезать морковь.
     */
    public String chopCarrots() {
        sleep(2000);
        return "Морковь нарезана";
    }

    /**
     * Почистить картошку.
     */
    public String peelPotatoes() {
        sleep(5000);
        return "Картошка почищена";
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
