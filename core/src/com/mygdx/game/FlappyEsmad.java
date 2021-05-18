package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Random;

public class FlappyEsmad extends ApplicationAdapter {
	SpriteBatch batch;
	Texture background;
	Texture [] capuchos;
	int flapState=0;
	float capuchoY;
	int gameState=0;
	float velocity=0;
	float gravity=2;
	Texture toptube;
	Texture bottomTube;
	int numberOfTube=4;
	float gap=400;
	float[] tubeOffset= new float[numberOfTube];
	Random random= new Random();
	float[] tubeX=new float[numberOfTube];
	float tubeVelocity=4;

	float distanceBetweenTubes;

	
	@Override
	public void create () {
		batch = new SpriteBatch();
		background = new Texture("fondo.jpg");
		capuchos = new Texture[2];
		capuchos[0] = new Texture("capucho.png");
		capuchos[1] = new Texture("capucho_2.png");
		bottomTube= new Texture("bottomtube.png");
		toptube= new Texture("toptube.png");

		capuchoY=Gdx.graphics.getHeight()/2 - capuchos[flapState].getHeight()/2;
		distanceBetweenTubes = Gdx.graphics.getWidth()/2;

		for (int i =0; i< numberOfTube; i++){

			tubeOffset[i]=(random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - gap - 200);
			tubeX[i] = Gdx.graphics.getWidth()/2 - toptube.getWidth()/2 + i * distanceBetweenTubes;
		}
	}

	@Override
	public void render () {
		batch.begin();
		batch.draw(background,0,0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

		if(gameState!=0){


			if (Gdx.input.justTouched()){
				velocity=-30;

			}

			for (int i=0; i < numberOfTube; i++){
				tubeX[i] -= tubeVelocity;

				batch.draw(toptube,tubeX[i],
						Gdx.graphics.getHeight()/2  + gap / 2 +tubeOffset[i]);

				batch.draw(bottomTube,tubeX[i],
						Gdx.graphics.getHeight()/2 -gap/2 - bottomTube.getHeight() + tubeOffset[i]);
			}

			if (capuchoY>0 || velocity < 0){
				velocity += gravity;
				capuchoY -= velocity;

			}

			if (flapState==0){
				flapState=1;
			}else {
				flapState=0;
			}


		}else{
			if(Gdx.input.justTouched()){
				gameState=1;
			}
		}
		if (flapState==0){
			flapState=1;
		}else {
			flapState=0;
		}





	batch.draw(capuchos[flapState],Gdx.graphics.getWidth()/2 - capuchos[flapState].getWidth()/2,
			capuchoY);
	batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();

	}
}
