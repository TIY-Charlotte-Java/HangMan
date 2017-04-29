package com.example.services;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Ben on 4/14/17.
 */
@Service
public class WordService {
    private static List<String> words;

    public String getRandomWord() {
        if (words == null) {
            words = getWordsFromFile();
        }

        return words.get((int)(Math.random() * words.size()));
    }

    private List<String> getWordsFromFile() {
            File f = new File("words.txt");
            Scanner fileScanner = null;
            List<String> words = new ArrayList<>();

            try {
                fileScanner = new Scanner(f);
            } catch (FileNotFoundException e) {
                System.err.println("Words file not found.");
            }

            if (fileScanner != null) {
                while (fileScanner.hasNext()) {
                    words.add(fileScanner.nextLine());
                }
            }

        return words;
    }
}
