package test_case;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import AverMedia_control.AverMedia_control;
import AverMedia_control.File_Fol_control;
import audio.SoundWave_compare_hightlight;
import control.directory_parameter;
import image.OCR_Tesseract;
import image.audio_image;
import image.image_RGB_compare;
import image.template_match_RGB;
import rmt_control.HASFY18_RMT_AM420;
import rmt_control.UsbUirt;

public class HAS_test_demo_01 {
	public static boolean cd_playback_control_play_01_demo() throws InterruptedException, IOException {
		new java.util.Date();
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		boolean result = false;
		boolean state_change = false;
		int OK_counter = 0;
		int state_change_counter = 0;

		File_Fol_control.clear_runningFol();
		String test_ID = File_Fol_control.create_test_subFol();
		Thread.sleep(500);
		String testID = File_Fol_control.get_testID();
		BufferedWriter out = new BufferedWriter(new FileWriter(testID+"\\log.txt", true));
		BufferedWriter sys_out = new BufferedWriter(new FileWriter(directory_parameter.dir_systemlog()+"\\system_log.txt", true));

		out.write(timeStamp+" [TEST] cd_playback_control_play_01");
    	out.newLine();
    	out.flush();
    	sys_out.write(timeStamp+" [TEST] cd_playback_control_play_01");
    	sys_out.newLine();
    	sys_out.flush();
    	
		String img_dir_1 = AverMedia_control.capture_image_single_dir();
		Thread.sleep(300);
		AverMedia_control.record_video_start();
		
		try {
			UsbUirt.UIRT_Transmit(HASFY18_RMT_AM420.PLAY());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Thread.sleep(1200);
		String img_dir_2 = AverMedia_control.capture_image_single_dir();
		
		do {		
			Thread.sleep(1200);
			if(image_RGB_compare.image_compare_tol(img_dir_1, img_dir_2) == true){
				out.write("[IMAGE_RGB]State change image failed, count:"+state_change_counter);
			    out.newLine();
			    out.flush();
			    timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		    	sys_out.write(timeStamp+"[IMAGE_RGB]State change image failed, count:"+state_change_counter);
		    	sys_out.newLine();
		    	sys_out.flush();
			}else {
				state_change = true;
			}
			if(state_change_counter>=3) {
				out.write("[IMAGE_RGB]State change image failed.");
		    	out.newLine();
		    	out.flush();
		    	timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		    	sys_out.write(timeStamp+"[IMAGE_RGB]State change image failed.");
		    	sys_out.newLine();
		    	sys_out.flush();
				break;
			}
			state_change_counter++;
			img_dir_2 = AverMedia_control.capture_image_single_dir();
			
		}while(state_change == false);
		

		if(state_change == false){
			out.write("[IMAGE_RGB]State change image failed.");
		    out.newLine();
		    out.flush();
		    timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	    	sys_out.write(timeStamp+"[IMAGE_RGB]State change image failed.");
	    	sys_out.newLine();
	    	sys_out.flush();
		}
		else if(image_RGB_compare.image_compare_tol(img_dir_1, img_dir_2) == false && state_change == true){
			OK_counter++;
			out.write("[IMAGE_RGB]State change OK.");
		    out.newLine();
		    out.flush();
		    timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	    	sys_out.write(timeStamp+"[IMAGE_RGB]State change OK.");
	    	sys_out.newLine();
	    	sys_out.flush();
		    if(template_match_RGB.image_compare(img_dir_2, directory_parameter.HAS_CDDA_play_image()) == true){
		    	OK_counter++;
		    	out.write("[TEMPLATE_RGB][CDDA_PLAY]Template matched 100%.");
		    	out.newLine();
		    	out.flush();
		    	timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		    	sys_out.write(timeStamp+"[TEMPLATE_RGB][CDDA_PLAY]Template matched 100%.");
		    	sys_out.newLine();
		    	sys_out.flush();
		    } 
		    else if(template_match_RGB.image_compare_tol(img_dir_2,  directory_parameter.HAS_CDDA_play_image()) == true){
		    	OK_counter++;
		    	out.write("[TEMPLATE_RGB][CDDA_PLAY]Template matched (with tolerance).");
		    	out.newLine();
		    	out.flush();
		    	timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		    	sys_out.write(timeStamp+"[TEMPLATE_RGB][CDDA_PLAY]Template matched (with tolerance).");
		    	sys_out.newLine();
		    	sys_out.flush();
		    }
		    else{
		    	out.write("[TEMPLATE_RGB][CDDA_PLAY]Template does not match.");
		    	out.newLine();
		    	out.flush();
		    	timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		    	sys_out.write(timeStamp+"[TEMPLATE_RGB][CDDA_PLAY]Template does not match.");
		    	sys_out.newLine();
		    	sys_out.flush();
		    }
		}
		Thread.sleep(4000);
		String video = AverMedia_control.record_video_end_dir();
		File vFile = new File(video);
		vFile.delete();
		audio_image.remove_mute();
		if(audio_image.check_mute() == true){
	    	OK_counter++;
	    	out.write("[AUDIO_MUTE]No Mute found.");
	    	out.newLine();
	    	out.flush();
	    	timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	    	sys_out.write(timeStamp+"[AUDIO_MUTE]No Mute found.");
	    	sys_out.newLine();
	    	sys_out.flush();
	    }else {
	    	out.write("[AUDIO_MUTE]Mute test FAIL.");
	    	out.newLine();
	    	out.flush();
	    	timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	    	sys_out.write(timeStamp+"[AUDIO_MUTE]Mute test FAIL.");
	    	sys_out.newLine();
	    	sys_out.flush();
	    }
		
		if(SoundWave_compare_hightlight.match_audio(directory_parameter.HAS_CDDA_24kMagic_trak1_image(), test_ID+"\\audio\\audio_1_wave.png") == true){
	    	OK_counter++;
	    	out.write("[AUDIO_SCORE]SCORE > 80.");
	    	out.newLine();
	    	out.flush();
	    	timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	    	sys_out.write(timeStamp+"[AUDIO_SCORE]SCORE > 80.");
	    	sys_out.newLine();
	    	sys_out.flush();
	    }else {
	    	out.write("[AUDIO_SCORE]SCORE failed. SCORE < 80");
	    	out.newLine();
	    	out.flush();
	    	timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	    	sys_out.write(timeStamp+"[AUDIO_SCORE]SCORE failed. SCORE < 80");
	    	sys_out.newLine();
	    	sys_out.flush();
	    }
		
		if(OK_counter>=4) {
			result = true;
		}else {
			result = false;
		}
		out.write("[TEST][RESULT][CDDA_PLAY]Test Result: "+result);
		out.close();
		timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
    	sys_out.write(timeStamp+"[TEST][RESULT][CDDA_PLAY]Test Result: "+testID+", "+result);
    	sys_out.newLine();
    	sys_out.close();
		System.out.println("[TEST][RESULT][CDDA_PLAY]Test Result: "+testID+", "+result);

		try {
			UsbUirt.UIRT_Transmit(HASFY18_RMT_AM420.STOP());
			Thread.sleep(500);
			UsbUirt.UIRT_Transmit(HASFY18_RMT_AM420.STOP());
			Thread.sleep(500);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public static boolean USB_temp_display_demo1() throws IOException, InterruptedException {
		new java.util.Date();
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		File_Fol_control.clear_runningFol();
		String test_ID = File_Fol_control.create_test_fol();
		BufferedWriter out = new BufferedWriter(new FileWriter(test_ID+"\\log.txt", true));
		BufferedWriter sys_out = new BufferedWriter(new FileWriter(directory_parameter.dir_systemlog()+"\\system_log.txt", true));
		boolean result = false;
		boolean result_2 = false;
		int result_counter = 0;
		
		result_2 = OCR_Tesseract.OCR_check(AverMedia_control.capture_image_track_dir(), "Shape of You");
		out.write("[OCR]Track Name: "+result_2);
    	out.newLine();
    	out.flush();
		timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
    	sys_out.write(timeStamp+"[OCR]Track Name: "+result_2);
    	sys_out.newLine();
    	sys_out.flush();
    	if(result_2 == true) {
    		result_counter++;
    	}
    	
		Thread.sleep(1000);
		result_2 = OCR_Tesseract.OCR_check(AverMedia_control.capture_image_album_dir(), "[Deluxe]");
		out.write("[OCR]Track Album: "+result_2);
    	out.newLine();
		out.flush();
		timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
    	sys_out.write(timeStamp+"[OCR]Track Album: "+result_2);
    	sys_out.newLine();
    	sys_out.flush();
    	if(result_2 == true) {
    		result_counter++;
    	}
    	
		Thread.sleep(1000);
		result_2 = OCR_Tesseract.OCR_check(AverMedia_control.capture_image_artist_dir(), "Ed Sheeran");
		out.write("[OCR]Track Artist: "+result_2);
		out.newLine();
		out.flush();
		timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
    	sys_out.write(timeStamp+"[OCR]Track Artist: "+result_2);
    	sys_out.newLine();
    	sys_out.flush();
    	if(result_2 == true) {
    		result_counter++;
    	}
    	
		Thread.sleep(1000);
		result_2 = OCR_Tesseract.OCR_check(AverMedia_control.capture_image_bitrate_dir(), "127k");
		out.write("[OCR]Track Bitrate: "+result_2);
		out.newLine();
		out.flush();
		timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
    	sys_out.write(timeStamp+"[OCR]Track Bitrate: "+result_2);
    	sys_out.newLine();
    	sys_out.flush();
    	if(result_2 == true) {
    		result_counter++;
    	}
		Thread.sleep(1000);
		result_2 = OCR_Tesseract.OCR_check(AverMedia_control.capture_image_codec_dir(), "WMA");
		out.write("[OCR]Track Codec: "+result_2);
		out.newLine();
		out.flush();
		timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
    	sys_out.write(timeStamp+"[OCR]Track Codec: "+result_2);
    	sys_out.newLine();
    	sys_out.flush();
    	if(result_2 == true) {
    		result_counter++;
    	}
    	
    	if(result_counter >= 5) {
    		result = true;
    	}else {
    		result = false;
    	}
    	
		out.write("[TEST][RESULT][USB_TEMP]Test Result: "+test_ID+", "+result);
		out.close();
		timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
    	sys_out.write(timeStamp+"[TEST][RESULT][USB_TEMP]Test Result: "+test_ID+", "+result);
    	sys_out.newLine();
    	sys_out.close();
    	
    	return result;
	}
	
	public static boolean USB_temp_display_demo2() throws IOException, InterruptedException {
		new java.util.Date();
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		File_Fol_control.clear_runningFol();
		String test_ID = File_Fol_control.create_test_fol();
		BufferedWriter out = new BufferedWriter(new FileWriter(test_ID+"\\log.txt", true));
		BufferedWriter sys_out = new BufferedWriter(new FileWriter(directory_parameter.dir_systemlog()+"\\system_log.txt", true));
		boolean result = false;
		boolean result_2 = false;
		int result_counter = 0;
		
		result_2 = OCR_Tesseract.OCR_check(AverMedia_control.capture_image_track_dir(), "Qi Li Xiang");
		out.write("[OCR]Track Name: "+result_2);
    	out.newLine();
    	out.flush();
		timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
    	sys_out.write(timeStamp+"[OCR]Track Name: "+result_2);
    	sys_out.newLine();
    	sys_out.flush();
    	if(result_2 == true) {
    		result_counter++;
    	}
    	
		Thread.sleep(1000);
		result_2 = OCR_Tesseract.OCR_check(AverMedia_control.capture_image_album_dir(), "Qi Li Xiang");
		out.write("[OCR]Track Album: "+result_2);
    	out.newLine();
		out.flush();
		timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
    	sys_out.write(timeStamp+"[OCR]Track Album: "+result_2);
    	sys_out.newLine();
    	sys_out.flush();
    	if(result_2 == true) {
    		result_counter++;
    	}
    	
		Thread.sleep(1000);
		result_2 = OCR_Tesseract.OCR_check(AverMedia_control.capture_image_artist_dir(), "Jay Chou");
		out.write("[OCR]Track Artist: "+result_2);
		out.newLine();
		out.flush();
		timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
    	sys_out.write(timeStamp+"[OCR]Track Artist: "+result_2);
    	sys_out.newLine();
    	sys_out.flush();
    	if(result_2 == true) {
    		result_counter++;
    	}
    	
		Thread.sleep(1000);
		result_2 = OCR_Tesseract.OCR_check(AverMedia_control.capture_image_bitrate_dir(), "320k");
		out.write("[OCR]Track Bitrate: "+result_2);
		out.newLine();
		out.flush();
		timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
    	sys_out.write(timeStamp+"[OCR]Track Bitrate: "+result_2);
    	sys_out.newLine();
    	sys_out.flush();
    	if(result_2 == true) {
    		result_counter++;
    	}
		Thread.sleep(1000);
		result_2 = OCR_Tesseract.OCR_check(AverMedia_control.capture_image_codec_dir(), "MP3");
		out.write("[OCR]Track Codec: "+result_2);
		out.newLine();
		out.flush();
		timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
    	sys_out.write(timeStamp+"[OCR]Track Codec: "+result_2);
    	sys_out.newLine();
    	sys_out.flush();
    	if(result_2 == true) {
    		result_counter++;
    	}
    	
    	if(result_counter >= 5) {
    		result = true;
    	}else {
    		result = false;
    	}
    	
		out.write("[TEST][RESULT][USB_TEMP]Test Result: "+test_ID+", "+result);
		out.close();
		timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
    	sys_out.write(timeStamp+"[TEST][RESULT][USB_TEMP]Test Result: "+test_ID+", "+result);
    	sys_out.newLine();
    	sys_out.close();
    	
    	return result;
	}
}
