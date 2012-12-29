package ghosty;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.Iterator;


// bug: java.nio.file.NotDirectoryException

public class Watcher {
  private Directory dir;
	
	
	public Watcher(Directory dir) {
		this.dir=dir;
	}

	public void watch(Iterator<FILE> it){
		try{
			WatchService w=FileSystems.getDefault().newWatchService();
	
	if(!it.hasNext()) return;
	FILE f=it.next();
	f.getLocation().register(w, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_MODIFY, StandardWatchEventKinds.OVERFLOW, StandardWatchEventKinds.ENTRY_DELETE);
	WatchKey k = w.take();
	for (WatchEvent<?> object : k.pollEvents()) {
        if (object.kind() == StandardWatchEventKinds.ENTRY_MODIFY) {
        	System.out.println("Modify: " + object.context().toString());
            if(f.getNumberelt()!=0)
            watch(it);
        }
        if (object.kind() == StandardWatchEventKinds.ENTRY_DELETE) {
            System.out.println("Delete: " + object.context().toString());
        }
        if (object.kind() == StandardWatchEventKinds.ENTRY_CREATE) {
            System.out.println("Created: " + object.context().toString());
        }
        if (object.kind() == StandardWatchEventKinds.OVERFLOW) {
            System.out.println("lost events!");
        }
    }
			
} catch (IOException e) {
	
	e.printStackTrace();
} catch (InterruptedException e) {
	
	e.printStackTrace();
}
	}
	
	
	
public void notifySynchro(){
		
	}


}
