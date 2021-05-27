package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
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
	float gap=600;
	float[] tubeOffset= new float[numberOfTube];
	Random random= new Random();
	float[] tubeX=new float[numberOfTube];
	float tubeVelocity=3;
	float distanceBetweenTubes;
	Texture gameOver;
	int score=0;
	int scoringTube=0;
	BitmapFont bitmapFont;
	ShapeRenderer shapeRenderer;
	Circle circle;
	Rectangle[] topTubeRectangle;
	Rectangle[] bottomTubeRectangle;

	public void createTub(){
		for (int i =0; i< numberOfTube; i++){
			tubeOffset[i]=(random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - gap - 200);
			tubeX[i] =(float) Gdx.graphics.getWidth()/2 - toptube.getWidth()/2 +Gdx.graphics.getWidth()+ i * distanceBetweenTubes;
			topTubeRectangle[i] = new Rectangle();
			bottomTubeRectangle[i] = new Rectangle();
		}
	}
	@Override
	public void create () {
		batch = new SpriteBatch();
		background = new Texture("fondo5.jpg");
		capuchos = new Texture[2];
		capuchos[0] = new Texture("capucho.png");
		capuchos[1] = new Texture("capucho_2.png");
		bottomTube= new Texture("bottomtube.png");
		toptube= new Texture("toptube.png");
		gameOver = new Texture("fin.png");
		bitmapFont =new BitmapFont();
		bitmapFont.setColor(Color.WHITE);
		bitmapFont.getData().scale(5);
		shapeRenderer = new ShapeRenderer();
		circle = new Circle();
		topTubeRectangle = new Rectangle[numberOfTube];
		bottomTubeRectangle = new Rectangle[numberOfTube];
		distanceBetweenTubes = (float) (Gdx.graphics.getWidth());
		capuchoY=Gdx.graphics.getHeight()/2 - capuchos[flapState].getHeight()/2;
		createTub();
	}

	public void gameStart(){
		capuchoY=Gdx.graphics.getHeight()/2 - capuchos[flapState].getHeight()/2;
		scoringTube=0;
		score=0;
		velocity=0;
		createTub();
	}
	@Override
	public void render () {
		batch.begin();
		batch.draw(background,0,0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		if(gameState==1){
			if (Gdx.input.justTouched()){
				velocity=-30;
			}
			if (tubeX[scoringTube] < Gdx.graphics.getWidth()/2){
				score++;
				if (scoringTube < numberOfTube - 1){
					scoringTube++;
				} else {
					scoringTube =0;
				}

			}
			for (int i=0; i < numberOfTube; i++){
				if(tubeX[i] < -toptube.getWidth()){

					tubeX[i]+= numberOfTube*distanceBetweenTubes;

				} else {
					tubeX[i] -= tubeVelocity;
				}
				batch.draw(toptube,tubeX[i],
						Gdx.graphics.getHeight()/2  + gap / 2 +tubeOffset[i]);

				batch.draw(bottomTube,tubeX[i],
						Gdx.graphics.getHeight()/2 -gap/2 - bottomTube.getHeight() + tubeOffset[i]);

				topTubeRectangle[i] = new Rectangle(tubeX[i],
						Gdx.graphics.getHeight() / 2  + gap / 2 + tubeOffset[i],
						toptube.getWidth(),
						toptube.getHeight());
				bottomTubeRectangle[i] = new Rectangle(tubeX[i],
						Gdx.graphics.getHeight() / 2 - gap / 2 - bottomTube.getHeight() + tubeOffset[i],
						bottomTube.getWidth(),
						bottomTube.getHeight());
			}
			if (capuchoY>0){
				velocity += gravity;
				capuchoY -= velocity;
			} else {
				gameState=2;
			}
			if (flapState == 0) {
				flapState = 1;
			} else {
				flapState = 0;
			}
		} else if(gameState == 0) {

			if (Gdx.input.justTouched()) {
				gameState = 1;
			}
		} else if (gameState == 2) {
			batch.draw(gameOver,Gdx.graphics.getWidth() / 2 - gameOver.getWidth() / 2,
					Gdx.graphics.getHeight() / 2 - gameOver.getHeight() / 2);
			if (Gdx.input.justTouched()) {
				gameState = 1;
				gameStart();
			}
		}
		batch.draw(capuchos[flapState],Gdx.graphics.getWidth() / 2 - capuchos[flapState].getWidth() / 2 ,
				capuchoY);
		bitmapFont.draw(batch,Integer.toString(score),150,150);
		batch.end();
		circle.set(Gdx.graphics.getWidth() / 2
				,capuchoY + capuchos[flapState].getWidth() / 2
				,capuchos[flapState].getWidth() / 2);
		for (int i = 0 ; i < numberOfTube ; i++) {
			if (Intersector.overlaps(circle,topTubeRectangle[i])
					|| Intersector.overlaps(circle,bottomTubeRectangle[i]) ) {
				gameState = 2;
			}
		}
		shapeRenderer.end();


	}
	
	@Override
	public void dispose () {
		batch.dispose();

	}
}
