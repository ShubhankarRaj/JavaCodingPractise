package org.bankroberry;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.*;
import java.util.stream.Stream;

public class App {
    private static final Logger log = LoggerFactory.getLogger(App.class);

    public Loot lootBankUsingSimpleJava(final Thief thief, final String victim){
        //  Normal Java Implementation - Serial
        Actions.unlockTheDoor();

        final String safetyBoxNumber = Actions.figureOutSafetyBoxNumber(victim);
        final int pin = Actions.hackSecretPin(victim);

        final Loot loot = Actions.openSafetyLock(safetyBoxNumber, pin);
        log.info("{} gets the content of the safety box: '{}'", thief.getName(), thief.handleLoot(loot));
        return loot;
    }

    public Loot lootusingFunctionalProgramming(final Thief thief, final String victim){
        //  Functional Programming Implementation - Serial
        return Stream.of(Actions.unlockTheDoor())
                .map((ignore)->Actions.figureOutSafetyBoxNumber(victim))
                .map((safetyBoxNumber)-> Actions.openSafetyLock(safetyBoxNumber, Actions.hackSecretPin(victim)))
                .peek(loot -> log.info("{} gets content of the safetyBox: {}",thief.getName(), thief.handleLoot(loot)))
                .findFirst().get();
    }

    public Loot lootUsingFutures(final Thief thief, final String victim) throws ExecutionException, InterruptedException {
        //  Futures implementation
        final ExecutorService executorService = Executors.newFixedThreadPool(4);
        final Future<Boolean> doorUnlockFuture = executorService.submit(
                Actions::unlockTheDoor
        );
        doorUnlockFuture.get();

        final Future<String> safetyBoxNumberFuture = executorService.submit(
                ()->Actions.figureOutSafetyBoxNumber(victim)
        );

        final Future<Integer> getPinFuture = executorService.submit(
                ()->Actions.hackSecretPin(victim)
        );
        final Future<Loot> lootFuture = executorService.submit(
                ()->Actions.openSafetyLock(safetyBoxNumberFuture.get(), getPinFuture.get())
        );
        executorService.shutdown();

        final Loot loot = lootFuture.get();
        log.info("{} get the content of the safety box: '{}'", thief.getName(), thief.handleLoot(loot));

        return loot;
    }


    public Loot lootBankUsingCompletable(final Thief thief, final String victim) throws ExecutionException, InterruptedException {
        return CompletableFuture.supplyAsync(Actions::unlockTheDoor)
                .thenCompose(isOpened ->
                        CompletableFuture.supplyAsync(() -> Actions.figureOutSafetyBoxNumber(victim))
                .thenCombineAsync(
                        CompletableFuture.supplyAsync(() -> Actions.hackSecretPin(victim)),
                        Actions::openSafetyLock
                ).exceptionally(e -> {
                    log.error("Something went wrong: {} Run, run, run !!",e.getMessage());
                    return Loot.BAD;
                        }
                        )
                ).thenApply(
                        loot -> {
                                log.info("{} gets the content of the safety box: '{}'", thief.getName(), thief.handleLoot(loot));
                                return loot;
                        }
                ).join();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException, IOException {

        Properties props = new Properties();
        props.load(new FileInputStream("src/log4j.properties"));
        PropertyConfigurator.configure(props);

        App app = new App();
        long start = System.currentTimeMillis();
        app.lootBankUsingSimpleJava(Thief.PETE, "Raj");
        long finish = System.currentTimeMillis();
        long timeElapsed = (finish-start)/1000;
        log.info(String.format("Simple Java execution Completed in %d seconds!", timeElapsed));

        start = System.currentTimeMillis();
        app.lootusingFunctionalProgramming(Thief.LORA, "Niharika");
        finish = System.currentTimeMillis();
        timeElapsed = (finish-start)/1000;
        log.info(String.format("Functional Java execution Completed in %d seconds!", timeElapsed));

        start = System.currentTimeMillis();
        app.lootUsingFutures(Thief.WILL, "Niharika");
        finish = System.currentTimeMillis();
        timeElapsed = (finish-start)/1000;
        log.info(String.format("Using Java Futures execution Completed in %d seconds!", timeElapsed));

        start = System.currentTimeMillis();
        app.lootBankUsingCompletable(Thief.WILL, "Shubhankar");
        finish = System.currentTimeMillis();
        timeElapsed = (finish-start)/1000;
        log.info(String.format("Using Completable Future execution Completed in %d seconds!", timeElapsed));
    }
}
