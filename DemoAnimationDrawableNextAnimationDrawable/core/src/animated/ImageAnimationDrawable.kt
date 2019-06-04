package animated

import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.Drawable

class ImageAnimationDrawable : Image() {
    private var time:Float = 0f //will be reset to 0 every new animation started
    fun getTime() = time //need outside that determine Animation.isAnimationFinished(stateTime)
    protected var speed:Float = 1f //can be used that manipulate speed of animation

    private var animation: Animation<Drawable>? = null //Animation which use Drawable from game.aniskin as frames
    fun setAnimation(animation: Animation<Drawable>) {
        this.time = 0f //reset time to 0, used for calculation of returned animation frame
        this.animation = animation
    }

    override fun act(delta: Float) {
        this.time += delta * speed
        if (animation != null && animation!!.animationDuration > 0) {
            val frame = animation!!.getKeyFrame(time, true)
            this.drawable = frame
            this.pack()
            this.invalidateHierarchy()
            this.invalidate()
        } else {
            println("act fail statement")
            println(animation)
        }
        super.act(delta)
    }
}