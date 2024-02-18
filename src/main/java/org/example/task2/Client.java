package org.example.task2;

public interface Client {
    Event readData();
    Result sendData(Address dest, Payload payload);
}
