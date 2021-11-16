package com.example.javafxco1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    private TableView<People> tableview_List;

    @FXML
    protected void initialize() {
        //
        //TABLEVIEW ADD DATA
        //Define data in ObservableList
        final ObservableList<People> data = FXCollections.observableArrayList();
        try {
            Connection con = DBConnect.getConnection();
            ResultSet rs = con.createStatement().executeQuery("select * from elcademy");

            while (rs.next()){
                data.add(new People(rs.getString("id"),rs.getString("visa")));
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


    }


}
