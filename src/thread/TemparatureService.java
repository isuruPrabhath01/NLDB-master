package thread;

import javafx.concurrent.Task;
import util.Service;

import java.util.Random;

public class TemparatureService extends Task<Integer> {
    @Override
    protected Integer call() throws Exception {
        Random r = new Random();
        while (true){
            int i = r.nextInt(37);
            if(i>34){
                Thread.sleep(800);
                updateValue(i);
            }
        }

    }
}
