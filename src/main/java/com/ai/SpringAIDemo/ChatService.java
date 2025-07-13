package com.ai.SpringAIDemo;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ChatService {
    private final ChatModel chatModel;
    public ChatService(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public String getResponse(String chat){

        var template = """
           give me the information in simple way {chat}.
           Keep the prompt easy to understand and point wise to each point to new line.
        """;

        PromptTemplate promptTemplate = new PromptTemplate(template);

        Map<String, Object> params = Map.of("chat", chat);

        Prompt prompt = promptTemplate.create(params);
        return chatModel.call(prompt).getResult().getOutput().getText();



    }
    public String getResponseOptions(String prompt) {
       return chatModel.call(prompt);
    }

}



