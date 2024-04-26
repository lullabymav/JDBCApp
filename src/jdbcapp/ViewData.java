/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbcapp;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lenovo
 */
public class ViewData {
    public String lecture_number, lecture_name, lecturer_number, classes;
    int totalData;
    String data[][] = new String[500][4];
    //row 500  column 4 (depends on the database
    
    Connector connector = new Connector();
    
    JFrame window = new JFrame("JDBC");
    JTable tabel;
    DefaultTableModel tableModel; //otomatis dibuat kalo buat JTable
    JScrollPane scrollPane;
    Object columnName[] = {"Lecture Number","Lecture Name","Lecturer Number","Class"};

    public ViewData() {
        tableModel = new DefaultTableModel(columnName, 0); // model to create table
        tabel = new JTable(tableModel); 
        scrollPane = new JScrollPane(tabel); // to made the table scrollable
        
        window.add(scrollPane);   
        scrollPane.setBounds(20, 35, 500, 300);
        //scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        
        window.setLayout(null);
        window.setSize(550,600);
        //window.setDefaultCloseOperation(3);
        window.setVisible(true);
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        
        showData();
        
        tabel.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
                int row = tabel.getSelectedRow();
                int column = tabel.getSelectedColumn();
                
                //System.out.println("row " + row + " col " + column);
                
                String lecture_name = tabel.getValueAt(row, 1).toString();
                System.out.println("data : " + lecture_name);
                
                int input = JOptionPane.showConfirmDialog(null,
                            "do you want to delete " + lecture_name + "?",
                            "Option...",
                            JOptionPane.YES_NO_OPTION);
                
                if (input == 0){
                    // delete data
                    connector.deleteData(lecture_name);
                    // delete based on primary key
                    showData();
                }else {
                    input = JOptionPane.showConfirmDialog(null,
                            "do you want to update " + lecture_name + "?",
                            "Option...",
                            JOptionPane.YES_NO_OPTION);
                    if (input == 0){
                        // go to update page/ui
                        UpdateData updateData = new UpdateData(lecture_name);
                    }
                }
                
                //System.out.println(input);
            }
            
        });
    }
    
    private void showData(){
        data = connector.readData();
        tabel.setModel((new JTable(data, columnName)).getModel());
    }
}
