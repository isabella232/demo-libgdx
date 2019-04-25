package animated

import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.scenes.scene2d.Group
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.utils.Drawable
import com.badlogic.gdx.utils.Array as UIArray
import com.kda.KDA

class AnimatedHumanDrawable(private  val game:KDA , private val boxWidth:Float , private val boxHeight:Float, private var direction:String) {
    /** right or left*/
    fun setDirection(side:String){
        if (side == "left"){ this.stack.setScale(-1f,1f); this.direction = side}
        else if (side == "right"){ this.stack.setScale(1f,1f); this.direction = side}
    }

    private val box = Table()
    fun viewBox() = box
    private val stack = Group()
    private val img = ImageAnimationDrawable()
    private val fnames = UIArray<String>(arrayOf(
            "move-skin-male-back-R-0","move-skin-male-back-R-L1","move-skin-male-back-R-L2","move-skin-male-back-R-L1",
            "move-skin-male-back-R-0","move-skin-male-back-R-R1","move-skin-male-back-R-R2","move-skin-male-back-R-R1"))
    init {
        this.stack.addActor(img)
        this.stack.setOrigin(this.boxWidth/2,this.boxHeight/2)
        //boxWidth and boxHeight used that calculate axes of mirroring at center of each Drawable 300x300px in this case
        this.box.add(stack)
    }
    private fun createAnimationDrawable(names:UIArray<String>):Animation<Drawable>{
        val frames = UIArray<Drawable>()
        frames.setSize(names.size)
        for (i in 0 until names.size) {
            val path = names[i]
            val drawable = game.aniskin.getDrawable(path)
            frames[i] = drawable
            println(frames[i])
        }
        return Animation(0.1f, frames)
    }

    private val ani = createAnimationDrawable(fnames)
    init {
        ani.playMode = Animation.PlayMode.LOOP
        img.setAnimation(ani)
    }

    /** calculate moving direction and animation mirroring depend of position on Stage object of screen*/
    fun calculateAction(delta:Float){
        if (this.direction == "right"){
            if (this.box.x > 640) {
                this.setDirection("left")
                this.box.x -= 100 * delta
            }else{
                this.box.x += 100 * delta
            }
        }else if (this.direction == "left"){
            if (this.box.x < 100){
                this.setDirection("right")
                this.box.x += 100 * delta
            }else{
                this.box.x -= 100 * delta
            }
        }
    }

}