package com.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.faces.context.FacesContext;

import org.apache.myfaces.custom.fileupload.UploadedFile;

public class FileUploadUtil {

	public static boolean uploadImage(UploadedFile uploadedFile, String fileName) {

		final int BUFFER_SIZE = 4096;
		
		// opens input stream of the request for reading data
        InputStream inputStream;
		try {
			inputStream = uploadedFile.getInputStream();
		
        
        File uploadedLocation = new File(
        		FacesContext.getCurrentInstance().getExternalContext().getRealPath("")
						+ "/uploadedfiles/quesImages" + "/"
						+ fileName);
         
        // opens an output stream for writing file
        FileOutputStream outputStream = new FileOutputStream(uploadedLocation);
         
        //byte[] buffer = new byte[BUFFER_SIZE];
        byte[] buffer = uploadedFile.getBytes();
        int bytesRead = -1;
        System.out.println("Receiving data...");
         
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }
         
        System.out.println("Data received.");
        outputStream.close();
        inputStream.close();
        return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

}
