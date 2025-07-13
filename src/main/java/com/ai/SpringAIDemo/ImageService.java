package com.ai.SpringAIDemo;


import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class ImageService {
     private final OpenAiImageModel openAiImageModel;

    public ImageService(OpenAiImageModel openAiImageModel) {
        this.openAiImageModel = openAiImageModel;
    }

    public ImageResponse generateImage(String prompt){
         ImageResponse imageResponse = openAiImageModel.call(
                new ImagePrompt(prompt,
                        OpenAiImageOptions.builder()
                                .model("dall-e-2")
                                .N(1)
                                .height(512)
                                .width(512)
                                .build())
        );
         return imageResponse;

    }
}


