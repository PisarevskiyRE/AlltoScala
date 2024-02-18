package org.example.task2;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        Client client = new Client() {
            @Override
            public Event readData() {
                List<Address> recipients = new ArrayList<>();
                recipients.add(new Address("datacenter1", "node1"));
                recipients.add(new Address("datacenter2", "node2"));
                Payload payload = new Payload("origin", new byte[]{1, 2, 3});
                return new Event(recipients, payload);
            }

            @Override
            public Result sendData(Address dest, Payload payload) {
                System.out.println("Sending data to " + dest);
                return Result.ACCEPTED;
            }
        };
        Handler handler = new HandlerImpl(client);
        handler.performOperation();
    }
}
