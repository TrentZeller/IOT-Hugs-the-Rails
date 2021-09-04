import java.util.Scanner;
import javax.swing.*;
import java.awt.GraphicsEnvironment;
import java.awt.GraphicsDevice;

class Main{
  public static final String TEXT_RED = "\u001B[31m";
  public static final String TEXT_GREEN = "\u001B[32m";
  public static final String TEXT_YELLOW = "\u001B[33m";
 static GraphicsDevice device = GraphicsEnvironment
        .getLocalGraphicsEnvironment().getScreenDevices()[0];
 
  public static void main(String[] args) {
    String username;
    String password;
    Scanner sc = new Scanner(System.in);
    IOTSystem iot = new IOTSystem();
    Sensors camera = new Sensors("Camera", 10000);
    Sensors barometer = new Sensors("Barometer", 10001);
    Sensors OD = new Sensors("Obstacle Detection", 10002);
    Sensors RFS = new Sensors("Rail Friction Sensor", 10003);
    Sensors TOF = new Sensors("Time of Flight", 10004);

    

    iot.displayStatus("Startup initialized.");
    iot.lightUp(TEXT_GREEN);
    //login GUI
    GUI frame = new GUI();
    frame.setTitle("Login Form");
    frame.setVisible(false);
    frame.setBounds(10, 10, 370, 600);
    frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
    frame.setUndecorated(true);
    device.setFullScreenWindow(frame);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(false);

    
    while(iot.getIsOn()) {
      boolean warning = false;
      if(barometer.isRaining() && iot.getSpeed() > 40) {
        warning = true;
        iot.displayStatus("Warning: It is raining.");
        iot.lightUp(TEXT_YELLOW);
        iot.deploySand();
      }

      if(barometer.isRaining() && iot.getSpeed() > 40) {
        warning = true;
        iot.displayStatus("Warning: It is raining and the train is in high speed. Reduce speed?");
        iot.lightUp(TEXT_RED);
        iot.applyBrakes(10);
      }

      if(barometer.isRaining() && iot.getSpeed() < 20) {
        warning = true;
        iot.displayStatus("Warning: It is raining and the train is in low speed");
        iot.lightUp(TEXT_YELLOW);
      }

      if(OD.objectOnTrack()) {
        warning = true;
        iot.displayStatus("Warning: Object on the Track");
        iot.lightUp(TEXT_YELLOW);
        iot.stopTrain();
      }
// wheels are slipping if RPM != GPS speed
      if(RFS.wheelsAreSlipping() && iot.getSpeed() > 40) {
        warning = true;
        iot.displayStatus("Warning: Wheels are slipping. Deploying Sand.");
        iot.lightUp(TEXT_YELLOW);
        iot.deploySand();
      }

      if(RFS.wheelsAreSlipping() && iot.getSpeed() > 40) {
        warning = true;
        iot.displayStatus("Warning: Wheels are slipping. Deploying Sand.");
        iot.lightUp(TEXT_YELLOW);
        iot.deploySand();
      }        

      if(camera.gateIsUp()){
        warning = true;
        iot.displayStatus("Warning: Gate is up. Stopping Train.");
        iot.lightUp(TEXT_YELLOW);
        iot.stopTrain();
      } 

      if(TOF.movementOnTrack() && iot.getSpeed() > 40){
        warning = true;
        iot.displayStatus("Warning: Object on track. Slowing down");
        iot.lightUp(TEXT_YELLOW);
        iot.applyBrakes(iot.getSpeed() / 2.0);
      }               
      if (warning == false) {
        iot.displayStatus("Fully operational");
        iot.lightUp(TEXT_GREEN);
      }
    }
  
  }
}