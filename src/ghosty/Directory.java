package ghosty;



import java.io.IOException;
import static java.nio.file.FileVisitResult.*;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.nio.file.attribute.*;

public class Directory {
	private Path location;
	
	/**
	 * this three fields are used for create a representation of the file tree in which we work;
	 */
	///this is the representation
	private List<FILE> filetree;
	///it is the number of elements in a directory
	private int numberelt;
	
	public Directory(Path location){
		this.location=location;
		filetree=new ArrayList<FILE>();
	}
	
	/**
	 * 
	 * @param start
	 * @return result
	 * @throws IOException
	 * 
	 * start is the root of the file system in which the method will work.
	 * 
	 * this method return a list of FILE which represent the file tree.
	 * 
	 * note: this method don't work with the .ghosty directory generated by the program himself in order to keep the program data confidential
	 */
	public void fileTreeMaker(Path start) throws IOException{
		
		Files.walkFileTree(start, new SimpleFileVisitor<Path>(){
			 @Override
             public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
			 	throws IOException
			 {
				if(!dir.getFileName().equals(".ghosty")) 
				{
					numberelt=0;
					filetree.add(new FILE(dir,numberelt));
				}
				
				return CONTINUE; 
			 }
			 
			@Override
	         public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
	             throws IOException
	         {
				if(!file.getParent().getFileName().equals(".ghosty"))
	             {
					filetree.add(new FILE(file,-1));
					numberelt++;
	             }
				return CONTINUE;
	         }
			
			@Override
	         public FileVisitResult postVisitDirectory(Path dir, IOException e)
	             throws IOException
	         {
	             if (e == null) {
	                 if(!dir.getFileName().equals(".ghosty"))
	                 {	                	 
	                	 filetree.get(filetree.size()-numberelt-1).setNumberelt(numberelt);	                	 

	                 }
	                 return CONTINUE;
	             } else {
	                 // directory iteration failed
	                 throw e;
	             }
	         }
			
		});
	}

	public void setLocation(Path newlocation){
		location=newlocation;
	}
	
	public List<FILE> getFileTree(){
		return filetree;
	}
	
	public FILE[] getFileTreeArray(){
		return filetree.toArray(null);
	}
	
	public void clearFileTree(){
		filetree.clear();
	}
	
	public void removeFile(FILE f){
		filetree.remove(f);
	}
	
	public Iterator<FILE> fileTreeIterator(){
		return filetree.iterator();
	}
	
	public Path getDirectoryPath(){
		return location;
	}
	
	public FILE[] updateFileTree(){
		FILE[] tmp=filetree.toArray(null);
		filetree.clear();
		try {
			this.fileTreeMaker(location);
		} catch (Exception e) {
			System.out.println("error\n"+e.getMessage());
		}
		return tmp;
	}
}
