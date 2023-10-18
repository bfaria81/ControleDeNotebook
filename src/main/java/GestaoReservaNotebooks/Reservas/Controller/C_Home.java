package GestaoReservaNotebooks.Reservas.Controller;

import GestaoReservaNotebooks.Reservas.Model.M_Usuario;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class C_Home {

    @GetMapping("/home")
    public String getHome(HttpSession session, Model model){

        if(session.getAttribute("usuario") != null){
            model.addAttribute("usuario", session.getAttribute("usuario"));
            return "home/home";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/Home")
    public String getHomePartial(HttpServletRequest request){

        if(request.getHeader("Referer") != null){
            return "home/partial_home";
        } else {
            return "redirect:/";
        }
    }

//        Object objUsuario = session.getAttribute("usuario");
//        if(objUsuario != null){
//            objUsuario = (M_Usuario) objUsuario;
//            model.addAttribute("nome", ((M_Usuario)objUsuario).getNome()); //polimorfismo


}
