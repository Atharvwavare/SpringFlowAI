package com.ai.SpringAIDemo;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.ai.image.ImageResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


@RestController
public class GenAIController {



    private final ChatService chatService;
    private final ImageService imageService;
    private final RecipeService recipeService;
    private final StartupPlanService startupPlanService;
    private final HistoricFactsService historicFactsService;
    private final NotesSummaryService notesSummaryService;
    private final LanguageTranslateService languageTranslateService;

    public GenAIController(ChatService chatService, ImageService imageService, RecipeService recipeService, StartupPlanService startupPlanService, HistoricFactsService historicFactsService, NotesSummaryService notesSummaryService, LanguageTranslateService languageTranslateService) {
        this.chatService = chatService;
        this.imageService = imageService;
        this.recipeService = recipeService;
        this.startupPlanService = startupPlanService;
        this.historicFactsService = historicFactsService;
        this.notesSummaryService = notesSummaryService;
        this.languageTranslateService = languageTranslateService;
    }

    @GetMapping("ask-ai")
    public String getResponse(@RequestParam String prompt){
            return chatService.getResponse(prompt);

    }

    @GetMapping("ask-ai-options")
    public String getResponseOptions(@RequestParam String prompt){
        return chatService.getResponseOptions(prompt);
    }
    @GetMapping("generate-image")
    public List<String> generateImage(HttpServletResponse response, @RequestParam String prompt) throws IOException {
        ImageResponse imageResponse = imageService.generateImage(prompt);

         List<String> imageUrls = imageResponse.getResults().stream()
                .map(result -> result.getOutput().getUrl())
                .collect(Collectors.toList());
         return imageUrls;
    }

    @GetMapping("receipe-creator")
    public String receipecreator(@RequestParam String ingredients,
                                       @RequestParam(defaultValue = "any") String cuisine,
                                       @RequestParam(defaultValue = " ") String diet_restrictions){
        return recipeService.createReceipe(ingredients,cuisine,diet_restrictions);
    }


    @GetMapping("startup-idea-generator")
    public String createStartup(@RequestParam String idea,
                                 @RequestParam(defaultValue = "any") String problem,
                                 @RequestParam(defaultValue = " ") String solution){
        return startupPlanService.createStartup(idea,problem,solution);
    }

    @GetMapping("history-fact")
    public String getHistoryFact(@RequestParam String date,
                                 @RequestParam(defaultValue = "India") String region){
        return historicFactsService.getFactByDate(date,region);
    }


    @GetMapping("summarize-notes")
    public String summarizeNotes(@RequestParam String notes) {
        return notesSummaryService.summarizeNotes(notes);
    }


    @GetMapping("translate-ai")
    public String translateAI(
            @RequestParam String direction,
            @RequestParam String text
    ) {
        return languageTranslateService.translateAndExplain(direction, text);
    }



}

