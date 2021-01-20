package jp.risu87.tranch;

import org.lwjgl.glfw.GLFW;

public class Core {
	
	public static void main(String... args) {
		if (!GLFW.glfwInit()) {
			throw new IllegalStateException();
		}
		GLFW.glfwTerminate();
	}
	
	private static void preinit() {
		
	}
	
	private static void init() {
		if (!GLFW.glfwInit()) {
			throw new IllegalStateException();
		}
		GLFW.glfwTerminate();
	}
}


