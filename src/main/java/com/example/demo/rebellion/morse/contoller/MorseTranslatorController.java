package com.example.demo.rebellion.morse.contoller;




import com.rebellion.morse.service.MorseTranslatorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MorseTranslatorController {

    private final MorseTranslatorService service;

    public MorseTranslatorController(MorseTranslatorService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String index() {
        return "translator"; // Thymeleaf template
    }

    @PostMapping("/translate")
    public String translate(@RequestParam String input,
                            @RequestParam String mode,
                            Model model) {
        String output;
        if ("encode".equalsIgnoreCase(mode)) {
            output = service.lettersToMorseCode(input);
        } else {
            output = service.morseCodeToLetters(input);
        }
        model.addAttribute("input", input);
        model.addAttribute("mode", mode);
        model.addAttribute("output", output);
        return "translator";
    }
}

