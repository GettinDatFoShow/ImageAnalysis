package application;
import java.io.IOException;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class DataTableController {

    @FXML
    private AnchorPane dataTableMainPane;

    @FXML
    private SplitPane dtSplitPane;

    @FXML
    private AnchorPane tableAPane;

    @FXML
    private TableView<?> dataTableView;

    @FXML
    private TableColumn<?, ?> ImageTcolumn;

    @FXML
    private TableColumn<?, ?> LocationTcoloumn;

    @FXML
    private AnchorPane imageInfoApane;

    @FXML
    private Label ImageFxDetailsLabel;

    @FXML
    private GridPane ImageInfoGrid;

    @FXML
    private Label DateLocationLabel;

    @FXML
    private Label ImageSizeLabel;

    @FXML
    private Label colorfulnessLabel;

    @FXML
    private Label redLabel;

    @FXML
    private Label GreenLabel;

    @FXML
    private Label BlueLabel;

    @FXML
    private Label DateDetailsLabel;

    @FXML
    private Label sizeDetailsLabel;

    @FXML
    private Label colorfulnessDetailsLabel;

    @FXML
    private Label RedDetailsLabel;

    @FXML
    private Label GreenDetailsLabel;

    @FXML
    private Label BlueDetailsLabel;

    @FXML
    private Button BackButton;

    @FXML
    private Button ViewButton;

    @FXML
    private Button DeleteButton;

	public Main main;
    
    void setMain(Main main){
    	this.main = main;
    }
    
    @FXML
    void loadImageData(MouseEvent event) {
    	
    	
    	
    	this.RedDetailsLabel.setText("");
    	this.GreenDetailsLabel.setText("");
    	this.BlueDetailsLabel.setText("");
    	this.sizeDetailsLabel.setText("");
    	this.DateDetailsLabel.setText("");
    	//this.colorfulnessDetailsLabel.setText("");
    }
    
    @FXML
    void returnToMainView(ActionEvent event) throws IOException{
    	Parent mainView = FXMLLoader.load(Main.class.getResource("views/ICFXmainView.fxml"));
    	Scene ICFXmainView = new Scene(mainView);
    	this.main.MainStage.setScene(ICFXmainView);
//    	this.main.MainStage.setMaximized(true);
    	this.main.MainStage.show();
    	
    }
    
}
