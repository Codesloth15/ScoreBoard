package codesloth.com.score_board;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainMenuActivity extends AppCompatActivity {
    EditText player_Name1, player_Name2, goal_Setter;
    Button start_Button;
    String goalSetter;
    int reader;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        initializingVariables();
        startButtonOnClick();

    }

    public void initializingVariables() {
        player_Name1 = findViewById(R.id.player_Name1);
        player_Name2 = findViewById(R.id.player_Name2);
        goal_Setter = findViewById(R.id.goal_Setter);
        start_Button = findViewById(R.id.start_Button);
        dialog = new Dialog(MainMenuActivity.this);
        player_Name1.setFilters(new InputFilter[] {new InputFilter.LengthFilter(10)});
        player_Name2.setFilters(new InputFilter[] {new InputFilter.LengthFilter(10)});
        goal_Setter.setFilters(new InputFilter[] {new InputFilter.LengthFilter(3) {
        }});

    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public void AlertDialogForName() {
        dialog.setContentView(R.layout.namealertdialog);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.windialog));
        Button ok_Button = dialog.findViewById(R.id.ok_Button);
        ok_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainMenuActivity.this, "name should not be empty", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void startButtonOnClick() {
        start_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String player1 = player_Name1.getText().toString();
                String player2 = player_Name2.getText().toString();
                if (player1.isEmpty() || player2.isEmpty()) {
                    AlertDialogForName();
                } else {
                    alertDialogForGoal();
                }
            }
        });
    }

    @SuppressLint("SetTextI18n")
    public void alertDialogForGoal() {
        reader = 0;
        int[] x = new int[1000];
        int counter = 0;
        x[0] = 0;
        for (int j = 0; j < x.length; j++) {
            x[j] = counter;
            counter++;
        }

        // create variable in Sting
        String playerOneName = player_Name1.getText().toString();
        String playerTwoName = player_Name2.getText().toString();
        goalSetter = goal_Setter.getText().toString();
        //apply intent

        for (int i : x) {
            if (goalSetter.equals(String.valueOf(i)) || goalSetter.equals("0")) {
                reader++;
            }
        }
        if (reader > 0) {
            reader = 0;
            Intent intent = new Intent(MainMenuActivity.this, BoardActivity.class);

            intent.putExtra("PlayerOneName", playerOneName);
            intent.putExtra("PlayerTwoName", playerTwoName);
            intent.putExtra("GoalSetter", goalSetter);
            startActivity(intent);
            goalSetter = "None";
            finish();
        } else {
            dialog.setContentView(R.layout.namealertdialog);
            TextView text = dialog.findViewById(R.id.win_Text);
            text.setText("invalid goal  ");
            dialog.show();
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onBackPressed() {
        dialog.setContentView(R.layout.exitdialog);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.windialog));
        Button yes_Button = dialog.findViewById(R.id.yes_Button);
        Button no_Button = dialog.findViewById(R.id.no_Button);
        yes_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        no_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}