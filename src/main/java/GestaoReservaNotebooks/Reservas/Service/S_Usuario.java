package GestaoReservaNotebooks.Reservas.Service;

import GestaoReservaNotebooks.Reservas.Model.M_Resposta;
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

    public static M_Resposta salvarEditUsuario(String nome, String matricula, String email,
                                  String cargo, String senhaAtual, String novaSenha,
                                  String confSenha, String ativo, M_Usuario usuario){

        boolean podeSalvar = true;
        String mansagem = "";
        boolean isAdmin = usuario.getCargo() == 1;
        // se for administrador não recebe a matricula, senão recebe a matricula
        matricula = isAdmin ? S_Generico.limparNumero(matricula) : matricula;

        if(senhaAtual.equals(usuario.getSenha())) {
            if (S_Generico.campoVazio(nome)){
                podeSalvar = false;
                mansagem += "O nome precisa ser preenchido!";
            }
            if (S_Generico.campoVazio(matricula)) {
                podeSalvar = false;
                mansagem += "Matrícula inválida";
            }
            if (S_Generico.campoVazio(email)) {
                podeSalvar = false;
                mansagem += "O email precisa ser preenchido!";
            }
            if (!novaSenha.equals(confSenha)) {
                podeSalvar = false;
                mansagem += "A sua senha e confirmação de senha precisam ser iguais!";
            }
        } else {
            podeSalvar = false;
            mansagem += "Senha inválida!";
        }
        if (podeSalvar){
            usuario.setNome(nome);
            usuario.setEmail(email);
            if (!S_Generico.campoVazio(novaSenha)){
                usuario.setSenha(novaSenha);
            }
            if (isAdmin){
                usuario.setCargo(Long.parseLong(cargo));
                usuario.setMatricula(Long.parseLong(matricula));
                usuario.setAtivo(Boolean.parseBoolean(ativo));
            }
            try{
                r_usuario.save(usuario);
                mansagem += "Atualização efetuada com sucesso!";
            }catch (DataIntegrityViolationException e){
                podeSalvar = false;
                mansagem += "Falha ao salvar dados!";
            }
        }
        return new M_Resposta(podeSalvar, mansagem);
    }
}
