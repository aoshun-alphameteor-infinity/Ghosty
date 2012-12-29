package ghosty;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.FileContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;


public class Synchronization {
	
	public static InputStream download(Drive host,File f){
		String url=f.getDownloadUrl();
		if(url == null)return null;
		if(url.length() <= 0)return null;
		GenericUrl downurl=new GenericUrl(url);
		try{
			HttpResponse answer= host.getRequestFactory().buildGetRequest(downurl).execute();
			return answer.getContent();
		}catch(Exception e){
			System.out.println("error\n"+e.getMessage());
			return null;	
		}
	
	}
	
	public static File Update(Drive host,String fileId, Path newContent){
		try {
			File present=host.files().get(fileId).execute();
			
			FileContent content=new FileContent(null,newContent.toFile());
			
			File result=host.files().update(fileId, present, content).execute();
			
			return result;
		} catch (Exception e) {
			System.out.println("error\n"+e.getMessage());
			return null;
		}
	}
	
	
	public static File upload(Drive host, String extendedfilename, String parentId, Path file){
		File meta=new File();
		meta.setTitle(extendedfilename);
		
		if(parentId !=null && parentId.length()>0){
			meta.setParents(Arrays.asList(new File.ParentReference().setId(parentId)));
		}
		
		FileContent content=new FileContent(null,file.toFile());
		try{
			File result= host.files().insert(meta,content).execute();
			return result;
			}catch (Exception e){
				System.out.println("error\n"+e.getMessage());
				return null;
			}
		
	}
	
		///pour l'inspiration
	 /**
	   * Download a file's content.
	   *
	   * @param service Drive API service instance.
	   * @param file Drive File instance.
	   * @return InputStream containing the file's content if successful,
	   *         {@code null} otherwise.
	   */
	  private static InputStream downloadFile(Drive service, File file) {
	    if (file.getDownloadUrl() != null && file.getDownloadUrl().length() > 0) {
	      try {
	        HttpResponse resp =
	            service.getRequestFactory().buildGetRequest(new GenericUrl(file.getDownloadUrl()))
	                .execute();
	        return resp.getContent();
	      } catch (IOException e) {
	        // An error occurred.
	        e.printStackTrace();
	        return null;
	      }
	    } else {
	      // The file doesn't have any content stored on Drive.
	      return null;
	    }
	  }
	
}
