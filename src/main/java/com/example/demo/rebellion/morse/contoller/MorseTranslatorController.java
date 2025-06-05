package com.example.demo.rebellion.morse.contoller;

import com.example.demo.rebellion.morse.service.MorseTranslatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Web controller for the Morse Translator application.
 * <p>
 * Handles HTTP requests for encoding and decoding text to/from Morse code.
 * Uses Thymeleaf views to render a user-friendly web interface.
 * </p>
 */
@Controller
public class MorseTranslatorController {

    private final MorseTranslatorService translatorService;

    /**
     * Constructor for MorseTranslatorController.
     *
     * @param translatorService the service used to encode and decode Morse code
     */
    @Autowired
    public MorseTranslatorController(MorseTranslatorService translatorService) {
        this.translatorService = translatorService;
    }

    /**
     * Displays the home page with the Morse Translator form.
     *
     * @param model Spring model to pass attributes to the view
     * @return the name of the Thymeleaf template to render (translator.html)
     */
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("mode", "encode");
        model.addAttribute("inputText", "");
        model.addAttribute("outputText", "");
        return "translator"; // maps to src/main/resources/templates/translator.html
    }

    /**
     * Handles form submissions to encode or decode text.
     *
     * @param mode      the translation mode, either "encode" or "decode"
     * @param inputText the user input text to be translated
     * @param model     Spring model to pass data to the view
     * @return the name of the Thymeleaf template to render with the result
     */
    @PostMapping("/translate")
    public String translate(
            @RequestParam("mode") String mode,
            @RequestParam("inputText") String inputText,
            Model model) {
        System.out.println("Input Text: '" + inputText + "'");
        System.out.println("Mode: " + mode);

        String result;
        if ("decode".equalsIgnoreCase(mode)) {
            result = translatorService.decode(inputText);
        } else {
            result = translatorService.encode(inputText);
        }

        model.addAttribute("mode", mode);
        model.addAttribute("inputText", inputText);
        model.addAttribute("outputText", result);

        return "translator";
    }
}
