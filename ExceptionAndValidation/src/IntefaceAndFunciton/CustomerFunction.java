package IntefaceAndFunciton;

import DatabaseConfig.ConnectDatabase;
import Entity.Customer;
import Validation.ValidationFunction;
import Exception.NotValidCustomerNameException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerFunction implements IManagement<Customer>{
    private final Connection connect = ConnectDatabase.getJDBCConnection();
    @Override
    public void add(Customer customer) {
        if(connect!=null){
            PreparedStatement myStm = null;
            String addCustomer = "INSERT INTO customer(name,discount) VALUES(?,?)" ;
            try {
                myStm = connect.prepareStatement(addCustomer);
                String validName = customer.getName();
                if (!ValidationFunction.isStringName(validName)){
                    throw new NotValidCustomerNameException("Name shouldn't contain number!");
                }else {
                    myStm.setString(1,validName);
                    myStm.setInt(2,customer.getDiscount());
                    int insert = myStm.executeUpdate();
                    if(insert!=0) System.out.println("Add customer successful");
                    else System.out.println("Add customer fail");
                }
            }catch (SQLException e){
                throw new RuntimeException(e);
            }catch (NotValidCustomerNameException e){
                System.out.println(e.getMessage());
            }
        }else {
            System.out.println("Connect DB fail");
        }
    }

    @Override
    public Customer getDataById(List<Customer> customers, long id){
        for(Customer c: customers){
            if(id == c.getId()){
                return c;
            }
        }
        return null;
    }

    @Override
    public List<Customer> getAllData() {
        List<Customer> listCustomer = new ArrayList<>();
        if(connect!=null){
            Statement myStm = null;
            String selectAll = "SELECT * FROM customer";
            try {
                myStm = connect.createStatement();
                ResultSet resultSet = myStm.executeQuery(selectAll);
                while (resultSet.next()){
                    listCustomer.add(new Customer(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getInt("discount")
                    ));
                }
                return listCustomer;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }else{
            System.out.println("Connect database was failed");
        }
        return null;
    }
}
