package uet.oop.bomberman.utilities;

import com.google.common.util.concurrent.FakeTimeLimiter;
import com.google.common.util.concurrent.SimpleTimeLimiter;
import com.google.common.util.concurrent.TimeLimiter;

import java.util.concurrent.*;

public class SetTimeout {
    public static void run(Runnable runnable, long delay){
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(delay);
                runnable.run();
            }
            catch (Exception e){
                System.err.println(e.getMessage());
            }
        });
        thread.start();

    }
}
