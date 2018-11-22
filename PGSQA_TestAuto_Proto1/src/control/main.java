package control;
import image.*;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import rmt_control.*;
import test_case.HAS_test_001;
import test_case.HAS_test_demo_01;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

import org.opencv.imgproc.Imgproc;

import FFT_badlogic.*;
import FFT_badlogic_io.Plot;
import AverMedia_control.AverMedia_control;
import AverMedia_control.File_Fol_control;
import audio.*;


public class main {
	public static void main(String args[]) throws IOException{
		//timer for process starts
		long startTime = System.nanoTime();
		boolean result = false;
		int OK_count = 0;
		int NG_count = 0;
		System.out.println("start");
		
//		image.crop_image("C:\\SQA_Projects\\SQA_Test_Automation\\expected_result\\HAS\\common\\1.png", "C:\\SQA_Projects\\SQA_Test_Automation\\expected_result\\HAS\\common\\2.png", 414, 110, 365, 40);
		
		File_Fol_control.create_test_mainFol("demo");
//		actual test sample
		for(int x=0; x<8; x++) {
			try {
				result = HAS_test_demo_01.cd_playback_control_play_01_demo();
//				result = HAS_test_demo.USB_temp_display_demo1();
//				result = HAS_test_demo.USB_temp_display_demo2();
				String test_ID = File_Fol_control.get_testID();
				System.out.println((x+1)+". ID: "+test_ID+", Result cd_playback_control_play_01 :"+result);
				System.out.println((x+1)+". ID: "+test_ID+", Result USB_temp_display :"+result);
				Thread.sleep(5000);		
				
				if(result == true) {
					OK_count++;
				}else {
					NG_count++;
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		File_Fol_control.create_test_mainFol("demo2");
//		actual test sample
		for(int x=0; x<15; x++) {
			try {
				result = HAS_test_demo_01.cd_playback_control_play_01_demo();
//				result = HAS_test_demo.USB_temp_display_demo1();
//				result = HAS_test_demo.USB_temp_display_demo2();
				String test_ID = File_Fol_control.get_testID();
				System.out.println((x+1)+". ID: "+test_ID+", Result cd_playback_control_play_01 :"+result);
				System.out.println((x+1)+". ID: "+test_ID+", Result USB_temp_display :"+result);
				Thread.sleep(5000);		
				
				if(result == true) {
					OK_count++;
				}else {
					NG_count++;
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
//		test end
//		email.send_email();
		
		
		long estimatedTime = System.nanoTime() - startTime;
		
		System.out.println("Solution Time : " + new DecimalFormat("#.#########").format(estimatedTime / 1000000000) + " Seconds");
		System.out.println("Result OK: "+OK_count);
		System.out.println("Result NG: "+NG_count);
	}
	
	
}
