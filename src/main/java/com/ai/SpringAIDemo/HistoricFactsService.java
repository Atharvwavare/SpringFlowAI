package com.ai.SpringAIDemo;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class HistoricFactsService {

    private final ChatModel chatModel;

    public HistoricFactsService(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public String getFactByDate(String date, String region) {
        var template = """
           Tell me one important historical event that happened in {region} on {date}.
           Keep the explanation simple, point wise, and under 100 words.
        """;

        PromptTemplate promptTemplate = new PromptTemplate(template);

        Map<String, Object> params = Map.of("date", date,
                                            "region",region);

        Prompt prompt = promptTemplate.create(params);
        return chatModel.call(prompt).getResult().getOutput().getText();
    }
}
