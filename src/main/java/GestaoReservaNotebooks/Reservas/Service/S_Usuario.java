package GestaoReservaNotebooks.Reservas.Service;

import GestaoReservaNotebooks.Reservas.Model.M_Usuario;
import GestaoReservaNotebooks.Reservas.Repository.R_Usuario;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class S_Usuario {

    private static R_Usuario r_usuario;
    public S_Usuario(R_Usuario r_usuario) {this.r_usuario = r_usuario;}

    public static M_Usuario validaLogin(String matricula, String senha){
        matricula = S_Generico.limparNumero(matricula);

        if(S_Generico.campoVazio(matricula)){
            return null;
        } else if(S_Generico.campoVazio(senha)){
            return null;
        }
        return r_usuario.findByMatriculaESenha(Long.parseLong(matricula), senha);
    }



    public static String cadastrarUsuario(String nome, String matricula, String email, String cargo) {
        boolean podeSalvar = true;
        String mensagem = "";

        matricula = S_Generico.limparNumero(matricula);
        cargo = S_Generico.limparNumero(cargo);


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

        if (S_Generico.campoVazio(cargo)) {
            podeSalvar = false;
            mensagem += "O cargo precisa ser selecionado !";
        }

        if (podeSalvar) {
            M_Usuario m_usuario = new M_Usuario();
            m_usuario.setNome(nome);
            m_usuario.setMatricula(Long.parseLong(matricula));
            m_usuario.setEmail(email);
            m_usuario.setCargo(Long.parseLong(cargo));
            m_usuario.setAtivo(true);
            m_usuario.setSenha(S_GeradorDeSenha.geradorDeSenha(5,3,2));

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
