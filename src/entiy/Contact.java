package entiy;

import business.ContactBusiness;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Contact {
    private int id;
    private String name;
    private String email;
    private String phone;
    private boolean sex;
    private String address;
    private int rating;
    private Date cretaed;

    public Contact() {
    }

    public Contact(int id, String name, String email, String phone, boolean sex, String address, int rating, Date cretaed) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.sex = sex;
        this.address = address;
        this.rating = rating;
        this.cretaed = cretaed;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public boolean isSex() {
        return sex;
    }

    public String getAddress() {
        return address;
    }

    public int getRating() {
        return rating;
    }

    public Date getCretaed() {
        return cretaed;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setCretaed(Date cretaed) {
        this.cretaed = cretaed;
    }

    public void inputData(Scanner scanner){
        this.name = inputName(scanner);
        this.email = inputEmail(scanner);
        System.out.println("Moi ban nhap vao phone ");
        this.phone = scanner.nextLine();
        System.out.println("Moi ban nhap vao gioi tinh (NAM-TRUE, NAM-FALSE)");
        this.sex = Boolean.parseBoolean(scanner.nextLine());
        System.out.println("Nhap vao dia chi");
        this.address = scanner.nextLine();
        System.out.println("Moi ban nhap vao muc do quan trong 1-5");
        this.rating = Integer.parseInt(scanner.nextLine());
        System.out.println("Ngay tao lien he ");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            this.cretaed = sdf.parse(scanner.nextLine());
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public String inputName(Scanner scanner){
        System.out.println("Moi ban nhap vao ten ");
        do {
            String name = scanner.nextLine();
            if (!name.isEmpty() && name.length() < 50) {
                return name;
            }
            System.err.println("Ten phai khac rong va nho hon 50 ky tu");
        } while (true);

    }
    public String inputEmail(Scanner scanner){
        System.out.println("Moi ban nhap vao email ");
        do {
            String email = scanner.nextLine();
            if(!email.isEmpty()) {
                if(!ContactBusiness.checkEmailContactExist(email)) {
                    return email;
                } else {
                    System.err.println(email + "DA TON TAI");
                    continue;
                }
            }
            System.err.println("Email khong duoc rong");
        }while (true);
    }
    public void displayData(){
        System.out.println("ID : " + this.id);
        System.out.println("Name : " + this.name);
        System.out.println("Email : " + this.email);
        System.out.println("Phone : " + this.phone);
        System.out.println("Sex :"+(this.sex ? "NAM" : "NU"));
        System.out.println("Address : " + this.address);
        System.out.println("Rating : " + this.rating);
        System.out.println("Created at : " + this.cretaed);
    }
}
