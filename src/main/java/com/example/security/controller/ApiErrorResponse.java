package com.example.security.controller;

public record ApiErrorResponse(
        int errorCode,
        String description) {

}