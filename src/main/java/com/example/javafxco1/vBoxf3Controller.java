package com.example.javafxco1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import okhttp3.Request;
import okhttp3.Response;

import okhttp3.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static com.example.javafxco1.HelloController.static_Label;
import static com.example.javafxco1.vBoxf2Controller.id;

public class vBoxf3Controller {

    @FXML
    private Button button_return;


    @FXML
     Label label_job, label_phone, label_prenom, label_nom,label_visa, label_id;



    @FXML
    protected void initialize() throws IOException {
//        label_insVIsa.setText("00000000000000000000000");

    }



    public void updateLabel(String data) {
        label_visa.setText(data);

    }


    OkHttpClient client = new OkHttpClient();
    String responsestring = "";


    public void GetSingleRestAPI(String selectedid) throws IOException {
        System.out.println("GetSingleResAPI CALLED");
        Request r = new Request.Builder()
                .url("http://localhost:8080/elcam/find/" + selectedid).build();

        try {
            Response resp = client.newCall(r).execute();
            String jsonstring = resp.body().string();
//            System.out.println(resp.body().string());

            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(jsonstring);
            System.out.println(jsonObject);

            String Visa = (String) jsonObject.get("visa");
            System.out.println(Visa);
            label_visa.setText("xd");

        } catch (IOException exception) {

        } catch (ParseException parseException) {
            parseException.printStackTrace();
        }
    }

    public void setVisa(String currentstatustext) {
        label_visa.setText(currentstatustext);
    }


    public void buttonReturn(ActionEvent event) throws IOException {


    }

}
