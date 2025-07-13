package com.ai.SpringAIDemo;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class LanguageTranslateService {

    private final ChatModel chatModel;

    public LanguageTranslateService(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public String translateAndExplain(String direction, String text) {
        String template = "";

        if ("en-to-de".equalsIgnoreCase(direction)) {
            template = """
                Translate the following English sentence to German and explain the grammar in simple bullet points in point wise
                from 1) - first point
                     2) - second point
                     3) - third point likewise for all points that each sentence is on new line:
                {text}
            """;
        } else if ("de-to-en".equalsIgnoreCase(direction)) {
            template = """
                Translate the following German sentence to English and explain the grammar in simple bullet points in point wise
                from 1) first point
                     2) second point
                     3) third point likewise for all points that each sentence is on new line:
                {text}
            """;
        } else {
            return "Invalid translation direction.";
        }

        PromptTemplate promptTemplate = new PromptTemplate(template);
        Map<String, Object> params = Map.of("text", text);
        Prompt prompt = promptTemplate.create(params);

        return chatModel.call(prompt).getResult().getOutput().getText();
    }
}
