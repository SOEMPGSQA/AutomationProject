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

public class sample {
	public static boolean cd_playback_control_play_01_demo() throws InterruptedException, IOException {
		new java.util.Date();
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		boolean result = false;
		boolean state_change = false;
		int OK_counter = 0;
		int state_change_counter = 0;

		File_Fol_control.clear_runningFol(); //remove all the files inside "Running" folder
		String test_ID = File_Fol_control.create_test_fol(); //create new folder with ID, for actual results and logs
		
		BufferedWriter out = new BufferedWriter(new FileWriter(test_ID+"\\log.txt", true));//logs for actual test ID
		BufferedWriter sys_out = new BufferedWriter(new FileWriter(directory_parameter.dir_systemlog()+"\\system_log.txt", true));//logs accumulation

		//logging START
		out.write(timeStamp+" [TEST] cd_playback_control_play_01");
    	out.newLine();
    	out.flush();
    	sys_out.write(timeStamp+" [TEST] cd_playback_control_play_01");
    	sys_out.newLine();
    	sys_out.flush();
    	//logging PAUSE

		String img_dir_1 = AverMedia_control.capture_image_single_dir(); //capture image, then return image path
		Thread.sleep(300);
		AverMedia_control.record_video_start(); //start video records
		
		try {
			UsbUirt.UIRT_Transmit(HASFY18_RMT_AM420.PLAY()); //send [PLAY] command from remote control
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Thread.sleep(1200);
		String img_dir_2 = AverMedia_control.capture_image_single_dir(); //capture image, then return image path
		
		//check status change(from STOP -> PLAY)
		do {		
			Thread.sleep(1200);
			if(image_RGB_compare.image_compare_tol(img_dir_1, img_dir_2) == true){ //screen before & after [PLAY] comparison
				//logging START
				out.write("[IMAGE_RGB]State change image failed, count:"+state_change_counter);
			    out.newLine();
			    out.flush();
			    timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		    	sys_out.write(timeStamp+"[IMAGE_RGB]State change image failed, count:"+state_change_counter);
		    	sys_out.newLine();
		    	sys_out.flush();
		    	//logging PAUSE
			}else {
				state_change = true;
			}
			if(state_change_counter>=3) {
				//logging START
				out.write("[IMAGE_RGB]State change image failed.");
		    	out.newLine();
		    	out.flush();
		    	timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		    	sys_out.write(timeStamp+"[IMAGE_RGB]State change image failed.");
		    	sys_out.newLine();
		    	sys_out.flush();
		    	//logging PAUSE
				break;
			}
			state_change_counter++;
			img_dir_2 = AverMedia_control.capture_image_single_dir(); //capture image, then return image path
		}while(state_change == false);
		

		if(state_change == false){
			//logging START
			out.write("[IMAGE_RGB]State change image failed.");
		    out.newLine();
		    out.flush();
		    timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	    	sys_out.write(timeStamp+"[IMAGE_RGB]State change image failed.");
	    	sys_out.newLine();
	    	sys_out.flush();
	    	//logging PAUSE
		}
		else if(image_RGB_compare.image_compare_tol(img_dir_1, img_dir_2) == false && state_change == true){
			OK_counter++;
			//logging START
			out.write("[IMAGE_RGB]State change OK.");
		    out.newLine();
		    out.flush();
		    timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	    	sys_out.write(timeStamp+"[IMAGE_RGB]State change OK.");
	    	sys_out.newLine();
	    	sys_out.flush();
	    	//logging PAUSE
		    if(template_match_RGB.image_compare(img_dir_2, directory_parameter.HAS_CDDA_play_image()) == true){
		    	OK_counter++;
		    	//logging START
		    	out.write("[TEMPLATE_RGB][CDDA_PLAY]Template matched 100%.");
		    	out.newLine();
		    	out.flush();
		    	timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		    	sys_out.write(timeStamp+"[TEMPLATE_RGB][CDDA_PLAY]Template matched 100%.");
		    	sys_out.newLine();
		    	sys_out.flush();
		    	//logging PAUSE
		    } 
		    else if(template_match_RGB.image_compare_tol(img_dir_2,  directory_parameter.HAS_CDDA_play_image()) == true){
		    	OK_counter++;
		    	//logging START
		    	out.write("[TEMPLATE_RGB][CDDA_PLAY]Template matched (with tolerance).");
		    	out.newLine();
		    	out.flush();
		    	timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		    	sys_out.write(timeStamp+"[TEMPLATE_RGB][CDDA_PLAY]Template matched (with tolerance).");
		    	sys_out.newLine();
		    	sys_out.flush();
		    	//logging PAUSE
		    }
		    else{
		    	//logging START
		    	out.write("[TEMPLATE_RGB][CDDA_PLAY]Template does not match.");
		    	out.newLine();
		    	out.flush();
		    	timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		    	sys_out.write(timeStamp+"[TEMPLATE_RGB][CDDA_PLAY]Template does not match.");
		    	sys_out.newLine();
		    	sys_out.flush();
		    	//logging PAUSE
		    }
		}
		Thread.sleep(20000);
		String video = AverMedia_control.record_video_end_dir(); //end video record, and extract WAV from video, then return video path
		File vFile = new File(video);
		vFile.delete(); //delete recorded video file
		audio_image.remove_mute(); //remove beginning and ending mute from wave image
		
		//check wave image for mute
		if(audio_image.check_mute() == true){
	    	OK_counter++;
	    	//logging START
	    	out.write("[AUDIO_MUTE]No Mute found.");
	    	out.newLine();
	    	out.flush();
	    	timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	    	sys_out.write(timeStamp+"[AUDIO_MUTE]No Mute found.");
	    	sys_out.newLine();
	    	sys_out.flush();
	    	//logging PAUSE
	    }else {
	    	//logging START
	    	out.write("[AUDIO_MUTE]Mute test FAIL.");
	    	out.newLine();
	    	out.flush();
	    	timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	    	sys_out.write(timeStamp+"[AUDIO_MUTE]Mute test FAIL.");
	    	sys_out.newLine();
	    	sys_out.flush();
	    	//logging PAUSE
	    }
		
		//check wave image with template result, for amplitude frequency and amplitude trend
		if(SoundWave_compare_hightlight.match_audio(directory_parameter.HAS_CDDA_24kMagic_trak1_image(), test_ID+"\\audio\\audio_1_wave.png") == true){
	    	OK_counter++;
	    	//logging START
	    	out.write("[AUDIO_SCORE]SCORE > 80.");
	    	out.newLine();
	    	out.flush();
	    	timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	    	sys_out.write(timeStamp+"[AUDIO_SCORE]SCORE > 80.");
	    	sys_out.newLine();
	    	sys_out.flush();
	    	//logging PAUSE
	    }else {
	    	//logging START
	    	out.write("[AUDIO_SCORE]SCORE failed. SCORE < 80");
	    	out.newLine();
	    	out.flush();
	    	timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	    	sys_out.write(timeStamp+"[AUDIO_SCORE]SCORE failed. SCORE < 80");
	    	sys_out.newLine();
	    	sys_out.flush();
	    	//logging PAUSE
	    }
		
		//[IMAGE]Stage change, [IMAGE]Play icon check, [AUDIO]Mute Check, [AUDIO]Audio score
		//Check for 4 OKs
		if(OK_counter>=4) {
			result = true;
		}else {
			result = false;
		}
		out.write("[TEST][RESULT][CDDA_PLAY]Test Result: "+result);
		out.close();
		timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
    	sys_out.write(timeStamp+"[TEST][RESULT][CDDA_PLAY]Test Result: "+test_ID+", "+result);
    	sys_out.newLine();
    	sys_out.close();
		System.out.println("[TEST][RESULT][CDDA_PLAY]Test Result: "+test_ID+", "+result);

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
}
