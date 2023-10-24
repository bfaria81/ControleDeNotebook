package GestaoReservaNotebooks.Reservas.Controller;

import GestaoReservaNotebooks.Reservas.Model.M_Resposta;
import GestaoReservaNotebooks.Reservas.Model.M_Usuario;
import GestaoReservaNotebooks.Reservas.Service.S_Notebook;
import GestaoReservaNotebooks.Reservas.Service.S_Usuario;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class C_CadUsuario {
    @GetMapping("/cadastro/usuario")
    public String getCadastro(HttpServletRequest request) {
        if(request.getHeader("Referer") != null){
            return "cadastro/usuario";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/cadastro/usuario")
    @ResponseBody
    public String postCadastroUsuario(@RequestParam("nome") String nome,
                                      @RequestParam("matricula") String matricula,
                                      @RequestParam("email") String email,
                                      @RequestParam("cargo") String cargo) {
        return S_Usuario.cadastrarUsuario(nome, matricula, email, cargo);
    }

    @GetMapping("/edit/usuario")
    public String getEditUsuario(HttpServletRequest request,
                                 HttpSession session,
                                 Model model) {
        if (request.getHeader("Referer") != null) {
            M_Usuario usuario = (M_Usuario) session.getAttribute("usuario");
            model.addAttribute("usuario", usuario);
            if (usuario.getCargo() == 1) {
                return "/cadastro/partialView/edit_cad_usuario_gestor";
            } else {
                return "/cadastro/partialView/edit_cad_usuario_default";
            }

        } else {
            return null;
        }
    }

    @PostMapping("/edit/usuario")
    @ResponseBody
    public M_Resposta postEditUsuario(HttpServletRequest request,
                                      HttpSession session,
                                      @RequestParam("nome") String nome,
                                      @RequestParam("email") String email,
                                      @RequestParam("senhaAtual") String senhaAtual,
                                      @RequestParam("novaSenha") String novaSenha,
                                      @RequestParam("confSenha") String confSenha,
                                      @RequestParam(value="matricula",required = false) String matricula,
                                      @RequestParam(value="cargo",required = false) String cargo,
                                      @RequestParam(value="ativo",required = false) String ativo){

        Object usuario = session.getAttribute("usuario");
        return S_Usuario.salvarEditUsuario(nome,matricula,email,cargo,
                senhaAtual,novaSenha,confSenha,ativo, (M_Usuario) usuario);
    }



}
