package com.example.javafxco1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import okhttp3.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class vBoxf1Controller {
    @FXML
    private TreeView treeview_SideMenu;
    @FXML
    ImageView image_Home, image_Office, image_User, image_Office2, image_Exit, image_UserS2 = new ImageView();
    @FXML
    private TextField textfield_reg_id, txtf_Username;
    @FXML
    private PasswordField passwordfield_Password;
    @FXML
    private TableColumn tableColumn_id, tableColumn_job, tableColumn_nom, tableColumn_password, tableColumn_phone, tableColumn_prenom, tableColumn_visa;
    @FXML
    private TableView tableview_registration;




    @FXML
    private VBox vbox_fragment1;


    OkHttpClient client = new OkHttpClient();

    public void GetRestAPI(ActionEvent e) throws IOException {
        Request r = new Request.Builder()
                .url("http://localhost:8080/elcam/all").build();
        try {
            Response resp = client.newCall(r).execute();

            JSONParser parser = new JSONParser();
            Object obj = parser.parse(resp.body().string());
            JSONArray jsonArray = (JSONArray)obj;

            String Id = "", Visa = "", Nom = "", Prenom = "", Job = "", Password = "", phoneNumber = "";

            ObservableList<jsonObject> data = FXCollections.observableArrayList();

            for (var i = 0; i < jsonArray.toArray().length; i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                Id = jsonObject.get("id").toString();
                Visa = jsonObject.get("visa").toString();
                Nom = jsonObject.get("nom").toString();
                Prenom = jsonObject.get("prenom").toString();
                Job = jsonObject.get("job").toString();
                Password = jsonObject.get("password").toString();
                phoneNumber = jsonObject.get("phoneNumber").toString();

                data.add(new jsonObject(Id, Visa, Nom, Prenom, Job, Password, phoneNumber));

            }

            tableColumn_id.setCellValueFactory(new PropertyValueFactory<jsonObject,String>("Id"));
            tableColumn_visa.setCellValueFactory(new PropertyValueFactory<jsonObject,String>("visa"));
            tableColumn_nom.setCellValueFactory(new PropertyValueFactory<jsonObject,String>("nom"));
            tableColumn_prenom.setCellValueFactory(new PropertyValueFactory<jsonObject,String>("prenom"));
            tableColumn_job.setCellValueFactory(new PropertyValueFactory<jsonObject,String>("job"));
            tableColumn_password.setCellValueFactory(new PropertyValueFactory<jsonObject,String>("password"));
            tableColumn_phone.setCellValueFactory(new PropertyValueFactory<jsonObject,String>("phoneNumber"));




            tableview_registration.setItems(data);


        }

        catch (IOException exception) {

        } catch (ParseException parseException) {
            parseException.printStackTrace();
        }
    }

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public void PostRestAPI(ActionEvent e) throws IOException {

        String json = "";

        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder().url("http://localhost:8080/elcam/add").post(body).build();

        System.out.println(json);

        try (Response response = client.newCall(request).execute()) {
            System.out.println(response.body().string());
        }

    }


    public void UpdateRestAPI(ActionEvent e) throws IOException {

        String json = "";

        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder().url("http://localhost:8080/elcam/update/" + textfield_reg_id.getText()).put(body).build();
        try (Response response = client.newCall(request).execute()) {
            System.out.println(response.body().string());
        }
    }


    public void DeleteRestAPI(ActionEvent e) throws IOException {

        Request request = new Request.Builder().url("http://localhost:8080/elcam/delete/" + textfield_reg_id.getText()).delete().build();
        try (Response response = client.newCall(request).execute()) {
            System.out.println(response.body().string());
        }
    }

}
