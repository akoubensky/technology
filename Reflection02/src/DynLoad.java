
public class DynLoad {
  @SuppressWarnings("unchecked")
  public static void main(String[] args) {
    String[] names = new String[] { "A", "B", "C" };
    for (String name : names) {
      try {
        Class<I> clazz = (Class<I>) Class.forName(name);
        System.out.println(clazz.getName());
        I i = clazz.newInstance();
        i.printName();
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      } catch (InstantiationException e) {
        e.printStackTrace();
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      } catch (ClassCastException e) {
        e.printStackTrace();
      }
    }
  }
}
