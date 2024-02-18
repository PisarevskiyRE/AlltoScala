package org.example.taks1;

public class Test {
    public static void main(String[] args) {
        // Создание клиента
        Client client = new ClientImpl();

        // Создание обработчика
        Handler handler = new HandlerImpl(client);

        // Выполнение операции
        ApplicationStatusResponse response = handler.performOperation("someId");

        // Обработка результата
        if (response instanceof ApplicationStatusResponse.Success successResponse) {
            System.out.println("Application status: " + successResponse.status());
        } else if (response instanceof ApplicationStatusResponse.Failure failureResponse) {
            System.out.println("Operation failed after " + failureResponse.retriesCount() + " retries.");
        }
    }
}

