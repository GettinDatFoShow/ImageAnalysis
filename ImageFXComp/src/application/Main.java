package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

 
/**
 * @web http://java-buddy.blogspot.com
 */
public class Main extends Application {
	public static Stage MainStage;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.MainStage = primaryStage;
		Parent root = FXMLLoader.load(getClass().getResource("views/ICFXmainView.fxml"));
		this.MainStage.setTitle("ImageFX Compare");
		this.MainStage.setScene(new Scene(root));
    	this.MainStage.setResizable(true);
//		this.MainStage.setMaximized(true);
		this.MainStage.show();
	}
	public static JDBCAdapter adapter;
	
	public static void main(String[] args){
		String driver = "org.h2.Driver";
	    String server = "jdbc:h2:~/mydb";
	    String  user = "sa";
	    String  pass  = "";
		adapter = new JDBCAdapter(server,driver,user,pass);
		System.out.println("any error=" + adapter.error());
		String sql0 = "CREATE TABLE ImageComp(ImagePath VARCHAR(90), ImageName VARCHAR(30), ImageSize VARCHAR(30), percentRed VARCHAR(30), percentGreen VARCHAR(30), percentBlue VARCHAR(30), Colorfulness  VARCHAR(30),Histogram BLOB, Primary key (imagepath))";
		adapter.executeUpdate(sql0);
		launch(args);
	}

}
