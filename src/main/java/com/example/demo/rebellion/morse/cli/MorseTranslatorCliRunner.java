package com.example.demo.rebellion.morse.cli;




import com.example.demo.rebellion.morse.service.MorseTranslatorService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class MorseTranslatorCliRunner implements CommandLineRunner {

    private final MorseTranslatorService translatorService;

    public MorseTranslatorCliRunner(MorseTranslatorService translatorService) {
        this.translatorService = translatorService;
    }

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
