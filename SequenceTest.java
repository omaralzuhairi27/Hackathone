import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;
import java.util.Optional;

class SequenceTest {

    private Sequence sequence = new Sequence();
    private String text = "ababaa";

    @ParameterizedTest
    @CsvSource({
            "ababaa, 11",
    })
    void testaccumlateSimilarities(String word, Integer expectedNumber) {
        List <String> words = sequence.toListOfSuffixes(text);
        Optional <Integer> actualNumber = sequence.accumlateSimilarities(words, word);
        Assertions.assertEquals(expectedNumber, actualNumber.get());
    }

    @ParameterizedTest
    @CsvSource({
            "ababaa, 6",
            "babaa, 0",
            "abaa, 3",
            "baa, 0",
            "aa, 1",
            "a, 1",

    })
    void testCountSimilarities(String word, Integer expectedNumber) {
        int actualNumber = sequence.countSimilarities(word, text);
        Assertions.assertEquals(expectedNumber, actualNumber);
    }
}