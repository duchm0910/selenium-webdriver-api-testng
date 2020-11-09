package javaForTest;

public class Topic_02_Get_Driver_Path {
    public static void main(String[] args) {
        String project_location = System.getProperty("user.dir");
        System.out.println(project_location);
    }
}
