package com.example.demo.service;

import com.example.demo.rebellion.morse.service.MorseTranslatorService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link MorseTranslatorService}.
 */
class MorseTranslatorServiceTest {

    private final MorseTranslatorService service = new MorseTranslatorService();

    @Nested
    class EncodeTests {

        /**
         * Parameterized test for various encoding scenarios.
         */
        @ParameterizedTest(name = "Input: \"{0}\" → Morse: \"{1}\"")
        @CsvSource({
            "'I like you', '.. / .-.. .. -.- . / -.-- --- ..-'",
            "'Hello', '.... . .-.. .-.. ---'",
            "'SOS', '... --- ...'",
            "'Hi 5', '.... .. / .....'",
            "'Hello #World', '.... . .-.. .-.. --- / .-- --- .-. .-.. -..'"
        })
        void shouldEncodeTextToMorse(String input, String expectedMorse) {
            assertEquals(expectedMorse, service.encode(input));
        }

        @Test
        void shouldReturnEmptyStringForNullOrEmptyInput() {
            assertEquals("", service.encode(""));
            assertEquals("", service.encode(null));
        }
    }

    @Nested
    class DecodeTests {

        /**
         * Parameterized test for various decoding scenarios.
         */
        @ParameterizedTest(name = "Morse: \"{0}\" → Output: \"{1}\"")
        @CsvSource({
            "'.. / .-.. .. -.- . / -.-- --- ..-', 'I LIKE YOU'",
            "'.... . .-.. .-.. ---', 'HELLO'",
            "'... --- ...', 'SOS'",
            "'.... .. / .....', 'HI 5'",
            "'.... . .-.. .-.. --- / .-- --- .-. .-.. -..', 'HELLO WORLD'"
        })
        void shouldDecodeMorseToText(String morse, String expectedText) {
            assertEquals(expectedText, service.decode(morse));
        }

        /**
         * Test decoding with invalid Morse segments, which should be ignored.
         */
        @Test
        void shouldIgnoreInvalidMorseSegments() {
            String morse = "... --- ... ### ..--";
            assertEquals("SOS", service.decode(morse));
        }

        @Test
        void shouldReturnEmptyStringForNullOrEmptyInput() {
            assertEquals("", service.decode(""));
            assertEquals("", service.decode(null));
        }
    }
}
