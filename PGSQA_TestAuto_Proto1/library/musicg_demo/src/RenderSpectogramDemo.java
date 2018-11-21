//package com.musicg.main.demo; 
 
 
// import com.musicg.graphic.GraphicRender; 
 import com.musicg.wave.Wave; 
 import com.musicg.wave.extension.Spectrogram; 
 
 
 public class RenderSpectogramDemo { 
 	public static void main(String[] args) { 
 

 		String inFolder = "audio_work";		 
 		String outFolder = "out"; 
 		String filename = "cock_a_1.wav"; 
 
 
 		// create a wave object 
 		Wave wave = new Wave(inFolder+"/"+filename); 
 		Spectrogram spectrogram = new Spectrogram(wave); 
 
 
 		// Graphic render 
 		GraphicRender render = new GraphicRender(); 
 		// render.setHorizontalMarker(1); 
 		// render.setVerticalMarker(1); 
 		render.renderSpectrogram(spectrogram, outFolder + "/"+filename+".jpg"); 
 
 
 		// change the spectrogram representation 
 		int fftSampleSize = 1024; 
 		int overlapFactor = 0; 
 		spectrogram = new Spectrogram(wave, fftSampleSize, overlapFactor); 
 		render.renderSpectrogram(spectrogram, outFolder + "/"+filename+"2.jpg"); 
 	} 
 } 
