package com.example.demo.rebellion.morse.service;



import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MorseTranslatorService {

    private static final Map<Character, String> LETTER_TO_MORSE = new HashMap<>();
    private static final Map<String, Character> MORSE_TO_LETTER = new HashMap<>();

    static {
        // Letters
        LETTER_TO_MORSE.put('A', ".-");
        LETTER_TO_MORSE.put('B', "-...");
        LETTER_TO_MORSE.put('C', "-.-.");
        LETTER_TO_MORSE.put('D', "-..");
        LETTER_TO_MORSE.put('E', ".");
        LETTER_TO_MORSE.put('F', "..-.");
        LETTER_TO_MORSE.put('G', "--.");
        LETTER_TO_MORSE.put('H', "....");
        LETTER_TO_MORSE.put('I', "..");
        LETTER_TO_MORSE.put('J', ".---");
        LETTER_TO_MORSE.put('K', "-.-");
        LETTER_TO_MORSE.put('L', ".-..");
        LETTER_TO_MORSE.put('M', "--");
        LETTER_TO_MORSE.put('N', "-.");
        LETTER_TO_MORSE.put('O', "---");
        LETTER_TO_MORSE.put('P', ".--.");
        LETTER_TO_MORSE.put('Q', "--.-");
        LETTER_TO_MORSE.put('R', ".-.");
        LETTER_TO_MORSE.put('S', "...");
        LETTER_TO_MORSE.put('T', "-");
        LETTER_TO_MORSE.put('U', "..-");
        LETTER_TO_MORSE.put('V', "...-");
        LETTER_TO_MORSE.put('W', ".--");
        LETTER_TO_MORSE.put('X', "-..-");
        LETTER_TO_MORSE.put('Y', "-.--");
        LETTER_TO_MORSE.put('Z', "--..");

        // Numbers
        LETTER_TO_MORSE.put('0', "-----");
        LETTER_TO_MORSE.put('1', ".----");
        LETTER_TO_MORSE.put('2', "..---");
        LETTER_TO_MORSE.put('3', "...--");
        LETTER_TO_MORSE.put('4', "....-");
        LETTER_TO_MORSE.put('5', ".....");
        LETTER_TO_MORSE.put('6', "-....");
        LETTER_TO_MORSE.put('7', "--...");
        LETTER_TO_MORSE.put('8', "---..");
        LETTER_TO_MORSE.put('9', "----.");

        // Punctuation
        LETTER_TO_MORSE.put('.', ".-.-.-");
        LETTER_TO_MORSE.put(',', "--..--");
        LETTER_TO_MORSE.put('?', "..--..");
        LETTER_TO_MORSE.put('\'', ".----.");
        LETTER_TO_MORSE.put('!', "-.-.--");
        LETTER_TO_MORSE.put('/', "-..-.");
        LETTER_TO_MORSE.put('(', "-.--.");
        LETTER_TO_MORSE.put(')', "-.--.-");
        LETTER_TO_MORSE.put('&', ".-...");
        LETTER_TO_MORSE.put(':', "---...");
        LETTER_TO_MORSE.put(';', "-.-.-.");
        LETTER_TO_MORSE.put('=', "-...-");
        LETTER_TO_MORSE.put('+', ".-.-.");
        LETTER_TO_MORSE.put('-', "-....-");
        LETTER_TO_MORSE.put('_', "..--.-");
        LETTER_TO_MORSE.put('"', ".-..-.");
        LETTER_TO_MORSE.put('$', "...-..-");
        LETTER_TO_MORSE.put('@', ".--.-.");

        // Space
        LETTER_TO_MORSE.put(' ', "/");

        // Build reverse map
        LETTER_TO_MORSE.forEach((k, v) -> MORSE_TO_LETTER.put(v, k));
    }

    public String lettersToMorseCode(String text) {
        if (text == null || text.trim().isEmpty()) {
            return "";
        }

        return text.toUpperCase()
                .chars()
                .mapToObj(c -> LETTER_TO_MORSE.getOrDefault((char) c, ""))
                .filter(m -> !m.isEmpty())
                .collect(Collectors.joining(" "));
    }

    public String morseCodeToLetters(String code) {
        if (code == null || code.trim().isEmpty()) {
            return "";
        }

        StringBuilder decoded = new StringBuilder();
        String[] tokens = code.trim().split(" ");

        for (String token : tokens) {
            if (token.equals("/")) {
                decoded.append(' ');
            } else {
                Character letter = MORSE_TO_LETTER.get(token);
                if (letter != null) {
                    decoded.append(letter);
                }
            }
        }
        return decoded.toString();
    }
}
