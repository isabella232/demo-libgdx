package gui

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.scenes.scene2d.ui.Skin

class AnimationSkin : Skin() {

    fun prepare(){
        addRegions(TextureAtlas(Gdx.files.internal("animation/human.atlas")))
        /* human animation atlas added into Skin instance, and can be used later in code
        as Drawable with autorebuild whitespace borders, which is comfort and compact
         */
    }
}