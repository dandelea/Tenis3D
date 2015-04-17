package tenis.screens.splash;

import tenis.screens.MainMenuScreen;
import tenis.tween.SpriteAccessor;
import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SplashDeveloperScreen implements Screen {
	private TweenManager tweenManager;

	private SpriteBatch batch;
	private Sprite devSplash;

	private String dev = "img/splash_screen/developer.png";

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		devSplash.draw(batch);
		batch.end();
		
		tweenManager.update(delta);
	}

	@Override
	public void show() {
		batch = new SpriteBatch();

		tweenManager = new TweenManager();
		Tween.registerAccessor(Sprite.class, new SpriteAccessor());

		Texture devTex = new Texture(Gdx.files.internal(dev));
		devSplash = new Sprite(devTex);
		devSplash.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		Tween.set(devSplash, SpriteAccessor.ALPHA).target(0).start(tweenManager);
		Tween.to(devSplash, SpriteAccessor.ALPHA, 2).target(1).start(tweenManager);
		// Do the same in the opposite direction (Yoyo). Delay of 1 second.
		Tween.to(devSplash, SpriteAccessor.ALPHA, 2).target(1).repeatYoyo(1, 1).setCallback(new TweenCallback() {
			
			@Override
			public void onEvent(int arg0, BaseTween<?> arg1) {
				((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenuScreen());
				
			}
		}).start(tweenManager);
	}

	@Override
	public void resize(int width, int height) {
		devSplash.setSize(width, height);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		batch.dispose();
		devSplash.getTexture().dispose();
	}

}
