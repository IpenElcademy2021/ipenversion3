package com.example.javafxco1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import okhttp3.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.*;
import java.sql.Connection;


public class HelloController {
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
    private Label label_currentstatus;
    public static Label static_Label;


    @FXML
    private Label label_CS;

    @FXML
     VBox vbox_fragment1;

    //
    private Stage stage;
    private Scene scene;
    private Parent root;

    MethodClass MC = new MethodClass();





    @FXML
    protected void initialize() throws IOException {

        //Populating TREEVIEW
        TreeItem<String> rootItem = new TreeItem<>("Files");
        //TreeItem<String> rootItem = new TreeItem<>("Files", new ImageView(new Image("Folder_Icon.png")));

        TreeItem<String> branchItem1 = new TreeItem<>("Assures");
        TreeItem<String> branchItem2 = new TreeItem<>("Prestation");
        TreeItem<String> branchItem3 = new TreeItem<>("Rassemblement de CI");
        TreeItem<String> branchItem4 = new TreeItem<>("Compte individuel");


        TreeItem<String> leafItem1 = new TreeItem<>("CI Orphelin");
        TreeItem<String> leafItem2 = new TreeItem<>("Confirmation a");
        TreeItem<String> leafItem3 = new TreeItem<>("Inscription CI re");
        TreeItem<String> leafItem4 = new TreeItem<>("Revocation");

        TreeItem<String> leafItem5 = new TreeItem<>("Ouverture de CI");
        TreeItem<String> leafItem6 = new TreeItem<>("Inscriptin CI");

        TreeItem<String> leafItem7 = new TreeItem<>("Confirmation");

        branchItem3.getChildren().addAll(leafItem1, leafItem2, leafItem3, leafItem4);
        branchItem4.getChildren().addAll(leafItem5, leafItem6);
        leafItem5.getChildren().addAll(leafItem7);

        rootItem.getChildren().addAll(branchItem1, branchItem2, branchItem3, branchItem4);

        treeview_SideMenu.setShowRoot(false);
        treeview_SideMenu.setRoot(rootItem);

        SwitchToFragment1();

        static_Label = label_currentstatus;
    }





    public void switchToScene1(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root, 1100, 750);
        stage.setScene(scene);
        scene.getStylesheets().add("Stylesheet.css");
        stage.show();
    }

    public void switchToScene2(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Scene2.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root, 1100, 750);
        stage.setScene(scene);
        scene.getStylesheets().add("Stylesheet.css");
        stage.show();
    }

    public void SwitchToFragment1() throws IOException {
        VBox vBoxf1;
        vBoxf1 = FXMLLoader.load((getClass().getResource("vBoxf1.fxml")));
        vbox_fragment1.getChildren().setAll(vBoxf1);
    }

    public void SwitchToFragment2() throws IOException {
        VBox vBoxf2 = FXMLLoader.load((getClass().getResource("vBoxf2.fxml")));
        vbox_fragment1.getChildren().setAll(vBoxf2);
    }

    public void SwitchToFragment3() throws IOException {
        VBox vBoxf3 = FXMLLoader.load((getClass().getResource("vBoxf3.fxml")));
        vbox_fragment1.getChildren().setAll(vBoxf3);
    }

    public void SwitchToFragment4() throws IOException {
        VBox vBoxf4 = FXMLLoader.load((getClass().getResource("vBoxf4.fxml")));
        vbox_fragment1.getChildren().setAll(vBoxf4);
    }

    public void setCurrentStatusText(String currentstatustext) {
        label_currentstatus.setText(currentstatustext);

        System.out.println("DD");
    }


    @FXML
    public void clickItem(MouseEvent event)
    {
        if (event.getClickCount() == 2) //Checking double click
        {
            String id = tableview_registration.getSelectionModel().getSelectedItem().toString();

            System.out.println(tableview_registration.getSelectionModel().getSelectedItem());

        }
    }

//    TVMembers tvMembers = new TVMembers();

    public void getSelectedItem() throws IOException {

//        TVMembers tvMembers = tableview_registration.getSelectionModel().getSelectedItem();
//        System.out.println(tvMembers.getNom());


    }



    Connection con;
    PreparedStatement prepstatment;
    ResultSet rs;
    String userrole, AccStatus = "";
    public void login(ActionEvent e) {
        String uname = txtf_Username.getText();
        String passw = passwordfield_Password.getText();


        if(uname.equals("") || passw.equals(""))
        {
            MC.MessageBox("Username or Password is BLANK!", "Warning!");
        }
        else
        {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost/rau", "root", "rauelcadb");

                prepstatment = con.prepareStatement("select * from rauauthentication where username=? and password=?");

                prepstatment.setString(1, uname);
                prepstatment.setString(2, passw);

                rs = prepstatment.executeQuery();


                if (rs.next()) {
                    AccStatus = rs.getString(5);
                    userrole = rs.getString(4);

                    if (AccStatus.equals("0")) {
                        MC.MessageBox("Account disabled, please contact an ADMIN", "DEAD Account");
                    } else {
                        if (userrole.equals("1")) {
                            MC.MessageBox("You have been logged in! as a User.", "Success! (User)");
                        } else if (userrole.equals("2")) {
                            MC.MessageBox("You have been logged in! as an ADMIN.", "Success! (ADMIN)");
                        }
                    }
                }
                else {
                    MC.MessageBox("Failed login attempt, try again!", "Failure!");
                    txtf_Username.setText("");
                    passwordfield_Password.setText("");
                    txtf_Username.requestFocus();
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }
        }
    }

}