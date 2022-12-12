package application;

class A {
    int x = 68;
    int y = 13;
    public A(int x) {
        y += 5;
        x /= 2;
        System.out.println("salutare clasa A");
    }
    public String toString() {
        return "eu sunt A";
    }
    public int getX() {
        return x * 2 + 1;
    }
    public int getY() {
        return y + 2;
    }
}

class B extends A {
    int x = 15;
    int y;
    public B() {
        super(7);
        x *= 2;
        y = getX();
        System.out.println("salutare clasa B");
    }

    public String toString() {
        return "eu sunt B";
    }
    public int getX() {
        return x + 3;
    }
}

public class TaliduciTest {
    public static void main(String[] args) {
        A a = new A(5);
        A b = new B();

        System.out.println((B) b);
        System.out.println((A) b);
        System.out.println((Object) b);

        System.out.println(b.getClass().getName());
        System.out.println(a.getClass().getName());
        System.out.println(b.getClass().getSuperclass().getName());

        System.out.println("a.x=" + a.x);
        System.out.println("b.x=" + b.x);
        System.out.println("((B) b).x=" + ((B) b).x);
        System.out.println("a.y=" + a.y);
        System.out.println("b.y=" + b.y);

        System.out.println("a.getX()=" + a.getX());
        System.out.println("b.getX()=" + b.getX());
        System.out.println("((B) b).getX()=" + ((B) b).getX());
        System.out.println("b.getY()=" + b.getY());
        System.out.println("((B) b).getY()=" + ((B) b).getY());
    }
}
