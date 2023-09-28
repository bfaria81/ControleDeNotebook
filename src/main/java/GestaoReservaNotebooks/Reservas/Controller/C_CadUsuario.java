package GestaoReservaNotebooks.Reservas.Controller;

import GestaoReservaNotebooks.Reservas.Service.S_Notebook;
import GestaoReservaNotebooks.Reservas.Service.S_Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class C_CadUsuario {
    @GetMapping("/cadastro")
    public String getCadastro(){
    return "cadastro/usuario";
    }

    @PostMapping("/cadastro/usuario")
    @ResponseBody
    public String postCadastroUsuario(@RequestParam("nome") String nome,
                                      @RequestParam("matricula") String matricula,
                                      @RequestParam("email") String email,
                                      @RequestParam("cargo") String cargo){
        return S_Usuario.cadastrarUsuario(nome,matricula,email,cargo);
    }


}
