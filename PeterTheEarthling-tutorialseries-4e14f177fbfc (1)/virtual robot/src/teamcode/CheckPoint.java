package teamcode;


import static com.company.Robot.worldXPosition;
import static com.company.Robot.worldYPosition;

public class CheckPoint extends Thread
{

    double x;
    double y;
    double r;

    public CheckPoint(double x, double y,double r)
    {

        this.x=x;
        this.y=y;
        this.r=r;

    }

    public void run()
    {

        while(!(worldXPosition>x-r&&worldXPosition<x+r&&worldYPosition>y-r&&worldYPosition<y+r)) {
            try {
                sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        onHit();
        return;

    }

    public void onHit(){}

}
