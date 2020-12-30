package com.example.nick_yen.gameshootapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private boolean isMute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        findViewById(R.id.tv_start).setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, GameActivity.class));
        });

        TextView tv_score = findViewById(R.id.tv_score);

        final SharedPreferences prefs = getSharedPreferences("game", MODE_PRIVATE);
        tv_score.setText("得分: " + prefs.getInt("highscore" , 0));

        isMute = prefs.getBoolean("isMute", false);

        final ImageView iv_vol = findViewById(R.id.iv_vol);

        if (isMute)
            iv_vol.setImageResource(R.drawable.ic_outline_volume_off_24);
        else
            iv_vol.setImageResource(R.drawable.ic_outline_volume_up_24);

        iv_vol.setOnClickListener(v ->  {

                isMute = !isMute;
                if (isMute)
                    iv_vol.setImageResource(R.drawable.ic_outline_volume_off_24);
                else
                    iv_vol.setImageResource(R.drawable.ic_outline_volume_up_24);

                SharedPreferences.Editor editor = prefs.edit();
                editor.putBoolean("isMute", isMute);
                editor.apply();

        });
    }
}