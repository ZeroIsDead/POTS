/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pots;

import DataAbstractions.User;
import java.util.List;

/**
 *
 * @author JONATHAN
 */
public abstract class Manager extends User {

    public Manager(List<String> FieldNames, List<String> Details) {
        super(FieldNames, Details);
    }
}
