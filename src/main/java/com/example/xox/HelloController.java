package com.example.xox;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class HelloController {
    @FXML
    private VBox model;

    @FXML
    private Label welcomeText;

    private final ModelArea modelArea;
    private ArrayList<NButton>buttonList=new ArrayList<>();

    public HelloController(ModelArea modelArea) {
        this.modelArea = modelArea;
    }
    @FXML
    public void initialize() {
        int[][] area = modelArea.getArea();
        for (int i = 0; i < area.length; i++) {
            HBox hBox = new HBox();
            hBox.prefHeightProperty().bind(model.heightProperty());//адаптивно растягиваем Hbox по размерам VBox,почемуто не нужно делить Vbox делить на равные части чтоб ряды кнопок были одинаковы по высоте
            for (int j = 0; j < area[0].length; j++) {
                NButton button = new NButton(i, j);//класс кнопки хранящей в себе данные ячейки в матрице к которой она привязана
                button.setOnAction(actionEvent->onHelloButtonClick(button.getRowCol()));//действие по нажатию на неё
                button.setText("");
                button.prefWidthProperty().bind(hBox.widthProperty());//адаптации ширины кнопок
                button.prefHeightProperty().bind(hBox.heightProperty());//высота кнопок подстраивается под высоту hbox
                //button.prefHeightProperty().bind(hBox.heightProperty().divide(area[0].length).subtract(10)); полная форма записи которая делит ширину hbox на количество кнопок и добавляет отступы между ними
                hBox.getChildren().add(button);
                buttonList.add(button);//собираем кнопки в лист чтоб потом можно было найти и изменить конкретную
            }
            model.getChildren().add(hBox);
        }
    }

    protected void onHelloButtonClick(int[]rowCol) {//отправка изменений в модель связь View-Controller-Model
        modelArea.update(rowCol[0],rowCol[1],1);
    }
    public void setWin(String str){//сюда пришлет модель статус окончания матча Model-Controller-View
        welcomeText.setText(str);
        model.setDisable(true);
    }
    public void setCell(int numbButton,int symb) {//изменение ячейки модели присылает сюда запрос на изменение состояния кнопки связь Model-Controller-View
        buttonList.get(numbButton).setText((symb==1)?"X":"O");
        buttonList.get(numbButton).setDisable(true);
    }
}
