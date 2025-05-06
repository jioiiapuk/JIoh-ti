package com.example.xox;

import javafx.scene.control.Button;

public class NButton extends Button {
    private int row;
    private int col;

    public NButton(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int[] getRowCol() {
        return new int[]{row,col};
    }
}
