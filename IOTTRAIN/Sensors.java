import java.util.*;

public class Sensors{
  private String type;
  private int id;
  private boolean data;


  public Sensors(String type_, int id_){
    type = type_;
    id = id_;
    data = false;
  }

  public boolean objectOnTrack(){
    if(type.equalsIgnoreCase("Obstacle Detection")){
      return data;
    }
    return false;
  }

  public boolean isRaining(){
    if(type.equalsIgnoreCase("Barometer")){
      return data;
    }
    return false;
  }

  public boolean wheelsAreSlipping(){
    if(type.equalsIgnoreCase("Rail Friction Sensor")){
      return data;
    }
    return false;
  }

  public boolean gateIsUp(){
    if(type.equalsIgnoreCase("Camera")){
      return data;
    }
    return false;
  }

  public boolean movementOnTrack(){
    if(type.equalsIgnoreCase("Time of Flight")){
      return data;
    }
    return false;
  }

  public int get_ID(){
    return id;
  }

  public String get_Type(){
    return type;
  }

  public void set_data(boolean data_){
    data = data_;
  }
}