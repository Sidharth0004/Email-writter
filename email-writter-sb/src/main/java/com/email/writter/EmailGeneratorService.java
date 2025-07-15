package com.email.writter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;
import java.util.Objects;

@Service
public class EmailGeneratorService {

    private final WebClient webClient;


    @Value("${gemini.api.url}")
    private String geminiApiUrl;
    @Value("${gemini.api.key}")
    private String getGeminiApiKey;

    public EmailGeneratorService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public String generateEmailReply(EmailRequest emailRequest){
     //Build Promt
      String  prompt = buildPrompt(emailRequest);

     //Craft a req
    Map<String , Object> requestbody = Map.of(
            "contents", new Object[]{
                    Map.of("parts" , new Object[]{
                   Map.of("text",prompt)
                    })
            }
    );

      //Do req and get Request
        String response = webClient.post()
                .uri(geminiApiUrl + getGeminiApiKey)
                .header("Content-Type", "application/json") // ✅ Fix here
                .bodyValue(requestbody)
                .retrieve()
                .bodyToMono(String.class)
                .block();

      //Return the Request
        return  extractReturnContent(response);

  }

        private String extractReturnContent(String response) {
        try{
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(response);
            return  rootNode.path("candidates")
                    .get(0)
                    .path("content")
                    .path("parts")
                    .get(0)
                    .get("text")
                    .asText();


        } catch (Exception e) {
            return "Error processing request: " +e.getMessage();
        }
    }


    private String buildPrompt(EmailRequest emailRequest) {
      StringBuilder prompt = new StringBuilder();
      prompt.append("Generate a professional email reply for the following email content." +
              " Please don't generate a subject line");
      if(emailRequest.getTone() != null && !emailRequest.getTone().isEmpty() ){
        prompt.append("Use a").append(emailRequest.getTone()).append(" tone. ");
      }
prompt.append("\nOriginal email:").append(emailRequest.getEmailContent());
      return  prompt.toString();
    }


}
