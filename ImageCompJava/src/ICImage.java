
import java.awt.Color;
import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ICImage extends Component {
	
	public int iWidth;
	public int iHeight;
	public int alpha;
	public int red;
	public int green;
	public int blue;
	public int pixel;
	public int iPArray;
	public int[] iRArray;
	public int[] iGArray;
	public int[] iBArray;
	public int[] iAArray;
	public int[][] iColorArray;
	public long iDate;
	public String iName;
	public String iSrc;
	public BufferedImage image;
	public Color color;
	
	// constructor begin 
	
	public ICImage(String imageSource) { 
		setISource(imageSource); 				// link to image
		createBImage(); 						// create buffered image
	} 
	
	// constructor end

//  MUTATORS ======  ( SET METHODS )  ======
	
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
	}
	
	public void setIHeight(BufferedImage image){ // setting iHeight
		this.iHeight = image.getHeight();
	}
	
	public void setIWidth(BufferedImage image){ // setting iWidth
		this.iWidth = image.getWidth();
	}
	
	public void setColorArray(BufferedImage image){ // setting ColorArray 
		
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
	
//  ACCESSORS ======  ( GET METHODS )	======
	
	public String getISource(){ 				// getting  image source (iSrc) string
		return this.iSrc;
	}
	
	public int getIHeight(){
		return this.iHeight;
	}
	
	public int getIWidth(){
		return this.iWidth;
	}
	
	public int[][] getColorArray(){
		return this.iColorArray;
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
	

	
} // ************************    end of ICImage Class    *****************************



//	
//  public static void main(String[] foo) {
//    new ICImage();
//  }
//
//  public void printPixelARGB(int pixel) {
//    int alpha = (pixel >> 24) & 0xff;
//    int red = (pixel >> 16) & 0xff;
//    int green = (pixel >> 8) & 0xff;
//    int blue = (pixel) & 0xff;
//    System.out.println("argb: " + alpha + ", " + red + ", " + green + ", " + blue);
//  }
//
//  private void pixelByPixel(BufferedImage image, int[][] argblist) {
//    System.out.println("width, height: " + w + ", " + h);
//
//    for (int i = 0; i < h; i++) {
//      for (int j = 0; j < w; j++) {
//        System.out.println("x,y: " + j + ", " + i);
//        int pixel = image.getRGB(j, i);
//        printPixelARGB(pixel);
//        System.out.println("");
//      }
//    }
//  }
//
//  public iCompareImage(String imageLocation) {
//	  
//    try {
//      // get the BufferedImage, using the ImageIO class
//      BufferedImage image = ImageIO.read(this.getClass().getResource("image.png"));
//      int w = image.getWidth();
//      int h = image.getHeight();
//      int[][] argbList = new int[h][w];
//      pixelByPixel(image, argbList);
//    } catch (IOException e) {
//      System.err.println(e.getMessage());
//    }
//  }
//}
