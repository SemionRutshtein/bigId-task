package il.bigid.interviewtest.processing;

import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import static il.bigid.interviewtest.constants.Configuration.LINES_BATCH;


public class Helper {
    private static AtomicInteger batch = new AtomicInteger(0);

    public static void setBatch(AtomicInteger batch) {
        Helper.batch = batch;
    }

    public static char[] readFileWithCorrectLength(String file) throws IOException {
        FileReader fileReader = new FileReader(file);
        char[] destination = new char[LINES_BATCH];
        int offset = 0;
        if (batch.equals(new AtomicInteger(LINES_BATCH))) {
            offset = 0;
        } else {
            offset= batch.intValue()-1;
        }
        synchronized (Helper.class) {
            fileReader.read(destination, offset, destination.length);
        }
        return destination;
    }

}
