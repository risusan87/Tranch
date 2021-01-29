package jp.risu87.tranch.util;

import org.joml.Matrix4f;

import jp.risu87.tranch.engine.Core;

/**
 * カメラ。
 * スクリーン上に描写する範囲を表します。
 * @author kobayashi
 */
public class Camera {
	private Matrix4f projection;
	private Matrix4f world;
	private static Camera instance = null;
	private float camX;
	private float camY;
	private float camScale;
	private static final float maxScale = 2.0f;
	private static final float minScele = 0.5f;
	
	/**
	 * CoreクラスでsetupCamera()メソッドを呼び出した際にインスタンス化します。
	 * 複数回呼び出すのは無効です。
	 */
	private Camera() {
		this.moveCamera(0f, 0f);
		this.scaleCamera(0f);
		this.getProjectionMatrix4f(60f, Core.window.getaspectRatio(), 0.0f, 100.f);
		this.getWorldMatirix4f();
	}
	
	/**
	 * 単制度浮動小数型四次元視野マトリックスを返却します。
	 * @param par1fov　Field of View
	 * @param par2aspratio ウィンドウのアスペクト比
	 * @param par3zNear 描写範囲の手前
	 * @param par4zFar　描写範囲の奥
	 * @return 計算された四次元視野マトリックス
	 */
	public Matrix4f getProjectionMatrix4f(float par1fov, float par2aspratio, float par3zNear, float par4zFar) {
		this.projection.identity();
		this.projection.perspective(par1fov, par2aspratio, par3zNear, par4zFar);
		return this.projection;
	}
	
	/**
	 * 単制度浮動小数型四次元世界マトリックスを返却します。
	 * 要はカメラの位置です。
	 * @param par1x カメラのx軸上の位置
	 * @param par2y カメラのy軸上の位置
	 * @param par3scale カメラの縮尺
	 * @return 計算された四次元世界マトリックス
	 */
	public Matrix4f getWorldMatirix4f() {
		this.world.identity();
		this.world.translate(-this.camX, -this.camY, this.camScale);
		return this.world;
	}
	
	/**
	 * カメラの縮尺度を変更します。
	 * @throws IllegalArgumentException 無効な引数条件が渡された場合
	 * @param par1ratio 引数は以下の条件を満たす必要があります: (-1.0f < arg < 1.0f)
	 */
	public void scaleCamera(float par1ratio) {
		if (par1ratio < -1.0f || par1ratio > 1.0f) 
			throw new IllegalArgumentException("Argument is out of valid range: " + par1ratio);
		this.camScale = par1ratio < 0.0f ? (1.0f + par1ratio) * minScele : par1ratio * maxScale;
	}
	
	/**
	 * カメラをx,y軸に沿って移動させます。
	 * 引数はそれぞれ現在地からの相対座標です。
	 * @param par1relCamX
	 * @param par2relCamY
	 */
	public void moveCamera(float par1relCamX, float par2relCamY) {
		this.camX += par1relCamX;
		this.camY += par2relCamY;
	}
	
	/**
	 * Coreクラスでinit()メソッドでのみ１回だけ呼び出します。
	 * Cameraクラスをインスタンス化し、それを返却します。
	 * @return Camera
	 */
	public static Camera setupCamera() {
		if (instance == null)
			instance = new Camera();
		return instance;
	}
}
