package org.calicoder;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class AllOfFuture {

    //  Completable Future to download web page
    public static CompletableFuture<String> downloadWebPage(String webPageLink) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        });
    }

    public static void main(String[] args) {

        //  List of Completable Futures to download Content from each page
        List<String> webPageLinks = Arrays.asList("dsfsdfds","fdsfsdfs","sdfdsfsdf");
        List<CompletableFuture<String>> pageContentFutures = webPageLinks.stream()
                .map(webPageLink -> downloadWebPage(webPageLink))
                .collect(Collectors.toList());

        //  Creating a single future which is a combination of all futures
        CompletableFuture<Void> allFutures = CompletableFuture.allOf(pageContentFutures.toArray(new CompletableFuture[pageContentFutures.size()]));

        //  Join the results of all the futures
        CompletableFuture<List<String>> allPageContentsFuture = allFutures.thenApply(v -> {
            return pageContentFutures.stream()
                    .map(pageContentFuture -> pageContentFuture.join())
                    .collect(Collectors.toList());
        });
        //  Completable future to count the number of web pages having "completable future" keyword

    }


}
