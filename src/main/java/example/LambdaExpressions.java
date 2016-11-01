package example;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class LambdaExpressions {

  @FunctionalInterface
  interface CheckTrait {
    boolean test(Animal a);
  }

  static CheckTrait swimmer1 = a -> a.canSwim();
  static CheckTrait swimmer2 = (Animal a) -> a.canSwim();
  static CheckTrait swimmer3 = (Animal a) -> { return a.canSwim(); };

  static Supplier<Animal> creator1 = () -> new Animal();
  static Supplier<Animal> creator2 = Animal::new;

  public static void main(String[] args) {
    List<Animal> animals = new ArrayList<>();
    animals.add(creator1.get());
    animals.stream().forEach(a -> swimmer3.test(a));
    animals.stream().filter(a -> a.canSwim() && a.canHop()).count();
    animals.stream().forEach(Animal::makeSound);
  }
}
