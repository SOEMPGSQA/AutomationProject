package test_case;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import AverMedia_control.AverMedia_control;
import AverMedia_control.File_Fol_control;
import control.directory_parameter;
import image.audio_image;
import image.image_RGB_compare;
import image.template_match_RGB;
import rmt_control.HASFY18_RMT_AM420;
import rmt_control.UsbUirt;

public class HAS_test_001 {
	public static boolean cd_playback_control_play_01() throws InterruptedException, IOException {
		boolean result = false;
		int OK_counter = 0;

		String test_ID = File_Fol_control.create_test_fol();
		BufferedWriter out = new BufferedWriter(new FileWriter(test_ID+"\\log.txt", true));
		out.write("[TEST] cd_playback_control_play_01 \n");
    	out.newLine();
		String img_dir_1 = AverMedia_control.capture_image_single_dir();
		AverMedia_control.record_video_start();
		
		try {
			UsbUirt.UIRT_Transmit(HASFY18_RMT_AM420.PLAY());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Thread.sleep(3500);
		String img_dir_2 = AverMedia_control.capture_image_single_dir();

		if(image_RGB_compare.image_compare_tol(img_dir_1, img_dir_2) == true){
			out.write("[IMAGE_RGB]State change image failed. \n");
		    out.newLine();
		}
		else if(image_RGB_compare.image_compare_tol(img_dir_1, img_dir_2) == false){
			out.write("[IMAGE_RGB]State change OK. \n");
		    out.newLine();
		    OK_counter++;
		    
		    Thread.sleep(5000);
		    if(template_match_RGB.image_compare(img_dir_2, directory_parameter.HAS_CDDA_play_image()) == true){
		    	OK_counter++;
		    	out.write("[TEMPLATE_RGB][CDDA_PLAY]Template matched 100%. \n");
		    	out.newLine();
		    } 
		    else if(template_match_RGB.image_compare_tol(img_dir_2,  directory_parameter.HAS_CDDA_play_image()) == true){
		    	OK_counter++;
		    	out.write("[TEMPLATE_RGB][CDDA_PLAY]Template matched (with tolerance). \n");
		    	out.newLine();
		    }
		    else{
		    	out.write("[TEMPLATE_RGB][CDDA_PLAY]Template does not match. \n");
		    }
		    
		}
		String video = AverMedia_control.record_video_end_dir();
		File vFile = new File(video);
		vFile.delete();
		audio_image.remove_mute();
		if(audio_image.check_mute() == true){
	    	OK_counter++;
	    	out.write("[AUDIO_MUTE]No Mute found. \n");
	    	out.newLine();
	    }
		
//		System.out.println("Counter: "+OK_counter);
		if(OK_counter>=3) {
			result = true;
		}else {
			result = false;
		}
		out.write("[RESULT][CDDA_PLAY]Test Result: "+result+" \n");
		System.out.println("[RESULT][CDDA_PLAY]Test Result: "+result);
		out.close();
		return result;
	}
	
	public static boolean cd_playback_control_FF_02() throws InterruptedException, IOException {
		boolean result = false;
		int OK_counter = 0;
		boolean precon_flag = false;

		String test_ID = File_Fol_control.create_test_fol();
		BufferedWriter out = new BufferedWriter(new FileWriter(test_ID+"\\log.txt", true));
		out.write("[TEST]cd_playback_control_FF_02 \n");
    	out.newLine();
		
		String img_dir_1 = AverMedia_control.capture_image_single_dir();
		
		if(template_match_RGB.image_compare(img_dir_1, directory_parameter.HAS_CDDA_play_image()) == true){
			precon_flag = true;
	    	out.write("[TEMPLATE_RGB][CDDA_PLAY]Template matched 100%. Pre-con OK \n");
	    	out.newLine();
	    }
	    else if(template_match_RGB.image_compare_tol(img_dir_1, directory_parameter.HAS_CDDA_play_image()) == true){
	    	precon_flag = true;
	    	out.write("[TEMPLATE_RGB][CDDA_PLAY]Template matched (with tolerance). Pre-con OK  \n");
	    	out.newLine();
	    }
	    else{
	    	precon_flag = false;
	    	out.write("[TEMPLATE_RGB][CDDA_PLAY]Template does not match. Pre-con NG \n");
	    }
		
		if(precon_flag == true) {
			AverMedia_control.record_video_start();
			img_dir_1 = AverMedia_control.capture_image_single_dir();
			try {
				UsbUirt.UIRT_Transmit(HASFY18_RMT_AM420.FASTFORWARD());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Thread.sleep(1000);
			String img_dir_2 = AverMedia_control.capture_image_single_dir();
	
			if(image_RGB_compare.image_compare_tol(img_dir_1, img_dir_2) == true){
				out.write("[IMAGE_RGB]State change image failed. \n");
			    out.newLine();
			}
			else if(image_RGB_compare.image_compare_tol(img_dir_1, img_dir_2) == false){
				out.write("[IMAGE_RGB]State change OK. \n");
			    out.newLine();
			    OK_counter++;
			    
			    Thread.sleep(5000);
			    if(template_match_RGB.image_compare(img_dir_2,  directory_parameter.HAS_CDDA_FFx2_image()) == true){
			    	OK_counter++;
			    	out.write("[TEMPLATE_RGB][CDDA_FFx2]Template matched 100%. \n");
			    	out.newLine();
			    }
			    else if(template_match_RGB.image_compare_tol(img_dir_2, directory_parameter.HAS_CDDA_FFx2_image()) == true){
			    	OK_counter++;
			    	out.write("[TEMPLATE_RGB][CDDA_FFx2]Template matched (with tolerance). \n");
			    	out.newLine();
			    }
			    else{
			    	out.write("[TEMPLATE_RGB]Template does not match. \n");
			    }
			    
			}
			String video = AverMedia_control.record_video_end_dir();
			File vFile = new File(video);
			vFile.delete();
			audio_image.remove_mute();
		}
//		System.out.println("Counter: "+OK_counter);
		if(OK_counter>=2) {
			result = true;
		}else {
			result = false;
		}
		out.write("[RESULT]Test Result PLAY: "+result+" \n");
		System.out.println("[RESULT]Test Result PLAY: "+result);
		out.close();
		
		return result;
	}
	
	public static boolean cd_playback_control_FF_03() throws InterruptedException, IOException {
		boolean result = false;
		int OK_counter = 0;
		boolean precon_flag = false;

		String test_ID = File_Fol_control.create_test_fol();
		BufferedWriter out = new BufferedWriter(new FileWriter(test_ID+"\\log.txt", true));
		out.write("[TEST]cd_playback_control_FF_03 \n");
    	out.newLine();
		
		String img_dir_1 = AverMedia_control.capture_image_single_dir();
		
		if(template_match_RGB.image_compare(img_dir_1, directory_parameter.HAS_CDDA_FFx2_image()) == true){
			precon_flag = true;
	    	out.write("[TEMPLATE_RGB][CDDA_FFx2]Template matched 100%. Pre-con OK \n");
	    	out.newLine();
	    }
	    else if(template_match_RGB.image_compare_tol(img_dir_1, directory_parameter.HAS_CDDA_FFx2_image()) == true){
	    	precon_flag = true;
	    	out.write("[TEMPLATE_RGB][CDDA_FFx2]Template matched (with tolerance). Pre-con OK  \n");
	    	out.newLine();
	    }
	    else{
	    	precon_flag = false;
	    	out.write("[TEMPLATE_RGB][CDDA_FFx2]Template does not match. Pre-con NG \n");
	    }
		
		if(precon_flag == true) {
			AverMedia_control.record_video_start();
			img_dir_1 = AverMedia_control.capture_image_single_dir();
			try {
				UsbUirt.UIRT_Transmit(HASFY18_RMT_AM420.FASTFORWARD());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Thread.sleep(1000);
			String img_dir_2 = AverMedia_control.capture_image_single_dir();
	
			if(image_RGB_compare.image_compare_tol(img_dir_1, img_dir_2) == true){
				out.write("[IMAGE_RGB]State change image failed. \n");
			    out.newLine();
			}
			else if(image_RGB_compare.image_compare_tol(img_dir_1, img_dir_2) == false){
				out.write("[IMAGE_RGB]State change OK. \n");
			    out.newLine();
			    OK_counter++;
			    
			    Thread.sleep(5000);
			    if(template_match_RGB.image_compare(img_dir_2,  directory_parameter.HAS_CDDA_FF1_image()) == true){
			    	OK_counter++;
			    	out.write("[TEMPLATE_RGB][CDDA_FF1]Template matched 100%. \n");
			    	out.newLine();
			    }
			    else if(template_match_RGB.image_compare_tol(img_dir_2, directory_parameter.HAS_CDDA_FF1_image()) == true){
			    	OK_counter++;
			    	out.write("[TEMPLATE_RGB][CDDA_FF1]Template matched (with tolerance). \n");
			    	out.newLine();
			    }
			    else{
			    	out.write("[TEMPLATE_RGB]Template does not match. \n");
			    }
			    
			}
			String video = AverMedia_control.record_video_end_dir();
			File vFile = new File(video);
			vFile.delete();
			audio_image.remove_mute();
		}
//		System.out.println("Counter: "+OK_counter);
		if(OK_counter>=2) {
			result = true;
		}else {
			result = false;
		}
		out.write("[RESULT]Test Result PLAY: "+result+" \n");
		System.out.println("[RESULT]Test Result PLAY: "+result);
		out.close();
		
		return result;
	}
}
