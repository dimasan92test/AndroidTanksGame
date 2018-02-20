package com.tanks.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

//Главный класс игры
public class TanksGame extends Game {
	
    private SpriteBatch batch;

    @Override
    public void create() {
        batch = new SpriteBatch();
        ScreenManager.getInstance().init(this, batch); //инициализация менеджера экранов
        ScreenManager.getInstance().switchScreen(ScreenManager.ScreenType.MENU); //установка текущего экрана(при запуске - экран меню)
    }


    @Override
    public void render() {
        float dt = Gdx.graphics.getDeltaTime();
        getScreen().render(dt);
    }

    @Override
    public void dispose() {
        batch.dispose();
		Assets.getInstance().dispose();
    }
}
