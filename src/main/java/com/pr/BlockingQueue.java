package com.pr;

import com.pr.Animal.Animal;

public class BlockingQueue {

    private final int capacity;
    private final Animal[] buffer;
    private int front;
    private int back;
    private int size;

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
        this.buffer =  new Animal[capacity];
        this.front = 0;
        this.back = 0;
        this.size = 0;

    }

    public synchronized void enqueue(Animal animal){
        if(size == capacity){
            System.out.println("Заблокирована попытка животного " + animal.voice() + " встать в очередь.");
        }
        while (size == capacity) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        buffer[back] = animal;
        System.out.println("Животное встает в очередь: " + animal.voice());
        System.out.println("Заполненность очереди: " + (size + 1) + " из " + capacity + ".");
        back = (back + 1) % capacity;
        size++;

        notifyAll();
    }

    public synchronized Animal dequeue(){
       if(size == 0){
           System.out.println("Очередь пуста. Врач может попить чай.");
       }
        while (size == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        Animal animal = buffer[front];
        System.out.println("Животное уходит из очереди: " + animal.voice());
        System.out.println("Заполненность очереди: " + (size - 1) + " из " + capacity + ".");
        front = (front + 1) % capacity;
        size--;

        notifyAll();
        return animal;
    }

    public int getSize (){
        return size;
    }
}
