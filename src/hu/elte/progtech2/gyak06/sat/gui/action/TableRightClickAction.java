package hu.elte.progtech2.gyak06.sat.gui.action;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import hu.elte.progtech2.gyak06.sat.gui.*;

public class TableRightClickAction extends MouseAdapter {

    private TableRemoveAction delAction;

    public TableRightClickAction(TableRemoveAction delAction) {
        this.delAction = delAction;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e)) {
            Point p = e.getPoint();
            JTable table = (JTable) e.getSource();
            int rowNumber = table.rowAtPoint(p);
            ListSelectionModel model = table.getSelectionModel();
            model.setSelectionInterval(rowNumber, rowNumber);
            createPopup(e);
        }
    }

    private void createPopup(MouseEvent e) {
        JPopupMenu popup = new JPopupMenu();
        JMenuItem delete = new JMenuItem(GuiConstants.DEL_MENU_TEXT);

        delAction.setTable((JTable) e.getSource());
        delete.addActionListener(delAction);
        popup.add(delete);
        popup.show(e.getComponent(), e.getX(), e.getY());
    }

}
