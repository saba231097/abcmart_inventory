package com.app.dao;

import com.app.beans.Product;
import com.app.utils.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductDAO {
    public boolean addProductsToInvenDB(Product[] products){
        StringBuilder query=new StringBuilder();
        for(Product p:products){
            String q="insert into products (`product_name`,`batchnumber`,`category`,`expirydate`," +
                    "`qty`,`cp_per_unit`,`sp_per_unit`) values('"+p.getProductName()+"','"+p.getBatchNumber()+"" +
                    "','"+p.getCategory()+"','"+p.getExpiryDate()+"',"+p.getQty()+","+p.getCostPrice()+","+p.getSalePrice()+");";
            query.append(q);
        }
        System.out.println(query.toString());
        Connection con=null;
        Statement st=null;
        con= DBConnection.getDBConnection();
        try {
            st=con.createStatement();
            int i=st.executeUpdate(query.toString());
            if(i>0){
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                con.close();
                st.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }
}
