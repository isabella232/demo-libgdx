package animated

import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.utils.Array as UIArray
import com.kda.KDA

class AnimatedHumanTextureRegion(private  val game:KDA) {

    private val img = ImageAnimationTextureRegion()
    fun viewBox() = img
    private val fnames = UIArray<String>()
    init {
        fnames.add("stay-skin-male-back-R-0","stay-skin-male-back-R-0","priem-skin-male-back-R-1","priem-skin-male-back-R-2")
    }
    private fun createAnimationTextureRegion(names:UIArray<String>):Animation<TextureRegion>{
        val frames = UIArray<TextureRegion>()
        frames.setSize(names.size)
        for (i in 0 until names.size) {
            val path = names[i]
            frames[i] = game.aniskin.humanAtlas!!.findRegion(path)
            println(frames[i])
        }
        return Animation(0.5f, frames)
    }

    private val ani = createAnimationTextureRegion(fnames)
    init {
        ani.playMode = Animation.PlayMode.LOOP
        img.setAnimation(ani)
    }


}