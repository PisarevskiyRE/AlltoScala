package org.example.taks1;

public class MyHandler implements Handler {

    private final Client client;

    public MyHandler(Client client) {
        this.client = client;
    }

    @Override
    public ApplicationStatusResponse performOperation(String id) {
        long startTime = System.currentTimeMillis();
        Response response1 = client.getApplicationStatus1(id);
        long timeElapsed = System.currentTimeMillis() - startTime;

        if (response1 instanceof Response.Success) {
            Response.Success successResponse = (Response.Success) response1;
            return new ApplicationStatusResponse.Success(successResponse.applicationId(), successResponse.applicationStatus());
        } else if (response1 instanceof Response.RetryAfter) {
            Response.RetryAfter retryAfterResponse = (Response.RetryAfter) response1;
            long delay1 = retryAfterResponse.delay().getSeconds() * 1000 - timeElapsed;
            if (delay1 > 0) {
                try {
                    Thread.sleep(delay1);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        startTime = System.currentTimeMillis();
        Response response2 = client.getApplicationStatus2(id);
        timeElapsed = System.currentTimeMillis() - startTime;

        if (response2 instanceof Response.Success) {
            Response.Success successResponse = (Response.Success) response2;
            return new ApplicationStatusResponse.Success(successResponse.applicationId(), successResponse.applicationStatus());
        } else if (response2 instanceof Response.RetryAfter) {
            Response.RetryAfter retryAfterResponse = (Response.RetryAfter) response2;
            long delay2 = retryAfterResponse.delay().getSeconds() * 1000 - timeElapsed;
            if (delay2 > 0) {
                try {
                    Thread.sleep(delay2);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        return new ApplicationStatusResponse.Failure(null, 2);
    }
}
