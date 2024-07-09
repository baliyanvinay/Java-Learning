# Java-Learning
## 1. Explain access modifiers in Java
Access modifiers determines how a particular class, method or variable can be accessed inside the class or the package or other classes in different packages or globally.<br>
There are 4 types of access modifiers available in Java. 
- Default: When no access modifier is defined, access scope is only within the same package.
- Private: Accessible only within the class.
- Protected: Accessible within the same package or subclasses in different packages.
- Public: Accessible from anywhere.

Note: Top-level classes/interfaces cannot be declared as protected/private.

## 1. Explain the functionality static keyword in Java
- The static keyword is a non-access modifier used for methods, attributes & code blocks.
- Static methods/attributes can be accessed without creating an object of a class.
- Static members cannot access non-static members of a class.
- Static members are tied to class and not objects.
