package GestaoReservaNotebooks.Reservas.Service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class S_GeradorDeSenha {

    public static String geradorDeSenha(int qtdLetras, int qtdNumeros, int qtdEspeciais) {

        final char[] CARACTERES_TEXTO = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
        final char[] CARACTERES_ESPECIAIS = "!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~".toCharArray();
        final char[] CARACTERES_NUMERICOS = "0123456789".toCharArray();

        String enigma = "";

        Random rand = new Random();

        //preenche caracteres textuais
        for (int i = 0; i < qtdLetras; i++) {
            enigma += CARACTERES_TEXTO[rand.nextInt(0, CARACTERES_TEXTO.length)];
        }

        //preenche caracteres numéricos
        for (int i = 0; i < qtdNumeros; i++) {
            enigma += CARACTERES_NUMERICOS[rand.nextInt(0, CARACTERES_NUMERICOS.length)];
        }

        //preenche caracteres especiais
        for (int i = 0; i < qtdEspeciais; i++) {
            enigma += CARACTERES_ESPECIAIS[rand.nextInt(0, CARACTERES_ESPECIAIS.length)];
        }

        return enigma;
    }
}


