package org.example.task2;

import java.time.Duration;
import java.util.List;

public class HandlerImpl implements Handler {

    private final Client client;

    public HandlerImpl(Client client) {
        this.client = client;
    }

    @Override
    public Duration timeout() {
        return Duration.ofSeconds(1);
    }

    @Override
    public void performOperation() {
        while (true) {
            Event event = client.readData();
            List<Address> recipients = event.recipients();
            Payload payload = event.payload();

            for (Address recipient : recipients) {
                Result result = client.sendData(recipient, payload);
                if (result == Result.REJECTED) {
                    try {
                        Thread.sleep(timeout().toMillis());
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }
    }
}
