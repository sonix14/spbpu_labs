package lab_2;

import java.lang.reflect.*;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
	
	public static void main(String[] args) {
		Class<CoolClass> cool = CoolClass.class;
		Method[] methods = cool.getDeclaredMethods();
		for (Method method : methods) {
			invokeMethod(method);
		}
	}
	
	
	public static void invokeMethod(Method method) {
		if (method.isAnnotationPresent(CoolAnnotation.class)) {
			try {
				method.setAccessible(true);
				if (!Modifier.isPublic(method.getModifiers())) {
					Object[] parameters = getParam(method);
					for (int i = 0; i < method.getAnnotation(CoolAnnotation.class).value(); i++) {
						method.invoke(null, parameters);
					}
					System.out.println("------------------------------------------");
				}
			} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			}
        }
    }
	
	public static Object[] getParam(Method method) {
		Object[] objects = new Object[method.getParameterCount()];
		for (int i = 0; i < objects.length; i++) {
            if (method.getParameters()[i].getType().equals(int.class)) {
            	objects[i] = ThreadLocalRandom.current().nextInt();
            } else if (method.getParameters()[i].getType().equals(double.class)) {
            	objects[i] = ThreadLocalRandom.current().nextDouble();
            } else if (method.getParameters()[i].getType().equals(boolean.class)) {
            	objects[i] = ThreadLocalRandom.current().nextBoolean();
            } else if (method.getParameters()[i].getType().equals(char.class)) {
            	objects[i] = (char) (ThreadLocalRandom.current().nextInt());
            } else if (method.getParameters()[i].getType().equals(float.class)) {
            	objects[i] = ThreadLocalRandom.current().nextFloat();
            } else {
            	objects[i] = "string";
            }
            
        }
        return objects;
	}
}
