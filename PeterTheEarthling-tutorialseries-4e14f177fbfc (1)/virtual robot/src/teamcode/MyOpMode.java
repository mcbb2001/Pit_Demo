package teamcode;

import PurePursuit.*;


import RobotUtilities.MovementVars;
import com.company.ComputerDebugging;
import com.company.Robot;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.text.*;

import java.util.*;
import java.net.*;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.company.Robot.worldXPosition;
import static com.company.Robot.worldYPosition;
import static com.company.Robot.worldAngle_rad;



public class MyOpMode extends OpMode {
    RobotVars robot = new RobotVars();

    InetAddress host;
    ServerSocket ss;
    ArrayList<WayPoint> p=new ArrayList();

    Socket S;
    boolean run=false;
    public static double lookAhead=40;

    Socket s;

    public static int i = 7;

    PurePursuit purePursuit = new PurePursuit();

    @Override
    public void init()
    {

        try {
            host = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        worldXPosition=10;
        worldYPosition=10;
        Robot.worldAngle_rad=0;


        //purePursuit.followPath(p,100,1);


    }


    ObjectOutputStream oos;
    ObjectInputStream ois = null;

    @Override
    public void loop()
    {


        try {
            s= new Socket(host.getHostName(), 5056);
            S= new Socket(host.getHostName(), 5057);
            s.setSoTimeout(100);
            S.setSoTimeout(100);
        } catch (IOException e) {
            //e.printStackTrace();
        }

        try {
            if(s!=null)
                ois = new ObjectInputStream(s.getInputStream());
        } catch (IOException e) {
            //e.printStackTrace();
        }

        long heapSize = Runtime.getRuntime().freeMemory();

        //convert ObjectInputStream object to String
        //ArrayList<double[]> message = null;


        //take points from server and turn them into points on the screen
        try {

            p.clear();

            for(double[] d:(ArrayList<double[]>)ois.readObject())
            {

                p.add(new WayPoint(d[0]/2160*365,(1080-d[1])/5.9+22,.1,.1,1));

            }

        } catch (Exception e) {
            //e.printStackTrace();
        }


        try
        {

            //read button input if applicable
            String m = (String)ois.readObject();
            if(m==null)
                m="";

            if(m.equals("r")) {
                run = true;
                worldXPosition=10;
                worldYPosition=10;
                Robot.worldAngle_rad=0;
                purePursuit.lastGoal=new double[] {p.get(0).x,p.get(0).y};
                ComputerDebugging.clearLogPoints();
            }else if(m.equals("=")) {
                lookAhead++;
                //System.out.println(lookAhead);
            }
            else if(m.equals("-")) {
                lookAhead--;
                //System.out.println(lookAhead);
            }
            else if(m.equals("s")) {

                run = false;
                ComputerDebugging.clearLogPoints();
                worldXPosition=10;
                worldYPosition=10;
                worldAngle_rad=0;
                Robot.xSpeed=0;
                Robot.ySpeed=0;
                Robot.turnSpeed=0;
                MovementVars.movement_x=0;
                MovementVars.movement_y=0;
                MovementVars.movement_turn=0;
            }

        }catch(Exception e)
        {


            //e.printStackTrace();

        }


        //output data to server
            try {
                if(S!=null) {
                    oos = new ObjectOutputStream(S.getOutputStream());
                    oos.writeDouble(lookAhead);
                    oos.close();
                }

                //oos.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(0);
                return;
            }

            //input data from server
        try {
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


        //follow pure pursuit path
        if(p.size()>0)
        {
            p.add(p.get(p.size()-1));
            purePursuit.drawPath(p);
            if(run)
                purePursuit.followPath(p,lookAhead);
        }


    }


}
