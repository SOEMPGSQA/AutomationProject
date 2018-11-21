package audio;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.musicg.wave.Wave;

import javazoom.jl.converter.Converter;
import javazoom.jl.decoder.JavaLayerException;

import it.sauronsoftware.jave.*; 

public class audio_to_SoundWave{
	public static void extractWAVfromAVI(String path_input, String path_output) {
		File source = new File(path_input);
        File target = new File(path_output);
        AudioAttributes audio = new AudioAttributes();
        audio.setCodec("pcm_s16le");
        EncodingAttributes attrs = new EncodingAttributes();
        attrs.setFormat("wav");
        attrs.setAudioAttributes(audio);
        Encoder encoder = new Encoder();
        try {
            encoder.encode(source, target, attrs);
        } catch (IllegalArgumentException | EncoderException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
	
	public static File convertMP3toWAV(String path){
		File mp3 = new File(path);
		File temp = null;
		String pathDir ="";
		List<String> pathList = Arrays.asList(path.split("\\\\"));
		for(int i=0;i<pathList.size()-1;i++) {
			pathDir = pathDir+""+pathList.get(i).toString()+"\\\\";
		}
		try {
			temp = File.createTempFile(mp3.getName().split("\\.")[0],".wav");
			temp.deleteOnExit();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		Converter converter = new Converter();
		try {
			System.out.println(temp.toString());
			converter.convert(mp3.getAbsolutePath(), temp.getAbsolutePath());
			temp.renameTo(new File(pathDir+"temp.wav"));
			System.out.println(temp.getAbsolutePath().toString());
		} catch (JavaLayerException e) {
			e.printStackTrace();
		}
		return temp;
	}
	
	public static void convert_WAVaudio_to_image(String input_path, String output_path) {
 		String filename = input_path; 
// 		String pathDir ="";
//		List<String> pathList = Arrays.asList(input_path.split("\\\\"));
//		for(int i=0;i<pathList.size()-1;i++) {
//			pathDir = pathDir+""+pathList.get(i).toString()+"\\\\";
//		}
// 		String outFolder = pathDir; 
 
 		// create a wave object 
 		Wave wave = new Wave(filename); 
 	 
 		// Graphic render 
 		GraphicRender render = new GraphicRender(); 
 		//render.setHorizontalMarker(1); 
 		//render.setVerticalMarker(1); 
 		render.renderWaveform(wave, output_path); 
 		 
 		// change the amplitude representation 
 		float timeStep=0.1F; 
 		render.renderWaveform(wave,timeStep,output_path); 
	}
}
