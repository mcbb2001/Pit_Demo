package PurePursuit;

public class WayPoint
{

    public static int iRel;
    public double x;
    public double y;
    double maxSpeed;
    double goalTurnPercentageBias;
    double turnAccelerationCap;
    public WayPoint(double x, double y, double maxSpeed, double goalTurnPercentageBias,double turnAccelerationCap)
    {

        this.x=x;
        this.y=y;
        this.maxSpeed=maxSpeed;
        this.goalTurnPercentageBias=goalTurnPercentageBias;
        this.turnAccelerationCap=turnAccelerationCap;

    }


}
