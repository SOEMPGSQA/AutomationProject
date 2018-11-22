package AverMedia_control;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import control.directory_parameter;

public class File_Fol_control {
	public static String main_test_ID = directory_parameter.actual_result_dir();
	
	public static int file_count(String dir) {
		String dir_path = dir;	
		int file_counter = 0;
		File[] listOfFiles = new File(dir_path).listFiles();

	    for (int i = 0; i < listOfFiles.length; i++) {
	    	if (listOfFiles[i].isFile()) {
	    		file_counter++;
	    	} 
	    }
	    return file_counter;
	}
	
	public static int folder_count(String dir) {
		String dir_path = dir;
		int fol_counter = 0;
		File[] listOfFiles = new File(dir_path).listFiles();
		
	    for (int i = 0; i < listOfFiles.length; i++) {
	    	if (listOfFiles[i].isDirectory()) {
	    		fol_counter++;
	    	} 
	    }
		return fol_counter;
	}
	
	public static String create_test_fol() {
		String dir = directory_parameter.actual_result_dir();
		String test_ID_dir = "";
		int fol_count = folder_count(dir);
		
		new File(dir+"//test_"+(fol_count+1)).mkdirs();
		new File(dir+"//test_"+(fol_count+1)+"//image").mkdirs();
		new File(dir+"//test_"+(fol_count+1)+"//audio").mkdirs();
		new File(dir+"//test_"+(fol_count+1)+"//video").mkdirs();

		try {
			File logFile = new File(dir+"//test_"+(fol_count+1)+"//log.txt");
			logFile.createNewFile(); // if file already exists will do nothing 
			FileOutputStream oFile = new FileOutputStream(logFile, false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		test_ID_dir = dir+"\\test_"+(fol_count+1);
		return test_ID_dir;
	}
	
	
	public static String create_test_mainFol(String test_name) {
		String test_ID_dir = "";
		String dir = directory_parameter.actual_result_dir();
		new java.util.Date();
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		test_ID_dir = dir+"//"+timeStamp+"_test_"+(test_name);
		new File(test_ID_dir).mkdirs();
		main_test_ID = test_ID_dir;
		return test_ID_dir;
	}
	
	public static String create_test_subFol() {
		String test_ID_dir = "";
		String dir = directory_parameter.actual_result_dir();
		int fol_count = folder_count(main_test_ID);
		
		new File(main_test_ID+"//test_"+(fol_count+1)).mkdirs();
		new File(main_test_ID+"//test_"+(fol_count+1)+"//image").mkdirs();
		new File(main_test_ID+"//test_"+(fol_count+1)+"//audio").mkdirs();
		new File(main_test_ID+"//test_"+(fol_count+1)+"//video").mkdirs();

		try {
			File logFile = new File(main_test_ID+"//test_"+(fol_count+1)+"//log.txt");
			logFile.createNewFile(); // if file already exists will do nothing 
			FileOutputStream oFile = new FileOutputStream(logFile, false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		test_ID_dir = main_test_ID+"\\test_"+(fol_count+1);
		return test_ID_dir;
	}
	
	public static String get_testID() {
		String dir = main_test_ID;
		String test_ID_dir = "";
		int fol_count = folder_count(dir);
		
		test_ID_dir = dir+"\\test_"+(fol_count);
		return test_ID_dir;
	}
	
	public static void clear_runningFol() {
		File index = new File(directory_parameter.running_dir());
		String[]entries = index.list();
		for(String s: entries){
		    File currentFile = new File(index.getPath(),s);
		    currentFile.delete();
		}
	}

	public static void merge_log() throws IOException {
		String dir = directory_parameter.actual_result_dir();
        Path output = Paths.get(dir+"\\all_log.txt");
        
        String dir_path = dir;

		File[] listOfFiles = new File(dir_path).listFiles();
//		List<String> folderName = new ArrayList();
		List<Path> x = Files.list(Paths.get(dir))
	            .filter(p -> Files.exists(p))
	            .map(s -> s.getFileName())
	            .sorted()
	            .collect(Collectors.toList());

		x.forEach(System.out::println);
        
//        File[] directories = new File(dir).listFiles(File::isDirectory);
//        Arrays.sort(directories);
        for(int i=0;i<listOfFiles.length;i++) {
        	System.out.println(listOfFiles[i].toString());
//            Path directory = Paths.get(dir);
//            Stream<Path> filesToProcess = Files.list(directory);
//            
//	        // Iterate all files
//	        filesToProcess.forEach(path -> {
//	            // Get all lines of that file
//	            Stream<String> lines;
//				try {
//					lines = Files.lines(path);
//	
//	            // Iterate all lines
//	            lines.forEach(line -> {
//	                // Append the line separator
//	                String lineToWrite = line + System.lineSeparator();
//	
//	                // Write every line to the output file
//	                try {
//						Files.write(output, lineToWrite.getBytes(StandardCharsets.UTF_8));
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//	            });
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//	        });
        }
	}
}
