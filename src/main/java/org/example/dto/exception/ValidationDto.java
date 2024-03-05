package org.example.dto.exception;

import java.util.Map;

public record ValidationDto(String message,
                            Map<String, String> errors) {}
