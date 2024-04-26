/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbcapp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author Lenovo
 */
public class InputData extends JFrame{
    Connector connector = new Connector();
    
    JFrame window = new JFrame("JDBC");
    
    JLabel lNo = new JLabel ("LECTURE NUMBER ");
    JTextField fNo = new JTextField();
    JLabel lName = new JLabel ("LECTURE NAME   ");
    JTextField fName = new JTextField();
    JLabel lNIP = new JLabel ("LECTURER NUMBER ");
    JTextField fNIP = new JTextField();
    JLabel lClass = new JLabel ("CLASS ");
    JTextField fClass = new JTextField();
    
    JButton btnAddPanel = new JButton("ADD");
    JButton btnView = new JButton("VIEW");

    public InputData() {
        window.setLayout(null);
        window.setSize(550, 200);
        window.setVisible(true);
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        
        window.add(lNo);
        window.add(lName);
        window.add(lNIP);
        window.add(lClass);
        window.add(fNo);
        window.add(fName);
        window.add(fNIP);
        window.add(fClass);
        
        window.add(btnAddPanel);
        window.add(btnView);
        
        //LABEL
        lNo.setBounds(5, 35, 120, 20);
        lName.setBounds(5, 60, 120, 20);
        lNIP.setBounds(5, 85, 140, 20);
        lClass.setBounds(5, 105, 120, 20);
        
        //TEXTFIELD
        fNo.setBounds(150, 35, 120, 20);
        fName.setBounds(150, 60, 120, 20);
        fNIP.setBounds(150, 85, 120, 20);
        fClass.setBounds(150, 105, 120, 20);
        
        //BUTTONPANEL
        btnAddPanel.setBounds(300, 35, 90, 20);
        btnView.setBounds(300, 60, 90, 20);
        
        btnAddPanel.addActionListener(new ActionListener() {
            @Override //ctrl + space
            public void actionPerformed(ActionEvent e) {
                connector.insertData(getNo(), getName(), getNIP(), getClasses());
            }
        });
        
        btnView.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                ViewData viewData = new ViewData();
            }
        });
        
    }

    public String getNo() {
        return fNo.getText();
    }

    public String getName() {
        return fName.getText();
    }

    public String getNIP() {
        return fNIP.getText();
    }

    public String getClasses() {
        return fClass.getText();
    }
}