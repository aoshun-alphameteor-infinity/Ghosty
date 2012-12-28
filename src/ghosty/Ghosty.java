package ghosty;
//it's just what we can use in java.io.*
import java.io.Reader;
import java.io.Writer;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;

//other import
import static java.nio.file.StandardOpenOption.*;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.Scanner;

public class Ghosty {
private static boolean debug=false;
private static FileLock lock;

private static boolean options() {
		String s=null;
		Scanner sc=new Scanner(System.in);
		while(sc.hasNext()){
			s=sc.next();
			
		if(!s.equals("ghosty")) continue;
			if(sc.hasNext()){ s=sc.next();
			if(s.equals("--clean")) init();
			else if(s.equals("--debug")) debug=true;
			}
		}
		
		sc.close();
		return true;
		
	}
	
		private static void init() {
		
		
	}
	
	private static void getInfo(){
		
	}
	
	
	public static void main (String args) throws Exception{
		Path path = null;
		FileChannel working= FileChannel.open(path, CREATE);
		lock=working.lock();
		// test watcher
		while(true){
			
			Directory dir=new Directory();
			try {
				dir.fileTreeMaker(FileSystems.getDefault().getPath("."));
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			Watcher w=new Watcher(dir);
			Iterator<FILE> it=dir.fileTreeIterator();
			w.watch(it);
	
		}
		
	}
}
