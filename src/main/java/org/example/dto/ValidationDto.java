package org.example.dto;

import java.util.Map;

public record ValidationDto(String message,
                            Map<String, String> errors) {}
