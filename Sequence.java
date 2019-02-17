import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Sequence {


    public List <String> toListOfSuffixes(String word) {
        return IntStream.range(0, word.length())
                .mapToObj(e -> word.substring(e, word.length()))
                .collect(Collectors.toList());

    }

    public Optional <Integer> accumlateSimilarities(List <String> words, String word) {
        return words.stream()
                .map(e -> countSimilarities(e, word))
                .reduce((a, b) -> a + b);

    }

    private Stack <String> lettersToStack(String word) {
        Stack <String> letters = new Stack <>();
        Stream.of(word.split(""))
                .forEach(letters::push);
        Collections.reverse(letters);
        return letters;
    }


    public int countSimilarities(String word1, String word2) {
        Stack <String> letters1 = lettersToStack(word1);
        Stack <String> letters2 = lettersToStack(word2);
        int similarities = 0;
        int failures = 0;
        while (isNotEmpty(letters1, letters2)) {
            if (compareFirstLetters(letters1, letters2) == 0) {
                failures += 1;
            } else {
                similarities += 1;
            }
        }
        if (failures > 1) {
            return 0;
        }
        return similarities;
    }


    private boolean isNotEmpty(Stack <String> letters1, Stack <String> letters2) {
        return !letters1.isEmpty() && !letters2.isEmpty();
    }

    private int compareFirstLetters(Stack <String> letters1, Stack <String> letters2) {
        String letter1 = letters1.pop();
        String letter2 = letters2.pop();
        if (!letter1.equals(letter2)) {
            return 0;
        }
        return 1;
    }


}

