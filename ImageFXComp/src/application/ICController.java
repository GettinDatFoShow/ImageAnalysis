package application;

import java.net.URL;
import java.util.ResourceBundle;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;

public class ICController implements Initializable {
	
	public final int Yval = 255;
	public int Xval;
	public ICImage image;
	public NumberAxis xAxis = new NumberAxis();
	public CategoryAxis yAxis = new CategoryAxis();
	public FileChooser fileChooser = new FileChooser();
	public Desktop desktop = Desktop.getDesktop();
//	public ObservableList<XYChart.Series<String, Double>> ICchartData;
	public Main main;
	public static boolean running;
	long[] alphaBin;
	long[] redBin;
	long[] greenBin;
	long[] blueBin;
	double percentRed;
	double percentGreen;
	double percentBlue;
	double percentTransparent;
	XYChart.Series seriesAlpha = new XYChart.Series();
	XYChart.Series seriesRed = new XYChart.Series();
	XYChart.Series seriesGreen = new XYChart.Series();
	XYChart.Series seriesBlue = new XYChart.Series();
	
    @FXML
    private BorderPane ICFXBorderPain;

    @FXML
    private GridPane ICFXGridPane;

    @FXML
    private Button colorChartButton;

    @FXML
    private Button RGBhistoButton;

    @FXML
    private Button RGBpGraphButton;

    @FXML
    private PieChart RGBpieChart;

    @FXML
    private BarChart<?, ?> RGBpGraph;

    @FXML
    private LineChart<String, Number> RGBhistogram;

    @FXML
    private MenuButton menuButton;

    @FXML
    private MenuItem miBrowseButton;

    @FXML
    private MenuItem miDataButton;

    @FXML
    private MenuItem miCloseButton;

    @FXML
    private ImageView mainImage;

    @FXML
    private Label imageNameLabel;
    
    void setMain(Main main){
    	this.main = main;
    }
    
    @FXML
    void browseImage(ActionEvent event) {
    	this.getImageFile();
    }
    
    private void getImageFile(){
    	fileChooser.setTitle("Open Image File");
        fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"))
            ); 
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
            );
        File file = fileChooser.showOpenDialog(application.Main.MainStage);
        if (file != null) {
            try {
				openFile(file);
			} catch (IOException e) {
				e.printStackTrace();
				this.getImageFile();
			}
        }
        this.setNewImageView();
        this.setRGBhisto();
//        this.setRGBhisto();
//        this.setRGBpieChart();
//        this.setRGBpGraph();
    }
    
//    private void setRGBpGraph() {
//    	this.RGBhistogram.getData().addAll(this.getICchartData());
//    }

	private void setRGBpieChart() {
		// TODO Auto-generated method stub
		
	}

	private void setRGBhisto() {
    	if(this.running){
    		resetICchartData();
    	}
    	else{
    		//    	this.ICchartData = FXCollections.observableArrayList();
        	setICchartData();
    	}
    }
		
	private void openFile(File file) throws IOException {
        this.image = new ICImage(file);
    }
    
    private void setNewImageView(){
    	Image image = new Image(this.image.getIURL());
    	this.mainImage.setImage(image);
    }
    
    
    private void resetICchartData(){
		this.RGBhistogram.getData().removeAll(seriesRed,seriesAlpha,seriesGreen,seriesBlue);
    }
    
    
    private void setICchartData(){
//    	this.ICchartData = FXCollections.observableArrayList();
    	XYChart.Series seriesAlpha = new XYChart.Series();
    	XYChart.Series seriesRed = new XYChart.Series();
    	XYChart.Series seriesGreen = new XYChart.Series();
    	XYChart.Series seriesBlue = new XYChart.Series();

		seriesAlpha.setName("Alpha");
		seriesRed.setName("Red");
		seriesGreen.setName("Green");
		seriesBlue.setName("Blue");

		alphaBin = this.image.getAlphaBin();
		redBin = this.image.getRedBin();
		greenBin = this.image.getGreenBin();
		blueBin = this.image.getBlueBin();
			
		for(int i = 0; i < 255; i++){
			seriesAlpha.getData().add(new XYChart.Data(Integer.toString(i+1), alphaBin[i]));
			seriesRed.getData().add(new XYChart.Data(Integer.toString(i+1), redBin[i]));
			seriesGreen.getData().add(new XYChart.Data(Integer.toString(i+1), greenBin[i]));
			seriesBlue.getData().add(new XYChart.Data(Integer.toString(i+1), blueBin[i]));
			}
		this.RGBhistogram.getData().addAll(seriesRed,seriesAlpha,seriesGreen,seriesBlue);
		this.running = true;
    }
    
//    private ObservableList<XYChart.Series<String, Double>> getICchartData(){
//    	return this.ICchartData;
//    }
    
    
    @FXML
    void saveHistogram(ActionEvent event) {
//    	if(running){
//    		running = false;
//    		this.RGBhistogram.getData().removeAll(seriesRed,seriesAlpha,seriesGreen,seriesBlue);
//    	}
    }

    @FXML
    void changePercentView(ActionEvent event) {

    }

    @FXML
    void changePieView(ActionEvent event) {
    	
    }

    @FXML
    void closeProgram(ActionEvent event) {

    }

    @FXML
    void showData(ActionEvent event) {

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println("Starting up ImageFXcompare....");
		this.running = false;
	}

}


// FOR A USER TO SAVE THE IMAGE!!! 
//MenuItem cmItem2 = new MenuItem("Save Image");
//cmItem2.setOnAction(new EventHandler<ActionEvent>() {
//    public void handle(ActionEvent e) {
//        FileChooser fileChooser = new FileChooser();
//        fileChooser.setTitle("Save Image");
//        System.out.println(pic.getId());
//        File file = fileChooser.showSaveDialog(stage);
//        if (file != null) {
//            try {
//                ImageIO.write(SwingFXUtils.fromFXImage(pic.getImage(),
//                    null), "png", file);
//            } catch (IOException ex) {
//                System.out.println(ex.getMessage());
//            }
//        }
//    }
//}
//);
