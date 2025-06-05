package com.example.demo.rebellion.morse.cli;

import com.example.demo.rebellion.morse.service.MorseTranslatorService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

/**
 * CLI runner for the Morse Translator application.
 * <p>
 * This component runs on application startup and provides
 * an interactive command-line interface for encoding and decoding
 * Morse code using the {@link MorseTranslatorService}.
 * </p>
 * 
 * Users can choose between "encode", "decode", or "exit" commands.
 */
@Component
public class MorseTranslatorCliRunner implements CommandLineRunner {

    private final MorseTranslatorService translatorService;

    /**
     * Constructor for injecting the MorseTranslatorService dependency.
     *
     * @param translatorService the service used to encode and decode Morse code
     */
    public MorseTranslatorCliRunner(MorseTranslatorService translatorService) {
        this.translatorService = translatorService;
    }

    /**
     * Executes the command-line interface loop after the application starts.
     * <p>
     * Prompts the user to select a mode (encode, decode, or exit),
     * then reads input text and displays the translated result.
     * </p>
     *
     * @param args command-line arguments (not used)
     */
    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("🤖 Welcome to R2-D2 Morse Translator CLI");

        while (true) {
            System.out.print("Enter mode (encode/decode/exit): ");
            String mode = scanner.nextLine().trim().toLowerCase();

            if (mode.equals("exit")) {
                System.out.println("👋 Goodbye, human!");
                break;
            }

            System.out.print("Enter text: ");
            String input = scanner.nextLine();

            String result;
            if (mode.equals("encode")) {
                result = translatorService.encode(input);
            } else if (mode.equals("decode")) {
                result = translatorService.decode(input);
            } else {
                System.out.println("❌ Invalid mode. Please enter 'encode' or 'decode'.");
                continue;
            }

            System.out.println("📡 Result:\n" + result + "\n");
        }

        scanner.close();
    }
}
