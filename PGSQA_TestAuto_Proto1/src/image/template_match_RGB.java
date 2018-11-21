package image;

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

public class template_match_RGB {
	public static boolean image_compare(String path1, String path2) throws IOException{
		new java.util.Date();
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		BufferedImage img1 = null;
		BufferedImage img2 = null;
		String testID = File_Fol_control.get_testID();
		BufferedWriter out = new BufferedWriter(new FileWriter(testID+"\\log.txt", true));
		BufferedWriter sys_out = new BufferedWriter(new FileWriter(directory_parameter.dir_systemlog()+"\\system_log.txt", true));
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
		if ((width1 <= width2) || (height1 <= height2)) {
			System.err.println("Error: Images dimensions mismatch");
			System.exit(1);
		}
		boolean flag_match = false;
		boolean flag_match_line = false;
		boolean flag_match_line_save = false;
		boolean flag_break = false;
		
		int x2 = 0, y2 = 0, x1_point = 0, x1_point_save = 0, y1_point_save = 0;
		try {
			for (int y1 = 0; y1 < height1; y1++) {
				for (int x1 = 0; x1 < width1; x1++) {
					if(flag_match_line == true) {
						flag_match_line = false;
						x1 = x1+x1_point;
					}
					if((width1 - x1) < width2 && flag_match != true) {
						break;
					}
					else if(img1.getRGB(x1, y1) == img2.getRGB(x2, y2)) {
						flag_match = true;
						if(flag_match_line_save == false) {
							x1_point_save = x1;
							y1_point_save = y1;
							flag_match_line_save = true;
						}
						if(x2 == (width2-1)) {
							x1_point = x1_point_save;
							flag_match_line = true;
							x2=0;
							if(y2 == (height2-1)) {
								flag_break = true;
							}
							y2++;
							break;
						}
						x2++;
					}	
					else if(img1.getRGB(x1, y1) != img2.getRGB(x2, y2) && flag_match == false) {
						flag_match = false;
						flag_match_line = false;
						flag_match_line_save = false;
						x1_point_save = 0;
						y1_point_save = 0;
						x2 = 0; y2 = 0;
					}
					else if(img1.getRGB(x1, y1) != img2.getRGB(x2, y2) && flag_match == true) {
						flag_match = false;
						flag_match_line = false;
						flag_match_line_save = false;
						x1 = x1_point_save;
						y1 = y1_point_save;
						x1_point_save = 0;
						x1_point_save = 0;
						x2 = 0; y2 = 0;
					}
				}
				if((height1 - y1) < height2 && flag_match != true) {
					break;
				}
				else if(flag_break == true) {
					break;
				}
			}
		}catch(ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
        out.write("[TEMPLATE_RGB]100% match: "+flag_match);
	    out.newLine();
		out.close();
		timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		sys_out.write(timeStamp+"[TEMPLATE_RGB]100% match: "+flag_match);
		sys_out.newLine();
		sys_out.close();
		System.out.println("[TEMPLATE_RGB]100% match: "+flag_match);
		return flag_match;
	}
	
	public static boolean image_compare_tol(String path1, String path2) throws IOException{
		new java.util.Date();
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		BufferedImage img1 = null;
		BufferedImage img2 = null;		
		double tolerance = 2;
		double diff_r = 0;
		double diff_g = 0;
		double diff_b = 0;
		double max_diff = 0;
		String point ="";
		String testID = File_Fol_control.get_testID();
		
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
		if ((width1 <= width2) || (height1 <= height2)) {
			System.err.println("Error: Images dimensions mismatch");
			System.exit(1);
		}
		boolean flag_match = false;
		boolean flag_match_line = false;
		boolean flag_match_line_save = false;
		boolean flag_break = false;
		BufferedWriter out = new BufferedWriter(new FileWriter(testID+"\\log.txt", true));
		BufferedWriter sys_out = new BufferedWriter(new FileWriter(directory_parameter.dir_systemlog()+"\\system_log.txt", true));
		int x2 = 0, y2 = 0, x1_point = 0, x1_point_save = 0, y1_point_save = 0;
		try {
			for (int y1 = 0; y1 < height1; y1++) {
				for (int x1 = 0; x1 < width1; x1++) {
					if(flag_match_line == true) {
						flag_match_line = false;
						x1 = x1+x1_point;
					}
					int rgb1 = img1.getRGB(x1, y1);
			        int rgb2 = img2.getRGB(x2, y2);
			        int r1 = (rgb1 >> 16) & 0xff;
			        int g1 = (rgb1 >>  8) & 0xff;
			        int b1 = (rgb1      ) & 0xff;
			        int r2 = (rgb2 >> 16) & 0xff;
			        int g2 = (rgb2 >>  8) & 0xff;
			        int b2 = (rgb2      ) & 0xff;
//			        out.write(""+x1+", "+y1+" ("+r1+" "+g1+" "+b1+") "+x2+", "+y2+" ("+r2+" "+g2+" "+b2+")");
//				    out.newLine();
			        diff_r = Math.abs(r1 - r2);
			        diff_g = Math.abs(g1 - g2);
			        diff_b = Math.abs(b1 - b2);

					if((width1 - x1) < width2 && flag_match != true) {
						break;
					}
					else if(diff_r <= tolerance && diff_g <= tolerance && diff_b <= tolerance) {
						flag_match = true;
				        if(max_diff < Math.abs(r1 - r2)) {
				        	max_diff = Math.abs(r1 - r2);
				        	point = ""+x1+","+y1;
				        }
				        if(max_diff < Math.abs(g1 - g2)) {
				        	max_diff = Math.abs(g1 - g2);
				        	point = ""+x1+","+y1;
				        }
				        if(max_diff < Math.abs(b1 - b2)) {
				        	max_diff = Math.abs(b1 - b2);
				        	point = ""+x1+","+y1;
				        }
						if(flag_match_line_save == false) {
							x1_point_save = x1;
							y1_point_save = y1;
							flag_match_line_save = true;
						}
						if(x2 == (width2-1)) {
							x1_point = x1_point_save;
							flag_match_line = true;
							x2=0;
							if(y2 == (height2-1)) {
								flag_break = true;
							}
							y2++;
							break;
						}
						x2++;
					}	
					else if((diff_r > tolerance || diff_g > tolerance || diff_b > tolerance) && flag_match == false) {
						flag_match = false;
						flag_match_line = false;
						flag_match_line_save = false;
						x1_point_save = 0;
						y1_point_save = 0;
						x2 = 0; y2 = 0;
						max_diff = 0;
					}
					else if((diff_r > tolerance || diff_g > tolerance || diff_b > tolerance) && flag_match == true) {
						flag_match = false;
						flag_match_line = false;
						flag_match_line_save = false;
						x1 = x1_point_save;
						y1 = y1_point_save;
						x1_point_save = 0;
						x1_point_save = 0;
						x2 = 0; y2 = 0;
						max_diff = 0;
					}
				}
				if((height1 - y1) < height2 && flag_match != true) {
//					System.out.println("BREAK by Y");
					break;
				}
				else if(flag_break == true) {
//					System.out.println("BREAK by FLAG");
					break;
				}
			}
		}catch(ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
		
        out.write("[TEMPLATE_RGB]Max differences: "+point+" = "+max_diff+", "+flag_match);
	    out.newLine();
		out.close();
		timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		sys_out.write(timeStamp+"[TEMPLATE_RGB]Max differences: "+point+" = "+max_diff+", "+flag_match);
		sys_out.newLine();
		sys_out.close();
		System.out.println("[TEMPLATE_RGB]Max differences: "+point+" = "+max_diff+", "+flag_match);
//		System.out.println("Match of images: " + flag_match);
		return flag_match;
	}
}
