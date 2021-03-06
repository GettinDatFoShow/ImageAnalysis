package application;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;
import java.util.HashMap;
import java.util.Map;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;

public class DataTableController implements Initializable{
	public JDBCAdapter adapter = Main.adapter;
//	public static final String Column1MapKey = "P=";
//    public static final String Column2MapKey = "N=";
//    public static final String Column3MapKey = "D=";
//    public static final String Column4MapKey = "R=";
//    public static final String Column5MapKey = "G=";
//    public static final String Column6MapKey = "B=";
//    public static final String Column7MapKey = "C=";
    public static ObservableList<ImageRecord> tableRecord;
//    public static final String Column2MapKey = "N=";
    @FXML
    private AnchorPane dataTableMainPane;

    @FXML
    private SplitPane dtSplitPane;

    @FXML
    private AnchorPane tableAPane;

    @FXML
    private TableView<ImageRecord> dataTableView;

    @FXML
    private TableColumn<ImageRecord, String> NameColumn;

    @FXML
    private TableColumn<ImageRecord, String> PathColumn;

    @FXML
    private TableColumn<ImageRecord, String> DateColumn;

    @FXML
    private TableColumn<ImageRecord, String> RedColumn;

    @FXML
    private TableColumn<ImageRecord, String> GreenColumn;

    @FXML
    private TableColumn<ImageRecord, String> BlueColumn;

    @FXML
    private TableColumn<ImageRecord, String> ColorColumn;
    
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
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.NameColumn.setCellValueFactory(new PropertyValueFactory<>("filename"));
      	this.PathColumn.setCellValueFactory(new PropertyValueFactory<>("filepath"));
        this.DateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        this.RedColumn.setCellValueFactory(new PropertyValueFactory<>("red"));
        this.GreenColumn.setCellValueFactory(new PropertyValueFactory<>("green"));
        this.BlueColumn.setCellValueFactory(new PropertyValueFactory<>("blue"));
        this.ColorColumn.setCellValueFactory(new PropertyValueFactory<>("colorfulness"));
      	this.dataTableView.setEditable(true);
        this.tableRecord = getImageRecords();
        this.dataTableView.setItems(tableRecord);
        this.dataTableView.getSelectionModel().setCellSelectionEnabled(true);
        Callback<TableColumn<ImageRecord, String>, TableCell<ImageRecord, String>>
            cellFactoryForProperty = new Callback<TableColumn<ImageRecord, String>,
                TableCell<ImageRecord, String>>() {
                    @SuppressWarnings("unchecked")
					@Override
                    public TableCell call(TableColumn p) {
                        return new TextFieldTableCell(new StringConverter() {
                            @Override
                            public String toString(Object t) {
                                return t.toString();
                            }
                            @Override
                            public Object fromString(String string) {
                                return string;
                            }                                    
                        });
                    }
        };
        this.NameColumn.setCellFactory(cellFactoryForProperty);
        this.PathColumn.setCellFactory(cellFactoryForProperty);
        this.DateColumn.setCellFactory(cellFactoryForProperty);
        this.RedColumn.setCellFactory(cellFactoryForProperty);
        this.GreenColumn.setCellFactory(cellFactoryForProperty);
        this.BlueColumn.setCellFactory(cellFactoryForProperty);
        this.ColorColumn.setCellFactory(cellFactoryForProperty);
	}
 
    private ObservableList<ImageRecord> getImageRecords() {
        int max = 10;
        String sql= "select * from ImageFX;";
	    int	n = adapter.executeQuery(sql);
        ObservableList<ImageRecord> allData = FXCollections.observableArrayList();
        for (int i = 0; i < n; i++) {
            String fp = adapter.getValueAt(i,1);
            String fn = adapter.getValueAt(i, 0);
            String d = adapter.getValueAt(i, 2);
            String r = adapter.getValueAt(i, 3);
            String g = adapter.getValueAt(i, 4);
            String b = adapter.getValueAt(i, 5);
            String c = adapter.getValueAt(i, 6);
//            String h = adapter.getValueAt(i, 7);
            ImageRecord record = new ImageRecord(fp, fn, d, r, g, b, c);        
            
            allData.add(record);
 
        }
        return allData;
    }
}
