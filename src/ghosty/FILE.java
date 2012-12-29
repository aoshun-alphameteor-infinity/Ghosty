package ghosty;

import java.nio.file.Path;

public class FILE {
 private Path location;
 
 private boolean isDir;
 
  public FILE(Path location, boolean isDir){
	  this.location=location;
	  this.isDir=isDir;
  }
  
  public Path getLocation(){
	  return location;
  }
  
  public boolean isDirectory(){
	  return isDir;
  }
  
  @Override
  public boolean equals(Object o){
	  if(!(o instanceof FILE))return false;
	  FILE f=(FILE)o;
	  if(f.isDir!=isDir)return false;
	  if(!location.equals(f.location))return false;
	  return true;
  }
  
  @Override
  public String toString(){
	  if(isDir==true)return location.toString()+" is a repertory";
	  else return location.toString()+" is a file";
  }
 
}
