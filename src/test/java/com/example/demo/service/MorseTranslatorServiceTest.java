package com.example.demo.service;

import com.example.demo.rebellion.morse.service.MorseTranslatorService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MorseTranslatorServiceTest {

    private final MorseTranslatorService service = new MorseTranslatorService();

    @Test
    void testEncode() {
        String input = "I like you";
        String expected = ".. / .-.. .. -.- . / -.-- --- ..-";
        assertEquals(expected, service.encode(input));
    }

    @Test
    void testDecode() {
        String morse = ".. / .- -- / .-. ..- -. -. .. -. --. / --- ..- - / --- ..-. / -.-. --- ..-. ..-. . .-.-.-";
        String expected = "I AM RUNNING OUT OF COFFEE.";
        assertEquals(expected, service.decode(morse));
    }

    @Test
    void testEmptyInput() {
        assertEquals("", service.encode(""));
        assertEquals("", service.decode(""));
        assertEquals("", service.encode(null));
        assertEquals("", service.decode(null));
    }

    @Test
    void testUnsupportedCharacters() {
        // The '#' is not in the map, so it should be skipped silently
        String input = "Hello #World";
        String expected = ".... . .-.. .-.. --- / .-- --- .-. .-.. -..";
        assertEquals(expected, service.encode(input));
    }
}
