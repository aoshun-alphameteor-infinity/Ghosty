package ghosty;

import java.nio.file.Path;

public class FILE {
 private Path location;
 /**
  * if it is a file, this number is -1 
  * else it represent the number of element
  */
 private int numberelt;
 
  public FILE(Path location, int numberelt){
	  this.location=location;
	  this.numberelt=numberelt;
  }
  
  public Path getLocation(){
	  return location;
  }
  
  public int getNumberelt(){
	  return numberelt;
  }
 
}
