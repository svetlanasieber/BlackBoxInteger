import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        Class<BlackBoxInt> clazz = BlackBoxInt.class;
        Constructor<BlackBoxInt> constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);
        BlackBoxInt blackBoxInt = constructor.newInstance(); 

        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();

        while(!command.equals("END")) {
            
            String methodName = command.split("_")[0];
            int argument = Integer.parseInt(command.split("_")[1]); 

            Method method = clazz.getDeclaredMethod(methodName, int.class); 
            method.setAccessible(true); 
          
            method.invoke(blackBoxInt, argument); 


            Field innerValueField = clazz.getDeclaredField("innerValue");
            innerValueField.setAccessible(true);
            System.out.println(innerValueField.get(blackBoxInt)); 

            command = scanner.nextLine();
        }
    }
}
