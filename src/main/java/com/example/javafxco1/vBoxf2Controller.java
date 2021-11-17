package com.example.javafxco1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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

    public void getSelectedItem() throws IOException {

        TVMembers tvMembers = tableview_List.getSelectionModel().getSelectedItem();

        System.out.println(tvMembers.getId());


    }



}
