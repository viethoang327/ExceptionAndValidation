import Entity.Customer;
import Entity.Invoice;
import IntefaceAndFunciton.CustomerFunction;
import Exception.NotFoundCustomerException;
import Exception.NotFoundInvoiceException;
import IntefaceAndFunciton.InvoiceFunction;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // 1. TEST CUSTOMER
        CustomerFunction testCustomer = new CustomerFunction();
        Customer customer1 = new Customer(3,"Ngoc",35);
        // add and test name validation
        System.out.println("==========add and test name validation=====");
        testCustomer.add(customer1);
        // getAll
        System.out.println("==========All data of Customer==============");
        List<Customer> listCustomer = testCustomer.getAllData();
        for(Customer c: listCustomer){
            System.out.println(c);
        }
        // findCustomerById and test NotFoundCustomerException
        System.out.println("==========Find 1 Customer ==========");
        try{
            Customer rs = testCustomer.getDataById(listCustomer,5);
            if(rs==null){
                throw new NotFoundCustomerException("The ID is not match with any Customer!");
            }else {
                System.out.println(rs);
            }
        }
        catch (NotFoundCustomerException e){
            System.out.println(e.getMessage());
        }
        // 2.TEST INVOICE
        Invoice invoice1 = new Invoice(2,customer1,712.357);
        InvoiceFunction testInvoice = new InvoiceFunction();
        // add invoice and test numberValidation
        System.out.println("======== add invoice and test numberValidation =======");
        testInvoice.add(invoice1);
        // get ALL invoice
        System.out.println("==========All data of Invoice==============");
        List<Invoice> listInvoices = testInvoice.getAllData();
        for(Invoice i: listInvoices){
            System.out.println(i);
        }
        // findInvoiceById and test NotFoundInvoiceException
        System.out.println("==========Find 1 Invoice by ID ==========");
        try{
            Invoice rst = testInvoice.getDataById(listInvoices,2);
            if(rst==null){
                throw new NotFoundInvoiceException("The ID is not match with any Invoice!");
            }else {
                System.out.println(rst);
            }
        }
        catch (NotFoundInvoiceException e){
            System.out.println(e.getMessage());
        }
    }
}