package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class FlappyEsmad extends ApplicationAdapter {
	SpriteBatch batch;
	Texture background;
	Texture [] capuchos;
	int flapState=0;

	
	@Override
	public void create () {
		batch = new SpriteBatch();
		background = new Texture("fondo.jpg");
		capuchos = new Texture[2];
		capuchos[0] = new Texture("capucho.png");
		capuchos[1] = new Texture("capucho_2.png");
	}

	@Override
	public void render () {
		if (flapState==0){
			flapState=1;
		}else {
			flapState=0;
		}
	batch.begin();
	batch.draw(background,0,0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
	batch.draw(capuchos[flapState],Gdx.graphics.getWidth()/2 - capuchos[flapState].getWidth()/2,
			Gdx.graphics.getHeight()/2 - capuchos[flapState].getHeight()/2);
	batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();

	}
}
