package javaForTest;

import java.util.Random;

public class Topic_01_Random_Email {
  public static void main(String[] args) {
    Random rand = new Random();
    System.out.println(rand.nextInt(9999));
  }
}
