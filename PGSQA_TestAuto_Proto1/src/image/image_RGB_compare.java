package image;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import AverMedia_control.File_Fol_control;
import control.directory_parameter;

public class image_RGB_compare {
	public static boolean image_compare(String path1, String path2) throws IOException{
		new java.util.Date();
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		BufferedImage img1 = null;
		BufferedImage img2 = null;
		String testID = File_Fol_control.get_testID();
		BufferedWriter out = new BufferedWriter(new FileWriter(testID+"\\log.txt", true));
		BufferedWriter sys_out = new BufferedWriter(new FileWriter(directory_parameter.dir_systemlog()+"\\system_log.txt", true));
		double tolerance = 1.5;
		boolean result = false;

		try {
			File url1 = new File(path1);
			File url2 = new File(path2);
			img1 = ImageIO.read(url1);
			img2 = ImageIO.read(url2);
		} catch (IOException e) {
			e.printStackTrace();
		}
		int width1 = img1.getWidth(null);
		int width2 = img2.getWidth(null);
		int height1 = img1.getHeight(null);
		int height2 = img2.getHeight(null);
		if ((width1 != width2) || (height1 != height2)) {
			System.err.println("Error: Images dimensions mismatch");
			System.exit(1);
		}
		long diff = 0;
//		System.out.println("Width: "+width1+" Height: "+height2);
		for (int y = 0; y < height1; y++) {
			for (int x = 0; x < width1; x++) {
		        int rgb1 = img1.getRGB(x, y);
		        int rgb2 = img2.getRGB(x, y);
		        int r1 = (rgb1 >> 16) & 0xff;
		        int g1 = (rgb1 >>  8) & 0xff;
		        int b1 = (rgb1      ) & 0xff;
		        int r2 = (rgb2 >> 16) & 0xff;
		        int g2 = (rgb2 >>  8) & 0xff;
		        int b2 = (rgb2      ) & 0xff;

		        diff += Math.abs(r1 - r2);
		        diff += Math.abs(g1 - g2);
		        diff += Math.abs(b1 - b2);
			}
		}
		double n = width1 * height1 * 3;
		double p = diff / n / 255.0;
		DecimalFormat df = new DecimalFormat("####0.0000");
		System.out.println("Differences of images: " + df.format(p * 100.0));
		
		if((p * 100.0) < tolerance) {
			result = true;
		}else {
			result = false;
		}
        out.write("[IMAGE_RGB]Result: "+result+", "+(p * 100.0));
	    out.newLine();
	    out.close();
	    timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	    sys_out.write(timeStamp+"[IMAGE_RGB]Result: "+result+", "+(p * 100.0));
	    sys_out.newLine();
	    sys_out.close();
	    System.out.println("[IMAGE_RGB]Result: "+result+", "+(p * 100.0));
//		System.out.println(result);
		return result;
	}
	public static boolean image_compare_tol(String path1, String path2) throws IOException{
		new java.util.Date();
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		BufferedImage img1 = null;
		BufferedImage img2 = null;
		
		int max_diff = 0;
		String point = "";
		String testID = File_Fol_control.get_testID();
		BufferedWriter out = new BufferedWriter(new FileWriter(testID+"\\log.txt", true));
		BufferedWriter sys_out = new BufferedWriter(new FileWriter(directory_parameter.dir_systemlog()+"\\system_log.txt", true));
		Color myWhite = new Color(255, 255, 255);
		int rgb = myWhite.getRGB();
		
		double tolerance = 0.001;//1.5;
		boolean result = false;
		
		try {
			File url1 = new File(path1);
			File url2 = new File(path2);
			img1 = ImageIO.read(url1);
			img2 = ImageIO.read(url2);
		} catch (IOException e) {
			e.printStackTrace();
		}
		int width1 = img1.getWidth(null);
		int width2 = img2.getWidth(null);
		int height1 = img1.getHeight(null);
		int height2 = img2.getHeight(null);
		if ((width1 != width2) || (height1 != height2)) {
			System.err.println("Error: Images dimensions mismatch");
			System.exit(1);
		}
		long diff = 0;
//		System.out.println("Width: "+width1+" Height: "+height2);
		for (int y = 0; y < height1; y++) {
			for (int x = 0; x < width1; x++) {
				
		        int rgb1 = img1.getRGB(x, y);
		        int rgb2 = img2.getRGB(x, y);
		        int r1 = (rgb1 >> 16) & 0xff;
		        int g1 = (rgb1 >>  8) & 0xff;
		        int b1 = (rgb1      ) & 0xff;
		        int r2 = (rgb2 >> 16) & 0xff;
		        int g2 = (rgb2 >>  8) & 0xff;
		        int b2 = (rgb2      ) & 0xff;
//		        out.write("Image 1: ("+x+","+y+") = "+rgb1+" "+r1+" "+g1+" "+b1+" || Image 2: "+x+","+y+") = "+rgb2+" "+r2+" "+g2+" "+b2+" \n");
//			    out.newLine();
		        if(rgb1 == rgb2) {
		        	img1.setRGB(x, y, rgb);
		        }
		        diff += Math.abs(r1 - r2);
		        diff += Math.abs(g1 - g2);
		        diff += Math.abs(b1 - b2);
		        
		        diff += Math.abs(r1 - r2);
		        if(max_diff < Math.abs(r1 - r2)) {
		        	max_diff = Math.abs(r1 - r2);
		        	point = ""+x+","+y;
		        }
		        diff += Math.abs(g1 - g2);
		        if(max_diff < Math.abs(g1 - g2)) {
		        	max_diff = Math.abs(g1 - g2);
		        	point = ""+x+","+y;
		        }
		        diff += Math.abs(b1 - b2);
		        if(max_diff < Math.abs(b1 - b2)) {
		        	max_diff = Math.abs(b1 - b2);
		        	point = ""+x+","+y;
		        }
			}
		}
		double n = width1 * height1 * 3;
		double p = diff / n / 255.0;
		DecimalFormat df = new DecimalFormat("####0.0000");
//		System.out.println("Differences of images: " + df.format(p * 100.0));
		
		if((p * 100.0) < tolerance) {
			result = true;
		}else {
			result = false;
		}
		
	    try {
	    	File outputfile = new File(testID+"\\Image\\whiteArea_OK.png");
			ImageIO.write(img1, "png", outputfile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//	    System.out.println(p * 100.0);
        out.write("[IMAGE_RGB]Max differences: "+point+" = "+max_diff+", "+result);
	    out.newLine();
		out.close();
		timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		sys_out.write(timeStamp+"[IMAGE_RGB]Max differences: "+point+" = "+max_diff+", "+result);
		sys_out.newLine();
		sys_out.close();
	    System.out.println("[IMAGE_RGB]Max differences: "+point+" = "+max_diff+", "+result);
//		System.out.println(result);
		return result;
	}
}
