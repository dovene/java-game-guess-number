package com.exercice;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {

    private final static String FILE_PATH = "score.txt";
    private final static int UPPER_NUMBER_LIMIT = 100;

    public static void main(String[] args) {
        System.exit(0);
        int bestScore = readScore();
        System.out.println("Bienvenue dans ce jeu qui consiste à deveiner un nombre entre 1 et 100");
        System.out.println("le meilleur score est: " + bestScore + " Essayez de le battre");

        int attempts = 0;
        int numberToGuess = new Random().nextInt(1, UPPER_NUMBER_LIMIT + 1);
        Scanner scanner = new Scanner(System.in);
        int guessedNumber = -1;

        while (guessedNumber != numberToGuess) {
            System.out.print("Devine le nombre caché : ");
            // Read the input provided by the user
            String guessedNumberAsString = scanner.nextLine();
            try {
                attempts++;
                guessedNumber = Integer.parseInt(guessedNumberAsString);
                if (guessedNumber < numberToGuess) {
                    System.out.println("Le nombre est plus grand.");
                } else if (guessedNumber > numberToGuess) {
                    System.out.println("Le nombre est plus petit.");
                } else {
                    System.out.println("Félicitations ! Vous avez deviné le nombre en " + attempts + " essais.");
                    if (attempts < bestScore || bestScore == 0) {
                        bestScore = attempts;
                        writeBestScore(attempts);
                        System.out.println("Nouveau meilleur score enregistré : " + bestScore);
                    } else {
                        System.out.println("Meilleur score actuel : " + bestScore);
                    }
                    break;
                }
            } catch (NumberFormatException NumberFormatException) {
                System.out.println("Mauvaise saisie, il faut entrer un nombre");
            }
        }
        scanner.close();
    }

    private static int readScore() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line = reader.readLine();
            if (line != null && !line.isEmpty()) {
                return Integer.parseInt(line);
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private static void writeBestScore(int score) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            writer.write(String.valueOf(score));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
