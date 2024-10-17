/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package data;

import java.util.List;

/**
 *
 * @author JONATHAN
 */
public interface PaymentCollection {
    public Payment createPayment(String name);
    public List<Payment> getAllPayment();
    public Payment searchPayment(String ID);
    public List<Payment> filterPayment(List<String> Fields, List<String> Values);
    public List<Payment> sortPayment(List<String> Fields, List<String> Values);
}
