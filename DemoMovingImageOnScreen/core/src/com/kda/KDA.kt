package com.kda

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20

import gui.AnimationSkin as AniSkin

class KDA : Game() {
    internal var screenWidth:Float = 0.0f
    internal var screenHeight:Float = 0.0f
    internal val aniskin:AniSkin = AniSkin() //incuded human.atlas TextureAtlas for animation
    private var fresh:Boolean = true
    
    override fun create() {
        screenWidth = Gdx.graphics.width.toFloat()
        screenHeight = Gdx.graphics.height.toFloat()
        aniskin.prepare() //call preparing method for connect human.atlas for later using for animation
    }

    override fun render() {
        Gdx.gl.glClearColor(1f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        if (fresh && Gdx.input.justTouched()){
            setScreen(MainMenuScreen(this))
            fresh = false
        }
        super.render()
    }
}
