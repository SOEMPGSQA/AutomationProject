package test_case;

import AverMedia_control.AverMedia_control;
import AverMedia_control.File_Fol_control;
import control.directory_parameter;
import image.template_match_RGB;
import rmt_control.HASFY18_RMT_AM420;
import rmt_control.UsbUirt;

public class HAS_test_demo_02 {
	public static boolean cd_playback_control_play_01_demo2() throws Exception {
		boolean result = false;
		String main_test_ID = File_Fol_control.create_test_mainFol("demo2");
		String sub_test_ID = File_Fol_control.create_test_fol();
		
		UsbUirt.UIRT_Transmit(HASFY18_RMT_AM420.PLAY());
		Thread.sleep(3000);
		
		String img_dir_2 = AverMedia_control.capture_image_single_dir();
		Thread.sleep(500);
		result = template_match_RGB.image_compare_tol(img_dir_2,  directory_parameter.HAS_CDDA_play_image());
		
		return result;
	}
}
