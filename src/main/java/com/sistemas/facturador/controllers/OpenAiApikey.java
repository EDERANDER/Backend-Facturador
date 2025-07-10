package com.sistemas.facturador.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/facturador")
public class OpenAiApikey {

    @Value("${OpenAi.token}")
    private String openAiApi;

    @GetMapping("/apikey")
    public ResponseEntity<Map<String, String>> obtenerApiKey() {
        Map<String, String> response = new HashMap<>();
        response.put("apikey", openAiApi);
        return ResponseEntity.ok(response);
    }
}