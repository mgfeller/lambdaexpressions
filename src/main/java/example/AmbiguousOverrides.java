package example;

import java.util.function.Consumer;
import java.util.function.Function;

public class AmbiguousOverrides {

  interface Foo {
    String baz(Function<String, String> f);
    void baz(Consumer<Integer> c);
  }

  // Ambiguous method call
  public static void main(String[] args) {
    Foo foo = new Baz();
    // Ambiguous:
    // foo.baz(a -> System.err.println(a));
    foo.baz(a -> a);
    foo.baz(a -> { String value = "me"; });
  }


  static class Baz implements Foo {
    private String value;
    @Override
    public String baz(Function<String, String> f) {
      return f.apply("this");
    }

    @Override
    public void baz(Consumer<Integer> c) {    }
  }


}
