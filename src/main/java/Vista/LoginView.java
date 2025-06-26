package Vista;

import  Controlador.Login;
import Modelo.DatosLogin;
import Modelo.Usuario;

import javax.swing.*;
import java.awt.*;

public class LoginView extends JFrame {
    private final JTextField usuarioField= new JTextField(15);
    private final JPasswordField claveField= new JPasswordField(15);
    private final JButton loginButton = new JButton("Iniciar Sesion") ;
    private final DatosLogin datosLogin = new DatosLogin();
    private final Login login = new Login();


    public LoginView() {
        setTitle("Sistema de Login");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2,5,5));

        panel.add(new JLabel("Usuario:"));
        panel.add(usuarioField);

        panel.add(new JLabel("Clave:"));
        panel.add(claveField);

        panel.add(new JLabel());
        panel.add(loginButton);

        add(panel);

        loginButton.addActionListener(e -> autenticarusuario());

    }

    private void autenticarusuario() {
        String usuario = usuarioField.getText();
        String clave = String.valueOf(claveField.getPassword());

        Usuario user= login.autenticar(usuario,clave,datosLogin);

        if (user != null) {
            new MainView(user).setVisible(true);
            this.dispose();

        } else {
            JOptionPane.showMessageDialog(this,
                    "Usuario o clave incorrectos",
                    "Error", JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
