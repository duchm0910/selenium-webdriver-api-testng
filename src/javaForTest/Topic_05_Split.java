package javaForTest;

public class Topic_05_Split {
  public static void main(String[] args) {
    String oldUrl = "http://the-internet.herokuapp.com/basic_auth";

    String newUrl[] = oldUrl.split("//");

    //        http:
    //        the-internet.herokuapp.com/basic_auth
    System.out.println(newUrl[0]);
    System.out.println(newUrl[1]);
  }
}
