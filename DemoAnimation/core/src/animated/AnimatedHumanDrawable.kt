package animated

import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.scenes.scene2d.Group
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.Drawable
import com.badlogic.gdx.utils.Array as UIArray
import com.kda.KDA

class AnimatedHumanDrawable(private  val game:KDA) {
    private val img = ImageAnimationDrawable()
    fun viewBox() = img
    private val fnames = UIArray<String>()
    init {
        fnames.add("stay-skin-male-back-R-0","stay-skin-male-back-R-0","priem-skin-male-back-R-1","priem-skin-male-back-R-2")
    }
    private fun createAnimationDrawable(names:UIArray<String>):Animation<Drawable>{
        val frames = UIArray<Drawable>()
        frames.setSize(names.size)
        for (i in 0 until names.size) {
            val path = names[i]
            frames[i] = game.aniskin.getDrawable(path)
            println(frames[i])
        }
        return Animation(0.5f, frames)
    }

    private val ani = createAnimationDrawable(fnames)
    init {
        ani.playMode = Animation.PlayMode.LOOP
        img.setAnimation(ani)
    }


}