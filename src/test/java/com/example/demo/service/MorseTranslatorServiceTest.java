package com.example.demo.service;




import com.example.demo.rebellion.morse.service.MorseTranslatorService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

    class MorseTranslatorServiceTest {

        private final MorseTranslatorService service = new MorseTranslatorService();

        @Test
        void testLettersToMorseCode() {
            String input = "I like you";
            String expected = ".. / .-.. .. -.- . / -.-- --- ..-";
            assertEquals(expected, service.lettersToMorseCode(input));
        }

        @Test
        void testMorseCodeToLetters() {
            String morse = ".. / .- -- / .-. ..- -. -. .. -. --. / --- ..- - / --- ..-. / -.-. --- ..-. ..-. . .-.-.-";
            String expected = "I AM RUNNING OUT OF COFFEE.";
            assertEquals(expected, service.morseCodeToLetters(morse));
        }

        @Test
        void testEmptyInput() {
            assertEquals("", service.lettersToMorseCode(""));
            assertEquals("", service.morseCodeToLetters(""));
            assertEquals("", service.lettersToMorseCode(null));
            assertEquals("", service.morseCodeToLetters(null));
        }

        @Test
        void testUnsupportedCharacters() {
            String input = "Hello #World";
            String expected = ".... . .-.. .-.. --- / .-- --- .-. .-.. -..";
            assertEquals(expected, service.lettersToMorseCode(input));
        }
    }


