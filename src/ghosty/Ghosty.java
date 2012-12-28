package ghosty;
//it's just what we can use in java.io.*
import java.io.Reader;
import java.io.Writer;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;

public class Ghosty {
private static boolean debug=false;

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
		return true;
		
	}
	
		private static void init() {
		
		
	}
	
	private static void getInfo(){
		
	}
	
	
	public static void main (String args){
		
		
	}
}
