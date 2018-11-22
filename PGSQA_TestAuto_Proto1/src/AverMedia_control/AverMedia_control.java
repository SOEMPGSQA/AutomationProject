package AverMedia_control;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import audio.audio_to_SoundWave;
import control.directory_parameter;
import image.image;

public class AverMedia_control {
	public static void capture_image_single() {
		String dir_running = directory_parameter.running_dir();
		String dir_actual = File_Fol_control.main_test_ID;
		
		int fol_count = File_Fol_control.folder_count(dir_actual);
		int file_count = File_Fol_control.file_count(dir_actual+"\\test_"+fol_count+"\\image");
		
		try {
			Runtime.getRuntime().exec("library\\AutoIT\\Click_singleCapture.exe");
			Thread.sleep(1000);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		File[] listOfFiles = new File(dir_running).listFiles();
	    for (int i = 0; i < listOfFiles.length; i++) {
	    	if (listOfFiles[i].isFile() && listOfFiles[i].toString().substring(listOfFiles[i].toString().length() - 3).equals("png")) {
	    		listOfFiles[i].renameTo(new File(dir_actual+"\\test_"+fol_count+"\\image\\actual_image_"+(file_count+1)+".png"));
	    		listOfFiles[i].delete();
	    	} 
	    }
	}
	
	public static String capture_image_single_dir() {
		String dir_running = directory_parameter.running_dir();
		String dir_actual = File_Fol_control.main_test_ID;
		String image_dir = "";
		
		int fol_count = File_Fol_control.folder_count(dir_actual);
		int file_count = File_Fol_control.file_count(dir_actual+"\\test_"+fol_count+"\\image");
		
		try {
			Runtime.getRuntime().exec("library\\AutoIT\\Click_singleCapture.exe");
			Thread.sleep(1000);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		File[] listOfFiles = new File(dir_running).listFiles();
	    for (int i = 0; i < listOfFiles.length; i++) {
	    	if (listOfFiles[i].isFile() && listOfFiles[i].toString().substring(listOfFiles[i].toString().length() - 3).equals("png")) {
	    		listOfFiles[i].renameTo(new File(dir_actual+"\\test_"+fol_count+"\\image\\actual_image_"+(file_count+1)+".png"));
	    		listOfFiles[i].delete();
	    		image_dir = dir_actual+"\\test_"+fol_count+"\\image\\actual_image_"+(file_count+1)+".png";
	    	} 
	    }
	    return image_dir;
	}
	
	public static String capture_image_track_dir() throws IOException {
		String dir_running = directory_parameter.running_dir();
		String dir_actual = File_Fol_control.main_test_ID;
		String image_dir = "";
		
		int fol_count = File_Fol_control.folder_count(dir_actual);
		int file_count = File_Fol_control.file_count(dir_actual+"\\test_"+fol_count+"\\image");
		
		try {
			Runtime.getRuntime().exec("library\\AutoIT\\Click_singleCapture.exe");
			Thread.sleep(1000);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		File[] listOfFiles = new File(dir_running).listFiles();
	    for (int i = 0; i < listOfFiles.length; i++) {
	    	if (listOfFiles[i].isFile() && listOfFiles[i].toString().substring(listOfFiles[i].toString().length() - 3).equals("png")) {
	    		listOfFiles[i].renameTo(new File(dir_actual+"\\test_"+fol_count+"\\image\\actual_image_"+(file_count+1)+".png"));
	    		listOfFiles[i].delete();
	    		image_dir = dir_actual+"\\test_"+fol_count+"\\image\\actual_image_"+(file_count+1)+".png";
	    	} 
	    }
	    image.crop_image(image_dir, dir_actual+"\\test_"+fol_count+"\\image\\actual_image_"+(file_count+1)+"_track.png", 207, 84, 732, 32);
	    image_dir = dir_actual+"\\test_"+fol_count+"\\image\\actual_image_"+(file_count+1)+"_track.png";
	    return image_dir;
	}
	public static String capture_image_album_dir() throws IOException {
		String dir_running = directory_parameter.running_dir();
		String dir_actual = File_Fol_control.main_test_ID;
		String image_dir = "";
		
		int fol_count = File_Fol_control.folder_count(dir_actual);
		int file_count = File_Fol_control.file_count(dir_actual+"\\test_"+fol_count+"\\image");
		
		try {
			Runtime.getRuntime().exec("library\\AutoIT\\Click_singleCapture.exe");
			Thread.sleep(1000);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		File[] listOfFiles = new File(dir_running).listFiles();
	    for (int i = 0; i < listOfFiles.length; i++) {
	    	if (listOfFiles[i].isFile() && listOfFiles[i].toString().substring(listOfFiles[i].toString().length() - 3).equals("png")) {
	    		listOfFiles[i].renameTo(new File(dir_actual+"\\test_"+fol_count+"\\image\\actual_image_"+(file_count+1)+".png"));
	    		listOfFiles[i].delete();
	    		image_dir = dir_actual+"\\test_"+fol_count+"\\image\\actual_image_"+(file_count+1)+".png";
	    	} 
	    }
	    image.crop_image(image_dir, dir_actual+"\\test_"+fol_count+"\\image\\actual_image_"+(file_count+1)+"_album.png", 207, 118, 732, 32);
	    image_dir = dir_actual+"\\test_"+fol_count+"\\image\\actual_image_"+(file_count+1)+"_album.png";
	    return image_dir;
	}
	public static String capture_image_artist_dir() throws IOException {
		String dir_running = directory_parameter.running_dir();
		String dir_actual = File_Fol_control.main_test_ID;
		String image_dir = "";
		
		int fol_count = File_Fol_control.folder_count(dir_actual);
		int file_count = File_Fol_control.file_count(dir_actual+"\\test_"+fol_count+"\\image");
		
		try {
			Runtime.getRuntime().exec("library\\AutoIT\\Click_singleCapture.exe");
			Thread.sleep(1000);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		File[] listOfFiles = new File(dir_running).listFiles();
	    for (int i = 0; i < listOfFiles.length; i++) {
	    	if (listOfFiles[i].isFile() && listOfFiles[i].toString().substring(listOfFiles[i].toString().length() - 3).equals("png")) {
	    		listOfFiles[i].renameTo(new File(dir_actual+"\\test_"+fol_count+"\\image\\actual_image_"+(file_count+1)+".png"));
	    		listOfFiles[i].delete();
	    		image_dir = dir_actual+"\\test_"+fol_count+"\\image\\actual_image_"+(file_count+1)+".png";
	    	} 
	    }
	    image.crop_image(image_dir, dir_actual+"\\test_"+fol_count+"\\image\\actual_image_"+(file_count+1)+"_artist.png", 207, 153, 732, 32);
	    image_dir = dir_actual+"\\test_"+fol_count+"\\image\\actual_image_"+(file_count+1)+"_artist.png";
	    return image_dir;
	}
	
	public static String capture_image_bitrate_dir() throws IOException {
		String dir_running = directory_parameter.running_dir();
		String dir_actual = File_Fol_control.main_test_ID;
		String image_dir = "";
		
		int fol_count = File_Fol_control.folder_count(dir_actual);
		int file_count = File_Fol_control.file_count(dir_actual+"\\test_"+fol_count+"\\image");
		
		try {
			Runtime.getRuntime().exec("library\\AutoIT\\Click_singleCapture.exe");
			Thread.sleep(1000);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		File[] listOfFiles = new File(dir_running).listFiles();
	    for (int i = 0; i < listOfFiles.length; i++) {
	    	if (listOfFiles[i].isFile() && listOfFiles[i].toString().substring(listOfFiles[i].toString().length() - 3).equals("png")) {
	    		listOfFiles[i].renameTo(new File(dir_actual+"\\test_"+fol_count+"\\image\\actual_image_"+(file_count+1)+".png"));
	    		listOfFiles[i].delete();
	    		image_dir = dir_actual+"\\test_"+fol_count+"\\image\\actual_image_"+(file_count+1)+".png";
	    	} 
	    }
	    image.crop_image(image_dir, dir_actual+"\\test_"+fol_count+"\\image\\actual_image_"+(file_count+1)+"_bitrate.png", 513, 52, 100, 32);
	    image_dir = dir_actual+"\\test_"+fol_count+"\\image\\actual_image_"+(file_count+1)+"_bitrate.png";
	    return image_dir;
	}
	
	public static String capture_image_codec_dir() throws IOException {
		String dir_running = directory_parameter.running_dir();
		String dir_actual = File_Fol_control.main_test_ID;
		String image_dir = "";
		
		int fol_count = File_Fol_control.folder_count(dir_actual);
		int file_count = File_Fol_control.file_count(dir_actual+"\\test_"+fol_count+"\\image");
		
		try {
			Runtime.getRuntime().exec("library\\AutoIT\\Click_singleCapture.exe");
			Thread.sleep(1000);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		File[] listOfFiles = new File(dir_running).listFiles();
	    for (int i = 0; i < listOfFiles.length; i++) {
	    	if (listOfFiles[i].isFile() && listOfFiles[i].toString().substring(listOfFiles[i].toString().length() - 3).equals("png")) {
	    		listOfFiles[i].renameTo(new File(dir_actual+"\\test_"+fol_count+"\\image\\actual_image_"+(file_count+1)+".png"));
	    		listOfFiles[i].delete();
	    		image_dir = dir_actual+"\\test_"+fol_count+"\\image\\actual_image_"+(file_count+1)+".png";
	    	} 
	    }
	    image.crop_image(image_dir, dir_actual+"\\test_"+fol_count+"\\image\\actual_image_"+(file_count+1)+"_codec.png", 621, 52, 100, 32);
	    image_dir = dir_actual+"\\test_"+fol_count+"\\image\\actual_image_"+(file_count+1)+"_codec.png";
	    return image_dir;
	}
	
	public static void crop_image_ch1(String input_path, String output_path) throws IOException{
		String inFolder = input_path;
	    BufferedImage image = ImageIO.read(new File(inFolder));
	    BufferedImage out = image.getSubimage(0, 0, 960, 540);
	    ImageIO.write(out, "png", new File(output_path));
	}
	
	public static void crop_image_ch2(String input_path, String output_path) throws IOException{
		String inFolder = input_path;
	    BufferedImage image = ImageIO.read(new File(inFolder));
	    BufferedImage out = image.getSubimage(961, 0, 1920, 540);
	    ImageIO.write(out, "png", new File(output_path));
	}
	
	public static void crop_image_ch3(String input_path, String output_path) throws IOException{
		String inFolder = input_path;
	    BufferedImage image = ImageIO.read(new File(inFolder));
	    BufferedImage out = image.getSubimage(0, 541, 960, 1080);
	    ImageIO.write(out, "png", new File(output_path));
	}
	
	public static void crop_image_ch4(String input_path, String output_path) throws IOException{
		String inFolder = input_path;
	    BufferedImage image = ImageIO.read(new File(inFolder));
	    BufferedImage out = image.getSubimage(961, 541, 1920, 1080);
	    ImageIO.write(out, "png", new File(output_path));
	}
	
	public static void record_video_start() {
		try {
			Runtime.getRuntime().exec("library\\AutoIT\\Click_recordVideo.exe");
			Thread.sleep(1000);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void record_video_end() throws InterruptedException {
		String dir_running = directory_parameter.running_dir();
		String dir_actual = File_Fol_control.main_test_ID;
		String video_dir = "";
		String audio_dir = "";
		int fol_count = File_Fol_control.folder_count(dir_actual);
		int file_count = File_Fol_control.file_count(dir_actual+"\\test_"+fol_count+"\\video");
		
		try {
			Runtime.getRuntime().exec("library\\AutoIT\\Click_recordVideo.exe");
			Thread.sleep(1000);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		File[] listOfFiles = new File(dir_running).listFiles();
	    for (int i = 0; i < listOfFiles.length; i++) {
	    	if (listOfFiles[i].isFile() && listOfFiles[i].toString().substring(listOfFiles[i].toString().length() - 3).equals("avi")) {
	    		listOfFiles[i].renameTo(new File(dir_actual+"\\test_"+fol_count+"\\video\\actual_video_"+(file_count+1)+".avi"));
	    		video_dir = dir_actual+"\\test_"+fol_count+"\\video\\actual_video_"+(file_count+1)+".avi";
	    		audio_dir = dir_actual+"\\test_"+fol_count+"\\audio\\actual_audio_"+(file_count+1)+".wav";
	    		listOfFiles[i].delete();
	    	} 
	    }
	    Thread.sleep(2000);
	    audio_to_SoundWave.extractWAVfromAVI(video_dir, audio_dir);
	    audio_to_SoundWave.convert_WAVaudio_to_image(audio_dir, audio_dir.substring(0, audio_dir.length() - 4)+"_wave.png");
	}
	
	public static String record_video_end_dir() {
		String dir_running = directory_parameter.running_dir();
		String dir_actual = File_Fol_control.main_test_ID;
		String video_dir = "";
		String audio_dir = "";
		
		int fol_count = File_Fol_control.folder_count(dir_actual);
		int file_count = File_Fol_control.file_count(dir_actual+"\\test_"+fol_count+"\\video");
		
		try {
			Runtime.getRuntime().exec("library\\AutoIT\\Click_recordVideo.exe");
			Thread.sleep(1000);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		File[] listOfFiles = new File(dir_running).listFiles();
	    for (int i = 0; i < listOfFiles.length; i++) {
	    	if (listOfFiles[i].isFile() && listOfFiles[i].toString().substring(listOfFiles[i].toString().length() - 3).equals("avi")) {
	    		listOfFiles[i].renameTo(new File(dir_actual+"\\test_"+fol_count+"\\video\\actual_video_"+(file_count+1)+".avi"));
	    		listOfFiles[i].delete();
	    		video_dir = dir_actual+"\\test_"+fol_count+"\\video\\actual_video_"+(file_count+1)+".avi";
	    		audio_dir = dir_actual+"\\test_"+fol_count+"\\audio\\actual_audio_"+(file_count+1)+".wav";
	    	} 
	    }
	    
	    try {
	    	audio_to_SoundWave.extractWAVfromAVI(video_dir, audio_dir);
			Thread.sleep(1000);
		    audio_to_SoundWave.convert_WAVaudio_to_image(audio_dir, audio_dir.substring(0, audio_dir.length() - 4)+"_wave.png");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return video_dir;
	}
}
