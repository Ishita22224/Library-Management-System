package org.example;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


// CLASS FOR ISSUED BOOKS
public class IssuedBook {
    private Book book;
    private int timeElapsed;
    private int timeMax;
    private int fine;

    public IssuedBook(Book book) {
        this.book = book;
        this.fine = 0;
        this.timeElapsed = 0;
        this.timeMax = 10;

        // Calculates the Fine for each issued Book separately
        ScheduledExecutorService exe = Executors.newSingleThreadScheduledExecutor();

        exe.scheduleAtFixedRate(new Runnable() {

            @Override
            public void run() {
                timeElapsed++;
                if (timeElapsed > timeMax) {
                    int diff = timeElapsed - timeMax;
                    fine = diff * 3;
                }
            }

        }, 1, 1, TimeUnit.SECONDS);
    }

    public Book getBook() {
        return this.book;
    }
    public int retFine() {
        return this.fine;
    }
}