package com.cobbleopolis.scape;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.FirstPersonCameraController;
import com.badlogic.gdx.utils.Array;

public class Scape implements ApplicationListener {
	public PerspectiveCamera cam;
	public FirstPersonCameraController camController;
	public ModelBatch modelBatch;
	public AssetManager assets;
	public Array<ModelInstance> instances = new Array<>();
	public Environment environment;
	public boolean loading;

	public float red = 0f;
	public float green = 0f;
	public float blue = 0f;

	public float changeRed = .025f / 2;
	public float changeGreen = .025f / 2;
	public float changeBlue = .025f / 2;

	@Override
	public void create () {
		modelBatch = new ModelBatch();
		environment = new Environment();
		environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
		environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));

		cam = new PerspectiveCamera(30, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cam.position.set(0f, 0f, -9f);
		cam.lookAt(0,0,0);
		cam.near = .1f;
		cam.far = 300f;
		cam.update();

		camController = new FirstPersonCameraController(cam);
		Gdx.input.setInputProcessor(camController);

		assets = new AssetManager();
		assets.load("test.g3db", Model.class);
		assets.load("test2.g3db", Model.class);
		assets.load("monkey.g3db", Model.class);
		loading = true;
	}

	private void doneLoading() {
		addObject("test.g3db");
		addObject("test.g3db", 0f, 4f, 0f);
		addObject("test2.g3db", 0f, 1.5f, 0f);
		addObject("test2.g3db", 0f, 3f, 0f);
		addObject("monkey.g3db", 3f, 0f, 0f);
		addObject("monkey.g3db", -3f, 0f, 0f);
		addObject("monkey.g3db", 0f, 0f, 3f);
		addObject("monkey.g3db", 0f, 0f, -3f);
		addObject("monkey.g3db", 0f, 0f, -3f);
		loading = false;
	}

	@Override
	public void render () {
		if (loading && assets.update())
			doneLoading();
		camController.update();

		red += changeRed;
		green += -changeGreen;
		blue += -changeBlue;

		System.out.println(red * 255 + " | " + green * 255 + " | " + blue * 255);

		if(red > 1f || red < 0f){
			changeRed *= -1;
		}

		if(green > 1f || green < 0f){
			changeGreen *= -1;
		}

		if(blue > 1f || blue < 0f){
			changeBlue *= -1;
		}

		Gdx.gl.glClearColor(red, green, blue, 1f);

		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		modelBatch.begin(cam);
		modelBatch.render(instances, environment);
		modelBatch.end();
	}

	@Override
	public void dispose () {
		modelBatch.dispose();
		instances.clear();
		assets.dispose();
	}

	public void resume () {
	}

	public void resize (int width, int height) {

	}

	public void pause () {
	}

	public void addObject(String modelName){
		addObject(modelName, 0f, 0f, 0f);
	}

	public void addObject(String modelName, float x, float y, float z){
		ModelInstance modelInst = new ModelInstance(assets.get(modelName, Model.class));
		modelInst.transform.translate(x, y, z);
		instances.add(modelInst);
	}
}