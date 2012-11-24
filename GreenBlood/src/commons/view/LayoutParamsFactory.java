package commons.view;

import android.graphics.Rect;
import android.graphics.RectF;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.AbsoluteLayout;
import android.widget.FrameLayout;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import br.com.greenblood.math.Vector2D;

@SuppressWarnings("deprecation")
public class LayoutParamsFactory {
    public static final int MATCH_PARENT = LayoutParams.MATCH_PARENT;
    public static final int WRAP_CONTENT = LayoutParams.WRAP_CONTENT;

    private LayoutParamsFactory() {

    }

    public static AbsoluteLayout.LayoutParams newAbsolute(Rect bounds) {
        return newAbsolute(bounds.left, bounds.top, bounds.width(), bounds.height());
    }
    
    public static AbsoluteLayout.LayoutParams newAbsolute(RectF bounds) {
        return newAbsolute(bounds.left, bounds.top, bounds.width(), bounds.height());
    }

    public static AbsoluteLayout.LayoutParams newAbsolute(Vector2D size) {
        return newAbsolute(0, 0, size.x, size.y);
    }

    public static AbsoluteLayout.LayoutParams newAbsolute(float x, float y, float width, float height) {
        return new AbsoluteLayout.LayoutParams((int) width, (int) height, (int) x, (int) y);
    }

    public static ViewGroup.LayoutParams newViewGroup(Vector2D size) {
        return newViewGroup((int) size.x, (int) size.y);
    }
    
    public static ViewGroup.LayoutParams newMatchViewGroup() {
        return newViewGroup(MATCH_PARENT, MATCH_PARENT);
    }
    
    public static ViewGroup.LayoutParams newMatchWrapViewGroup() {
        return newViewGroup(MATCH_PARENT, WRAP_CONTENT);
    }
    
    public static ViewGroup.LayoutParams newWrapViewGroup() {
        return newViewGroup(WRAP_CONTENT, WRAP_CONTENT);
    }
    
    public static WindowManager.LayoutParams newMatchWindow() {
        return new WindowManager.LayoutParams(MATCH_PARENT, MATCH_PARENT);
    }
    
    public static ViewGroup.LayoutParams newViewGroup(int width, int height) {
        return new ViewGroup.LayoutParams(width, height);
    }

    public static LinearLayout.LayoutParams newLinear(RectF bounds) {
        return newLinear(bounds.width(), bounds.height());
    }

    public static LinearLayout.LayoutParams newMatchLinear() {
        return newLinear(MATCH_PARENT, MATCH_PARENT);
    }
    
    public static LinearLayout.LayoutParams newMatchWrapLinear() {
        return newLinear(MATCH_PARENT, WRAP_CONTENT);
    }

    public static LinearLayout.LayoutParams newWrapLinear() {
        return newWrapLinear(0);
    }
    
    public static LinearLayout.LayoutParams newWrapMatchLinear() {
        return newLinear(WRAP_CONTENT, MATCH_PARENT, 0);
    }
    
    public static LinearLayout.LayoutParams newWrapLinear(int weight) {
        return newLinear(WRAP_CONTENT, WRAP_CONTENT, weight);
    }

    public static LinearLayout.LayoutParams newLinear(float width, float height) {
        return newLinear((int) width, (int) height, 0);
    }
    
    public static LinearLayout.LayoutParams newLinear(float width, float height, int weight) {
        android.widget.LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams((int) width, (int) height);
        layoutParams.weight = weight;
        return layoutParams;
    }


    public static Gallery.LayoutParams newMatchGallery() {
        return newGallery(MATCH_PARENT, MATCH_PARENT);
    }

    public static Gallery.LayoutParams newGallery(RectF bounds) {
        return newGallery((int) bounds.width(), (int) bounds.height());
    }

    public static Gallery.LayoutParams newGallery(int width, int height) {
        return new Gallery.LayoutParams(width, height);
    }
    
    public static GridView.LayoutParams newGrid(int width, int height) {
        return new GridView.LayoutParams(width, height);
    }
    
    public static FrameLayout.LayoutParams newFrame(Vector2D size) {
        return newFrame((int) size.x, (int) size.y);
    }

    public static FrameLayout.LayoutParams newFrame(RectF bounds) {
        FrameLayout.LayoutParams params = newFrame((int) bounds.width(), (int) bounds.height());
        params.leftMargin = (int) bounds.left;
        params.topMargin = (int) bounds.top;
        return params;
    }

    public static FrameLayout.LayoutParams newFrame(int width, int height) {
        return new FrameLayout.LayoutParams(width, height);
    }
    
    public static FrameLayout.LayoutParams newWrapFrame() {
        return newFrame(WRAP_CONTENT, WRAP_CONTENT);
    }

    public static FrameLayout.LayoutParams newMatchFrame() {
        return newFrame(MATCH_PARENT, MATCH_PARENT);
    }
    
    public static RelativeLayout.LayoutParams newWrapRelative() {
        return newRelative(WRAP_CONTENT, WRAP_CONTENT);
    }
    
    public static RelativeLayout.LayoutParams newRelative(int width, int height) {
        return new RelativeLayout.LayoutParams(width, height);
    }

    public static WindowManager.LayoutParams newWindow(RectF bounds) {
        WindowManager.LayoutParams params =
                new WindowManager.LayoutParams((int) bounds.width(), (int) bounds.height());
        params.x = (int) bounds.left;
        params.y = (int) bounds.top;
        return params;
    }

    public static RectF fromAbsolute(LayoutParams params) {
        AbsoluteLayout.LayoutParams aParams = (AbsoluteLayout.LayoutParams) params;
        return new RectF(aParams.x, aParams.y, aParams.x + aParams.width, aParams.y + aParams.height);
    }

}
