package src.main.java.myPackage;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class Homework {

  @Retention(RetentionPolicy.RUNTIME)
  @Target(ElementType.METHOD)
  public @interface Test {
    int order() default 0;
  }

  @Retention(RetentionPolicy.RUNTIME)
  @Target(ElementType.METHOD)
  public @interface BeforeEach {
  }

  @Retention(RetentionPolicy.RUNTIME)
  @Target(ElementType.METHOD)
  public @interface AfterEach {
  }

  @Retention(RetentionPolicy.RUNTIME)
  @Target(ElementType.METHOD)
  public @interface Skip {
  }

  public static void main(String[] args) {
    TestProcessor.runTest(MyTest.class);
  }

  static class MyTest {

    @BeforeEach
    void beforeEachTest() {
      System.out.println("BeforeEach: Этот метод запускается перед каждым тестом");
    }

    @Test(order = -2)
    void firstTest() {
      System.out.println("firstTest запущен");
    }

    @Test
    void secondTest() {
      System.out.println("secondTest запущен");
    }

    @Test(order = 5)
    void thirdTest() {
      System.out.println("thirdTest запущен");
    }

    @AfterEach
    void afterEachTest() {
      System.out.println("AfterEach: Этот метод запускается после каждого теста");
    }

    @Test
    @Skip
    void skippedTest() {
      System.out.println("Этот тест не должен быть запущен");
    }
  }
}
