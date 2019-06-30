package com.kda

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Preferences

class KDA : Game() {
    internal var screenWidth:Float = 0.0f
    internal var screenHeight:Float = 0.0f
    internal var memoryCard: Preferences? = null // Gdx.app.getPreferences("Demo") can't be called before create(){}

    override fun create() {
        screenWidth = Gdx.graphics.width.toFloat()
        screenHeight = Gdx.graphics.height.toFloat()
        memoryCard = Gdx.app.getPreferences("Demo") //work done
        setScreen(MenuScreen(this))
    }
}
