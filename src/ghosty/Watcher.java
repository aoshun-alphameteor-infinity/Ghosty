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

public void watch(){
try{
WatchService w=FileSystems.getDefault().newWatchService();
Iterator<FILE> it=dir.fileTreeIterator();
while(it.hasNext()) {
FILE f=it.next();
f.getLocation().register(w, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_MODIFY, StandardWatchEventKinds.OVERFLOW, StandardWatchEventKinds.ENTRY_DELETE);
WatchKey k = w.take();
for (WatchEvent<?> object : k.pollEvents()) {
        if (object.kind() == StandardWatchEventKinds.ENTRY_MODIFY) {
         System.out.println("Modify: " + object.context().toString());
         this.dir.updateFileTree();
           
        }
        if (object.kind() == StandardWatchEventKinds.ENTRY_DELETE) {
        	
        	this.dir.updateFileTree();
            System.out.println("Delete: " + object.context().toString());
        }
        if (object.kind() == StandardWatchEventKinds.ENTRY_CREATE) {
        	
        	this.dir.updateFileTree();
            System.out.println("Created: " + object.context().toString());
        }
        if (object.kind() == StandardWatchEventKinds.OVERFLOW) {
            System.out.println("lost events!");
        }
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
