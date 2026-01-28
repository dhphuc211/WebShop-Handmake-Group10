package com.example.backend.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class EmailService {

    private static String RESEND_API_KEY;
    private static String RESEND_FROM_EMAIL;
    private static final String RESEND_API_URL = "https://api.resend.com/emails";
    private final HttpClient httpClient = HttpClient.newHttpClient();

    static {
        try (InputStream input = EmailService.class.getClassLoader().getResourceAsStream("oauth.properties")) {
            Properties prop = new Properties();
            if (input == null) {
                System.out.println("Sorry, unable to find oauth.properties");
            } else {
                prop.load(input);
                RESEND_API_KEY = prop.getProperty("resend.api.key");
                RESEND_FROM_EMAIL = prop.getProperty("resend.from.email");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public boolean sendNewPasswordEmail(String toEmail, String newPassword) {
        if (RESEND_API_KEY == null || RESEND_API_KEY.startsWith("REPLACE")) {
            System.err.println("Resend API Key is not configured.");
            return false;
        }

        String from = (RESEND_FROM_EMAIL != null) ? RESEND_FROM_EMAIL : "onboarding@resend.dev";
        String subject = "Your New Password for WebShop Handmade";
        String htmlBody = "<h1>Password Reset</h1>"
                + "<p>Hello,</p>"
                + "<p>Your password has been reset. Here is your new password:</p>"
                + "<h2>" + newPassword + "</h2>"
                + "<p>Please change it after logging in.</p>"
                + "<p>Thank you,<br>WebShop Handmade Team</p>";

        // Tạo JSON payload thủ công, sử dụng biến from từ cấu hình
        String jsonPayload = String.format("{\"from\":\"WebShop Handmade <%s>\",\"to\":\"%s\",\"subject\":\"%s\",\"html\":\"%s\"}",
                from, toEmail, subject, htmlBody.replace("\"", "\\\""));


        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(RESEND_API_URL))
                .header("Authorization", "Bearer " + RESEND_API_KEY)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonPayload, StandardCharsets.UTF_8))
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() >= 200 && response.statusCode() < 300) {
                System.out.println("Email sent successfully! Response: " + response.body());
                return true;
            } else {
                System.err.println("Failed to send email. Status: " + response.statusCode() + ", Body: " + response.body());
                return false;
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }
}