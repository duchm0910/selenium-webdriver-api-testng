package javaForTest;

//Class
public class Sample {
//    Khai bao bien (Global - toan cuc)
    String address = "Ho Chi Minh";
//    Access Modify: pham vi truy cap - public, private...

//    Ham
//    Action
    public void setAddressName (String newAddress){
//        bien cuc bo: newAddress
        address = newAddress;
    }
//    Get Lay gia tri
    public String getAddressName(){
        return address;
    }
//    Tham so = parameter, argument


}
