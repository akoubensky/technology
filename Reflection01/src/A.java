
public class A implements I {

  @Override
  public void printName() {
    System.out.println("this is A");
  }

  public static void main(String[] args) {
    A a = new A();
    a.printName();
  }

}
