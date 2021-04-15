package javaForTest;

public class Topic_02_Get_Driver_Path {
  public static void main(String[] args) {
    String project_location = System.getProperty("user.dir");
    String projectLocation = System.getProperty("user.dir");
    String fristFileName = "Despicable-Me-2-Minions.jpg";
    String secondFileName = "Background.jpg";
    String thirdFileName = "Sunflower_from_Silesia2.jpg";
    String firstFileNamePath = projectLocation+"\\uploadfiles\\"+fristFileName;
    String secondFileNamePath = projectLocation + "\\uploadfiles\\" + secondFileName;
    String thirdFileNamePath = projectLocation + "\\uploadfiles \\" + thirdFileName;
    System.out.println(firstFileNamePath);
  }
}
