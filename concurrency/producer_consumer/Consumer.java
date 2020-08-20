package com.martimlima.javaprojects.concurrency.producer_consumer;

public class Consumer implements Runnable {

    private final BlockingQueue<Integer> queue;
    private int consumedElements;

    public Consumer(BlockingQueue queue, int consumedElements) {
        this.queue = queue;
        this.consumedElements = consumedElements;
    }

    @Override
    public void run() {

        while (consumedElements > 0) {

            synchronized (queue) {
                // Block until element is removed from queue
                int elem = queue.poll();
                System.out.println("Thread " + Thread.currentThread().getName() + " has consumed element " + elem + " from the queue");

                if (queue.getSize() == 0) {
                    System.out.println(Thread.currentThread().getName() + " has left the queue empty");
                }
            }

            --this.consumedElements;

            try {
                Thread.sleep((int) (Math.random() * 10));
            } catch (InterruptedException e) {
                // thread.interrupt called, no handling needed
            }
        }
    }
}