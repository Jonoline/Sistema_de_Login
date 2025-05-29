package Controlador;

import Modelo.DatosLogin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class LoginTest {

    private Login login;
    private DatosLogin datos;

    @BeforeEach
    void setUp() {
        login = new Login();
        try {
            datos = new DatosLogin();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void loginValido() {
        boolean resultado = login.autenticar("xiao", "ola123", datos);
        // Assert: comprobar que el resultado es true
        assertTrue(resultado, "El login debería ser exitoso con credenciales correctas");
    }

    @Test
    void usuarioInexistente() {
        boolean resultado = login.autenticar("jonathan", "xdxd", datos);
        // Assert: comprobar que el resultado es true
        assertFalse(resultado, "El login debería ser incorrecto con credenciales incorrectas");
    }
    @Test
    void contraseñaIncorrecta() {
        boolean resultado = login.autenticar("xiao", "alakazam", datos);
        // Assert: comprobar que el resultado es true
        assertFalse(resultado, "El login debería ser incorrecto con usuario corecto, y contraseña incorrectas");
    }

    @Test
    void archivoNoEncontrado() throws IOException {
        assertThrows(IOException.class, () -> { new DatosLoginTest("ruta/inexistente.txt");
            }, "Debe lanzar IOException si el archivo no existe");
    }
}