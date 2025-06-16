package org.cacanhdaden.quanlythuoc.view.login.menu;
import java.util.HashMap;
import com.formdev.flatlaf.util.Animator;


public class MenuAnimation {

    private static final HashMap<MenuItem, Animator> animatorMap = new HashMap<>();
    private static final int ANIMATION_DURATION = 400;

    public static void animate(MenuItem menu, boolean show) {
        Animator existingAnimator = animatorMap.get(menu);

        if (existingAnimator != null && existingAnimator.isRunning()) {
            existingAnimator.stop();
        }

        menu.setMenuVisible(show);

        Animator animator = new Animator(ANIMATION_DURATION, getTimingTarget(menu, show));
        animator.setResolution(1);
        animator.setInterpolator(fraction -> (float) (1 - Math.pow(1 - fraction, 3))); // Ease-out cubic
        animatorMap.put(menu, animator);
        animator.start();
    }

    private static Animator.TimingTarget getTimingTarget(MenuItem menu, boolean show) {
        return new Animator.TimingTarget() {
            @Override
            public void timingEvent(float fraction) {
                menu.setAnimationProgress(show ? fraction : 1f - fraction);
                menu.revalidate();
            }

            @Override
            public void end() {
                animatorMap.remove(menu);
            }
        };
    }
}
