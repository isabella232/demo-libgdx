package animated

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.scenes.scene2d.Group
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.utils.Drawable
import com.badlogic.gdx.utils.TimeUtils
import com.badlogic.gdx.utils.Array as UIArray
import com.badlogic.gdx.utils.ArrayMap as UIMap
import com.kda.KDA

class AnimatedHumanDrawable(private  val game:KDA , private val boxWidth:Float , private val boxHeight:Float, private var direction:String) {
    /** right or left. Mirror Table() actor on screen aroung center vertical axis of sprite, which is viewbox*/
    fun setDirection(side:String){
        if (side == "left"){ this.stack.setScale(-1f,1f); this.direction = side}
        else if (side == "right"){ this.stack.setScale(1f,1f); this.direction = side}
    }
    /**stepL or stepR animations*/
    private var presentAnimation = "stepL"
    private fun switchPresentAnimation() {
        presentAnimation = if (presentAnimation == "stepL") "stepR" else "stepL"
        img.setAnimation(aniBox[presentAnimation]) //start new animation
    }
    
    private val box = Table()
    fun viewBox() = box
    private val stack = Group()
    private val img = ImageAnimationDrawable()
    //names of drawables from game.aniskin for animation of left leg step and right leg step
    private val namesL = UIArray<String>(arrayOf("move-skin-male-back-R-0","move-skin-male-back-R-L1","move-skin-male-back-R-L2","move-skin-male-back-R-L1"))
    private val namesR = UIArray<String>(arrayOf("move-skin-male-back-R-0","move-skin-male-back-R-R1","move-skin-male-back-R-R2","move-skin-male-back-R-R1"))
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
        }
        return Animation(0.1f, frames)
    }

    private val aniL = createAnimationDrawable(namesL)
    private val aniR = createAnimationDrawable(namesR)
    private val aniBox = UIMap<String, Animation<Drawable>>(2)
    init {
        aniL.playMode = Animation.PlayMode.NORMAL
        aniR.playMode = Animation.PlayMode.NORMAL
        aniBox.put("stepL",aniL)
        aniBox.put("stepR",aniR)
        img.setAnimation(aniBox[presentAnimation])
    }
    
    private var newX:Int = -1
    private var oldX:Int = -1
    private fun refreshTouchX(x:Int){
        oldX = newX
        newX = x
    }
    
    /** calculate moving direction and animation mirroring depend of position on Stage object of screen*/
    fun calculateAction(delta:Float){
        //moving section
        if(Gdx.input.justTouched()) refreshTouchX(Gdx.input.x)
        if (box.x > newX){ //need move left
            if (direction =="right") setDirection("left") //mirror animation on screen
            box.x-=(300*delta).toInt().toFloat() //offset along x direction on screen
        }else if (box.x < newX){ //need move right
            if (direction =="left") setDirection("right")
            box.x+=(300*delta).toInt().toFloat()
        }
        
        //2d animation section.
        // Switch left leg step and right leg step animations when previous animation finished.
        if (aniBox[presentAnimation].isAnimationFinished(img.getTime())) {
            val stampSec:Float = TimeUtils.nanoTime()/1_000_000_000.toFloat() //seconds of system timer
            switchPresentAnimation() // switch rigth -> left -> right loop leg animations
        }
    }

}