package com.example.demo.rebellion.morse.contoller;





import com.example.demo.rebellion.morse.service.MorseTranslatorService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MorseTranslatorController {

    private final MorseTranslatorService translatorService;

    @Autowired
    public MorseTranslatorController(MorseTranslatorService translatorService) {
        this.translatorService = translatorService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("mode", "encode");
        model.addAttribute("inputText", "");
        model.addAttribute("outputText", "");
        return "translator"; // maps to src/main/resources/templates/translator.html
    }

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
