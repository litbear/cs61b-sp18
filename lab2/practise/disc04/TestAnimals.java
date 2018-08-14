package disc04;

public class TestAnimals {
    public static void main(String[] args) {
        Animal a = new Animal("Pluto", 10);
        Cat c = new Cat("Garfield", 6);
        Dog d = new Dog("Fido", 4);

        // 第二题
        a.greet(); // Animal Pluto says: Huh?
        c.greet(); // Cat Garfield says: Meow!
        d.greet(); // Dog Fido says: WOOF!

        a = c;

        ((Cat) a).greet(); // Cat Garfield says: Meow!
        a.greet(); // Cat Garfield says: Meow!

        a = new Dog("Spot", 10);
        d = (Dog) a; // 编译错误 static 为 Animal 的变量不能赋值为其子类 Dog
    }
}
