import com.mezcladito.app.entidades.Jugador;
import com.mezcladito.app.servicios.JugadorServicio;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
@Data
public class MainFrame extends JFrame {

    @Autowired
    private JugadorServicio jugadorServicio;

    private JTextField tfName;
    private JTextField tfLastName;
    private JComboBox jComboAtaque;
    private JButton OKButton;
    private JButton clearButton;
    private JPanel mainPanel;
    private JLabel welcome;
    private JComboBox jComboDefensa;
    private JComboBox jComboBoxArquero;

    public MainFrame() {
        setContentPane(mainPanel);
        setTitle("MEZCLADITO");
        setSize(450, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        OKButton.addActionListener(e -> {
            String name = tfName.getText();
            String lastname = tfLastName.getText();
            int ataque = jComboAtaque.getSelectedIndex();
            int defensa = jComboDefensa.getSelectedIndex();
            boolean arquero;
            if (jComboBoxArquero.equals("SI")) {
                arquero = true;
            } else {
                arquero = false;
            }

            welcome.setText( name + " " + lastname + "se registró correctamente");
            try {
            Jugador jugador = jugadorServicio.crear(name,lastname,ataque,defensa,arquero);
                jugadorServicio.guardar(jugador);
            }catch (Exception ex){
                welcome.setText( ex.getMessage());
            }
        });
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfName.setText("");
                tfLastName.setText("");
                jComboAtaque.setSelectedIndex(0);
                jComboDefensa.setSelectedIndex(0);
                welcome.setText("");
            }
        });

    }

    public static void main(String[] args) {
        MainFrame myFrame = new MainFrame();
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
