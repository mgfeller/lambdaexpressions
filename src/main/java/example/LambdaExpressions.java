package example;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Supplier;

public class LambdaExpressions {

  @FunctionalInterface
  interface CheckTrait {
    boolean test(Animal a);
  }

  static CheckTrait swimmer1 = a -> a.canSwim();
  static CheckTrait swimmer2 = (Animal a) -> a.canSwim();
  static CheckTrait swimmer3 = (Animal a) -> {
    return a.canSwim();
  };

  static Supplier<Animal> creator1 = () -> new Animal();
  static Supplier<Animal> creator2 = Animal::new;

  public static void main(String[] args) {
    List<Animal> animals = new ArrayList<>();
    animals.add(creator1.get());
    animals.stream().forEach(a -> swimmer3.test(a));
    animals.stream().filter(a -> a.canSwim() && a.canHop()).count();
    animals.stream().forEach(Animal::makeSound);
    LambdaExpressions lambdaExpressions = new LambdaExpressions();
    System.err.println(lambdaExpressions.scope.apply("1", "2"));
  }

  private String value = "class";

  BiFunction<String, String, Integer> concatLength =
      (x, y) -> x.length() + y.length();

  BiFunction<String, String, Integer> concatLengthInner =
      new BiFunction<String, String, Integer>() {
        @Override
        public Integer apply(String x, String y) {
          return x.length() + y.length();
        }
      };

  BiFunction<String, String, String> scope = (x, y) -> {
    String value = "lambda";
    return this.value;
  };

  @FunctionalInterface
  interface lengthConcatable {
    Integer concatLength(String x, String y);
  }

  static class Foo {

    Integer custom(String a, String b, lengthConcatable lc) {
      return lc.concatLength(a, b);
    }

    Integer standard(String a, String b, BiFunction<String, String, Integer> lc) {
      return lc.apply(a, b);
    }

  }
}
