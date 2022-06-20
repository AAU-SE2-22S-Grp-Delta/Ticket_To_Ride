package at.aau.se2.tickettoride.listeners;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

public class MapOnTouchListener extends ScrollView implements View.OnTouchListener, View.OnLayoutChangeListener {
    //TODO: When the map zooms out, the off gets visible. When scrolling for a bit, the window jumps into the bounds. This is to be fixed

    //Attributes for controlls
    View window; //fragment which is scrolled
    View target; //fragment which is zoomed

    //Attributes for scrolling
    private float mx;
    private float my;
    private boolean scrollingStarted = false;
    private int scrollBoundX;
    private int scrollBoundY;

    //Attributes for zooming
    private float zoomLength0;
    private float zoomLength;
    private float scale = 1f;
    private float minScale = -1;
    private boolean zoomingStarted = false;


    public MapOnTouchListener(View window, View target) {
        super(target.getContext());
        this.window = window;
        this.target = target;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getPointerCount()) {
            case 1: { //occurs when there is one finger on the screen
                float curX = motionEvent.getX();
                float curY = motionEvent.getY();
                int dx = (int) (mx - curX);
                int dy = (int) (my - curY);

                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_MOVE:
                        if (scrollingStarted) {
                            scrollWindow(dx, dy);
                        } else {
                            scrollingStarted = true;
                        }
                        mx = curX;
                        my = curY;
                        break;
                    case MotionEvent.ACTION_UP:
                        scrollingStarted = false;
                        break;
                    default:
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
                            scaleTarget(calculateScale(zoomLength, zoomLength0), true);
                        } else {
                            zoomLength0 = (float) Math.sqrt(Math.pow(x0 - x1, 2) + Math.pow(y0 - y1, 2));
                            zoomingStarted = true;
                        }
                        break;
                    case MotionEvent.ACTION_POINTER_UP:
                    case MotionEvent.ACTION_UP:
                        zoomingStarted = false;
                        scrollingStarted = false;
                        scale = calculateScale(zoomLength, zoomLength0);
                        scaleTarget(scale, true);
                        break;
                    default:
                        break;
                }
            }
            break;

            default:
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

    private void calculateScrollBounds(float scale) {
        Log.d("onTouchEvent_bounds", "calculating bounds...");
        int widthTarget = target.getWidth();
        int heightTarget = target.getHeight();
        int widthWindow = window.getWidth();
        int heightWindow = window.getHeight();

        scrollBoundX = (int) (widthTarget * scale) / 2 - widthWindow / 2;
        scrollBoundY = (int) (heightTarget * scale) / 2 - heightWindow / 2;
        if (scrollBoundX < 0) scrollBoundX = 0;
        if (scrollBoundY < 0) scrollBoundY = 0;
    }

    private float calculateScale(float zoomLength, float zoomLength0) {
        float scale = this.scale * zoomLength / zoomLength0;
        if (scale < minScale) scale = minScale;
        float maxScale = 2;
        if (scale > maxScale) scale = maxScale;
        return scale;
    }

    private void scaleTarget(float scale, boolean smooth) {
        float scrollScaleFactor = scale / target.getScaleX();
        if (smooth) {
            if (scrollScaleFactor < 0.95f) {
                scrollScaleFactor = 0.95f;
                scale = target.getScaleX() * scrollScaleFactor;
            } else if (scrollScaleFactor > 1.05f) {
                scrollScaleFactor = 1.05f;
                scale = target.getScaleX() * scrollScaleFactor;
            }
        }
        calculateScrollBounds(scale);

        target.setScaleX(scale);
        target.setScaleY(scale);
        window.setScrollX((int) (window.getScrollX() * scrollScaleFactor));
        window.setScrollY((int) (window.getScrollY() * scrollScaleFactor));
        Log.d("pivot", "relScrollX: " + window.getScrollX() + ", relScrollY: " + window.getScrollY());
        scrollWindow(0, 0);
        Log.d("pivot", "ScrollX: " + window.getScrollX() + ", ScrollY: " + window.getScrollY());
    }

    private void scrollWindow(int dx, int dy) {
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
    }

    @Override
    //this has to be attached to the widow-fragment, than it is called when the window size changes
    public void onLayoutChange(View view, int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7) {
        calculateMinScale();
        calculateScrollBounds(minScale);
        this.scale = minScale;
        scaleTarget(minScale, false);
    }
}