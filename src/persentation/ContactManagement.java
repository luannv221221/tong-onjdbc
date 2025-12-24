package persentation;

import business.ContactBusiness;
import entiy.Contact;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class ContactManagement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("----------------------- Contact Book Menu --------------------");
            System.out.println("1 .Hiển thị danh sách liên hệ ");
            System.out.println("2. Thêm các liên hệ mới");
            System.out.println("3. Chỉnh sửa thông tin liên hệ");
            System.out.println("4. Xoa liên hệ");
            System.out.println("5. Tìm kiếm liên hệ");
            System.out.println("6. Thống kê số lượng liên hệ theo mức độ quan trọng");
            System.out.println("7. Thống kê số lượng liên hệ theo giới tính");
            System.out.println("8. Sắp xếp liên hệ theo tên(a-z / z-a)");
            System.out.println("9. Thoát chương trình");
            System.out.println("Moi bạn chọn từ 1 -9 ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    displayListContact();
                    break;
                case 2:
                    addContact(sc);
                    break;
                case 3:
                    editContact(sc);
                    break;
                case 4:
                    deleteContact(sc);
                    break;
                case 5:
                    searchContactByName(sc);
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    sortContact(sc);
                    break;
                case 9:
                    System.exit(0);
                default:
                    System.err.println("Moi chon lai");
            }
        } while (true);
    }

    public static void displayListContact(){
        List<Contact> contacts = ContactBusiness.findAll();
        System.out.println("====DANH SACH LIEN HE====");
        for (Contact contact : contacts) {
            System.out.println("--------------------");
            contact.displayData();
            System.out.println("--------------------");
        }
    }
    public static void addContact(Scanner sc){
        Contact contact = new Contact();
        contact.inputData(sc);
        ContactBusiness.addContact(contact);
    }
    public static void editContact(Scanner sc){
        System.out.println("Nhap vao ma lien he can tim");
        int contactId = Integer.parseInt(sc.nextLine());
        Contact contact = ContactBusiness.findContactById(contactId);
        if (contact != null){
            contact.inputData(sc);
            ContactBusiness.editContact(contact);
        } else {
            System.err.println("Khong tim thay ma lien he");
        }
    }
    public static void deleteContact(Scanner sc){
        System.out.println("Nhap vao ma can xoa");
        int contactId = Integer.parseInt(sc.nextLine());
        if (ContactBusiness.findContactById(contactId) != null){
            if(ContactBusiness.deleteContact(contactId)){
                System.out.println("Xoa lien he thanh cong");
            } else {
                System.out.println("Xoa that bai");
            }
        } else {
            System.err.println("Khong tim thay lien he voi id ban nhap dau");
        }
    }
    public static void sortContact(Scanner sc){
        List<Contact> contacts = ContactBusiness.findAll();
        System.out.println("Sap xep theo A-Z chon 1, Z-A chon 2");
        int choice = Integer.parseInt(sc.nextLine());
        switch (choice) {
            case 1:
                contacts.stream().sorted(Comparator.comparing(Contact::getName)).forEach((Contact::displayData));
                break;
            case 2:
                contacts.stream().sorted(Comparator.comparing(Contact::getName).reversed()).forEach((Contact::displayData));
                break;
            default:
                System.out.println("Chon sai");
        }
        // sap xep giam dan theo ten
        //contacts.stream().sorted(Comparator.comparing(Contact::getName).reversed()).forEach((Contact::displayData));
        // sap xep tang dan theo ten
        //contacts.stream().sorted(Comparator.comparing(Contact::getName)).forEach((Contact::displayData));
    }
    public static void searchContactByName(Scanner sc){
        System.out.println("Nhap vao ten can tim");
        String keyword = sc.nextLine();
        List<Contact> contacts = ContactBusiness.searchContactByName(keyword);
        System.out.println("====DANH SACH LIEN HE VOI TEN KHOP VOI "+keyword +"====");
        for (Contact contact : contacts) {
            System.out.println("--------------------");
            contact.displayData();
            System.out.println("--------------------");
        }
    }
}
