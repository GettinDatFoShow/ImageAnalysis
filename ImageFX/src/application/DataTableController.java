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
	public static final String Column1MapKey = "P=";
    public static final String Column2MapKey = "N=";
    public static final String Column3MapKey = "D=";
    public static final String Column4MapKey = "R=";
    public static final String Column5MapKey = "G=";
    public static final String Column6MapKey = "B=";
    public static final String Column7MapKey = "C=";
    public static ObservableList<Map> tableMap;
//    public static final String Column2MapKey = "N=";
    @FXML
    private AnchorPane dataTableMainPane;

    @FXML
    private SplitPane dtSplitPane;

    @FXML
    private AnchorPane tableAPane;

    @FXML
    private TableView<Map> dataTableView;

    @FXML
    private TableColumn<Map, String> ImageTcolumn;

    @FXML
    private TableColumn<Map, String> LocationTcoloumn;

    @FXML
    private TableColumn<Map, String> DateColumn;

    @FXML
    private TableColumn<Map, String> RedColumn;

    @FXML
    private TableColumn<Map, String> GreenColumn;

    @FXML
    private TableColumn<Map, String> BlueColumn;

    @FXML
    private TableColumn<Map, String> ColorColumn;
    
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
    void populateTable(){
    	
    	
    		
    		
    	
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		this.ImageTcolumn.setCellValueFactory(new MapValueFactory(Column1MapKey));
      	this.LocationTcoloumn.setCellValueFactory(new MapValueFactory(Column2MapKey));
        this.DateColumn.setCellValueFactory(new MapValueFactory(Column3MapKey));
        this.RedColumn.setCellValueFactory(new MapValueFactory(Column4MapKey));
        this.GreenColumn.setCellValueFactory(new MapValueFactory(Column5MapKey));
        this.BlueColumn.setCellValueFactory(new MapValueFactory(Column6MapKey));
        this.ColorColumn.setCellValueFactory(new MapValueFactory(Column7MapKey));
      	this.dataTableView.setEditable(true);
        this.tableMap = generateDataInMap();
        this.dataTableView.setItems(tableMap);
        this.dataTableView.getSelectionModel().setCellSelectionEnabled(true);
        Callback<TableColumn<Map, String>, TableCell<Map, String>>
            cellFactoryForMap = new Callback<TableColumn<Map, String>,
                TableCell<Map, String>>() {
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
        this.ImageTcolumn.setCellFactory(cellFactoryForMap);
        this.LocationTcoloumn.setCellFactory(cellFactoryForMap);
        this.DateColumn.setCellFactory(cellFactoryForMap);
        this.RedColumn.setCellFactory(cellFactoryForMap);
        this.GreenColumn.setCellFactory(cellFactoryForMap);
        this.BlueColumn.setCellFactory(cellFactoryForMap);
        this.ColorColumn.setCellFactory(cellFactoryForMap);

	}
 
    private ObservableList<Map> generateDataInMap() {
        int max = 10;
        String sql= "select * from Imagecomp;";
	    int	n = adapter.executeQuery(sql);
        ObservableList<Map> allData = FXCollections.observableArrayList();
        for (int i = 0; i < n; i++) {
            Map<String, String> dataRow = new HashMap<>();
 
            String filepath = adapter.getValueAt(i,1);
            String filename = adapter.getValueAt(i, 0);
            String date = adapter.getValueAt(i, 2);
            String red = adapter.getValueAt(i, 3);
            String green = adapter.getValueAt(i, 5);
            String blue = adapter.getValueAt(i, 6);
            String colorfulness = adapter.getValueAt(i, 7);
 
            dataRow.put(Column1MapKey, filepath);
            dataRow.put(Column2MapKey, filename);
            dataRow.put(Column3MapKey, date);
            dataRow.put(Column4MapKey, red);
            dataRow.put(Column5MapKey, green);
            dataRow.put(Column6MapKey, blue);
            dataRow.put(Column7MapKey, colorfulness);            
            
            allData.add(dataRow);
 
        }
        return allData;
    }
}