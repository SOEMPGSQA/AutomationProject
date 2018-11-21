package audio;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.musicg.fingerprint.FingerprintManager;
import com.musicg.fingerprint.FingerprintSimilarity;
import com.musicg.fingerprint.FingerprintSimilarityComputer;
import com.musicg.wave.Wave;

import AverMedia_control.File_Fol_control;
import control.directory_parameter;

public class AcousticFingerprint {
	public static boolean fingerprintscore(String path1, String path2) throws IOException{
		new java.util.Date();
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		boolean result = false;
		double tolerance = 0.5;
		double fingerprintScore = 0;
		byte[] firstFingerPrint = new FingerprintManager().extractFingerprint(new Wave(path1));
		byte[] secondFingerPrint = new FingerprintManager().extractFingerprint(new Wave(path2));
		
		String testID = File_Fol_control.get_testID();
		BufferedWriter out = new BufferedWriter(new FileWriter(testID+"\\log.txt", true));
		BufferedWriter sys_out = new BufferedWriter(new FileWriter(directory_parameter.dir_systemlog()+"\\system_log.txt", true));

		FingerprintSimilarity fingerprintSimilarity = new FingerprintSimilarityComputer(firstFingerPrint, secondFingerPrint).getFingerprintsSimilarity();
		fingerprintScore = fingerprintSimilarity.getScore();
		
		if(fingerprintScore > tolerance) {
			result = true;
		}else {
			result = false;
		}
		System.out.println("[AUDIO]Fingerprinting Score: ["+result+"] "+fingerprintScore);
		out.write("[AUDIO]Fingerprinting Score: ["+result+"] "+fingerprintScore);
		out.newLine();
		out.close();
		timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		sys_out.write(timeStamp+"[AUDIO]Fingerprinting Score: ["+result+"] "+fingerprintScore);
		sys_out.newLine();
		sys_out.close();
		return result;
	}
}
