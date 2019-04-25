package animated

import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.Drawable
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable

class ImageAnimationDrawable : Image() {
    private var time:Float = 0f
    protected var speed:Float = 1f
    init {
//        this.setSize(300f,300f)
    }
    private var animation: Animation<Drawable>? = null
    fun setAnimation(animation: Animation<Drawable>) {
//        this.setSize(300f,300f)
//        this.setOrigin(this.width/2f,this.height/2f)
//        this.setOrigin(1)
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
//            this.setOrigin(this.width/2f,this.height/2f)
            this.pack() //new string
//            this.setOrigin(this.width/2f,this.height/2f)
//            println(this.width/2+this.height/2)
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