package il.bigid.interviewtest.processing;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static il.bigid.interviewtest.constants.Configuration.MOST_POPULAR_ENGLISH_NAME_WORDS_ARRAY;



public class Reader {

    private static AtomicInteger charCount = new AtomicInteger(0);
    private static AtomicInteger lineCount = new AtomicInteger(0);

    public static Map<String , List<Offsets>> result = new ConcurrentHashMap<>();

    public static void readFile (String path) {
        List<String> textLines = new ArrayList<>();

        try (BufferedReader br = Files.newBufferedReader(Paths.get(path))) {
            textLines = br.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

            for (String line : textLines) {
                lineCount.incrementAndGet();

                int bound = line.length();
                IntStream.range(0, bound).forEach(i -> charCount.incrementAndGet());

                List<String> words = new ArrayList<>();
                Collections.addAll(words, MOST_POPULAR_ENGLISH_NAME_WORDS_ARRAY);

                for (String word: words) {
                    findNameInLineAndSaveData(line, word, lineCount, charCount);
                }
            }

    }

    private static void findNameInLineAndSaveData(String line, String name, AtomicInteger lineCount, AtomicInteger charCount) {
        Pattern pattern = Pattern.compile(name);
        if(pattern.matcher(line).find()) {
            if (result.get(name) != null) {
                List<Offsets> offsets1 = result.get(name);
                offsets1.add(new Offsets(lineCount.intValue(), charCount.intValue()));
                result.put(name, offsets1);
            } else {
                List<Offsets> offsets = new CopyOnWriteArrayList<>();
                offsets.add(new Offsets(lineCount.intValue(), charCount.intValue()));
                result.put(name, offsets);
            }

        }

    }


}


