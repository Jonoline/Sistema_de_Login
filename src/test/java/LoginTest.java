import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginTest {

    private Login login;
    private DatosLogin datos;

    @BeforeEach
    void setUp() {
        login = new Login();
        datos = new DatosLogin();
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
    void archivoNoEncontrado() {
        DatosLogin datos = new DatosLogin("ruta/inexistente.txt");

        // Assert: comprobar que el resultado es true
        assertTrue(datos.credenciales.isEmpty(), "Las credenciales deben estar vacías si el archivo no existe");
    }

}