package com.company;
import java.util.*;

public class Main {
    public static final String[] WORDS = {"elephant", "alligator", "computer", "database", "javascript"};
    public static final int CHANCES = 6;
    public static Set<Character> seenValues = new HashSet<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // get a random word from the list of words
        int rand = (int) (Math.random() * WORDS.length);
        String selectedWord = WORDS[rand];

        // this will be our running guess, we update the values as we keep guessing
        char[] guessedWord = new char[selectedWord.length()];
        Arrays.fill(guessedWord, '_');

        int incorrectAttempts = 0;

        while(incorrectAttempts < CHANCES){
            displayHangman(incorrectAttempts);
            displayWord(guessedWord);

            System.out.println("Enter a letter: ");
            // since there is no scanner.nextChar(), we must use the following to get a character from the user:
            char guess = scanner.next().charAt(0);

            boolean seenChar = checkIfCharSeen(guess);

            // to ensure we don't let the user guess the same character twice
            if(!seenChar){
                // check if the guess that we input is correct:
                if(checkGuess(selectedWord, guessedWord, guess)){
                    System.out.println("Correct guess!");
                }else{
                    System.out.println("Incorrect guess!");
                    incorrectAttempts++;
                }
            }else{
                System.out.println("Sorry, you have already guessed that letter! Try a different one.");
            }

            // check if there are any underscores, if so, we haven't guessed the word correctly yet
            if(isWordGuessed(guessedWord)){
                System.out.println("Congrats! You guessed the word correctly!");
                break;
            }
        }

        // when the game ends, if we reached the maximum amount of tries, we lost
        if(incorrectAttempts == CHANCES){
            System.out.println("Sorry, you ran out of attempts! The word was: " + selectedWord + ". Thanks for playing!");
        }

        // exit the game
        scanner.close();
        System.exit(1);
    }

    public static boolean checkIfCharSeen(char guess){
        if(seenValues.contains(guess)){
            return true;
        }else{
            seenValues.add(guess);
            return false;
        }
    }

    public static boolean isWordGuessed(char[] guessedWord){
        for(char letter : guessedWord){
            if(letter == '_'){
                return false;
            }
        }

        return true;
    }

    public static boolean checkGuess(String word, char[] guessedWord, char guess){
        boolean correctGuess = false;

        for(int i = 0; i < word.length(); i++){
            if(word.charAt(i) == guess){
                guessedWord[i] = guess;
                correctGuess = true;
            }
        }

        return correctGuess;
    }

    public static void displayWord(char[] guessedWord){
        System.out.print("Word: " );
        for(char ch : guessedWord){
            System.out.print(ch + " ");
        }
        System.out.println();
    }

    public static void displayHangman(int incorrectAttempts){
        System.out.println("Incorrect Attempts: " + incorrectAttempts);

        switch(incorrectAttempts){
            case 0:
                System.out.println("  +---+");
                System.out.println("  |   |");
                System.out.println("      |");
                System.out.println("      |");
                System.out.println("      |");
                System.out.println("      |");
                break;
            case 1:
                System.out.println("  +---+");
                System.out.println("  |   |");
                System.out.println("  O   |");
                System.out.println("      |");
                System.out.println("      |");
                System.out.println("      |");
                break;
            case 2:
                System.out.println("  +---+");
                System.out.println("  |   |");
                System.out.println("  O   |");
                System.out.println("  |   |");
                System.out.println("      |");
                System.out.println("      |");
                break;
            case 3:
                System.out.println("  +---+");
                System.out.println("  |   |");
                System.out.println("  O   |");
                System.out.println(" /|   |");
                System.out.println("      |");
                System.out.println("      |");
                break;
            case 4:
                System.out.println("  +---+");
                System.out.println("  |   |");
                System.out.println("  O   |");
                System.out.println(" /|\\ |");
                System.out.println("      |");
                System.out.println("      |");
                break;
            case 5:
                System.out.println("  +---+");
                System.out.println("  |   |");
                System.out.println("  O   |");
                System.out.println(" /|\\ |");
                System.out.println(" /    |");
                System.out.println("      |");
                break;
            case 6:
                System.out.println("  +---+");
                System.out.println("  |   |");
                System.out.println("  O   |");
                System.out.println(" /|\\ |");
                System.out.println(" / \\ |");
                System.out.println("      |");
                break;
        }
    }
}