package animated

import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable

class ImageAnimationTextureRegion : Image() {
    private var animation: Animation<TextureRegion>? = null
    private var time: Float = 0f
    protected var speed = 1f

//    protected var drawable = TextureRegionDrawable()
//
//    init {
//        this.setDrawable(drawable)
//    }

    fun setAnimation(animation: Animation<TextureRegion>) {
        this.time = 0f
        this.animation = animation
    }

    fun setPose(textureRegion: TextureRegion) {
        this.drawable = TextureRegionDrawable(textureRegion)
        this.setDrawable(this.drawable)
        this.invalidateHierarchy()
        this.setSize(this.prefWidth, this.prefHeight)
        this.invalidate()
        this.animation = null
    }

    override fun act(delta: Float) {
        this.time += delta * this.speed
        if (this.animation != null && this.animation!!.animationDuration > 0) {
            val frame = this.animation!!.getKeyFrame(this.time, true)
            this.drawable = TextureRegionDrawable(frame)
            this.invalidateHierarchy()
            this.invalidate()
            println("frame inside act")
            println(frame)
            println(time)
        } else {
            // setDrawable(null);
            println("act fail statement")
        }
        super.act(delta)
    }
}