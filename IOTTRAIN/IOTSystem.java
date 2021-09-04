public class IOTSystem{
  private boolean is_on;
  private float speed;
  private String userName;
  private String password;

  public IOTSystem(){
    is_on = false;
    speed = 0;
    userName = "admin";
    password = "password";
  }

  public float getSpeed() {
    return speed;
  }

  private void printError(String text) {
    System.out.println(text); //text
  }

  public boolean applyBrakes(double amount){
    decreaseSpeed(amount);
    displayStatus("Brakes applied.");
    if (getSpeed() == amount) {
      printError("Warining: Break failure.");
      return false;
    }
      return true;
  }

  public boolean deploySand(){
    displayStatus("Deploy Sand");
    return true;
  }

  public boolean increaseSpeed(double amount){
    float origSpeed = getSpeed(); 
    speed += amount;
    displayStatus("Speed increased.");
    if (getSpeed() == origSpeed) {
      printError("Warning: No acceleration");
      return false;
    }
    return true;
  }

  private boolean decreaseSpeed(double amount){
    float origSpeed = getSpeed(); 
    speed -= amount;
    displayStatus("Speed decreased.");
    if (getSpeed() == origSpeed) {
      printError("Warning: No acceleration");
      return false;
    }
    return true;
  }

  public void lightUp(String color) {
    displayStatus("Light changed to " + color);
    //light.color = color;
  }

  public void stopTrain(){
    while (getSpeed() >= 0){
      applyBrakes(2);
    }
    displayStatus("Stop Train!");
  }

  public void turnOn(){
    displayStatus("Turning on");
    is_on = true;
  }

  public void turnOff(){
    displayStatus("Turning off");
    is_on = false;
  }

  public void displayStatus(String status){
    System.out.println(status);
  }

  public boolean getIsOn(){
    return is_on;
  }

  public String getUsername() {
    return userName;
  }

  public String getPassword() {
    return password;
  }
}