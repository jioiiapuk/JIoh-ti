package com.example.xox;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        ModelArea modelArea=new ModelArea(new int[5][5]);//создаем матрицу любого размера, насчет квадратности не пробывал
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        HelloController helloController=new HelloController(modelArea);//создаем контроллер
        modelArea.setHelloController(helloController);//привязываем контроллер к матрице
        fxmlLoader.setController(helloController);//привязываем контроллер к view
        Scene scene = new Scene(fxmlLoader.load(), 320, 320);
        stage.setTitle("Крестики нолики");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}