import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Runner {
    public static String filePath;
    public static int shortestWord;
    public static int longestWord;
    public static int wordCount;
    public static String[] words;
    public static Monkey monkey;
    public static ArrayList<String> monkeyWords;

    public static void main(String[] args) {
        configure();

        monkey = new Monkey(shortestWord, longestWord);

        while(monkeyWords.size() < wordCount) {
            String word = monkey.type();

            if(isValidWord(word)) {
                System.out.println("Found: " + word + " with score: " + scoreWord(word));
                monkeyWords.add(word + " : " + scoreWord(word));
            }
        }

        System.out.println("Words found: " + monkeyWords);
    }

    public static void configure() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the full filepath: ");
        filePath = scanner.nextLine();
        System.out.print("Enter the shortest word length: ");
        shortestWord = scanner.nextInt();
        System.out.print("Enter the longest word length: ");
        longestWord = scanner.nextInt();
        System.out.print("Enter the number of words to wait for: ");
        wordCount = scanner.nextInt();

        words = handleFile(filePath, shortestWord, longestWord);
        monkeyWords = new ArrayList<>();
    }

    public static String[] handleFile(String filePath, int shortestWord, int longestWord) {
        try {
            ArrayList<String> file = (ArrayList<String>) Files.readAllLines(Paths.get(filePath));

            for(int i = 0; i < file.size(); i++) {
                if(file.get(i).length() <= shortestWord || file.get(i).length() >= longestWord) {
                    file.remove(i);
                    i--;
                }
            }

            String[] words = new String[file.size()];

            for(int i = 0; i < file.size(); i++) {
                words[i] = file.get(i);
            }

            return words;
        }
        catch (IOException e) {
            System.out.println("The file " + filePath + " could not be opened.");
            return null;
        }
    }

    public static boolean isValidWord(String word) {
        for(String i : words) {
            if(i.equals(word)) {
                return true;
            }
        }
        return false;
    }

    public static int scoreWord(String word) {
        int score = 0;
        for(int c = 0; c < word.length(); c++) {
            char i = word.charAt(c);
            if (i == 'a' || i == 'e' || i == 'i' || i == 'o' || i == 'u' || i == 'l' || i == 'n' || i == 's' || i == 't' || i == 'r') {
                score += 1;
            }
            else if (i == 'd' || i == 'g') {
                score += 2;
            }
            else if (i == 'b' || i == 'c' || i == 'm' || i == 'p') {
                score += 3;
            }
            else if (i == 'f' || i == 'h' || i == 'v' || i == 'w' || i == 'y')  {
                score += 4;
            }
            else if (i == 'k') {
                score += 5;
            }
            else if (i == 'j' || i == 'x') {
                score += 8;
            }
            else if (i == 'q' || i == 'z') {
                score += 10;
            }
        }

        return score;
    }
}
