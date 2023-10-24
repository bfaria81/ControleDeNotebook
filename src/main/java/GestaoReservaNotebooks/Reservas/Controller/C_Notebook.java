package GestaoReservaNotebooks.Reservas.Controller;


import GestaoReservaNotebooks.Reservas.Service.S_Notebook;
import GestaoReservaNotebooks.Reservas.Service.S_Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class C_Notebook {

    @GetMapping("/cadastro/notebook")
    public String getCadastrosNotebook(){
        return "cadastro/notebook";
    }

    @PostMapping("/cadastro/notebook")
    @ResponseBody
    public String postCadastroNotebook(@RequestParam("numero") String numero,
                                       @RequestParam("patrimonio") String patrimonio){
        return S_Notebook.cadastrarNotebook(numero,patrimonio);
    }


//    @GetMapping("/")
//    public String landPage(){
//        return "/cadastro/login";
//    }

    @PostMapping("/")
    public String loginPessoa(@RequestParam("usuario")String usuario,
                              @RequestParam("senha")String senha,
                              HttpSession session){
        session.setAttribute("usuario", S_Usuario.validaLogin(usuario, senha));

        if(session.getAttribute("usuario") != null){
            return "/home/home";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/home/partial_home")
    @ResponseBody
    public String postReservaNotebook(@RequestParam("nome") String nome,
                                      @RequestParam("matricula") String matricula,
                                      @RequestParam("email") String email,
                                      @RequestParam("cargo") String cargo) {
        return S_Usuario.cadastrarUsuario(nome, matricula, email, cargo);
    }



}
