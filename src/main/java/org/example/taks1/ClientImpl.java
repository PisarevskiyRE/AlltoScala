package org.example.taks1;

import java.time.Duration;

class ClientImpl implements Client {
    @Override
    public Response getApplicationStatus1(String id) {
        // Симуляция успешного ответа
        return new Response.Success("status1", "id1");
    }

    @Override
    public Response getApplicationStatus2(String id) {
        // Симуляция ответа о необходимости повторной попытки через 2 секунды
        return new Response.RetryAfter(Duration.ofSeconds(2));
    }
}