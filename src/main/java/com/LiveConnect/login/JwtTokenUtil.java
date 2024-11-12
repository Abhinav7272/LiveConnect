package com.LiveConnect.login;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Component
public class JwtTokenUtil {

    private static final String SECRET_KEY = "your_secret_key"; // Replace with your own secret key

    // Create a JWT with header, payload, and signature
    public String createToken(String username) throws NoSuchAlgorithmException {
        // Header
        String header = "{\"alg\":\"HS256\",\"typ\":\"JWT\"}";
        String encodedHeader = Base64.getUrlEncoder().withoutPadding().encodeToString(header.getBytes());

        // Payload with expiration time of 1 day
        long currentTimeMillis = System.currentTimeMillis();
        long expirationTime = currentTimeMillis + 86400000; // 24 hours in milliseconds
        String payload = String.format("{\"sub\":\"%s\",\"exp\":%d}", username, expirationTime);
        String encodedPayload = Base64.getUrlEncoder().withoutPadding().encodeToString(payload.getBytes());

        // Signature using HMAC-SHA256
        String signature = sign(encodedHeader + "." + encodedPayload, SECRET_KEY);

        // Combine header, payload, and signature into JWT format
        return encodedHeader + "." + encodedPayload + "." + signature;
    }

    // Helper function to create HMAC-SHA256 signature
    private static String sign(String data, String secret) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.update((data + secret).getBytes(StandardCharsets.UTF_8));
        byte[] hash = digest.digest();
        return Base64.getUrlEncoder().withoutPadding().encodeToString(hash);
    }

    // Verify JWT by checking signature and expiration
    public static boolean verifyToken(String token) throws NoSuchAlgorithmException {
        String[] parts = token.split("\\.");
        if (parts.length != 3) return false;

        String header = parts[0];
        String payload = parts[1];
        String signature = parts[2];

        // Verify signature
        String computedSignature = sign(header + "." + payload, SECRET_KEY);
        if (!computedSignature.equals(signature)) return false;

        // Decode payload to check expiration
        String decodedPayload = new String(Base64.getUrlDecoder().decode(payload));
        JSONObject jsonPayload = new JSONObject(decodedPayload);
        long exp = jsonPayload.getLong("exp");
        return exp > System.currentTimeMillis(); // Token is valid if not expired
    }

    // Extract username from the token
    public static String getUsernameFromToken(String token) {
        try {
            String[] parts = token.split("\\.");
            if (parts.length != 3) return null;

            // Decode payload to get username (subject)
            String decodedPayload = new String(Base64.getUrlDecoder().decode(parts[1]));
            JSONObject jsonPayload = new JSONObject(decodedPayload);
            return jsonPayload.getString("sub");
        } catch (Exception e) {
            return null; // Token is invalid
        }
    }
}
