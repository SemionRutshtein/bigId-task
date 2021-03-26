package il.bigid.interviewtest.processing;

import il.bigid.interviewtest.constants.Configuration;

import java.io.IOException;

import static il.bigid.interviewtest.constants.Configuration.LINES_BATCH;
import static il.bigid.interviewtest.processing.Reader.readFile;

public class Worker implements Runnable {

    private int textPart;

    public int getTextPart() {
        return textPart;
    }

    public Worker(int i) {
       this.textPart = LINES_BATCH*(i+1);
    }

    @Override
    public void run() {
        try {
            char[] chars = Helper.readFileWithCorrectLength(Configuration.PATH);
            String partOftext = chars.toString();
            readFile(partOftext);

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Thread:"  + Thread.currentThread().getName() );

    }
}
