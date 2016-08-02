package com.example.qiao.myboom2;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.Types.BoomType;
import com.nightonke.boommenu.Types.ButtonType;
import com.nightonke.boommenu.Types.PlaceType;
import com.nightonke.boommenu.Util;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private boolean init = false;
    private BoomMenuButton boomMenuButton;
    private BoomMenuButton boomMenuButton2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boomMenuButton = (BoomMenuButton)findViewById(R.id.boom);
        boomMenuButton2=(BoomMenuButton)findViewById(R.id.boom2);
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        // Use a param to record whether the boom button has been initialized
        // Because we don't need to init it again when onResume()
        if (init) return;
        init = true;

        Drawable[] subButtonDrawables = new Drawable[3];
        int[] drawablesResource = new int[]{
                R.drawable.search,
                R.drawable.record,
                R.drawable.mark
        };
        for (int i = 0; i < 3; i++)
            subButtonDrawables[i] = ContextCompat.getDrawable(this, drawablesResource[i]);

        String[] subButtonTexts = new String[]{"乔磊", "闫美玲", "qiaoqiao"};
        final Drawable[] circleSubButtonDrawables = new Drawable[3];
        for (int i = 0; i < 3; i++)
            circleSubButtonDrawables[i]
                    = ContextCompat.getDrawable(this, drawablesResource[i]);
        int[][] subButtonColors = new int[3][2];
        for (int i = 0; i < 3; i++) {
            subButtonColors[i][1] = GetRandomColor();
            subButtonColors[i][0] = Util.getInstance().getPressedColor(subButtonColors[i][1]);
        }
        final String[] circleSubButtonTexts = new String[]{
                "No.1 " ,
                "No.2 ",
                "No.3 "};

        // Now with Builder, you can init BMB more convenient
        new BoomMenuButton.Builder()
                .addSubButton(ContextCompat.getDrawable(this, R.drawable.boom),
                        subButtonColors[0],subButtonTexts[0])
                .addSubButton(ContextCompat.getDrawable(this, R.drawable.java),
                        subButtonColors[0],subButtonTexts[1])
                .addSubButton(ContextCompat.getDrawable(this, R.drawable.github),
                        subButtonColors[0], subButtonTexts[2])
                .button(ButtonType.HAM)
                .boom(BoomType.LINE)
                .place(PlaceType.HAM_3_1)
                .subButtonTextColor(ContextCompat.getColor(this, R.color.black))
                .subButtonsShadow(Util.getInstance().dp2px(2), Util.getInstance().dp2px(2))
                .init(boomMenuButton);
        new BoomMenuButton.Builder()
                .subButtons(circleSubButtonDrawables, subButtonColors, circleSubButtonTexts)
                .button(ButtonType.CIRCLE)
                .boom(BoomType.PARABOLA)
                .place(PlaceType.CIRCLE_3_1)
                .subButtonTextColor(ContextCompat.getColor(this, R.color.black))
                .subButtonsShadow(Util.getInstance().dp2px(2), Util.getInstance().dp2px(2))
                .init(boomMenuButton2);

//        boomMenuButton.init(
//                subButtonDrawables, // The drawables of images of sub buttons. Can not be null.
//                subButtonTexts,     // The texts of sub buttons, ok to be null.
//                subButtonColors,    // The colors of sub buttons, including pressed-state and normal-state.
//                ButtonType.HAM,     // The button type.
//                BoomType.PARABOLA,  // The boom type.
//                PlaceType.HAM_3_1,  // The place type.
//                null,               // Ease type to move the sub buttons when showing.
//                null,               // Ease type to scale the sub buttons when showing.
//                null,               // Ease type to rotate the sub buttons when showing.
//                null,               // Ease type to move the sub buttons when dismissing.
//                null,               // Ease type to scale the sub buttons when dismissing.
//                null,               // Ease type to rotate the sub buttons when dismissing.
//                null                // Rotation degree.
//        );
//
//        boomMenuButton.setTextViewColor(ContextCompat.getColor(this, R.color.black));
//        boomMenuButton.setSubButtonShadowOffset(Util.getInstance().dp2px(2), Util.getInstance().dp2px(2));

    }
    private static String[] Colors = {
            "#F44336",
            "#E91E63",
            "#9C27B0",
            "#2196F3",
            "#03A9F4",
            "#00BCD4",
            "#009688",
            "#4CAF50",
            "#8BC34A",
            "#CDDC39",
            "#FFEB3B",
            "#FFC107",
            "#FF9800",
            "#FF5722",
            "#795548",
            "#9E9E9E",
            "#607D8B"};

    public static int GetRandomColor() {
        Random random = new Random();
        int p = random.nextInt(Colors.length);
        return Color.parseColor(Colors[p]);
    }
}
