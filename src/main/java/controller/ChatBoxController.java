package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class ChatBoxController {

    @FXML
    private VBox chatBox;
    @FXML
    private TextArea chatArea;
    @FXML
    private TextField chatInputField;

    private Stage stage;
    private QueueController queueController;

    private static final String OPENAI_API_KEY = "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"; // Thay thế bằng API key của bạn
    private static final String OPENAI_API_URL = "https://api.openai.com/v1/chat/completions"; // Sử dụng endpoint mới

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setQueueController(QueueController queueController) {
        this.queueController = queueController;
    }

    @FXML
    private void handleAdjustSize() {
        chatBox.setPrefHeight(300); // Adjust height
        chatBox.setPrefWidth(400);  // Adjust width
    }

    @FXML
    private void handleHideChat() {
        stage.hide();
    }

    @FXML
    private void handleSendMessage() {
        String message = chatInputField.getText();
        if (!message.isEmpty()) {
            String response = sendMessageToChatGPT("You will be a chat bot that provide help for " +
                    "3 types of data structure: queue, stack and list. Only answer question relevant " +
                    "to these topics. If not, return response: \"Sorry, I only answer questions about" +
                    " queue, stack and list! Please ask another question!\"" + message);
            chatArea.appendText("User:" + message + "\n");
            chatArea.appendText("ChatGPT:" + response + "\n");
            chatInputField.clear();
        }
    }

    private String sendMessageToChatGPT(String message) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost request = new HttpPost(OPENAI_API_URL);
            request.setHeader("Content-Type", "application/json");
            request.setHeader("Authorization", "Bearer " + OPENAI_API_KEY);

            String jsonPayload = String.format(
                    "{\"model\": \"gpt-3.5-turbo\", \"messages\": [{\"role\": \"user\", \"content\": \"%s\"}]}",
                    message.replace("\"", "\\\"") // Escape dấu ngoặc kép trong thông điệp
            );
            request.setEntity(new StringEntity(jsonPayload));

            try (CloseableHttpResponse response = httpClient.execute(request)) {
                String responseBody = EntityUtils.toString(response.getEntity());

                System.out.println("Response JSON: " + responseBody);

                ObjectMapper mapper = new ObjectMapper();
                JsonNode jsonNode = mapper.readTree(responseBody);

                JsonNode choicesNode = jsonNode.path("choices");
                if (choicesNode.isArray() && !choicesNode.isEmpty()) {
                    return choicesNode.get(0).path("message").path("content").asText().trim();
                } else {
                    return "Error: Invalid response from ChatGPT.";
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return "Error: Unable to get response from ChatGPT.";
        }
    }
}
