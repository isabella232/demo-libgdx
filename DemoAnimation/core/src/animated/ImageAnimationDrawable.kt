package animated

import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.Drawable
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable

class ImageAnimationDrawable : Image() {
    private var time:Float = 0f
    protected var speed:Float = 1f

    private var animation: Animation<Drawable>? = null
    fun setAnimation(animation: Animation<Drawable>) {
        this.time = 0f
        this.animation = animation
    }

    fun setPose(drawable: Drawable) {
        this.drawable = drawable
        this.invalidateHierarchy()
        this.setSize(prefWidth, prefHeight)
        this.invalidate()
        this.animation = null
    }

    override fun act(delta: Float) {
        this.time += delta * speed
        if (animation != null && animation!!.animationDuration > 0) {
            val frame = animation!!.getKeyFrame(time, true)
            this.drawable = frame
            this.invalidateHierarchy()
            this.invalidate()
            println("drawable frame inside act")
            println(frame)
            println(time)
        } else {
            // setDrawable(null);
            println("act fail statement")
        }
        super.act(delta)
    }
}