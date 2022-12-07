package IntefaceAndFunciton;

import DatabaseConfig.ConnectDatabase;
import Entity.Customer;
import Entity.Invoice;
import Validation.ValidationFunction;
import Exception.NotValidAmountException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InvoiceFunction implements IManagement<Invoice>{
    private final Connection connect = ConnectDatabase.getJDBCConnection();
    @Override
    public void add(Invoice invoice) {
        if(connect!=null){
            PreparedStatement myStm = null;
            String addCustomer = "INSERT INTO invoice(customer_id,amount) VALUES(?,?)" ;
            try {
                myStm = connect.prepareStatement(addCustomer);
                Double validAmount = invoice.getAmount();
                if (!ValidationFunction.isNumber(validAmount.toString())){
                    throw new NotValidAmountException("Amount should be only numberic");
                }else {
                    myStm.setInt(1,invoice.getCustomer().getId());
                    myStm.setDouble(2,validAmount);
                    int insert = myStm.executeUpdate();
                    if(insert!=0) System.out.println("Add customer successful");
                    else System.out.println("Add customer fail");
                }
            }catch (SQLException e){
                throw new RuntimeException(e);
            }catch (NotValidAmountException e){
                System.out.println(e.getMessage());
            }
        }else {
            System.out.println("Connect DB fail");
        }
    }

    @Override
    public Invoice getDataById(List<Invoice> invoices, long id) {
        for(Invoice i: invoices){
            if(id == i.getId()){
                return i;
            }
        }
        return null;
    }

    @Override
    public List<Invoice> getAllData() {
        List<Invoice> listInvoice = new ArrayList<>();
        if(connect!=null){
            Statement myStm = null;
            String selectAll = "SELECT * FROM customer,invoice WHERE " +
                    "customer.id = invoice.customer_id";
            try {
                myStm = connect.createStatement();
                ResultSet resultSet = myStm.executeQuery(selectAll);
                while (resultSet.next()){
                    listInvoice.add(new Invoice(
                            resultSet.getInt("id"),
                            new Customer(resultSet.getInt("customer_id"),
                                    resultSet.getString("name"),
                                    resultSet.getInt("discount")),
                            resultSet.getDouble("amount")
                    ));
                }
                return listInvoice;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }else{
            System.out.println("Connect database was failed");
        }
        return null;
    }
}
