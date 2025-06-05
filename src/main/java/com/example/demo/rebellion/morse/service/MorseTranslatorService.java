package com.example.demo.rebellion.morse.service;



import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class MorseTranslatorService {

    private static final Map<Character, String> CHAR_TO_MORSE = new HashMap<>();
    private static final Map<String, Character> MORSE_TO_CHAR = new HashMap<>();

    static {
        String[][] morseTable = {
                {"A", ".-"}, {"B", "-..."}, {"C", "-.-."}, {"D", "-.."}, {"E", "."},
                {"F", "..-."}, {"G", "--."}, {"H", "...."}, {"I", ".."}, {"J", ".---"},
                {"K", "-.-"}, {"L", ".-.."}, {"M", "--"}, {"N", "-."}, {"O", "---"},
                {"P", ".--."}, {"Q", "--.-"}, {"R", ".-."}, {"S", "..."}, {"T", "-"},
                {"U", "..-"}, {"V", "...-"}, {"W", ".--"}, {"X", "-..-"}, {"Y", "-.--"},
                {"Z", "--.."}, {"0", "-----"}, {"1", ".----"}, {"2", "..---"}, {"3", "...--"},
                {"4", "....-"}, {"5", "....."}, {"6", "-...."}, {"7", "--..."}, {"8", "---.."},
                {"9", "----."}, {".", ".-.-.-"}, {",", "--..--"}, {"?", "..--.."}, {"'", ".----."},
                {"!", "-.-.--"}, {"/", "-..-."}, {"(", "-.--."}, {")", "-.--.-"}, {"&", ".-..."},
                {":", "---..."}, {";", "-.-.-."}, {"=", "-...-"}, {"+", ".-.-."}, {"-", "-....-"},
                {"_", "..--.-"}, {"\"", ".-..-."}, {"$", "...-..-"}, {"@", ".--.-."}, {" ", "/"}
        };

        for (String[] pair : morseTable) {
            CHAR_TO_MORSE.put(pair[0].charAt(0), pair[1]);
            MORSE_TO_CHAR.put(pair[1], pair[0].charAt(0));
        }
    }

    public String encode(String text) {
        if (text == null || text.isEmpty()) return "";
        StringBuilder morse = new StringBuilder();
        for (char c : text.toUpperCase().toCharArray()) {
            if (CHAR_TO_MORSE.containsKey(c)) {
                morse.append(CHAR_TO_MORSE.get(c)).append(" ");
            }
        }
        return morse.toString().trim();
    }

    public String decode(String morseCode) {
        if (morseCode == null || morseCode.isEmpty()) return "";
        StringBuilder decoded = new StringBuilder();
        for (String code : morseCode.trim().split(" ")) {
            if ("/".equals(code)) {
                decoded.append(" ");
            } else if (MORSE_TO_CHAR.containsKey(code)) {
                decoded.append(MORSE_TO_CHAR.get(code));
            }
        }
        return decoded.toString();
    }
}
