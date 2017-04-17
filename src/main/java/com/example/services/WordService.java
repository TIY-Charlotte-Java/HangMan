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
    private List<String> getWordsFromFile() {
        File f = new File(getClass().getClassLoader().getResource("words.txt").getFile());
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

    public String getRandomWord() {
        List<String> words = getWordsFromFile();

        return words.get((int)(Math.random() * words.size()));
    }
}
