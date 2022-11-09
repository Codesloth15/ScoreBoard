package codesloth.com.score_board;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class BoardActivity extends AppCompatActivity {
    TextView blue_Board, orange_Board,player1_ID,player2_ID,goal;
    Button add_BlueButton, sub_BlueButton, add_OrangeButton, sub_OrangeButton, orange_Reset, blue_Reset;
    Intent intentExtra;
    Dialog dialog;
    Button yes_Button;
    Button no_Button;
    TextView text;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);
        initializingVariables();
        onClick();
        getIntentExtra();
        winCondition();

    }
    //getting the extra
    @SuppressLint("SetTextI18n")
    public void getIntentExtra(){

        String playerOneName=intentExtra.getStringExtra("PlayerOneName");
        String playerTwoName=intentExtra.getStringExtra("PlayerTwoName");
        String goalSetter=intentExtra.getStringExtra("GoalSetter");
        player1_ID.setText(playerOneName);
        player2_ID.setText(playerTwoName);
        if(!goalSetter.equalsIgnoreCase("None")&&!goalSetter.equals("")) {
            goal.setText(" Goal: " + goalSetter);
        }else {
            goal.setText(" Goal: None");
        }
    }
    @SuppressLint("SetTextI18n")
    public void onClick() {
         Toast toast = Toast.makeText(BoardActivity.this,"done",Toast.LENGTH_SHORT);


        add_BlueButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {

                int x = Integer.parseInt(blue_Board.getText().toString());
                    x++;
                    if (x < 10) {
                        blue_Board.setText("0" +x);
                    } else {
                        blue_Board.setText(String.valueOf(x));
                    }
                winCondition();

            }

        });

        sub_BlueButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                int y = Integer.parseInt(blue_Board.getText().toString());
                if (y >0) {
                    y--;
                    if (y < 10) {
                        blue_Board.setText("0" + y);
                    } else {
                        blue_Board.setText(String.valueOf(y));
                    }
                }

            }
        });

        add_OrangeButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                int x = Integer.parseInt(orange_Board.getText().toString());
                x++;
                if (x < 10) {
                    orange_Board.setText("0"+x);
                } else {
                    orange_Board.setText(String.valueOf(x));
                }
                winCondition();
            }
        });

        sub_OrangeButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                int y = Integer.parseInt(orange_Board.getText().toString());
                if (y >0) {
                    y--;
                    if (y < 10) {
                        orange_Board.setText("0"+ y);
                    } else {
                       orange_Board.setText(String.valueOf(y));
                    }
                }


            }
        });

        orange_Reset.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                dialog.setContentView(R.layout.exitdialog);
                img= dialog.findViewById(R.id.logo);
                yes_Button =dialog.findViewById(R.id.yes_Button);
                no_Button = dialog.findViewById(R.id.no_Button);
                text = dialog.findViewById(R.id.Exit_Text);
                text.setText("Reset Score?");
                String x = orange_Board.getText().toString();
                text.setText("Reset Score?");
                img.setImageResource(R.drawable.reset);
                if(!x.equals("00")) {

                    yes_Button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            orange_Board.setText("00");
                            dialog.dismiss();
                        }
                    });
                    no_Button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                }else {
                toast.show();
                }


            }
        });

        blue_Reset.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                dialog.setContentView(R.layout.exitdialog);
                img= dialog.findViewById(R.id.logo);
                yes_Button =dialog.findViewById(R.id.yes_Button);
                no_Button = dialog.findViewById(R.id.no_Button);
                text = dialog.findViewById(R.id.Exit_Text);
                text.setText("Reset Score?");
                String y = blue_Board.getText().toString();
                text.setText("Reset Score?");
                img.setImageResource(R.drawable.reset);
             if (!y.equals("00")) {

                 yes_Button.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View view) {
                         blue_Board.setText("00");
                         dialog.dismiss();
                     }
                 });
                 no_Button.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View view) {
                         dialog.dismiss();
                     }

                 });
dialog.show();
             }else {
                 toast.show();
             }

            }
        });


    }
    @SuppressLint("UseCompatLoadingForDrawables")
    public void initializingVariables() {
        blue_Board = findViewById(R.id.blue_Board);
        orange_Board = findViewById(R.id.orange_Board);
        add_BlueButton = findViewById(R.id.add_BlueButton);
        sub_BlueButton = findViewById(R.id.sub_BlueButton);
        add_OrangeButton = findViewById(R.id.add_OrangeButton);
        sub_OrangeButton = findViewById(R.id.sub_OrangeButton);
        orange_Reset = findViewById(R.id.orange_Reset);
        blue_Reset = findViewById(R.id.blue_Reset);
        player2_ID = findViewById(R.id.player2_ID);
        player1_ID=findViewById(R.id.player1_ID);
        goal=findViewById(R.id.goal);
        intentExtra=getIntent();
        dialog = new Dialog(BoardActivity.this);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.windialog));
    }
    //Create Win Alert Dialog
    @SuppressLint({"SetTextI18n", "UseCompatLoadingForDrawables"})
    public void winCondition() {
        String goalSetter = intentExtra.getStringExtra("GoalSetter");
        String orangeBoard = orange_Board.getText().toString();
        String blueBoard = blue_Board.getText().toString();
        String playerName1 = player1_ID.getText().toString();
        String playerName2 = player2_ID.getText().toString();
        dialog.setContentView(R.layout.windialog);
        Button continue_Button = dialog.findViewById(R.id.continue_Button);
        TextView win = dialog.findViewById(R.id.win_Text);
        if (!goalSetter.equals("0") && !goalSetter.isEmpty()) {
            if (Integer.parseInt(goalSetter) < 10) {
                goalSetter = "0" + goalSetter;
            }
        }
        if (blueBoard.equals(goalSetter)&&!orangeBoard.equals(goalSetter)) {
            win.setText(playerName1 + " is the Winner");
            continue_Button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(BoardActivity.this,MainMenuActivity.class));
                    finish();
                }
            });
            dialog.show();
        }
        if (orangeBoard.equals(goalSetter)&&!blueBoard.equals(goalSetter)){
            win.setText(playerName2 + " is the Winner");
            continue_Button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(BoardActivity.this,MainMenuActivity.class));
                    finish();
                }
            });
            dialog.show();
        }

    }
    @SuppressLint({"UseCompatLoadingForDrawables", "SetTextI18n"})
    @Override
    public void onBackPressed() {
        dialog.setContentView(R.layout.exitdialog);
        yes_Button =dialog.findViewById(R.id.yes_Button);
        no_Button = dialog.findViewById(R.id.no_Button);
        text = dialog.findViewById(R.id.Exit_Text);
        text.setText("back to main menu?");
    yes_Button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(BoardActivity.this,MainMenuActivity.class));
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