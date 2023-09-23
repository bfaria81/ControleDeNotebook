package GestaoReservaNotebooks.Reservas.Service;

import GestaoReservaNotebooks.Reservas.Model.M_Notebook;
import GestaoReservaNotebooks.Reservas.Model.M_Resposta;
import GestaoReservaNotebooks.Reservas.Repository.R_Notebook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class S_Notebook {

    private static R_Notebook r_notebook;
    public S_Notebook(R_Notebook r_notebook) {
        this.r_notebook = r_notebook;
    }

    public static String cadastrarNotebook(String numero, String patrimonio) {
        boolean podeSalvar = true;
        String mensagem = "";
        numero = S_Generico.limparNumero(numero);
        patrimonio = S_Generico.limparNumero(patrimonio);

        if (S_Generico.campoVazio(numero)) {
            podeSalvar = false;
            mensagem += "O Número do Notebook precisa ser informado !<br/>";
        }

        if (S_Generico.campoVazio(patrimonio)) {
            podeSalvar = false;
            mensagem += "O Número do Patrimonio precisa ser informado !<br/>";
        }

        if (podeSalvar) {
            M_Notebook m_notebook = new M_Notebook();
            m_notebook.setNumero(Integer.parseInt(numero));
            m_notebook.setPatrimonio(Long.parseLong(patrimonio));
            m_notebook.setAtivo(true);

            try {
                r_notebook.save(m_notebook);
                mensagem += "Notebook cadastrado com Sucesso !<br/>";
            } catch (DataIntegrityViolationException e) {
                mensagem += "Erro ao cadastrar o Notebook: !<br/>";
            }
        }
        return mensagem;
    }
}
