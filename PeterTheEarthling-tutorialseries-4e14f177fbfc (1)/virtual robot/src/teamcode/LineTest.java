package teamcode;

import PurePursuit.PurePursuit;
import com.company.ComputerDebugging;
import com.company.FloatPoint;
import static com.company.Robot.worldXPosition;
import static com.company.Robot.worldYPosition;
import static com.company.Robot.worldAngle_rad;

public class LineTest extends Thread
{

    public void run()
    {

        while(true)
        {

            ComputerDebugging.sendLine(new FloatPoint(worldXPosition+Math.cos(worldAngle_rad-Math.PI/4)*30,worldYPosition+Math.sin(worldAngle_rad-Math.PI/4)*30),new FloatPoint(PurePursuit.xGoal,PurePursuit.yGoal));
            ComputerDebugging.sendLine(new FloatPoint(worldXPosition+Math.cos(worldAngle_rad+Math.PI/4)*30,worldYPosition+Math.sin(worldAngle_rad+Math.PI/4)*30),new FloatPoint(PurePursuit.xGoal,PurePursuit.yGoal));
            ComputerDebugging.sendLine(new FloatPoint(worldXPosition+Math.cos(worldAngle_rad-3*Math.PI/4)*30,worldYPosition+Math.sin(worldAngle_rad-3*Math.PI/4)*30),new FloatPoint(PurePursuit.xGoal,PurePursuit.yGoal));
            ComputerDebugging.sendLine(new FloatPoint(worldXPosition+Math.cos(worldAngle_rad+3*Math.PI/4)*30,worldYPosition+Math.sin(worldAngle_rad+3*Math.PI/4)*30),new FloatPoint(PurePursuit.xGoal,PurePursuit.yGoal));
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }


}
