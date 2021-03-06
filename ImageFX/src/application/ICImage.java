package application;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
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
	public ArrayList<String> totalColors = new ArrayList<String>();
	public int iAbin[];
	public int iRbin[];
	public int iGbin[];
	public int iBbin[];
	public String IURL;
	public String iDate;
	public String iName;
	public String iSrc;
	public BufferedImage image;
	public Color color;
	public File imageFile;
	public double[] percentages = new double[4];
	public double totalColor;
	public double transparentcy;
	public double percentRed;
	public double percentGreen;
	public double percentBlue;
	public double colorFulness;
	public double totalColorPercentage;
	
	// constructor begin 
	public ICImage(File file)
	{
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
		this.findTotalColors();
		this.setIcolorPercentages();
		this.setIDate();
	}
	
	public ICImage(String imageSource) 
	{ 
		this.setISource(imageSource); 				// link to image
		this.createBImage(); 						// create buffered image
		this.setIDate();
		this.findTotalColors();
		this.setIcolorPercentages();
	} 
	
	public ICImage(String imageSource, String imageName) 
	{ 
		this.setISource(imageSource); 				// link to image
		this.createBImage(); 						// create buffered image
		this.setIName(imageName);
		this.setIDate();
		this.findTotalColors();
		this.setIcolorPercentages();
	}
	
	// constructor end

//  MUTATORS ======  ( SET METHODS )  ======
	public void setIURL(File file)
	{
		try {
			this.IURL = file.toURI().toURL().toString();
		} catch (MalformedURLException e) {
			System.out.println("MalformedURL Exception");
			e.printStackTrace();
		}

	}
	
	public void setINameFromFile(File file)
	{
		this.iName = file.getName();
		int pos = this.iName.lastIndexOf(".");
		if (pos > 0) {
		    this.iName = this.iName.substring(0, pos);
		}
	}
	
	public void setISource(String imageSource){ // setting image source (iSrc) with string
		this.iSrc = imageSource;
	}

	
	public void createBImage()
	{					// method creates buffered image object
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
	
	
	public void createBImage(String imageLocation)
	{					// overload method creates buffered image object
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
	
	
	public void setIHeight(BufferedImage image)
	{ // setting iHeight
		this.iHeight = image.getHeight();
	}
	
	
	
	public void setIWidth(BufferedImage image)
	{ // setting iWidth
		this.iWidth = image.getWidth();
	}
	
	
	
	public void setColorArrays(BufferedImage image)
	{ // setting ColorArray 
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
	      setBins(alphaArray, redArray, greenArray, blueArray);
	     
	}
	

	
	
	public void setIName(String imageName){
		this.iName = imageName;
	}
	
	public void setIAArray(int[] alphaArray){
		this.iAArray = alphaArray;
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
	
	public void setBins(int[] alphaArray, int[] redArray, int[] greenArray, int[] blueArray){
		this.iAbin = new int[256];
		this.iRbin = new int[256];
		this.iGbin = new int[256];
		this.iBbin = new int[256];
		for(int i = 0; i < 256; i++){
			this.iAbin[i]=0;
			this.iRbin[i]=0;
			this.iGbin[i]=0;
			this.iBbin[i]=0;
		}
		int end = alphaArray.length;
		
		for(int i = 0; i < end ; i++){
			
			this.iAbin[alphaArray[i]] += 1 ;
			this.iRbin[redArray[i]] += 1;
			this.iGbin[greenArray[i]] += 1;
			this.iBbin[blueArray[i]] += 1 ;
		}
	}
	
	
	public void findTotalColors(){
		int end = this.getPixelTotal();
		String red= "";
		String green="";
		String blue="";
		ArrayList<String> result = new ArrayList<String>();
		int total;
		
		
		
		for(int i = 0; i < end; i++){
			red= "";
			green="";
			blue="";
			red = Integer.toString(this.iRArray[i]);
			green = Integer.toString(this.iGArray[i]);
			blue = Integer.toString(this.iBArray[i]);
			result.add(red+green+blue);
		}
		
		Collections.sort(result);;
		int pointer = 0;
		this.totalColors.add(result.get(0));
		for(int i = 0; i < end; i++){
			if(result.get(i).equals(this.totalColors.get(pointer))){

			}
			else{
				this.totalColors.add(result.get(i));	
				pointer +=1;
			}
		}
		
	}
	
	public void setIcolorPercentages(){
    	double red = 0;
    	double alpha = 0;
    	double blue = 0;
    	double green = 0;
    	double end = this.iRArray.length;
    	long total = this.iRArray.length*255;
    	this.totalColor = 0;
    	
    	for(int i = 0; i < end; i++){
    		alpha += this.iAArray[i];
    		red += this.iRArray[i];
    		blue += this.iBArray[i];
    		green += this.iGArray[i];
    		this.totalColor = red + blue + green;
    	}
    	
//    	System.out.println(alpha);
//    	System.out.println(red);
//    	System.out.println(green);
//    	System.out.println(blue);
//    	System.out.println(total);
//    	System.out.println("**************");
    	
    	this.transparentcy = alpha/totalColor*100;
    	this.percentRed = red/totalColor*100;
    	this.percentGreen = green/totalColor*100;
    	this.percentBlue = blue/totalColor*100;
    	System.out.println(this.getPixelTotal());
    	System.out.println(this.totalColors.size());
    	this.colorFulness =  (double) this.totalColors.size() / (double) this.getPixelTotal()*100;
    	
    	
    	System.out.println(this.transparentcy);
    	System.out.println(this.percentRed);
    	System.out.println(this.percentGreen);
    	System.out.println(this.percentBlue);
    	System.out.print("colorfulness: ");
    	System.out.println(this.colorFulness);
    	
    	this.percentages[0] = this.transparentcy;
    	this.percentages[1] = this.percentRed;
    	this.percentages[2] = this.percentGreen;
    	this.percentages[3] = this.percentBlue;
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
	
	public int[] getAlphaBin(){
		return this.iAbin;
	}
	public int[] getRedBin(){
		return this.iRbin;
	}
	public int[] getGreenBin(){
		return this.iGbin;
	}
	public int[] getBlueBin(){
		return this.iBbin;
	}
	
	
//	public double[] getIcolorPercentages(){
////		this.setIcolorPercentages();
//		return this.IcolorPercentages;
//	}

	
} // ************************    end of ICImage Class    *****************************
