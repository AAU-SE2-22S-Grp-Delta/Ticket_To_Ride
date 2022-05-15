package at.aau.se2.tickettoride.models;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.View;

public class DrawView extends View
{
    Bitmap bm;
    Canvas canvas;
    public DrawView(Context context)
    {
        super(context);
        bm = Bitmap.createBitmap(3840, 2160, Bitmap.Config.ARGB_8888);
        canvas = new Canvas();
    }
}
