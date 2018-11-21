package audio;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javazoom.jl.converter.Converter;
import javazoom.jl.decoder.JavaLayerException;

public class convertAudio {
	public static void convertMP3toWAV(String path){
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
	}
}
