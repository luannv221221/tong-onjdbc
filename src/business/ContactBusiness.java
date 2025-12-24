package business;

import entiy.Contact;
import util.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactBusiness {
    public static List<Contact> findAll(){
        Connection conn = null;
        CallableStatement stmt = null;
        List<Contact> contacts = new ArrayList<>();
        try {
            conn = ConnectionDB.getConnection();
            stmt = conn.prepareCall("{call sp_get_all_contact()}");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Contact contact = new Contact();
                contact.setId(rs.getInt("id"));
                contact.setName(rs.getString("name"));
                contact.setEmail(rs.getString("email"));
                contact.setPhone(rs.getString("phone"));
                contact.setSex(rs.getBoolean("sex"));
                contact.setAddress(rs.getString("address"));
                contact.setRating(rs.getInt("rating"));
                contact.setCretaed(rs.getDate("created"));
                contacts.add(contact);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return contacts;
    }
    public static boolean addContact(Contact contact){
        Connection conn = null;
        CallableStatement stmt = null;
        try{
            conn = ConnectionDB.getConnection();
            conn.setAutoCommit(false);
            stmt = conn.prepareCall("{call sp_add_contact(?,?,?,?,?,?,?)}");
            stmt.setString(1, contact.getName());
            stmt.setString(2, contact.getEmail());
            stmt.setString(3, contact.getPhone());
            stmt.setBoolean(4, contact.isSex());
            stmt.setString(5, contact.getAddress());
            stmt.setInt(6, contact.getRating());
            stmt.setDate(7, new Date(contact.getCretaed().getTime()));
            stmt.executeUpdate();
            conn.commit();
            return true;
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return false;
    }
    public static boolean editContact(Contact contact){
        Connection conn = null;
        CallableStatement stmt = null;
        try{
            conn = ConnectionDB.getConnection();
            conn.setAutoCommit(false);
            stmt = conn.prepareCall("{call sp_edit_contact(?,?,?,?,?,?,?,?)}");
            stmt.setInt(1, contact.getId());
            stmt.setString(2, contact.getName());
            stmt.setString(3, contact.getEmail());
            stmt.setString(4, contact.getPhone());
            stmt.setBoolean(5, contact.isSex());
            stmt.setString(6, contact.getAddress());
            stmt.setInt(7, contact.getRating());
            stmt.setDate(8, new Date(contact.getCretaed().getTime()));
            stmt.executeUpdate();
            conn.commit();
            return true;
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return false;
    }
    public static Contact findContactById(int id){
        Connection conn = null;
        CallableStatement stmt = null;
        Contact contact = null;
        try {
            conn = ConnectionDB.getConnection();
            stmt = conn.prepareCall("{call sp_find_contact_by_id(?)}");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                contact = new Contact();
                contact.setId(rs.getInt("id"));
                contact.setName(rs.getString("name"));
                contact.setEmail(rs.getString("email"));
                contact.setPhone(rs.getString("phone"));
                contact.setSex(rs.getBoolean("sex"));
                contact.setAddress(rs.getString("address"));
                contact.setRating(rs.getInt("rating"));
                contact.setCretaed(rs.getDate("created"));
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return contact;
    }
    public static boolean deleteContact(int id){
        Connection conn = null;
        CallableStatement stmt = null;
        try {
            conn = ConnectionDB.getConnection();
            stmt = conn.prepareCall("{call sp_delete_contact(?)}");
            stmt.setInt(1,id);
            stmt.executeUpdate();
            return true;
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return false;
    }
    public static List<Contact> searchContactByName(String name){
        Connection conn = null;
        CallableStatement stmt = null;
        List<Contact> contacts = new ArrayList<>();
        try {
            conn = ConnectionDB.getConnection();
            stmt = conn.prepareCall("{call sp_search_contact_by_name(?)}");
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Contact contact = new Contact();
                contact.setId(rs.getInt("id"));
                contact.setName(rs.getString("name"));
                contact.setEmail(rs.getString("email"));
                contact.setPhone(rs.getString("phone"));
                contact.setSex(rs.getBoolean("sex"));
                contact.setAddress(rs.getString("address"));
                contact.setRating(rs.getInt("rating"));
                contact.setCretaed(rs.getDate("created"));
                contacts.add(contact);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return contacts;
    }
    public static boolean checkEmailContactExist(String email){
        Connection conn = null;
        CallableStatement stmt = null;
        try {
            conn = ConnectionDB.getConnection();
            stmt = conn.prepareCall("{call sp_check_email_exist(?)}");
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return false;
    }

}
