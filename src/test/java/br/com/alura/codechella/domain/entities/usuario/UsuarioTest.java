package br.com.alura.codechella.domain.entities.usuario;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class UsuarioTest {
    @Test
    public void naoDeveCadastrarUsuarioComCpfNoFormatoInvalido() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Usuario("123456789-99", "Fábio", LocalDate.parse("1994-05-20"), "fabio_junior1994@hotmail.com"));

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Usuario("1234567899", "Fábio", LocalDate.parse("1994-05-20"), "fabio_junior1994@hotmail.com"));

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Usuario("", "Fábio", LocalDate.parse("1994-05-20"), "fabio_junior1994@hotmail.com"));
    }

    @Test
    public void deveCriarUsuarioUsandoFabricaDeUsuario() {
        FabricaDeUsuario fabricaDeUsuario = new FabricaDeUsuario();

        Usuario usuario = fabricaDeUsuario.comNomeCpfNascimento("Amanda Marques", "123.456.789-10", LocalDate.parse("1998-07-16"));
        Assertions.assertEquals("Amanda Marques", usuario.getNome());

        usuario = fabricaDeUsuario.incluiEndereco("123.456.789-10", 465, "torre 1 apto 138");
        Assertions.assertEquals("torre 1 apto 138", usuario.getEndereco().getComplemento());
    }

    @Test
    public void naoDeveCadastrarUsuarioComMenosDe18anos() {
        // Crio uma data de nascimento que subtrai 17 anos da data atual
        LocalDate dataNascimento = LocalDate.now().minusYears(17);  // Um usuário com 17 anos
        // Crio uma exceção e atribuo a ela o resultado do teste de idade mínima, pois o correto é que a exceção seja lançada.
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Usuario("123.456.789-00", "Fulano", dataNascimento, "fulano@example.com");
        });
        // Confiro se a mensagem da exceção é a mensagem que eu esperava, referente à idade inferior
        Assertions.assertEquals("Usuário deve ter pelo menos 18 anos de idade!", exception.getMessage());
    }


}
