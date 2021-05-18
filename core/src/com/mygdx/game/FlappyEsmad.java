package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class FlappyEsmad extends ApplicationAdapter {
	SpriteBatch batch;
	Texture background;

	
	@Override
	public void create () {
		batch = new SpriteBatch();
		background = new Texture("fondo.jpg");
	}

	@Override
	public void render () {
	batch.begin();
	batch.draw(background,0,0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
	batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();

	}
}
