package com.sistemas.facturador.Controllers;

import com.sistemas.facturador.models.PreguntaRequest;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/ia")
public class AsistenteAiController {

    @Value("${gpt.token}")
    private String API_KEY;
    private static final String ASSISTANT_ID = "asst_lJIL9qCSsoZVXnFViAHX4Hzn";

    @PostMapping("/pregunta")
    public Map<String, String> hacerPregunta(@RequestBody PreguntaRequest request) {

        // Configurar headers
        Unirest.config().setDefaultHeader("Authorization", "Bearer " + API_KEY);
        Unirest.config().setDefaultHeader("OpenAI-Beta", "assistants=v2");
        Unirest.config().setDefaultHeader("Content-Type", "application/json");

        // 1. Crear hilo
        HttpResponse<JsonNode> threadResponse = Unirest.post("https://api.openai.com/v1/threads").asJson();
        String threadId = threadResponse.getBody().getObject().getString("id");

        // 2. Enviar pregunta
        Unirest.post("https://api.openai.com/v1/threads/" + threadId + "/messages")
                .body("{\"role\": \"user\", \"content\": \"" + request.getPregunta() + "\"}")
                .asJson();

        // 3. Ejecutar run
        HttpResponse<JsonNode> runResponse = Unirest.post("https://api.openai.com/v1/threads/" + threadId + "/runs")
                .body("{\"assistant_id\": \"" + ASSISTANT_ID + "\"}")
                .asJson();

        String runId = runResponse.getBody().getObject().getString("id");

        // 4. Esperar hasta que termine
        String status = "queued";
        while (!status.equals("completed")) {
            HttpResponse<JsonNode> statusResponse = Unirest.get("https://api.openai.com/v1/threads/" + threadId + "/runs/" + runId).asJson();
            status = statusResponse.getBody().getObject().getString("status");

            try {
                Thread.sleep(1000); // puedes reducir si lo deseas
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // 5. Obtener la respuesta del asistente
        HttpResponse<JsonNode> messagesResponse = Unirest.get("https://api.openai.com/v1/threads/" + threadId + "/messages").asJson();
        var messages = messagesResponse.getBody().getObject().getJSONArray("data");
        var respuesta = messages
                .getJSONObject(0)
                .getJSONArray("content")
                .getJSONObject(0)
                .getJSONObject("text")
                .getString("value");

        // Retornar como JSON
        return Map.of("respuesta", respuesta);
    }
}
