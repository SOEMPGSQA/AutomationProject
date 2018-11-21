package image;

import java.applet.Applet;
import java.awt.AWTException;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

public class image extends Applet {
	public static void crop_image(String input_path, String output_path, int x1, int y1, int x2, int y2) throws IOException{
		String inFolder = input_path;
//		String pathDir ="";
//		List<String> pathList = Arrays.asList(input_path.split("\\\\"));
//		for(int i=0;i<pathList.size()-1;i++) {
//			pathDir = pathDir+""+pathList.get(i).toString()+"\\\\";
//		}
// 		String filename = "Untitled2.png"; 
	    BufferedImage image = ImageIO.read(new File(inFolder));
	    BufferedImage out = image.getSubimage(x1, y1, x2, y2);
	    ImageIO.write(out, "png", new File(output_path));
	}
	
	public static void resize_image(String input_path,String output_path, int width, int height) throws IOException {
		BufferedImage yourImage;
		File resizedImage = new File(output_path);
		Image newImage = ImageIO.read(new File(input_path));

		yourImage = resizeImage(newImage, width, height);
		ImageIO.write(yourImage, "png", resizedImage);		
	}
	
	public static BufferedImage resizeImage(final Image image, int width, int height) {
        final BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        final Graphics2D graphics2D = bufferedImage.createGraphics();
        graphics2D.setComposite(AlphaComposite.Src);
        //below three lines are for RenderingHints for better image quality at cost of higher processing time
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.drawImage(image, 0, 0, width, height, null);
        graphics2D.dispose();
        return bufferedImage;
    }
	
	public static void contImage2binary(String input_path, String output_path) {
		BufferedImage master;
        BufferedImage grayScale;
        BufferedImage blackWhite;
        String url_1 = input_path;
        
//        String pathDir ="";
//		List<String> pathList = Arrays.asList(input_path.split("\\\\"));
//		for(int i=0;i<pathList.size()-1;i++) {
//			pathDir = pathDir+""+pathList.get(i).toString()+"\\\\";
//		}
        
        try {
			master = ImageIO.read(new File(url_1));
			grayScale = ImageIO.read(new File(url_1));
			
	        ColorConvertOp op = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
	        op.filter(grayScale, grayScale);

	        blackWhite = new BufferedImage(master.getWidth(), master.getHeight(), BufferedImage.TYPE_BYTE_BINARY);
	        Graphics2D g2d = blackWhite.createGraphics();
	        g2d.drawImage(master, 0, 0, null);
	        g2d.dispose();
	        
	        File outputFile = new File(output_path);
	        ImageIO.write(blackWhite, "png", outputFile); 
//	        File outputFile2 = new File(pathDir+"\\grey_1.png");
//	        ImageIO.write(grayScale, "png", outputFile2);
//	        int width1 = blackWhite.getWidth(null);
//		    int height1 = blackWhite.getHeight(null);
//		    
//		    long diff = 0;
//		    int blackpixel_count = 0;
//		    System.out.println("Width: "+width1+" Height: "+height1);
//	      	for (int x = 0; x < width1; x++) {
//	      		for (int y = 0; y < height1; y++) {
//			        int rgb1 = blackWhite.getRGB(x, y);
//			        if(rgb1 == -16777216){
//			        	blackpixel_count += 1;
//			        }
//	      		}
//	      		blackpixel_count = 0;
//		    }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	public static void contImage2grey(String input_path, String output_path) {
		BufferedImage master;
        BufferedImage grayScale;
        BufferedImage blackWhite;
        String url_1 = input_path;
        
        
        try {
			master = ImageIO.read(new File(url_1));
			grayScale = ImageIO.read(new File(url_1));
			
	        ColorConvertOp op = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
	        op.filter(grayScale, grayScale);

	        blackWhite = new BufferedImage(master.getWidth(), master.getHeight(), BufferedImage.TYPE_BYTE_BINARY);
	        Graphics2D g2d = blackWhite.createGraphics();
	        g2d.drawImage(master, 0, 0, null);
	        g2d.dispose();
	        
	        File outputFile = new File(output_path);
	        ImageIO.write(grayScale, "png", outputFile);
		    
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	
	public static void screen_shoot(String path) {
		BufferedImage image;
		String pathDir =path;
//		List<String> pathList = Arrays.asList(path.split("\\\\"));
//		for(int i=0;i<pathList.size()-1;i++) {
//			pathDir = pathDir+""+pathList.get(i).toString()+"\\\\";
//		}
		
		try {
			for(int i=1;i<11;i++) {
				image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
				ImageIO.write(image, "png", new File(pathDir+"screenshot"+i+".png"));
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (HeadlessException | AWTException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
