
public class B implements I {

  @Override
  public void printName() {
    System.out.println("this is B");
  }
  
  public static void main(String[] args) {
    B b = new B();
    b.printName();
  }
}
