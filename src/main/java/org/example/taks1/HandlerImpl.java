package org.example.taks1;

public class HandlerImpl implements Handler {

    private final Client client;

    public HandlerImpl(Client client) {
        this.client = client;
    }

    @Override
    public ApplicationStatusResponse performOperation(String id) {
        long startTime = System.currentTimeMillis();
        Response response1 = client.getApplicationStatus1(id);
        long timeElapsed = System.currentTimeMillis() - startTime;

        if (response1 instanceof Response.Success successResponse) {
            return new ApplicationStatusResponse.Success(successResponse.applicationId(), successResponse.applicationStatus());
        } else if (response1 instanceof Response.RetryAfter retryAfterResponse) {
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

        if (response2 instanceof Response.Success successResponse) {
            return new ApplicationStatusResponse.Success(successResponse.applicationId(), successResponse.applicationStatus());
        } else if (response2 instanceof Response.RetryAfter retryAfterResponse) {
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
