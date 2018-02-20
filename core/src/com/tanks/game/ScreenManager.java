package com.tanks.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class ScreenManager {
	
    public enum ScreenType {
        MENU, GAME;
    }
	
	public static final int VIEW_WIDTH = 1280; 	//ширина области просмотра
    public static final int VIEW_HEIGHT = 720;	//высота оболасти просмотра

    private TanksGame tanksGame; 	//ссылка на основной класс игры
    private Viewport viewport;		//область просмотра
    private GameScreen gameScreen; 	//экземпляр игрового экрана
    private MenuScreen menuScreen;	//экземпляр экрана меню
//    private LoadingScreen loadingScreen;
//    private Screen targetScreen;

	private static final ScreenManager ourInstance = new ScreenManager(); //создание экземпляра синглтона
	 
	public static ScreenManager getInstance() {
        return ourInstance;
    } 

    public TanksGame getTanksGame() {
        return tanksGame;
    }

    public Viewport getViewport() {
        return viewport;
    }
	
	private ScreenManager() {
    }

    public void init(TanksGame tanksGame, SpriteBatch batch) {
        this.tanksGame = tanksGame;
        this.gameScreen = new GameScreen(batch);
        this.menuScreen = new MenuScreen(batch);
//        this.loadingScreen = new LoadingScreen(batch);
        this.viewport = new FitViewport(VIEW_WIDTH, VIEW_HEIGHT);
        this.viewport.apply();
    }

    public void onResize(int width, int height) {
        viewport.update(width, height, true);
        viewport.apply();
    }

    public void switchScreen(ScreenType type) {
        Screen currentScreen = tanksGame.getScreen();
        Assets.getInstance().clear();
        if (currentScreen != null) {
            currentScreen.dispose();
        }
//        rpgGame.setScreen(loadingScreen);
        switch (type) {
            case MENU:
                currentScreen = menuScreen;
                Assets.getInstance().loadAssets(ScreenType.MENU);
                break;
            case GAME:
                currentScreen = gameScreen;
                Assets.getInstance().loadAssets(ScreenType.GAME);
                break;
        }
        tanksGame.setScreen(currentScreen);
    }

//    public void goToTarget() {
//        rpgGame.setScreen(targetScreen);
//        targetScreen = null;
//    }
//
//    public void dispose() {
//        Assets.getInstance().dispose();
//        rpgGame.getScreen().dispose();
//    }
}
