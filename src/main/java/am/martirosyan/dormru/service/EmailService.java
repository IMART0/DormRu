package am.martirosyan.dormru.service;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class EmailService {

    private static final OkHttpClient client = new OkHttpClient();


    public void sendStatusUpdateEmail(String recipientEmail, String status, String text) {
        String json = """
            {
              "sender": {
                "name": "DormRU Support",
                "email": "uduw213@gmail.com"
              },
              "to": [
                {
                  "email": "%s",
                  "name": "USER"
                }
              ],
              "subject": "Изменение статуса жалобы",
              "htmlContent": "<!DOCTYPE html><html><body><h1>Статус вашей жалобы изменен</h1><p>Ваша жалоба: %s</p><p> имеет статус<strong>%s</strong></p></body></html>"
            }
            """.formatted(recipientEmail, text, status);

        RequestBody body = RequestBody.create(
                json, MediaType.get("application/json; charset=utf-8"));

        Request request = new Request.Builder()
                .url("https://api.brevo.com/v3/smtp/email")
                .post(body)
                .addHeader("accept", "application/json")
                .addHeader("api-key", API_KEY)
                .addHeader("content-type", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                log.info("Transactional email sent successfully!");
            } else {
                log.error("Failed to send transactional email. Code: {}", response.code());
                log.error("Response: {}", response.body().string());
            }
        } catch (IOException e) {
            throw new RuntimeException("Error sending transactional email", e);
        }
    }
}
