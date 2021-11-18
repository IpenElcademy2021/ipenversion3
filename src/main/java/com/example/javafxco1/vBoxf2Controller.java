package com.example.javafxco1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import javafx.scene.input.MouseEvent;


import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


public class vBoxf2Controller {

    @FXML
    private TableColumn tablecolumnVB2_Id, tablecolumnVB2_Visa;

    @FXML
    private TextField textfield_NID;


    @FXML
    private TableView<TVMembers> tableview_List;

    final ObservableList<TVMembers> data = FXCollections.observableArrayList();



    @FXML
    protected void initialize() {
        //
        //TABLEVIEW ADD DATA
        //Define data in ObservableList

        try {
            Connection con = DBConnect.getConnection();
            ResultSet rs = con.createStatement().executeQuery("select * from elcademy");

            while (rs.next()){
                data.add(new TVMembers(rs.getString("id"),rs.getString("visa")));
                System.out.println(data);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }


        //Associate data with columns
        tablecolumnVB2_Id.setCellValueFactory(new PropertyValueFactory<TVMembers,String>("id"));
        tablecolumnVB2_Visa.setCellValueFactory(new PropertyValueFactory<TVMembers,String>("visa"));

        //add data to table
        tableview_List.setItems(data);





        FilteredList<TVMembers> filteredData = new FilteredList(this.data, (b) -> {
            return true;
        });

        this.textfield_NID.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate((tvMembers) -> {
                if (newValue != null && !newValue.isEmpty()) {
                    String lowerCaseFilter = newValue.toLowerCase();


                    if (tvMembers.getId().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true;
                    } else if (tvMembers.getVisa().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true;
                    } else {
                        return String.valueOf(tvMembers.getVisa()).indexOf(lowerCaseFilter) != -1;
                    }
                } else {
                    return true;
                }
            });
        });
        SortedList<TVMembers> sortedData = new SortedList(filteredData);
        sortedData.comparatorProperty().bind(this.tableview_List.comparatorProperty());
        this.tableview_List.setItems(sortedData);

    }



    private Stage stage;
    private Scene scene;
    private Parent root;
    public static String id;
    public void getSelectedItem() throws IOException {

        TVMembers tvMembers = tableview_List.getSelectionModel().getSelectedItem();
        System.out.println(tvMembers.getId());

        FXMLLoader loader = new FXMLLoader(getClass().getResource("oldvBoxf3.fxml"));
        root = loader.load();
        vBoxf3Controller vBoxf3Controller = loader.getController();

        vBoxf3Controller.label_visa.setText("SIAJFDIASFASFASFSA");
        vBoxf3Controller.setVisa("SIAJFDIASFASFASFSA");
        vBoxf3Controller.updateLabel("SIAJFDIASFASFASFSA");

    }

    public void login(ActionEvent event) throws IOException{
        TVMembers tvMembers = tableview_List.getSelectionModel().getSelectedItem();
        System.out.println(tvMembers.getId());
        GetSingleRestAPI(event, tvMembers.getId());
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("oldvBoxf3.fxml"));
//        root = loader.load();
//
//        vBoxf3Controller vBoxf3Controller = loader.getController();
//
//        vBoxf3Controller.label_insVIsa.setText("SIAJFDIASFASFASFSA");
//        vBoxf3Controller.setVisa("SIAJFDIASFASFASFSA");
//        vBoxf3Controller.updateLabel("SIAJFDIASFASFASFSA");
//
//        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        scene = new Scene(root, 1100, 750);
//        stage.setScene(scene);
//        stage.show();
    }

    public void login1(ActionEvent event) throws IOException{
        TVMembers tvMembers = tableview_List.getSelectionModel().getSelectedItem();
        System.out.println(tvMembers.getId());

        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        HelloController helloController= loader1.getController();
        root = loader1.load();


        FXMLLoader loader = new FXMLLoader(getClass().getResource("vBoxf3.fxml"));


        vBoxf3Controller vBoxf3Controller = loader.getController();

        vBoxf3Controller.label_visa.setText("SIAJFDIASFASFASFSA");
        vBoxf3Controller.setVisa("SIAJFDIASFASFASFSA");
        vBoxf3Controller.updateLabel("SIAJFDIASFASFASFSA");

        VBox vBoxf1 = FXMLLoader.load((getClass().getResource("vBoxf3.fxml")));
        helloController.vbox_fragment1.getChildren().setAll(vBoxf1);


        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1100, 750);
        stage.setScene(scene);
        stage.show();
    }


    OkHttpClient client = new OkHttpClient();
    String responsestring = "";


//    @FXML
//    public void getSelectedRecord(MouseEvent mouseEvent) throws IOException {
//
//        TVMembers tvMembers = tableview_List.getSelectionModel().getSelectedItem();
//        System.out.println(tvMembers.getId());
//
//        GetSingleRestAPI(null , tvMembers.getId());
//
//
//    }

    public void GetSingleRestAPI(ActionEvent event, String selectedid) throws IOException {
        System.out.println("GetSingleResAPI CALLED");
        Request r = new Request.Builder()
                .url("http://localhost:8080/elcam/find/" + selectedid).build();

        try {
            Response resp = client.newCall(r).execute();
            String jsonstring = resp.body().string();

            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(jsonstring);
            System.out.println(jsonObject);

            String Id = (String) jsonObject.get("id").toString();
            String Visa = (String) jsonObject.get("visa");
            String Nom = (String) jsonObject.get("nom");
            String Prenom = (String) jsonObject.get("prenom");
            String Job = (String) jsonObject.get("job");
            String PhoneNumber = (String) jsonObject.get("phoneNumber");

            System.out.println(Visa);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("vBoxf3.fxml"));

            root = loader.load();

            vBoxf3Controller vBoxf3Controller = loader.getController();

            vBoxf3Controller.label_visa.setText(Visa);;
            vBoxf3Controller.label_job.setText(Job);;
            vBoxf3Controller.label_id.setText(Id);;
            vBoxf3Controller.label_phone.setText(PhoneNumber);;
            vBoxf3Controller.label_nom.setText(Nom);;
            vBoxf3Controller.label_prenom.setText(Prenom);;

            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root, 1100, 750);
            stage.setScene(scene);
            stage.show();

        } catch (IOException exception) {

        } catch (ParseException parseException) {
            parseException.printStackTrace();
        }

    }


}
