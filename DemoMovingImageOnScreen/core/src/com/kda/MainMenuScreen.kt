package com.kda

import animated.ImageMoving
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.scenes.scene2d.Stage

import com.badlogic.gdx.utils.viewport.FitViewport

class MainMenuScreen(private val game: KDA) : ScreenAdapter() {
    private val stage: Stage = Stage(FitViewport(game.screenWidth, game.screenHeight))
    private val player = ImageMoving(game)
    private val sprite = player.viewBox()
    
    override fun show() {
        Gdx.input.inputProcessor = stage
        stage.isDebugAll = true
        sprite.x = 500f
        stage.addActor(sprite)
    }

    override fun resize(width: Int, height: Int) {
        stage.viewport.update(width, height, true)
    }

    override fun render(delta: Float) {
        super.render(delta)
        Gdx.gl.glClearColor(0f, 0.5f, 0.5f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        if(Gdx.input.justTouched()) println("before calculateAction box.x= "+sprite.x.toString()) //500 always
        player.calculateAction(delta) //call player method for calculation moving on screen
        println(sprite.x) //print normal as expected
        stage.act(delta)
        stage.draw()
    }
}