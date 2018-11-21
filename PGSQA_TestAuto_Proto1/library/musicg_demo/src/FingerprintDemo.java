
// package com.musicg.main.demo; 
 
 import com.musicg.fingerprint.FingerprintManager; 
 import com.musicg.wave.Wave; 
 
 public class FingerprintDemo{ 
 	 
 	public static void main (String[] args){ 
 		 
 		String filename = "cock_a_1.wav"; 
 
 
 		// create a wave object 
 		Wave wave = new Wave("audio_work/"+filename); 
 

 		// get the fingerprint 
 		byte[] fingerprint=wave.getFingerprint(); 
 
 		// dump the fingerprint 
 		FingerprintManager fingerprintManager=new FingerprintManager(); 
 		fingerprintManager.saveFingerprintAsFile(fingerprint, "out/"+filename+".fingerprint"); 
 		 
 		// load fingerprint from file 
 		byte[] loadedFp=fingerprintManager.getFingerprintFromFile("out/"+filename+".fingerprint"); 
 		 
 		/* 
 		// fingerprint bytes checking 
 		for (int i=0; i<fingerprint.length; i++){ 
 			System.out.println(fingerprint[i]+" vs "+loadedFp[i]); 
 		} 
 		*/ 
 	} 
} 
