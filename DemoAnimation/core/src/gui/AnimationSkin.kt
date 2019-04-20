package gui

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.TextureAtlas

import com.badlogic.gdx.scenes.scene2d.ui.Skin


class AnimationSkin : Skin() {
    private val asm:AssetManager = AssetManager()
    private var defaultFont:BitmapFont? = null
    var humanAtlas:TextureAtlas? = null

    fun prepare(){
        asm.load("bmfonts/unifont/unifont-16.fnt", BitmapFont::class.java)
        asm.finishLoading()

        humanAtlas = TextureAtlas(Gdx.files.internal("animation/human.atlas"))

//        println("humanAtlas loaded")


        addRegions(TextureAtlas(Gdx.files.internal("skin/uiskin.atlas"))) //default ligdx skin from github
        addRegions(TextureAtlas(Gdx.files.internal("animation/human.atlas"))) //human animation atlas
        add("unifont-16", asm.get("bmfonts/unifont/unifont-16.fnt"), BitmapFont::class.java)
        load(Gdx.files.internal("skin/uiskin.json")) //now unifont-16 font can be used
        defaultFont = get("unifont-16" ,  BitmapFont::class.java) //prepared uses Hiero libgdx tool

    }
}