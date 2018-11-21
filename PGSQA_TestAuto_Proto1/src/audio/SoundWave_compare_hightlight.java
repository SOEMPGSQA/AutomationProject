package audio;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import AverMedia_control.File_Fol_control;
import control.directory_parameter;
import image.image;

public class SoundWave_compare_hightlight {
	public static boolean match_audio(String path1, String path2) throws InterruptedException, IOException {
		new java.util.Date();
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		BufferedImage img1 = null;
		BufferedImage img2 = null;
		
		boolean result = false;
		String testID = File_Fol_control.get_testID();
		BufferedWriter out = new BufferedWriter(new FileWriter(testID+"\\log.txt", true));
		BufferedWriter sys_out = new BufferedWriter(new FileWriter(directory_parameter.dir_systemlog()+"\\system_log.txt", true));

		String highest_file_name = "";
		double highest_pass_rate = 0;
		double highest_trend_rate = 0;
		double highest_amplitude_rate = 0;
		double tolerance = 80;
		String Crop_data = "";
		
		int case_num=0;
		List<String> crop_image_list = new ArrayList<String>();		
		try {
			File url1 = new File(path1);
			File url2 = new File(path2);
			img1 = ImageIO.read(url1);
			img2 = ImageIO.read(url2);
		} catch (IOException e) {
			e.printStackTrace();
		}
		int width1 = img1.getWidth(null);
		int width2 = img2.getWidth(null);
		int height1 = img1.getHeight(null);
		int height2 = img2.getHeight(null);
		if ((width1 <= width2) && (height1 != height2)) {
			System.err.println("Error: Original Images dimensions mismatch");
			System.exit(1);
		}
		else if((width1 == width2) && (height1 == height2)) {
			case_num = 0;
			
		}
		else if((width1 >= width2) && (height1 == height2)) {
			case_num = 1;
		}
		
		switch(case_num) {
		case 0:
			Crop_data = wave_hightlight(path1,path2);
			String [] all_rate1 = Crop_data.split(",");
			String pass_rate1 = all_rate1[3];
			highest_pass_rate = Double.parseDouble(pass_rate1);
			break;
			
		case 1:
			try {
				new File(testID+"//audio//temp").mkdirs();
				int width_diff = width1 - width2;
				for (int x=0;x<=width_diff;x++){
					if(x+width2>width1) {
						break;
					}
					image.crop_image(path1,testID+"//audio//temp//00"+(x+1)+".png", x, 0, width2, 500);
					Crop_data = wave_hightlight(testID+"//audio//temp//00"+(x+1)+".png",path2);
					crop_image_list.add(Crop_data);
					String [] all_rate2 = Crop_data.split(",");
					String pass_rate2 = all_rate2[3];
					
					if(Double.parseDouble(pass_rate2) > highest_pass_rate) {
						Thread.sleep(20);
						highest_file_name = (testID+"//audio//temp//00"+(x+1)+".png");
						highest_pass_rate = Double.parseDouble(pass_rate2);
						highest_amplitude_rate =  Double.parseDouble(all_rate2[1]);
						highest_trend_rate =  Double.parseDouble(all_rate2[2]);
						File new_file = new File(testID+"//audio//temp//red_NG.png");
						File old_file = new File(testID+"//audio//temp//red_NG_true.png");
					    if (old_file.exists()) {
					    	old_file.delete();     
					    }
					    new_file.renameTo(old_file);
					}
				}
				
				File red_NG_file = new File(testID+"//audio//temp//red_NG_true.png");
				if(red_NG_file.exists() == true) {
					Thread.sleep(20);
					red_NG_file.renameTo(new File(testID+"//audio//red_NG.png"));
					File index = new File(testID+"//audio//temp//");
					String[]entries = index.list();
					for(String s: entries){
					    File currentFile = new File(index.getPath(),s);
					    currentFile.delete();
					}
					index.delete();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		default:
			System.err.println("Error: match audio case break");
			System.exit(1);
		}
		if (highest_pass_rate>tolerance) {
			result = true;
		}else {
			result = false;
		}
		System.out.println("[AUDIO]File: "+highest_file_name+", Result: "+result);
		System.out.println("[AUDIO]Rate: "+highest_pass_rate+", amplitude rate: "+highest_amplitude_rate+", trend rate :"+highest_trend_rate);
		out.write("[AUDIO]File: "+highest_file_name+", Result: "+result);
		out.newLine();
		out.write("[AUDIO]Rate: "+highest_pass_rate+", amplitude rate: "+highest_amplitude_rate+", trend rate :"+highest_trend_rate);
		out.newLine();
		out.close();
		
		timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		sys_out.write(timeStamp+"[AUDIO]File: "+highest_file_name+", Result: "+result);
		sys_out.newLine();
		sys_out.write(timeStamp+"[AUDIO]Rate: "+highest_pass_rate+", amplitude rate: "+highest_amplitude_rate+", trend rate :"+highest_trend_rate);
		sys_out.newLine();
		sys_out.close();
		return result;
	}
	
	
	
	public static String wave_hightlight(String path1, String path2) throws InterruptedException{
		BufferedImage img1 = null;
		BufferedImage img2 = null;
		
		int frequency_tol = 24;
		String testID = File_Fol_control.get_testID();
		Color myRed = new Color(255, 0, 0);
		Color myBlack = new Color(0, 0, 0);
		int rgb_red = myRed.getRGB();
		int rgb_Black = myBlack.getRGB();
		
		double tolerance = 75;//1.5;
		boolean result = false;
		boolean trend_flag = false;
		boolean frequency_flag = false;
		
		int frequency_count_1_pre = 0;
		int frequency_count_1_cur = 0;
		int frequency_count_2_pre = 0;
		int frequency_count_2_cur = 0;
		int frequency_trend_1 = 0;
		int frequency_trend_2 = 0;
		int frequency_diff = 0;
		
		double amplitude_pass = 0;
		double trend_pass = 0;
		double amplitude_trend_pass = 0;
		
		double frequency_count_OK = 0;
		double frequency_trend_count = 1;
		double frequency_trend_flag_count = 0;
		
		try {
			File url1 = new File(path1);
			File url2 = new File(path2);
			img1 = ImageIO.read(url1);
			img2 = ImageIO.read(url2);
		} catch (IOException e) {
			e.printStackTrace();
		}
		int width1 = img1.getWidth(null);
		int width2 = img2.getWidth(null);
		int height1 = img1.getHeight(null);
		int height2 = img2.getHeight(null);
		if ((width1 != width2) || (height1 != height2)) {
			System.err.println("Error: Images dimensions mismatch");
			System.exit(1);
		}

		for (int x = 0; x < width1; x++) {
			for (int y = 0; y < height1; y++) {
		        int rgb1 = img1.getRGB(x, y);
		        int rgb2 = img2.getRGB(x, y);
//		        out.write("Image 1: ("+x+","+y+") = "+rgb1+" "+r1+" "+g1+" "+b1+" || Image 2: "+x+","+y+") = "+rgb2+" "+r2+" "+g2+" "+b2+" \n");
//			    out.newLine();
		        if(rgb1 != rgb2) {
		        	img1.setRGB(x, y, rgb_red);
		        }
		        if(rgb1 == rgb_Black) {
		        	frequency_count_1_cur++;
		        }
		        if(rgb2 == rgb_Black) {
		        	frequency_count_2_cur++;
		        }  
			}
			frequency_diff = frequency_count_1_cur-frequency_count_2_cur;
			if(frequency_diff < 0) {
				frequency_diff = frequency_diff * -1;
			}
			if(frequency_diff< frequency_tol) {
				frequency_count_OK++;
				frequency_flag = true;
			}
			
			if(x>=1) {
				if(frequency_count_1_cur > frequency_count_1_pre) {
					frequency_trend_1 = 1 ;
				}
				else if(frequency_count_1_cur < frequency_count_1_pre) {
					frequency_trend_1 = 2;
				}
				else{
					frequency_trend_1 = 3;
				}
				if(frequency_count_2_cur > frequency_count_2_pre) {
					frequency_trend_2 = 1;
				}
				else if(frequency_count_2_cur < frequency_count_2_pre) {
					frequency_trend_2 = 2;
				}
				else{
					frequency_trend_2 = 3;
				}
//				if(frequency_trend_1 == frequency_trend_2 || (frequency_trend_1 == 3 || frequency_trend_2 == 3)) {
				if(frequency_trend_1 == frequency_trend_2) {
					frequency_trend_count++;
					trend_flag = true;
				}
//				System.out.println("1_pre = "+frequency_count_1_pre+", 1_cur = "+frequency_count_1_cur+" || 2_pre = "+frequency_count_2_pre+", 2_cur = "+frequency_count_2_cur);
//				System.out.println("1_tren = "+frequency_trend_1+" || 2_tren = "+frequency_trend_2);
			}
			
			if(frequency_flag == true || trend_flag == true ) {
				frequency_trend_flag_count++;
			}
			frequency_count_1_pre = frequency_count_1_cur;
			frequency_count_2_pre = frequency_count_2_cur;
			frequency_count_1_cur=0;
			frequency_count_2_cur=0;
			frequency_flag = false;
			trend_flag = false;
		}
	
		amplitude_pass = (frequency_count_OK / width1)*100;
		trend_pass = (frequency_trend_count / width1)*100;
		amplitude_trend_pass = (frequency_trend_flag_count / width1)*100;
		String amplitude_trend_info = path2+","+amplitude_pass+","+trend_pass+","+amplitude_trend_pass;
		
//		System.out.println("Frequency pitch pass rate: " +frequency_count_OK+ " / "+width1+"= "+ amplitude_pass);
//		System.out.println("Frequency trend pass rate: " +frequency_trend_count+" / "+width1+"= "+ trend_pass);
//		System.out.println("Frequency pitch & trend pass rate: " +frequency_trend_flag_count+" / "+width1+"= "+ amplitude_trend_pass);
		
		if(((frequency_trend_flag_count / width1)*100) > tolerance) {
			result = true;
		}else {
			result = false;
		}
		
	    try {
	    	File outputfile = new File(testID+"\\audio\\temp\\red_NG.png");
	    	outputfile.getParentFile().mkdirs();
			ImageIO.write(img1, "png", outputfile);
			Thread.sleep(200);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return amplitude_trend_info;
	}	
}
