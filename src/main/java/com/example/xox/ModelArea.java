package com.example.xox;
import java.util.Random;

public class ModelArea{
    private int [][] area;
    private HelloController helloController;
    private boolean end=false;//победитель есть?
    private boolean next=true;//ходить дальше есть куда?

    public ModelArea(int[][] area) {//т.к и модель и контроллер создать одновременно не получается, но каждый друг в друге нуждается при создании, то контроллер привязывается позже
        this.area = area;
    }
    public void setHelloController(HelloController helloController) {//добавляем контроллер
        this.helloController = helloController;
    }

    public void update(int row,int col,int symb) {//Контроллер присылает запрос на изменение значения ячейки матрицы
        area[row][col]=symb;//меняем ячейку

        for (int i = 0; i < area.length; i++) {//проверяем строку в которой изменилась ячейка на победителя
            if (area[row][i] != symb){break;}
            else if (area[row][i] == symb && i == area.length - 1) end = true;
        }
        if(!end) {//проверяем столбец
            for (int i = 0; i < area.length; i++) {
                if (area[i][col] != symb){break;}
                else if (area[i][col] == symb && i == area.length - 1) end = true;
            }
        }
        if (!end) {//диагональ
            for (int i = 0,j=0; i < area.length; i++,j++) {
                if(area[i][j]!=symb){break;}
                else if(area[i][j]==symb&&i==area.length-1)end=true;
            }
        }
        if (!end) {//2я диагональ
            for (int i = 0,j=area[0].length-1; i < area.length; i++,j--) {
                if(area[i][j]!=symb){break;}
                else if(area[i][j]==symb&&i==area.length-1)end=true;
            }
        }
        next=false;//отключаем возможность хода компа пока не проверим, что возможность есть
        helloController.setCell(area[0].length*row+col, symb);//отправляем запрос контроллеру на изменение View
        for (int i = 0; i <area.length; i++) {
            for (int j = 0; j <area[0].length ; j++) {
                if(area[i][j]==0) {
                    next = true;//если есть ячейка без изменений то даем разрешение на ход
                }
            }
        }
        if(end&&symb==2)helloController.setWin("Машина победила!");//выйгрышная комбинация была и ее знаки О
        else if(end&&symb==1) helloController.setWin("Ты победил(а)!");//выйгрышная комбинация была и ее знаки Х
        else if(symb==1&&next) PCqueue();//если прошлый ход был игрока, и ходить можно то сейчас ходит комп
        else if(!next)helloController.setWin("Ничья!");//если дошло до сюда, то победителя еще нет, и ходить больше некуда
    }
    public void PCqueue(){//случайная чистая клетка для хода пк
        Random random=new Random();
        int row= random.nextInt(area.length);
        int col=random.nextInt(area[0].length);
        while (area[row][col]!= 0) {
            row= random.nextInt(area.length);
            col=random.nextInt(area[0].length);
        }
        update(row,col,2);
    }

    public int[][] getArea() {
        return area;
    }
}
