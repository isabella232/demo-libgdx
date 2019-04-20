package com.kda

import animated.AnimatedHumanDrawable
import animated.AnimatedHumanTextureRegion
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.*

import com.badlogic.gdx.utils.viewport.FitViewport

import com.badlogic.gdx.scenes.scene2d.ui.List as UIList
import com.badlogic.gdx.utils.Array as UIArray

class MainMenuScreen(private val game: KDA) : ScreenAdapter() {
    private val stage: Stage = Stage(FitViewport(game.screenWidth, game.screenHeight))
    private val player = AnimatedHumanTextureRegion(game)
//    private val player = AnimatedHumanDrawable(game)
    private val backColor:Color = Color.BLACK //for human skin
    private val frontColor:Color = Color.WHITE //for human skin

    override fun show() {
        Gdx.input.inputProcessor = stage
        stage.isDebugAll = true
        val img = Image(game.aniskin.getDrawable("stay-skin-male-back-R-0"))
        stage.addActor(img)
        img.setPosition(0f, 0f)

        val sprite = player.viewBox()
        stage.addActor(sprite)
//        sprite.setPosition(game.screenWidth/2-100, game.screenHeight/2-100)
        sprite.setPosition(10f, 10f)

    }

    override fun resize(width: Int, height: Int) {
        stage.viewport.update(width, height, true)
    }

    override fun render(delta: Float) {
        super.render(delta)
        Gdx.gl.glClearColor(0f, 0.5f, 0.5f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        stage.act(delta);
        stage.draw();

    }
}