package GestaoReservaNotebooks.Reservas.Service;

import GestaoReservaNotebooks.Reservas.Model.M_Notebook;
import GestaoReservaNotebooks.Reservas.Model.M_Usuario;
import GestaoReservaNotebooks.Reservas.Repository.R_Notebook;
import GestaoReservaNotebooks.Reservas.Repository.R_Usuario;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class S_Usuario {

    private static R_Usuario r_usuario;
    public S_Usuario(R_Usuario r_usuario) {this.r_usuario = r_usuario;}

    public static String cadastrarUsuario(String nome, String matricula, String email, String ocupacao, String senha, String conf_Senha) {
        boolean podeSalvar = true;
        String mensagem = "";

        if (S_Generico.campoVazio(nome)) {
            podeSalvar = false;
            mensagem += "O Nome do usuário precisa ser informado !";
        }

        if (S_Generico.campoVazio(matricula)) {
            podeSalvar = false;
            mensagem += "O número do matrícula precisa ser informado !";
        }

        if (S_Generico.campoVazio(email)) {
            podeSalvar = false;
            mensagem += "O e-mail precisa ser informado !";
        }

        if (S_Generico.campoVazio(ocupacao)) {
            podeSalvar = false;
            mensagem += "A ocupação precisa ser selecionada !";
        }

        if(!senha.equals(conf_Senha) || senha == null || senha.trim().equals("")){
            mensagem += "Senha e confirmação devem ser iguais e a senha deve ser informada !";
            podeSalvar = false;
        }


        if (podeSalvar) {
            M_Usuario m_usuario = new M_Usuario();
            m_usuario.setNome(nome);
            m_usuario.setMatricula(Long.parseLong(matricula));
            m_usuario.setEmail(email);
            m_usuario.setOcupacao(ocupacao);
            m_usuario.setSenha(senha);

            try {
                r_usuario.save(m_usuario);
                mensagem += "Usuário cadastrado com Sucesso !";
            } catch (DataIntegrityViolationException e) {
                mensagem += "Erro ao cadastrar o Usuário no banco de dados: !";
            }
        }
        return mensagem;
    }

}
