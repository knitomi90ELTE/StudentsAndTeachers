package hu.elte.progtech2.gyak06.sat.gui.action;

import javax.swing.*;
import java.awt.event.*;
import hu.elte.progtech2.gyak06.sat.gui.*;
import hu.elte.progtech2.gyak06.sat.gui.model.*;

public class TableRemoveAction implements ActionListener {

    private JFrame parent;
    private JTable table;

    public TableRemoveAction(JFrame parent) {
        this.parent = parent;
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int ret = JOptionPane.showConfirmDialog(parent, GuiConstants.ARE_YOU_SURE, GuiConstants.CONFIRMATION, JOptionPane.WARNING_MESSAGE);
        if (ret == JOptionPane.OK_OPTION) {
            // kiválasztott sor elkérése, majd törlése
            int selectedRow = table.getSelectedRow();
            if (selectedRow > -1) {
                int convertRowIndexToModel = table.convertRowIndexToModel(selectedRow);
                GenericTableModel model = (GenericTableModel) table.getModel();
                model.removeEntity(convertRowIndexToModel);
            }
        }
    }

}
