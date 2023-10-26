package com.example.practice2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView mines;
    Button[][] cells;
    final int WIDTH = 10;
    final int HEIGHT = 10;
    final int MINECONST = 3;
    int MinesCurrent = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mines = findViewById(R.id.TV);
        mines.setText(""+MINECONST+" / "+MinesCurrent);
        generate();
    }
    public void generate(){
        GridLayout layout = findViewById(R.id.GRID);
        layout.removeAllViews();
        layout.setColumnCount(WIDTH);
        LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);

        cells = new Button[HEIGHT][WIDTH];

        for(int i = 0;i<HEIGHT;i++){
            for(int j = 0; j<WIDTH;j++){
                cells[i][j] = (Button) inflater.inflate(R.layout.cell,layout,false);
            }
        }
        for(int i = 0;i<HEIGHT;i++)
            for(int j = 0; j<WIDTH;j++) {
                cells[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        view.setBackgroundColor(Color.RED);
                    }
                });
                cells[i][j].setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        v.setBackgroundColor(Color.BLUE);
                        MinesCurrent--;
                        mines.setText("" + MINECONST + " / " + MinesCurrent);
                        if (MinesCurrent == 0) {
                            Toast.makeText(getApplicationContext(), "WIN!", Toast.LENGTH_LONG).show();
                        }
                        return true;
                    }
                });
                cells[i][j].setText("");
                cells[i][j].setTag("");
                layout.addView(cells[i][j]);

            }
        }

    }
//псевдослучайный генератор "раскидывает" определённое кол-во мин
//двухмерный массив 