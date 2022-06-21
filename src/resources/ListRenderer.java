/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import java.awt.Component;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.border.*;

/**
 *
 * @author Ephraim
 */
public class ListRenderer implements ListCellRenderer {

    private JLabel msg_label;

    public ListRenderer() {
        //renderer.add(render_label);
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

        String passedvalue = value.toString();
        this.msg_label = new JLabel(passedvalue);
        msg_label.setFont(new Font("Tahoma", Font.BOLD, 14));
        msg_label.setAlignmentX(JLabel.CENTER); //vertical alignment
        msg_label.setAlignmentY(JLabel.CENTER); //horizontal alignment
        msg_label.setBorder(new EmptyBorder(15,10,0,0));//top,left,bottom,right

        return msg_label;
    }

}
