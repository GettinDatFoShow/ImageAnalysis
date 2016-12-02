package application;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class ICImage extends Component {
	
	public final int A = 24;
	public final int R = 16;
	public final int G = 8;
	public int iWidth;
	public int iHeight;
	public int pixelTotal;
	public int[] iPArray;
	public int[] iRArray;
	public int[] iGArray;
	public int[] iBArray;
	public int[] iAArray;
	public int[][] iColorPixelArray;
	public int[][][] iColorIntArray;
	public long iAbin[];
	public long iRbin[];
	public long iGbin[];
	public long iBbin[];
	public String IURL;
	public String iDate;
	public String iName;
	public String iSrc;
	public BufferedImage image;
	public Color color;
	public File imageFile;
	
	// constructor begin 
	public ICImage(File file){
		this.imageFile = file;
		try {
			this.image = ImageIO.read(file);
		} catch (IOException e) {
			System.out.println("ERROR: NO IMAGE!!");
			System.out.print("FILE PATH : ");
			System.out.print(this.imageFile.getAbsolutePath());
			e.printStackTrace();
		}
		this.setIURL(file);
		this.setISource(this.imageFile.getAbsolutePath());
		this.setINameFromFile(file);
		this.setIHeight(image);
		this.setIWidth(image);
		this.setColorArrays(image);
		this.setIDate();
	}
	
	public ICImage(String imageSource) { 
		this.setISource(imageSource); 				// link to image
		this.createBImage(); 						// create buffered image
		
	} 
	
	public ICImage(String imageSource, String imageName) { 
		this.setISource(imageSource); 				// link to image
		this.createBImage(); 						// create buffered image
		this.setIName(imageName);
	}
	
	// constructor end

//  MUTATORS ======  ( SET METHODS )  ======
	public void setIURL(File file){
		try {
			this.IURL = file.toURI().toURL().toString();
		} catch (MalformedURLException e) {
			System.out.println("MalformedURL Exception");
			e.printStackTrace();
		}

	}
	
	public void setINameFromFile(File file){
		this.iName = file.getName();
		int pos = this.iName.lastIndexOf(".");
		if (pos > 0) {
		    this.iName = this.iName.substring(0, pos);
		}
	}
	
	public void setISource(String imageSource){ // setting image source (iSrc) with string
		this.iSrc = imageSource;
	}

	public void createBImage(){					// method creates buffered image object
		String imageSource = this.getISource();
		try{
			this.image = ImageIO.read(this.getClass().getResource(imageSource)); // set up buffered image
		}catch(IOException e){
		      System.err.println(e.getMessage());
		}		
		this.setIHeight(image);
		this.setIWidth(image);
		this.setColorArrays(image);
		this.setIDate();
	}
	
	public void createBImage(String imageLocation){					// overload method creates buffered image object
		try{
			this.image = ImageIO.read(this.getClass().getResource(imageLocation)); // set up buffered image
		}catch(IOException e){
		      System.err.println(e.getMessage());
		}		
		this.setIHeight(image);
		this.setIWidth(image);
		this.setColorArrays(image);
		this.setIDate();
	}
	
	public void setIHeight(BufferedImage image){ // setting iHeight
		this.iHeight = image.getHeight();
	}
	
	public void setIWidth(BufferedImage image){ // setting iWidth
		this.iWidth = image.getWidth();
	}
	
	public void setColorArrays(BufferedImage image){ // setting ColorArray 
		int h = this.getIHeight();
		int w = this.getIWidth();
		int pixel;
		this.iColorPixelArray = new int[h][w];
		int[] alphaArray = new int[h*w];
		int[] redArray = new int[h*w];
		int[] greenArray = new int[h*w];
		int[] blueArray = new int[h*w];
		int counter = 0;
	    for (int i = 0; i < h; i++) {
	      for (int j = 0; j < w; j++) {
	    	  pixel = image.getRGB(j, i);
		      alphaArray[counter] = this.getAlpha(pixel);
		      redArray[counter] = this.getRed(pixel);
		      greenArray[counter] = this.getGreen(pixel);
		      blueArray[counter] = this.getBlue(pixel);
		      this.iColorPixelArray[i][j] = pixel;
		      counter += 1; 
	      }
	     }
	      setPixelTotal(counter);
	      setIAArray(alphaArray);
	      setIRArray(redArray);
	      setIGArray(greenArray);
	      setIBArray(blueArray);
	      setIColorIntArray(this.iColorPixelArray);
	      setBins(alphaArray, redArray, greenArray, blueArray);
	}
	
	public void setIColorIntArray(int[][] pixelArray){
		int pixel;
		int h = this.getIHeight();
		int w = this.getIWidth();
		this.iColorIntArray = new int[h][w][];
	    for (int i = 0; i < h; i++) {
		      for (int j = 0; j < w; j++) {
		    	  pixel = (int) this.iColorPixelArray[i][j];
		    	  int[] temp = {this.getAlpha(pixel), this.getRed(pixel), this.getGreen(pixel), this.getBlue(pixel)};
		    	  this.iColorIntArray[i][j] = temp;
		      }
	    }
	}
	
	public void setIName(String imageName){
		this.iName = imageName;
	}
	
	public void setIAArray(int[] alphaArray){
		this.iAArray = alphaArray;
    }
	
	public void setBins(int[] alphaArray, int[] redArray, int[] greenArray, int[] blueArray){
		this.iAbin = new long[256];
		this.iRbin = new long[256];
		this.iGbin = new long[256];
		this.iBbin = new long[256];
		for(int i = 0; i < 256; i++){
			this.iAbin[i]=0;
			this.iRbin[i]=0;
			this.iGbin[i]=0;
			this.iBbin[i]=0;
		}
		int end = alphaArray.length;
		for(int i = 0; i < end ; i++){
			this.iAbin[alphaArray[i]]++;
			this.iRbin[redArray[i]]++;
			this.iGbin[greenArray[i]]++;
			this.iBbin[blueArray[i]]++;
		}
	}

	public void setIBArray(int[] blueArray){
		this.iBArray = blueArray;
	}
	
	public void setIRArray(int[] redArray){
		this.iRArray = redArray;
	}
	
	public void setIGArray(int[] greenArray){
		this.iGArray = greenArray;
	}
	
	public void setIDate(){
       //getting current date and time using Date class
       DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
       Date dateobj = new Date();
       this.iDate = df.format(dateobj);
	}

	public void setPixelTotal(int counter){
		this.pixelTotal = counter;
	}
	
//  ACCESSORS ======  ( GET METHODS )	======
	public String getIURL(){
		return this.IURL;
	}
	
	public String getISource(){ 				// getting  image source (iSrc) string
		return this.iSrc;
	}
	
	public String getIName(){
		return this.iName;
	}
	
	public BufferedImage getICImage(){
		return this.image;
	}
	
	public int getIHeight(){
		return this.iHeight;
	}
	
	public int getIWidth(){
		return this.iWidth;
	}
	
	public int[][] getIColorPixelArray(){
		return this.iColorPixelArray;
	}
	
	public int[][][] getIColorIntArray(){
		return this.iColorIntArray;
	}
	
	public int[] getIAArray(){
		return this.iAArray;
    }
	
	public int[] getIBArray(){
		return this.iBArray;
	}
	
	public int[] getIRArray(){
		return this.iRArray;
	}
	
	public int[] getIGArray(){
		return this.iGArray;
	}
	
	public int getAlpha(int pixel){
		return (pixel >> this.A) & 0xff;
	}
	
	public int getRed(int pixel){
		return (pixel >> this.R) & 0xff;
	}
	
	public int getGreen(int pixel){
		return (pixel >> this.G) & 0xff;
	}
	
	public int getBlue(int pixel){
		return (pixel) & 0xff;
	}
	
	public String getIDate(){
		return this.iDate;
	}
	
	public int getPixelTotal(){
		return this.pixelTotal;
	}
	// ===== ( OVERLOADED GET FUNCTIONS ) ======
	
	public double getAlpha(int[][] colorArray, int x, int y){
		int pixel = colorArray[y][x];
		return getAlpha(pixel);
	}
	
	public double getRed(int[][] colorArray, int x, int y){
		int pixel = colorArray[y][x];
		return getRed(pixel);
	}
	
	public double getGreen(int[][] colorArray, int x, int y){
		int pixel = colorArray[y][x];
		return getGreen(pixel);
	}
	
	public double getBlue(int[][] colorArray, int x, int y){
		int pixel = colorArray[y][x];
		return getBlue(pixel);
	}
	
	public long[] getAlphaBin(){
		return this.iAbin;
	}
	public long[] getRedBin(){
		return this.iRbin;
	}
	public long[] getGreenBin(){
		return this.iGbin;
	}
	public long[] getBlueBin(){
		return this.iBbin;
	}
	

	
} // ************************    end of ICImage Class    *****************************
