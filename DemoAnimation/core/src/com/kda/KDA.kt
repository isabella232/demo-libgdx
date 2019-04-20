package com.kda

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch

import gui.AnimationSkin as AniSkin //fantastic plastic skin class implementation

class KDA : Game() {
    internal var batch: SpriteBatch? = null
    internal var img: Texture? = null

    internal var screenWidth:Float = 0.0f
    internal var screenHeight:Float = 0.0f
    internal var fresh:Boolean = true;

    internal val asMan: AssetManager = AssetManager()
    internal val aniskin:AniSkin = AniSkin()

    override fun create() {
        batch = SpriteBatch()
        img = Texture("badlogic.jpg")

        screenWidth = Gdx.graphics.width.toFloat()
        screenHeight = Gdx.graphics.height.toFloat()

        aniskin.prepare()

    }

    override fun render() {
        Gdx.gl.glClearColor(1f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        batch!!.begin()
        batch!!.draw(img, 0f, 0f)
        batch!!.end()

        if (Gdx.input.justTouched() && fresh){
            fresh = false
            setScreen(MainMenuScreen(this))
        }
        super.render()
    }

    override fun dispose() {
        batch!!.dispose()
        img!!.dispose()
    }
}
