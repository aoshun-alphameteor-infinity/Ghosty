package ghosty;


import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.Arrays;
public class FILE implements Comparable<FILE>{
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
  
  public byte[] getMD5(){
	  int test=0;
	  if(!location.toFile().isFile())return null;
	  if(!location.toFile().canRead())return null;
	  try {
		MessageDigest md5= MessageDigest.getInstance("MD5");
		md5.reset();
		InputStream content=new FileInputStream(location.toFile());
		while((test=content.read())!=-1){
				md5.update((byte) test);
		}
		content.close();
		return md5.digest();
	} catch (Exception e) {
		System.out.println("error\n"+e.getMessage());
		return null;
	}
  }

  @Override
  public int compareTo(FILE f){
	  return f.location.compareTo(location);
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
 
  public static void main(String[] args){
	  FILE f1=new FILE(Paths.get("..", "test1.txt"),false);
	  FILE f2=new FILE(Paths.get("..", "test2.txt"),false);
	  FILE f3=new FILE(Paths.get("..", "test3.txt"),false);
	  
	  boolean b1=Arrays.equals(f1.getMD5(), f2.getMD5());
	  boolean b2=Arrays.equals(f1.getMD5(), f3.getMD5());
	  System.out.println("test1 :"+b1+"\n"+"test2 :"+b2);
  }
}
