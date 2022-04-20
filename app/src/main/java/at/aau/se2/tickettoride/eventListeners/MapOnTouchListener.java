package at.aau.se2.tickettoride.eventListeners;

import android.view.MotionEvent;
import android.view.View;

public class MapOnTouchListener implements View.OnTouchListener, View.OnLayoutChangeListener {
    //TODO: When the map zooms out, the off gets visible. When scrolling for a bit, the window jumps into the bounds. This is to be fixed

    //Attributes for controlls
    View window; //fragment which is scrolled
    View target; //fragment which is zoomed

    //Attributes for scrolling
    private float mx, my, curX, curY;
    private boolean scrollingStarted = false;
    private int scrollBoundX, scrollBoundY;

    //Attributes for zooming
    private float zoomLength0, zoomLength;
    private float scale = 1f, minScale = -1, maxScale = 2; //TODO: what is a good max scale?
    private boolean zoomingStarted = false;


    public MapOnTouchListener(View window, View target) {
        this.window = window;
        this.target = target;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        switch (motionEvent.getPointerCount()) {
            case 1: { //occurs when there is one finger on the screen
                curX = motionEvent.getX();
                curY = motionEvent.getY();
                int dx = (int) (mx - curX);
                int dy = (int) (my - curY);

                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_MOVE:
                        if (scrollingStarted) {
                            if (window.getScrollX() + dx < -scrollBoundX)
                                window.setScrollX(-scrollBoundX);
                            else if (window.getScrollX() + dx > scrollBoundX)
                                window.setScrollX(scrollBoundX);
                            else window.scrollBy(dx, 0);
                            if (window.getScrollY() + dy < -scrollBoundY)
                                window.setScrollY(-scrollBoundY);
                            else if (window.getScrollY() + dy > scrollBoundY)
                                window.setScrollY(scrollBoundY);
                            else window.scrollBy(0, dy);
                        } else {
                            scrollingStarted = true;
                        }
                        mx = curX;
                        my = curY;
                        break;
                    case MotionEvent.ACTION_UP:
                        scrollingStarted = false;
                        break;
                }

            }
            break;

            case 2: {  //when there are two fingers on the screen
                float x0 = motionEvent.getX(0);
                float y0 = motionEvent.getY(0);
                float x1 = motionEvent.getX(1);
                float y1 = motionEvent.getY(1);

                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_MOVE:
                        if (zoomingStarted) {
                            zoomLength = (float) Math.sqrt(Math.pow(x0 - x1, 2) + Math.pow(y0 - y1, 2));

                            float tempScale = scale * zoomLength / zoomLength0;
                            if (tempScale < minScale) tempScale = minScale;
                            if (tempScale > maxScale) tempScale = maxScale;
                            target.setScaleX(tempScale);
                            target.setScaleY(tempScale);
                        } else {
                            zoomLength0 = (float) Math.sqrt(Math.pow(x0 - x1, 2) + Math.pow(y0 - y1, 2));
                            zoomingStarted = true;
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        zoomingStarted = false;
                        scale *= zoomLength / zoomLength0;
                        if (scale < minScale) scale = minScale;
                        if (scale > maxScale) scale = maxScale;
                        target.setScaleX(scale);
                        target.setScaleY(scale);
                        calculateScrollBounds();
                        break;
                }
            }
            break;
        }
        return true;
    }

    private void calculateMinScale() {
        int widthTarget = target.getWidth();
        int heightTarget = target.getHeight();
        int widthWindow = window.getWidth();
        int heightWindow = window.getHeight();
        if (widthTarget == 0 || heightTarget == 0 || widthWindow == 0 || heightWindow == 0)
            return;

        float minScaleX = (float) widthWindow / widthTarget;
        float minScaleY = (float) heightWindow / heightTarget;

        this.minScale = Math.min(minScaleX, minScaleY);
    }

    private void calculateScrollBounds() {
        int widthTarget = target.getWidth();
        int heightTarget = target.getHeight();
        int widthWindow = window.getWidth();
        int heightWindow = window.getHeight();

        scrollBoundX = (int) (widthTarget * scale) / 2 - widthWindow / 2;
        scrollBoundY = (int) ((heightTarget * scale) / 2 - heightWindow / 2);
        if (scrollBoundX < 0) scrollBoundX = 0;
        if (scrollBoundY < 0) scrollBoundY = 0;
    }

    @Override
    //this has to be attached to the widow-fragment, than it is called when the window size changes
    public void onLayoutChange(View view, int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7) {
        calculateMinScale();
        calculateScrollBounds();
    }
}