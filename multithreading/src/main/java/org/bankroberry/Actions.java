
package org.bankroberry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ThreadLocalRandom;

public class Actions {

    private static final Logger log = LoggerFactory.getLogger(Actions.class);
    private static final long DELAY_MS = 1000L;

    private Actions(){}

    public static Boolean unlockTheDoor(){
        log.info("Unlocking the Door");
        delay(2000);
        log.info("Door unlocked");
        return true;
    }

    public static Boolean unlockTheDoorAsync(){
        log.info("Unlocking the Door");
        delay(2000);
        log.info("Door unlocked");
        return true;
    }

    public static int hackSecretPin(final String personName){
        log.info("Hacking the pin of the deposit box belonging to {}", personName);
        delay(5000);
        final int pin = (personName.hashCode() % 1000) + 1000;
        return pin;
    }

    public static String figureOutSafetyBoxNumber(final String personName){
        log.info("Figuring out the safety box number of {}", personName);
        delay(3000);
        final String safetyBoxnumber = "A" + ThreadLocalRandom.current().nextInt(100);
        log.info("Got the safety box number: {}", safetyBoxnumber);
        return safetyBoxnumber;
    }

    public static Loot openSafetyLock(final String safetyBoxNumber, final int pin){
        log.info("Opening the safe lock {} using the pin {}", safetyBoxNumber, pin);
        delay();
        log.info("Safety Box opened!");
        return Loot.randomLoot();
    }

    public static void delay(){
        delay(DELAY_MS);
    }

    public static void delay(long ms){
        try{
            Thread.sleep(ms);
        } catch(InterruptedException e){
            throw new RuntimeException(e);
        }
    }



}
