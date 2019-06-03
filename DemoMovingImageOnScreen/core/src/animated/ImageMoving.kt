package animated

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.kda.KDA

class ImageMoving(game: KDA) {
    fun viewBox() = img
    private val img = Image(game.aniskin.getDrawable("move-skin-male-back-R-0"))
    fun calculateAction(delta:Float){
        if (img.x > Gdx.input.x) img.x-=(100*delta).toInt().toFloat()
        else if (img.x < Gdx.input.x) img.x+=(100*delta).toInt().toFloat()
    }
}