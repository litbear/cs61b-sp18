package disc04;

import disc04.Animal;

public class Cat extends Animal {

    public Cat(String name, int age) {
        super(name, age);
        noise = "Meow!";
    }

    @Override
    public void greet() {
        System.out.println(getClass().getSimpleName() + " " + name + " says: " + makeNoise());
    }

}
