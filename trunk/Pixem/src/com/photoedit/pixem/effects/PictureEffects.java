/**
 *
 * Copyright (c) 2009-2011 Envision Mobile Ltd. All rights reserved.
 *
 * Other software and company names mentioned herein or used for developing
 * and/or running the Envision Mobile Ltd's software may be trademarks or trade
 * names of their respective owners.
 *
 * Everything in the source code herein is owned by Envision Mobile Ltd.
 * The recipient of this source code hereby acknowledges and agrees that such
 * information is proprietary to Envision Mobile Ltd. and shall not be used, 
 * disclosed, duplicated, and/or reversed engineered except in accordance
 * with the express written authorization of Envision Mobile Ltd.
 *
 * Module: PictureEffects.java
 * Project: Pixem
 *
 * Description:
 *
 *
 * Developer:   10107896
 * Date:        2011-11-08
 * Version:
 *
 *
 */
package com.photoedit.pixem.effects;

/**
 * @author 10107896
 *
 */
public class PictureEffects {

	public PictureEffects() { 
		/*package com.android.pixem.pictureEffects;

		import java.io.File;
		import java.io.IOException;
		import java.util.logging.Level;
		import java.util.logging.Logger;

		import android.graphics.Color;
		import android.provider.MediaStore.Images;

		import com.android.pixem.org.Matrix;


		public class Effects {

		    BufferedImage image;
		    File output = new File("output.jpeg");

		    public GetPixelColour() {
		        try {
		            image = Images.read(new File("rune.PNG"));
		        } catch (IOException e) {
		            System.out.println("fail");
		            System.exit(0);
		        }
		    }

		    public void makeGreyscale() {

		        int clr, red = 0, blue = 0, green = 0;

		        for (int i = 0; i < image.getWidth(); i++) {
		            for (int j = 0; j < image.getHeight(); j++) {
		                clr = image.getRGB(i, j);
		                red = (clr & 0x00ff0000) >> 16;
		                green = (clr & 0x0000ff00) >> 8;
		                blue = clr & 0x000000ff;

		                image.setRGB(i, j, (((red + green + blue) << 16) + ((red + green + blue) << 8)
		                        + (red + green + blue)));
		            }
		        }

		        try {
		            ImageIO.write(image, "jpeg", output);
		        } catch (IOException ex) {
		            Logger.getLogger(GetPixelColour.class.getName()).log(Level.SEVERE, null, ex);
		        }
		    }

		    public void makeSepia() {

		        int red = 0, green = 0, blue = 0, clr, total;
		        int sepiaIntensity = 15;

		        int sepiaDepth = 20;

		        for (int i = 0; i < image.getWidth(); i++) {
		            for (int j = 0; j < image.getHeight(); j++) {
		                clr = image.getRGB(i, j);
		                red = (clr & 0x00ff0000) >> 16;
		                green = (clr & 0x0000ff00) >> 8;
		                blue = clr & 0x000000ff;

		                total = (red + green + blue) / 3;

		                red = green = blue = total;

		                red = red + (sepiaDepth * 2);
		                green = green + sepiaDepth;
		                blue -= sepiaIntensity;

		                if (red > 255) {
		                    red = 255;
		                }
		                if (green > 255) {
		                    green = 255;
		                }
		                if (blue < 0) {
		                    blue = 0;
		                }
		                if (blue > 255) {
		                    blue = 255;
		                }

		                image.setRGB(i, j, ((red << 16) + (green << 8) + blue));
		            }
		        }

		        try {
		            ImageIO.write(image, "jpeg", output);
		        } catch (IOException ex) {
		            Logger.getLogger(GetPixelColour.class.getName()).log(Level.SEVERE, null, ex);
		        }
		    }

		    public void makeContrastBrighter() {

		        int clr;
		        double red = 0.0, blue = 0.0, green = 0.0, contrast;

		        contrast = (100.0 + 75.0) / 100.0;
		        contrast *= contrast;

		        for (int i = 0; i < image.getWidth(); i++) {
		            for (int j = 0; j < image.getHeight(); j++) {
		                clr = image.getRGB(i, j);

		                red = ((clr & 0x00ff0000) >> 16) / 255.0;
		                green = ((clr & 0x0000ff00) >> 8) / 255.0;
		                blue = (clr & 0x0000ff) / 255.0;

		                red -= 0.5;
		                red *= contrast;
		                red += 0.5;
		                red *= 255;

		                if (red > 255) {
		                    red = 255;
		                }
		                if (red < 0) {
		                    red = 0;
		                }

		                green -= 0.5;
		                green *= contrast;
		                green += 0.5;
		                green *= 255;

		                if (green > 255) {
		                    green = 255;
		                }
		                if (green < 0) {
		                    green = 0;
		                }

		                blue -= 0.5;
		                blue *= contrast;
		                blue += 0.5;
		                blue *= 255;

		                if (blue > 255) {
		                    blue = 255;
		                }
		                if (blue < 0) {
		                    blue = 0;
		                }

		                image.setRGB(i, j, (((int) red << 16) + ((int) green << 8) + (int) blue));
		            }
		        }

		        try {
		            ImageIO.write(image, "jpeg", output);
		        } catch (IOException ex) {
		            Logger.getLogger(GetPixelColour.class.getName()).log(Level.SEVERE, null, ex);
		        }
		    }

		    public void makeContrastDimmer() {

		        int clr;
		        double red = 0.0, blue = 0.0, green = 0.0, contrast;

		        contrast = (100.0 - 15.0) / 100.0;
		        contrast *= contrast;

		        for (int i = 0; i < image.getWidth(); i++) {
		            for (int j = 0; j < image.getHeight(); j++) {
		                clr = image.getRGB(i, j);

		                red = ((clr & 0x00ff0000) >> 16) / 255.0;
		                green = ((clr & 0x0000ff00) >> 8) / 255.0;
		                blue = (clr & 0x0000ff) / 255.0;

		                red -= 0.5;
		                red *= contrast;
		                red += 0.5;
		                red *= 255;

		                if (red > 255) {
		                    red = 255;
		                }
		                if (red < 0) {
		                    red = 0;
		                }

		                green -= 0.5;
		                green *= contrast;
		                green += 0.5;
		                green *= 255;

		                if (green > 255) {
		                    green = 255;
		                }
		                if (green < 0) {
		                    green = 0;
		                }

		                blue -= 0.5;
		                blue *= contrast;
		                blue += 0.5;
		                blue *= 255;

		                if (blue > 255) {
		                    blue = 255;
		                }
		                if (blue < 0) {
		                    blue = 0;
		                }

		                image.setRGB(i, j, (((int) red << 16) + ((int) green << 8) + (int) blue));
		            }
		        }

		        try {
		            ImageIO.write(image, "jpeg", output);
		        } catch (IOException ex) {
		            Logger.getLogger(GetPixelColour.class.getName()).log(Level.SEVERE, null, ex);
		        }
		    }

		    public void makeDecreaseColourDepth() {
		        int offset = 16, clr, red = 0, green = 0, blue = 0;

		        for (int i = 0; i < image.getWidth(); i++) {
		            for (int j = 0; j < image.getHeight(); j++) {
		                clr = image.getRGB(i, j);
		                red = (clr & 0x00ff0000) >> 16;
		                green = (clr & 0x0000ff00) >> 8;
		                blue = (clr & 0x000000ff);

		                red = ((red + (offset / 2)) - ((red + (offset / 2)) % offset)) - 1;
		                if (red < 0) {
		                    red = 0;
		                }

		                blue = ((blue + (offset / 2)) - ((blue + (offset / 2)) % offset)) - 1;
		                if (blue < 0) {
		                    blue = 0;
		                }

		                green = ((green + (offset / 2)) - ((green + (offset / 2)) % offset)) - 1;
		                if (green < 0) {
		                    green = 0;
		                }

		                image.setRGB(i, j, ((red << 16) + (green << 8) + blue));
		            }
		        }

		        try {
		            ImageIO.write(image, "jpeg", output);
		        } catch (IOException ex) {
		            Logger.getLogger(GetPixelColour.class.getName()).log(Level.SEVERE, null, ex);
		        }
		    }

		    public void makeSmooth() {

		        Matrix m = new Matrix();
		        int red = 0, green = 0, blue = 0;
		        //double [][] colours = new double[m.getMatrixSize()][m.getMatrixSize()];

		        int matrixSize = m.getMatrixSize();

		        BufferedImage temp = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);

		        double[][] redPixel = new double[matrixSize][matrixSize],
		                greenPixel = new double[matrixSize][matrixSize],
		                bluePixel = new double[matrixSize][matrixSize];

		        m.setValues(1);
		        m.setMiddle(8);
		        m.setFactor(9);

		        for (int i = 0; i < image.getWidth() - 2; i++) {
		            for (int j = 0; j < image.getHeight() - 2; j++) {

		                for (int x = 0; x < matrixSize; x++) {
		                    for (int y = 0; y < matrixSize; y++) {
		                        // colours[x][y] = image.getRGB(x, y);
		                        redPixel[x][y] = (image.getRGB(i + x, j + y) & 0x00ff0000) >> 16;
		                        greenPixel[x][y] = (image.getRGB(i + x, j + y) & 0x0000ff00) >> 8;
		                        bluePixel[x][y] = (image.getRGB(i + x, j + y) & 0x000000ff);

		                        red = 0;
		                        green = 0;
		                        blue = 0;

		                        for (int k = 0; k < matrixSize; k++) {
		                            for (int n = 0; n < matrixSize; n++) {
		                                red += (int) (redPixel[k][n] * m.matrix[k][n]);
		                                green += (int) (greenPixel[k][n] * m.matrix[k][n]);
		                                blue += (int) (bluePixel[k][n] * m.matrix[k][n]);
		                            }
		                        }

		                        red = (int) ((red / m.factor) + 5);
		                        if (red > 255) {
		                            red = 255;
		                        }
		                        if (red < 0) {
		                            red = 0;
		                        }


		                        green = (int) ((green / m.factor) + 5);
		                        if (green > 255) {
		                            green = 255;
		                        }
		                        if (green < 0) {
		                            green = 0;
		                        }

		                        blue = (int) ((blue / m.factor) + 5);
		                        if (blue > 255) {
		                            blue = 255;
		                        }
		                        if (blue < 0) {
		                            blue = 0;
		                        }

		                    }
		                }

		                temp.setRGB(i + 1, j + 1, ((red << 16) + (green << 8) + blue));
		            }
		        }

		        image = temp;

		        try {
		            ImageIO.write(image, "jpeg", output);
		        } catch (IOException ex) {
		            Logger.getLogger(GetPixelColour.class.getName()).log(Level.SEVERE, null, ex);
		        }
		    }

		    public void makeColourFilter(double redVal, double greenVal, double blueVal) {

		        byte red, green, blue;
		        int clr;

		        BufferedImage temp = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);

		        for (int x = 0; x < image.getWidth(); x++) {
		            for (int y = 0; y < image.getHeight(); y++) {

		                clr = image.getRGB(x, y);
		                red = (byte) (((clr & 0x00ff0000) >> 16) * redVal);
		                green = (byte) (((clr & 0x0000ff00) >> 8) * greenVal);
		                blue = (byte) ((clr & 0x000000ff) * blueVal);

		                image.setRGB(x, y, ((red << 16) + (green << 8) + (blue)));
		            }
		        }

		        try {
		            ImageIO.write(image, "jpeg", output);
		        } catch (IOException ex) {
		            Logger.getLogger(GetPixelColour.class.getName()).log(Level.SEVERE, null, ex);
		        }
		    }

		    public void makeBlur() {
		    }

		    public void addRectangleBorder(int frameSize, Color c) {


		        BufferedImage temp = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
		        int clr, red, blue, green;

		        for (int i = 0; i < image.getWidth(); i++) {
		            for (int j = 0; j < image.getHeight(); j++) {

		                if (i < frameSize / 2 || j < frameSize / 2 || i > image.getWidth() - frameSize / 2
		                        || j > image.getHeight() - frameSize / 2) {
		                    temp.setRGB(i, j, c.getRGB());
		                } else {
		                    temp.setRGB(i, j, image.getRGB(i, j));
		                }
		            }
		        }

		        image = temp;

		        try {
		            ImageIO.write(image, "jpeg", output);
		        } catch (IOException ex) {
		            Logger.getLogger(GetPixelColour.class.getName()).log(Level.SEVERE, null, ex);
		        }
		    }

		    public void makeRoundedBorder(int arcWidth, int arcHeight, int size,  Color c) {

		        BufferedImage temp = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
		        Graphics g = temp.getGraphics();

		        g.setColor(c);
		        g.fillRoundRect(size, size, image.getWidth() - (size * 2), image.getHeight() -  (size * 2), arcWidth, arcHeight);
		        
		        for (int i = 0; i < image.getWidth(); i++) {
		            for (int j = 0; j < image.getHeight(); j++) {

		                if (temp.getRGB(i, j) == c.getRGB())
		                    temp.setRGB(i, j, image.getRGB(i, j));
		            }
		        }
		        
		        image = temp;

		        try {
		            ImageIO.write(image, "jpeg", output);
		        } catch (IOException ex) {
		            Logger.getLogger(GetPixelColour.class.getName()).log(Level.SEVERE, null, ex);
		        }
		    }

		     public void switchBlueGreen() {

		        BufferedImage temp = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
		        int clr, red, blue, green;

		        for (int i = 0; i < image.getWidth(); i ++) {
		            for (int j = 0; j < image.getHeight(); j++) {
		                clr = image.getRGB(i, j);
		                red = (clr & 0x00ff0000) >> 16;
		                green = (clr & 0x0000ff00) >> 8;
		                blue = (clr & 0x000000ff);

		                temp.setRGB(i, j, ((red << 16) + (blue << 8) + (green)));
		            }
		        }
		        image = temp;

		        try {
		            ImageIO.write(image, "jpeg", output);
		        } catch (IOException ex) {
		            Logger.getLogger(GetPixelColour.class.getName()).log(Level.SEVERE, null, ex);
		        }
		    }
		}

		*/
	}
}
