package at.aau.se2.tickettoride.helpers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;

import androidx.core.content.ContextCompat;

import at.aau.se2.tickettoride.R;
import at.aau.se2.tickettoride.dataStructures.Mission;
import at.aau.se2.tickettoride.databinding.CardMissionBinding;
import at.aau.se2.tickettoride.enums.TrainCards;
import at.aau.se2.tickettoride.models.Missions;

public class ResourceHelper {
    public static Drawable getMissionResource(Context context, int card) {
        switch (card) {
            case 1:
                return ContextCompat.getDrawable(context, R.drawable.calgary_saltlakecity);
            case 2:
                return ContextCompat.getDrawable(context, R.drawable.kansascity_houston);
            case 3:
                return ContextCompat.getDrawable(context, R.drawable.losangeles_chicago);
            case 4:
                return ContextCompat.getDrawable(context, R.drawable.newyork_atlanta);
            case 5:
                return ContextCompat.getDrawable(context, R.drawable.portland_phoenix);
            case 6:
                return ContextCompat.getDrawable(context, R.drawable.seattle_newyork);
            default:
                Bitmap bitmap = textAsBitmap("Mission " + card, 48, Color.RED);
                return new BitmapDrawable(context.getResources(), bitmap);
        }
    }

    public static View getMissionView(Context context, int card) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        CardMissionBinding binding = CardMissionBinding.inflate(inflater);
        Mission mission = Missions.getMissionById(card);
        if (mission != null) {
            binding.destination.setText(String.format("%s - %s", mission.getDestination1(), mission.getDestination2()));
            binding.points.setText(String.format("%s", mission.getPoints()));
        }
        return binding.getRoot();
    }

    public static int getTrainResource(int card) {
        switch (TrainCards.valueOf(card)) {
            case BOX:
                return R.drawable.ic_train_purpur;
            case PASSENGER:
                return R.drawable.ic_train_white;
            case TANKER:
                return R.drawable.ic_train_blue;
            case REEFER:
                return R.drawable.ic_train_yellow;
            case FREIGHT:
                return R.drawable.ic_train_orange;
            case HOPPER:
                return R.drawable.ic_train_black;
            case COAL:
                return R.drawable.ic_train_red;
            case CABOOSE:
                return R.drawable.ic_train_green;
            case LOCOMOTIVE:
                return R.drawable.ic_train;
            default:
                throw new IllegalStateException("Unexpected value: " + card);
        }
    }

    private static Bitmap textAsBitmap(String text, float textSize, int textColor) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setTextSize(textSize);
        paint.setColor(textColor);
        paint.setTextAlign(Paint.Align.LEFT);
        float baseline = -paint.ascent();
        int width = (int) (paint.measureText(text) + 0.5f);
        int height = (int) (baseline + paint.descent() + 0.5f);
        Bitmap image = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(image);
        canvas.drawText(text, 0, baseline, paint);
        return image;
    }
}
