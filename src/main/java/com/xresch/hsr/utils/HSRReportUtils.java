package com.xresch.hsr.utils;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.io.FileUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**************************************************************************************
 * Just some utility methods used to create the report.
 * 
 * @author Reto Scheiwiller, (c) Copyright 2026
 * @license MIT License
 **************************************************************************************/

public class HSRReportUtils {
	
	public static Logger logger = Logger.getLogger(HSRReportUtils.class.getName());
	private static final int BUFFER_SIZE = 4096;
	
	
	private static Gson gson = new GsonBuilder()
				    	.setPrettyPrinting()
				    	.disableHtmlEscaping()
				    	.create()
				    	;

	/********************************************************************************************
	 * 
	 ********************************************************************************************/
	private static void extractFile(ZipInputStream in, File outdir, String name) throws IOException {
		
		byte[] buffer = new byte[BUFFER_SIZE];
		BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(outdir, name)));
		int count = -1;
		while ((count = in.read(buffer)) != -1)
			out.write(buffer, 0, count);
		out.close();
		
	}

	/********************************************************************************************
	 * 
	 ********************************************************************************************/
	private static void mkdirs(File outdir, String path) {
		File d = new File(outdir, path);
		if (!d.exists())
			d.mkdirs();
	}


	/********************************************************************************************
	 * Extract zipfile to outdir with complete directory structure
	 * 
	 * @param zipfile
	 *            Input .zip file
	 * @param outdir
	 *            Output directory
	 ********************************************************************************************/
	public static void extractZipFile(URI zipFileUri, String toDir) {
		
	    FileSystem zipFs;
		try {
			zipFs = FileSystems.newFileSystem(zipFileUri, new HashMap<String, String>());
		
		    final Path pathInZip = zipFs.getPath("./");
		    final Path targetDir = Paths.get(toDir);
		    
		    Files.walkFileTree(pathInZip, new SimpleFileVisitor<Path>() {
		        @Override
		        public FileVisitResult visitFile(Path filePath, BasicFileAttributes attrs) throws IOException {
		            // Make sure that we conserve the hierachy of files and folders inside the zip
		            Path relativePathInZip = pathInZip.relativize(filePath);
		            Path targetPath = targetDir.resolve(relativePathInZip.toString());
		            Files.createDirectories(targetPath.getParent());
		
		            // And extract the file
		            Files.copy(filePath, targetPath);
		
		            return FileVisitResult.CONTINUE;
		        }
		    });
	    
		} catch (IOException e) {
			logger.severe("Issue extracting zip file: "+e.getMessage());
			e.printStackTrace();
		}
	}
	/********************************************************************************************
	 * Extract zipfile to outdir with complete directory structure
	 * 
	 * @param zipfile
	 *            Input .zip file
	 * @param outdir
	 *            Output directory
	 ********************************************************************************************/
	public static void extractZipFile(ZipInputStream zin, String targetDir) {
		try {
						
			File outdir = new File(targetDir);
			
			if (!outdir.exists())
				outdir.mkdirs();
			
			ZipEntry entry = null;
			
			while ((entry = zin.getNextEntry()) != null) {

				String filePath = entry.getName();
				
				if (entry.isDirectory()) {
					mkdirs(outdir, filePath);
					continue;
				}
				
				//Create directories 
				new File(targetDir+"/"+filePath).getParentFile().mkdirs();

				extractFile(zin, outdir, filePath);
			}
			
		} catch (IOException e) {
			logger.log(Level.SEVERE, "Error extracting zip file", e);
			e.printStackTrace();
		}finally{
			try {
				zin.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/********************************************************************************************
	 * 
	 ********************************************************************************************/
	public static void deleteRecursively(File fileOrDirectory){
		
		if (fileOrDirectory.isDirectory()) {
			
			try {
				FileUtils.deleteDirectory(fileOrDirectory);
			} catch (IOException e) {
				logger.log(Level.SEVERE, "Error occured deleting directory '"+fileOrDirectory.getPath()+"'.", e);
				e.printStackTrace();
			}
	
		}
	
	}
	
	/********************************************************************************************
	 * 
	 ********************************************************************************************/
	public static void copyRecursively(String sourceDir,String targetDir){

		try {
			FileUtils.copyDirectory(new File(sourceDir), new File(targetDir));
		} catch (IOException e) {
			logger.log(Level.SEVERE, "Error occured copying directory '"+sourceDir+"' to '"+targetDir+"'.", e);
			e.printStackTrace();
		}
	}
	
	/********************************************************************************************
	 * 
	 ********************************************************************************************/
	public static void copyFile(File sourceFile,String targetFilePath){

		try {
			FileUtils.copyFile(sourceFile, new File(targetFilePath));
		} catch (IOException e) {
			logger.log(Level.SEVERE, "Error occured copying file '"+sourceFile.getPath()+"' to '"+targetFilePath+"'.", e);
			e.printStackTrace();
		}
	}
	
	/********************************************************************************************
	 * 
	 ********************************************************************************************/
	public static void writeStringToFile(String directory,String filename, String fileContent){
		String filepath = directory+"/"+filename;
		
		try {
			Files.createDirectories(Paths.get(directory));
			Files.write(Paths.get(filepath), fileContent.getBytes());

		} catch (IOException e) {
			logger.log(Level.SEVERE, "Error occured writing file '"+filepath+"'.", e);
			e.printStackTrace();
		}

	}
	
	/********************************************************************************************
	 * 
	 ********************************************************************************************/
	public static void replaceInFile(String filepath, String replace, String replacement){
	
		Path path = Paths.get(filepath);
		Charset charset = StandardCharsets.UTF_8;
	
		String content;
		try {
			content = new String(Files.readAllBytes(path), charset);
			content = content.replace(replace, replacement);
			Files.write(path, content.getBytes(charset));
			
		} catch (IOException e) {
			logger.log(Level.SEVERE, "Error replacing in file'"+filepath+"'.", e);
			e.printStackTrace();
		}

	}
	
	/********************************************************************************************
	 * 
	 ********************************************************************************************/
	 public static String generateJSON(Object o) {
        
        return gson.toJson(o);
    }

}