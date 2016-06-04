package pinta.com.ua.horsetask;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.letter1)
    EditText mEtLetter1;
    @BindView(R.id.letter2)
    EditText mEtLetter2;
    @BindView(R.id.number1)
    EditText mEtNumber1;
    @BindView(R.id.number2)
    EditText mEtNumber2;
    @BindView(R.id.btn_calculate)
    Button mBtnCalculate;
    private int[][] desk = new int[8][8];

    @OnClick(R.id.btn_calculate)
    public void onClickButton(View view) {
        if (validFields()) {
            makeCalculation();
        }
    }

    private void makeCalculation() {
        desk = new int[8][8];

        int letter1 = reparseLetter(mEtLetter1.getText().toString());
        int letter2 = reparseLetter(mEtLetter2.getText().toString());
        int number1 = Integer.parseInt(mEtNumber1.getText().toString());
        int number2 = Integer.parseInt(mEtNumber2.getText().toString());

        int[] horse1 = {letter1, number1};
        int[] horse2 = {letter2, number2};

        for (int i = 0; i < desk.length*desk.length; i++) {
            if (!horseLogic(horse1)) {
                if (horseLogic(horse2)) {
                    Toast.makeText(getApplicationContext(), "Count: " + (i + 1), Toast.LENGTH_SHORT).show();
                    break;
                }
            } else {
                Toast.makeText(getApplicationContext(), "Count: " + (i + 1), Toast.LENGTH_SHORT).show();
                break;
            }

        }
    }

    private int reparseLetter(String letter) {
        switch (letter) {
            case "a":
                return 1;

            case "b":
                return 2;

            case "c":
                return 3;

            case "d":
                return 4;

            case "e":
                return 5;

            case "f":
                return 6;

            case "g":
                return 7;

            case "h":
                return 8;

            default:
                return 0;
        }
    }

    private boolean validFields() {
        if (mEtLetter1.getText().length() != 0 && mEtLetter2.getText().length() != 0 &&
                mEtNumber1.getText().length() != 0 && mEtNumber2.getText().length() != 0) {
            return true;
        } else {
            Toast.makeText(this, "Field can not be empty", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    private boolean horseLogic(int[] horse) {
        if (!generate(horse[0] - 1, horse[1] - 2)) {
            if (!generate(horse[0] + 1, horse[1] - 2))
                if (!generate(horse[0] - 2, horse[1] - 1))
                    if (!generate(horse[0] + 2, horse[1] - 1))
                        if (!generate(horse[0] - 2, horse[1] + 1))
                            if (!generate(horse[0] + 2, horse[1] + 1))
                                if (!generate(horse[0] - 1, horse[1] + 2))
                                    if (!generate(horse[0] + 1, horse[1] + 2))
                                        return false;
        }
        return true;
    }

    private boolean generate(int i, int j) {
        if (0 <= i && i <= 7 && 0 <= j && j <= 7) {
            if (desk[i][j] != 0) {
                return true;
            } else {
                desk[i][j] = 1;
                return false;
            }
        }
        return false;
    }
}
