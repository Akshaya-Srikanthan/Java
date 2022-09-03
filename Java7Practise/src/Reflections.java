import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Reflections {

    private int num=20;
    private int age = 20;
    public void method1(int num){
        System.out.println("Method 1 "+num);
    }

    private int method2(int num){
        System.out.println("Method 2 Private"+num);
        return num;
    }

    public Reflections() {
        System.out.println("Constructor called");
    }

    public void method3(){
        System.out.println("Method 3 no params");
    }

    public Reflections(int num) {
        this.num = num;
    }

    public static void main(String[] args){


        Reflections reflection = new Reflections(20);
        System.out.println("Start");
        try{
            Class classObj = reflection.getClass();

            System.out.println("Class is "+classObj.getName());

            Constructor constructor = classObj.getConstructor();

            System.out.println("constructor is "+constructor.getName() +" belonging to class "+constructor.getClass().getName());

            Method[] m1= classObj.getMethods();

            System.out.println("Methods are..");

            for(Method m:m1){
                System.out.println("Methods "+m.getName());

            }

            System.out.println("Method Individually");

            Method mOne = classObj.getDeclaredMethod("method1", int.class);
            mOne.invoke(reflection,20);
            System.out.println("Method one name "+mOne.getName()+" and value is ");

            Method mTwo = classObj.getDeclaredMethod("method2", int.class);
            mTwo.setAccessible(true);
            int mTwoValue = (int) mTwo.invoke(reflection,20);
            System.out.println("Method Two name "+mTwo.getName()+" and value is "+mTwoValue);

            Method mThree = classObj.getDeclaredMethod("method3");
            mThree.invoke(reflection);
            System.out.println("Method Three name "+mThree.getName());


            Field privateField = classObj.getDeclaredField("num");
            privateField.setAccessible(true);

            System.out.println("Field value is "+(int)privateField.get(reflection));

            Field privateFieldAge = classObj.getDeclaredField("age");
            privateFieldAge.setAccessible(true);

            System.out.println("Field Age value is "+(int)privateFieldAge.get(reflection));

        }catch(NoSuchMethodException | InvocationTargetException | IllegalAccessException | NoSuchFieldException ex ){
            System.out.println("Exception..."+ex);
        }

    }
}

