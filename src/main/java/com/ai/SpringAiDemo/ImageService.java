package com.ai.SpringAiDemo;

import com.openai.models.images.ImageModel;

import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.stereotype.Service;

@Service
public class ImageService {
    private final OpenAiImageModel openAiImageModel;
    public ImageService(OpenAiImageModel openAiImageModel) {
        this.openAiImageModel = openAiImageModel;
    }


    public ImageResponse generateImage(String prompt) {
        OpenAiImageOptions options = OpenAiImageOptions.builder()
                .model("gpt-image-2")
                .build();
        return openAiImageModel.call(
                new ImagePrompt(prompt,options)
        );
    }
}
