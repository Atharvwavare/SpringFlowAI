package com.ai.SpringAIDemo;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class NotesSummaryService {

    private final ChatModel chatModel;

    public NotesSummaryService(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public String summarizeNotes(String notes) {
        String template = """
            Summarize the following notes into a concise explanation:
            {notes}
            
           give the point wise explanation, Make it short, clear, and under 70 words.
        """;

        PromptTemplate promptTemplate = new PromptTemplate(template);
        Map<String, Object> params = Map.of("notes", notes);
        Prompt prompt = promptTemplate.create(params);

        return chatModel.call(prompt).getResult().getOutput().getText();
    }
}
