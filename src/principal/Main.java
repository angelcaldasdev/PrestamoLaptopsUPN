
package principal;

import interfaz.FrmPrincipal;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            FrmPrincipal ventana = new FrmPrincipal();
            ventana.setVisible(true);
        });
    }
}
