package com.martimlima.javaprojects.concurrency.producer_consumer;

public class Producer implements Runnable {

    private final BlockingQueue<Integer> queue;
    private int elementsToProduce;

    public Producer(BlockingQueue queue, int elementsToProduce) {
        this.queue = queue;
        this.elementsToProduce = elementsToProduce;
    }


    @Override
    public void run() {

        while (elementsToProduce > 0){
            int elem = (int)(Math.random() * 9);

            synchronized (queue) {
                System.out.println("Thread " + Thread.currentThread().getName() + " will add the element " + elem + " to the queue");

                // Block until element is added to queue
                queue.offer(elem);

                if (queue.getSize() == queue.getLimit()) {
                    System.out.println("Thread " + Thread.currentThread().getName() + " has left the queue full");
                }
            }

            --elementsToProduce;

            try {
                Thread.sleep((int)(Math.random() * 10));
            } catch (InterruptedException e) {
                // thread.interrupt called, no handling needed
            }
        }
    }
}