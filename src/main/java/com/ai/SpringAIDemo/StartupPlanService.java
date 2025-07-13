package com.ai.SpringAIDemo;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class StartupPlanService {

    private final ChatModel chatModel;

    public StartupPlanService(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public String createStartup(String idea,
                                String problem,
                                String solution
                                ){
        var template_s = """
                
                the problem that i facing in real world is the {idea}.
                I want to start a StartUp having following Idea:{problem}.
                This is solution of it :{solution}.
                also gives the working,how many days required,what are steps to for startup become efficient
                how can I make benefits from startup,give point wise benefits
                Please provide me the detailed idea description for Startup using following points Idea,P
                Problem,Solution,benefits.
    
                this all give me in 200 words
                
                """;
        PromptTemplate promptTemplate = new PromptTemplate(template_s);
        Map<String,Object> params_s = Map.of(
                "idea",idea,
                "problem",problem,
                "solution",solution

        );

        Prompt prompt = promptTemplate.create(params_s);
        return chatModel.call(prompt).getResult().getOutput().getText();
    }
}
