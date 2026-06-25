package com.ai.SpringAiDemo;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.ai.image.ImageResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

@RestController
public class GenAIController {
    ChatService chatService;
    ImageService imageService;
    RecipeService recipeService;
    public GenAIController(ChatService chatService,ImageService imageService,RecipeService recipeService) {
        this.chatService = chatService;
        this.imageService = imageService;
        this.recipeService = recipeService;
    }
    @GetMapping("ask-ai")
    public String getResponse(@RequestParam String prompt) {
        return chatService.getResponse(prompt);
    }
    @GetMapping("ask-ai-options")
    public String getResponseOptions(@RequestParam String prompt) {
        return chatService.getResponse(prompt);
    }

    @GetMapping(value = "/generate-image", produces = "text/html")
    public String generateImage(@RequestParam String prompt) {

        ImageResponse imageResponse = imageService.generateImage(prompt);

        String base64 = imageResponse.getResult()
                .getOutput()
                .getB64Json();

        return "<img src='data:image/png;base64," + base64 + "' />";
    }

    @GetMapping("recipe-creator")
    public String recipeCreator(@RequestParam String ingredients,
                                @RequestParam (defaultValue = "any")String cuisine,
                                @RequestParam (defaultValue = "")String dietryRestrictions){
        return recipeService.createRecipe(ingredients, cuisine, dietryRestrictions);
    }

}
