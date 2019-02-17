import java.sql.SQLClientInfoException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
//queue
public class Sequence {


    public List <String> toList(String text) {
        return IntStream.range(0, text.length())
                .mapToObj(e -> text.substring(e, text.length()))
                .collect(Collectors.toList());

    }

    public Optional <Integer> getTheSquence(List <String> words, String word) {
        return words.stream()
                .map(e -> countDifferences(e, word))
                .reduce((a, b) -> a + b);

    }

    private Stack <String> toLetters(String word) {
        Stack <String> letters = new Stack <>();
        Stream.of(word.split(""))
                .forEach(letters::push);
        Collections.reverse(letters);
        return letters;
    }


    public int countDifferences(String word1, String word2) {
        Stack <String> letters1 = toLetters(word1);
        Stack <String> letters2 = toLetters(word2);
        int similarities = 0;
        int failures = 0;


        while (areThereMore(letters1, letters2)) {
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


    private boolean areThereMore(Stack <String> letters1, Stack <String> letters2) {
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
    public List<Integer> differences(String word1, String word2) {
        Stack <String> letters1 = toLetters(word1);
        Stack <String> letters2 = toLetters(word2);
        int similarities = 0;
        int failures = 0;
        List<Integer> numbers=new ArrayList <>();
        IntStream.range(0,word2.length())
                .takeWhile(e -> areThereMore(letters1, letters2))
                .forEach(e -> numbers.add(compareFirstLetters(letters1, letters2)));

        return numbers;
    }
   
}

