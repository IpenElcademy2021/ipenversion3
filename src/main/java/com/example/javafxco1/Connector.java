package com.example.javafxco1;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Connector {

    public static void Connecting(HelloController controller) {
        System.out.println("Connector.Connecting(): Called");
        controller.setCurrentStatusText("Bye World");
    }

}
