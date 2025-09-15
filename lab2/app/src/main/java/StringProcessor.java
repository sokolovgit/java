package lab2.app.src.main.java;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringProcessor {
    public static void main(String[] args) {
        try {
            Locale locale = Locale.forLanguageTag("uk-UA");
            String text = """
                    Тексти диктантів відповідають мовній і соціокультурній змістовим
                    лініям, мають пізнавальний характер і значний виховний потенціал.
                    Тут є розповіді про українських науковців, політиків, письменників
                    та акторів, а також пейзажні замальовки та цікаві факти про мальовничі
                    місця України, етюди про мистецькі жанри та незвичайні вподобання.
                    """;

            List<String> uniqueWords = extractUniqueWords(text, locale);
            sortWordsByFirstLetter(uniqueWords, locale);
            printWords("Unique words sorted by first letter:", uniqueWords);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static List<String> extractUniqueWords(String text, Locale locale) {
        Pattern pattern = Pattern.compile("\\p{L}+");
        Matcher matcher = pattern.matcher(text);

        Set<String> seen = new HashSet<>();
        List<String> uniqueWords = new ArrayList<>();

        while (matcher.find()) {
            String word = matcher.group();
            String lower = word.toLowerCase(locale);
            if (!seen.contains(lower)) {
                seen.add(lower);
                uniqueWords.add(word);
            }
        }
        return uniqueWords;
    }

    private static void sortWordsByFirstLetter(List<String> words, Locale locale) {
        Comparator<String> byFirstLetter = (s1, s2) -> {
            char c1 = s1.charAt(0);
            char c2 = s2.charAt(0);
            char lowerC1 = String.valueOf(c1).toLowerCase(locale).charAt(0);
            char lowerC2 = String.valueOf(c2).toLowerCase(locale).charAt(0);
            return Character.compare(lowerC1, lowerC2);
        };
        words.sort(byFirstLetter);
    }

    private static void printWords(String label, List<String> words) {
        System.out.println(label);
        for (String word : words) {
            System.out.println(word);
        }
    }
}