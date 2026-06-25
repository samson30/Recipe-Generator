package com.ai.SpringAiDemo;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RecipeService {
    private final ChatModel chatModel;
    public RecipeService(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public String createRecipe(String ingredients, String cuisine, String dietryRestrictions) {

        var template= """ 
                I want to create a recipe using the following {ingredients}.
                The cuisine type I prefer is {cuisine}.
                Please consider the following dietry restrictions: {dietryRestrictions}.
                Please provide me a recipe with a detailed including title, list of ingredients and cooking instructions.
                """;
        PromptTemplate promptTemplate = new PromptTemplate(template);
        Map<String,Object> params=Map.of(
                "ingredients",ingredients,
                "cuisine",cuisine,
                "dietryRestrictions",dietryRestrictions
        );
        Prompt prompt=promptTemplate.create(params);
        return chatModel.call(prompt).getResult().getOutput().getText();
    }


}

