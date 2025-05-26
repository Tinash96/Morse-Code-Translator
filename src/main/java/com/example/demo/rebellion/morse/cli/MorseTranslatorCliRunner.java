package com.example.demo.rebellion.morse.cli;



import com.rebellion.morse.service.MorseTranslatorService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class MorseTranslatorCliRunner implements CommandLineRunner {

    private final MorseTranslatorService service;

    public MorseTranslatorCliRunner(MorseTranslatorService service) {
        this.service = service;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to R2-D2 Morse Translator CLI");
        System.out.print("Enter mode (encode/decode): ");
        String mode = scanner.nextLine().trim();

        if (!mode.equalsIgnoreCase("encode") && !mode.equalsIgnoreCase("decode")) {
            System.out.println("Invalid mode! Please enter 'encode' or 'decode'.");
            return;
        }

        System.out.print("Enter text: ");
        String input = scanner.nextLine();

        String result;
        if ("encode".equalsIgnoreCase(mode)) {
            result = service.lettersToMorseCode(input);
        } else {
            result = service.morseCodeToLetters(input);
        }

        System.out.println("Result:");
        System.out.println(result);
    }
}

