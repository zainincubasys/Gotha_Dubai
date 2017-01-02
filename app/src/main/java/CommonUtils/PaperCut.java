package CommonUtils;

import android.view.View;
import android.view.ViewPropertyAnimator;

import com.twotoasters.jazzylistview.JazzyEffect;

public class PaperCut implements JazzyEffect {

    private static final int INITIAL_ROTATION_ANGLE = 30;

    @Override
    public void initView(View item, int position, int scrollDirection) {
        item.setPivotX(0);
        item.setPivotY(0);
        if(scrollDirection == -1){
            item.setPivotY(item.getHeight());
            item.setRotation(-1 * INITIAL_ROTATION_ANGLE);
        }else {
            item.setRotation(INITIAL_ROTATION_ANGLE * scrollDirection);
        }

    }

    @Override
    public void setupAnimation(View item, int position, int scrollDirection, ViewPropertyAnimator animator) {
        animator.setDuration(200);
        if(scrollDirection == -1){
            animator.rotationBy(INITIAL_ROTATION_ANGLE);

        }else {
            animator.rotationBy(-INITIAL_ROTATION_ANGLE * scrollDirection);
        }

    }
}

