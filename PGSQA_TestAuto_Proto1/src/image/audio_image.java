package image;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import AverMedia_control.File_Fol_control;
import control.directory_parameter;

public class audio_image {
	public static void remove_mute() throws IOException {
		String test_ID = File_Fol_control.get_testID();
		int x1 = 0;
		int x2 = 0;
		int y = 251;
		BufferedImage img1 = null;
		try {
			File url1 = new File(test_ID+"\\audio\\actual_audio_1_wave.png");
			img1 = ImageIO.read(url1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		int width1 = img1.getWidth(null);
		
		for(int x=0; x<width1; x++) {
			int rgb1 = img1.getRGB(x, y);
	        if (rgb1 != -1) {
	        	x1 = x;
	        	break;
	        }
		}
		x2 = width1 - x1;
		image.crop_image(test_ID+"\\audio\\actual_audio_1_wave.png", test_ID+"\\audio\\audio_1_wave.png", x1, 0, x2, 500);
		
		try {
			File url1 = new File(test_ID+"\\audio\\audio_1_wave.png");
			img1 = ImageIO.read(url1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		width1 = img1.getWidth(null);
		boolean break_flag = false;
		
		for(int x=(width1-1); x>1; x--) {
			int rgb1 = img1.getRGB(x, y);
	        if (rgb1 != -1 && x == (width1-1)) {
	        	x2 = img1.getWidth(null);
	        	break;
	        }
	        else if(rgb1 == -1) {
	        	x2 = x;
	        	break_flag = true;
	        }
	        else if(break_flag==true && rgb1 != -1) {
	        	break;
	        }
	        if(x2 == 0) {
	        	x2 = img1.getWidth(null);
	        }
		}
		image.crop_image(test_ID+"\\audio\\audio_1_wave.png", test_ID+"\\audio\\audio_1_wave.png", 0, 0, x2, 500);
	}
	
	public static void remove_mute_head(String path1, String path2) throws IOException {
		int x1 = 0;
		int x2 = 0;
		int y = 251;
		BufferedImage img1 = null;
		try {
			File url1 = new File(path1);
			img1 = ImageIO.read(url1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		int width1 = img1.getWidth(null);
		
		for(int x=0; x<width1; x++) {
			int rgb1 = img1.getRGB(x, y);
	        if (rgb1 != -1) {
	        	x1 = x;
	        	break;
	        }
		}
		x2 = width1 - x1;
		image.crop_image(path1, path2, x1, 0, x2, 500);
	}
	
	public static void remove_mute_tail(String path1, String path2) throws IOException {
		int x1 = 0;
		int x2 = 0;
		int y = 251;
		BufferedImage img1 = null;
		try {
			File url1 = new File(path1);
			img1 = ImageIO.read(url1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		int width1 = img1.getWidth(null);
		
		boolean break_flag = false;
		for(int x=(width1-1); x>1; x--) {
			int rgb1 = img1.getRGB(x, y);
	        if (rgb1 != -1 && x == (width1-1)) {
	        	x2 = img1.getWidth(null);
	        	break;
	        }
	        else if(rgb1 == -1) {
	        	x2 = x;
	        	break_flag = true;
	        }
	        else if(break_flag==true && rgb1 != -1) {
	        	break;
	        }
	        if(x2 == 0) {
	        	x2 = img1.getWidth(null);
	        }
		}
		image.crop_image(path1, path2, 0, 0, x2, 500);
	}
	
	public static boolean check_mute() throws IOException {
		new java.util.Date();
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		boolean result = false;
		String test_ID = File_Fol_control.get_testID();
		int counter = 0;
		int tolerance = 1;
		BufferedImage img1 = null;
		BufferedWriter out = new BufferedWriter(new FileWriter(test_ID+"\\log.txt", true));
		BufferedWriter sys_out = new BufferedWriter(new FileWriter(directory_parameter.dir_systemlog()+"\\system_log.txt", true));

		try {
			File url1 = new File(test_ID+"\\audio\\audio_1_wave.png");
			img1 = ImageIO.read(url1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		int width1 = img1.getWidth(null);
		
		for(int x=0; x<width1; x++) {
			for(int y=0; y<500; y++) {
				int rgb1 = img1.getRGB(x, y);
		        if (rgb1 != -1) {
		        	counter = counter + 1;
		        }
			}
			if(counter >= tolerance) {
				result = true;
			}else {
				result = false;
				break;
			}
			counter = 0;
		}
		out.write("[AUDIO_MUTE]Mute check: "+result+"\n");
    	out.newLine();
    	out.close();
    	timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
    	sys_out.write(timeStamp+"[AUDIO_MUTE]Mute check: "+result+"\n");
    	sys_out.newLine();
    	sys_out.close();
//		System.out.println("Mute check OK: "+result);
		return result;
	}
	
}
