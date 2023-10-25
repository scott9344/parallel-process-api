package com.innovativeintell.parallel.process.api.service;
import com.innovativeintell.parallel.process.api.model.APIResponse;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class ParallelDelegatorServiceImpl implements ParallelDelegatorService{

    Logger logger = LoggerFactory.getLogger("ParallelDelegatorServiceImpl");

    @Override
    public APIResponse invokeServices() {
        try {
            long startTime = System.currentTimeMillis();
            CompletableFuture<String> future = CompletableFuture.supplyAsync(this::invokeVantageAPI);
            CompletableFuture<String> future2 = CompletableFuture.supplyAsync(this::invokeVantageAPIWeekReport);

            String combined = Stream.of(future, future2)
                    .map(CompletableFuture::join)
                    .collect(Collectors.joining(" "));
            long endTime = System.currentTimeMillis();
            String totalTimeTaken = "Total time to invoke two apis "+ (endTime-startTime);
            return new APIResponse("TotalTimeTaken " +totalTimeTaken+ "Response " + combined );
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    @Async("threadPoolTaskExecutor")
    public String invokeVantageAPI() {
        long startTime = System.currentTimeMillis();
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://alpha-vantage.p.rapidapi.com/query?interval=5min&function=TIME_SERIES_INTRADAY&symbol=MSFT&datatype=json&output_size=compact")
                .get()
                .addHeader("X-RapidAPI-Key", "")
                .addHeader("X-RapidAPI-Host", "alpha-vantage.p.rapidapi.com")
                .build();

        try {
            String response = CompletableFuture.completedFuture(client.newCall(request).execute().body().string()).get();
            long endTime = System.currentTimeMillis();
            long invokeVantageAPIResponseTime = (endTime - startTime);
            return response+ "VantageAPI "+invokeVantageAPIResponseTime;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    @Async("threadPoolTaskExecutor")
    public String invokeVantageAPIWeekReport() {
        long startTime = System.currentTimeMillis();
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://alpha-vantage.p.rapidapi.com/query?function=TIME_SERIES_WEEKLY&symbol=MSFT&datatype=json")
                .get()
                .addHeader("X-RapidAPI-Key", "")
                .addHeader("X-RapidAPI-Host", "alpha-vantage.p.rapidapi.com")
                .build();

        try {
            String response = CompletableFuture.completedFuture(client.newCall(request).execute().body().string()).get();
            long endTime = System.currentTimeMillis();
            long totalTime = endTime-startTime;
            return response+ " invokeVantageAPIWeekReport "+ totalTime;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }

}
