package image;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import AverMedia_control.File_Fol_control;
import control.directory_parameter;
import net.sourceforge.tess4j.*;

public class OCR_Tesseract {

    public static boolean OCR_check(String path, String expected_result) throws IOException {		
    	String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
    	String testID = File_Fol_control.get_testID();
    	BufferedWriter out = new BufferedWriter(new FileWriter(testID+"\\log.txt", true));
		BufferedWriter sys_out = new BufferedWriter(new FileWriter(directory_parameter.dir_systemlog()+"\\system_log.txt", true));
		boolean result = false;
		String text = "";
		Tesseract tesseract = new Tesseract();
		try {
			tesseract.setDatapath("C:\\SQA_Projects\\eclipse-workspace\\PGSQA_TestAuto_Proto1\\library\\tessdata_best-master");
			text = tesseract.doOCR(new File(path));	
			text = text.replace("\n", "").replace("\r", "");
		} catch (TesseractException e) {		
			e.printStackTrace();
		}
		if(text.equals(expected_result)) {
			result = true;
		}else {
			result = false;
		}
		out.write("[OCR]OCR Compare: "+text+", "+expected_result+", "+result+"");
		out.newLine();
		out.close();
		timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		sys_out.write(timeStamp+"[OCR]OCR Compare: "+text+", "+expected_result+", "+result+"");
		sys_out.newLine();
		sys_out.close();
		System.out.println("[OCR]OCR Compare: "+text+", "+expected_result+", "+result+"");
		return result;
    }
}