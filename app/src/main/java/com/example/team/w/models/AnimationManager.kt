package com.example.team.w.models

import android.animation.Animator
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.view.View
import com.example.team.w.adapters.MyAnimatorListenerAdapter

object AnimationManager {

    private const val ANIMATION_LENGTH_SHORT = 300L
    private const val ANIMATION_LENGTH_MIDDLE = 500L
    private const val ANIMATION_LENGTH_LONG = 750L
    private const val ANIMATION_LENGTH_VERY_LONG = 1000L


    private val ANIMATION_DISAPPEAR = PropertyValuesHolder.ofFloat(
        "alpha",
        1f,
        0f
    )
    private val ANIMATION_APPEAR = PropertyValuesHolder.ofFloat(
        "alpha",
        0f,
        1f
    )

    var arrowPosition = 0f

    var previousPosition = 0

    var screenwidth = 0

    var animationJobs = 0

    fun arrowAnimation(view: View, position: Int) {
        // ObjectAnimatorにセットする
        val objectAnimator = ObjectAnimator.ofPropertyValuesHolder(
            view,
            PropertyValuesHolder.ofFloat(
                "translationX",
                arrowPosition,
                arrowPosition + screenwidth / 30 * (position - previousPosition)
            )
        )

        arrowPosition += screenwidth / 30 * (position - previousPosition)
        previousPosition = position

        objectAnimator.duration = ANIMATION_LENGTH_LONG

        objectAnimator.start()
    }

    fun disappearAnimation(view: View, endListener: () -> Unit): ObjectAnimator {
        // ObjectAnimatorにセットする
        val objectAnimator = ObjectAnimator.ofPropertyValuesHolder(
            view,
            ANIMATION_DISAPPEAR,
            PropertyValuesHolder.ofFloat(
                "scaleX",
                1f,
                0.25f
            ),
            PropertyValuesHolder.ofFloat(
                "scaleY",
                1f,
                0.25f
            )
        )

        objectAnimator.addListener(object : MyAnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)

                endListener()
            }
        })

        objectAnimator.duration = ANIMATION_LENGTH_LONG

        objectAnimator.start()

        return objectAnimator
    }

    fun appearAnimation(view: View, endListener: () -> Unit): ObjectAnimator {
        // ObjectAnimatorにセットする
        val objectAnimator = ObjectAnimator.ofPropertyValuesHolder(
            view,
            ANIMATION_APPEAR,
            PropertyValuesHolder.ofFloat(
                "scaleX",
                0.25f,
                1f
            ),
            PropertyValuesHolder.ofFloat(
                "scaleY",
                0.25f,
                1f
            )

        )

        objectAnimator.addListener(object : MyAnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)

                endListener()
            }
        })

        objectAnimator.duration = ANIMATION_LENGTH_LONG

        objectAnimator.start()

        return objectAnimator
    }

    fun stayAnimation(view: View, endListener: () -> Unit): ObjectAnimator{
        val objectAnimator = ObjectAnimator.ofPropertyValuesHolder(
            view
        )

        objectAnimator.addListener(object : MyAnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                endListener()
            }
        })

        objectAnimator.duration = ANIMATION_LENGTH_VERY_LONG

        objectAnimator.start()

        return objectAnimator
    }

    fun appearEditEventAnimation(view: View, endListener: () -> Unit) {

        val objectAnimator = ObjectAnimator.ofPropertyValuesHolder(
            view,
            ANIMATION_APPEAR
        )

        objectAnimator.addListener(object : MyAnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)

                endListener()
            }
        })

        objectAnimator.duration = ANIMATION_LENGTH_SHORT

        objectAnimator.start()

    }
}