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
  /**
   * this method determine if a file is a directory or not
   * @param test is the path of the file which need to be test
   * @return true if the file is a directory, false in other case
   */
  public static boolean FileIsDirectory(Path test){
	  Directory tmp=new Directory(test);
	  try {
		tmp.fileTreeMaker();
	} catch (Exception e) {
		System.out.println("error\n"+e.getMessage());
	}
	  if(tmp.getFileTree().size()!=1)return true;
	  else return false;
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
