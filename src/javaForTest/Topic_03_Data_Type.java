package javaForTest;

import java.util.ArrayList;
import java.util.HashSet;

public class Topic_03_Data_Type {
    public static void main(String[] args) {
//        Kieu du lieu ky tu
        char a = 'A';

//        Kieu du lieu so nguyen (duong/am)
        byte first_num = -15;
        short second_num = 12345;
        int third_num = 34922;
        long fourth_num = 243234923;

//        Kieu du lieu so thuc
        float fifth_num = 145.69F;
        double sixth_num = 3249.3234D;

//        Kieu Boolean (True/False)
        boolean status = true;
        status = false;

//        Kieu du lieu chuoi(so/chu/dac biet..)
        String fullName = "Hoang Minh Duc";

//        Kieu mang Array(co dinh)
        String[] address = {"Ha Noi", "Da Nang", "Ho Chi Minh"};
        int[] prices = {10000,30000,2020200};

//        Kieu Class
        Topic_03_Data_Type topic_03 = new Topic_03_Data_Type();

//        Kieu Object (Javascript Executor)

//        Kieu Collection (Arraylist/ Hashmap)
//        CRUD: Create Read Update Delete
        ArrayList<String> addressess = new ArrayList<String>();
        addressess.add("Ha Noi");
        addressess.add("Cao Bang");
        addressess.add("Ho Chi Minh");

        HashSet<String> add = new HashSet<String>();
        add.add("Ha Noi");
        add.add("Cao Bang");
        add.add("Ho Chi Minh");

    }
    public void setAddresses(){

    }
    public String getAddress(){
        return "";
    }
}
