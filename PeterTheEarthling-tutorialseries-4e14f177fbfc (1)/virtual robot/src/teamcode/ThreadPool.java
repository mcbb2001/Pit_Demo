package teamcode;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import PurePursuit.RobotVars;




public class ThreadPool {

    static ExecutorService pool = Executors.newFixedThreadPool(5);



    static Runnable robotVars = new RobotVars();



}
