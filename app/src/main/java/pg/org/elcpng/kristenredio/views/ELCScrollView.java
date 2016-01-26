package pg.org.elcpng.kristenredio.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by Shadrach.Jaungere on 20/12/2015.
 */
public class ELCScrollView extends ScrollView {

    public ELCScrollView(Context context) {
        super(context);
    }

    public ELCScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ELCScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public interface onScrollViewListener {
        void onScrollChanged(ELCScrollView v, int l, int t, int oldl, int oldt);
    }

    private onScrollViewListener mOnScrollViewListener;

    public void setOnScrollViewListener(onScrollViewListener listener) {
        this.mOnScrollViewListener = listener;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        mOnScrollViewListener.onScrollChanged( this, l, t, oldl, oldt );
        super.onScrollChanged( l, t, oldl, oldt );
    }
}
