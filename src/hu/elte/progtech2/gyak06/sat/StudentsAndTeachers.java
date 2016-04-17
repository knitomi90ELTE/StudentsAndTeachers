package hu.elte.progtech2.gyak06.sat;

import hu.elte.progtech2.gyak06.sat.db.init.DatabaseInitializer;
import hu.elte.progtech2.gyak06.sat.gui.FrameWithTable;
import javax.swing.SwingUtilities;

public class StudentsAndTeachers {

    private static boolean isInitializable = true;
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                if (isInitializable) {
                    DatabaseInitializer.getInstance().init();
                }
                new FrameWithTable().setVisible(true);
            }
        });
    }
    
}
