package GestaoReservaNotebooks.Reservas.Controller;

import GestaoReservaNotebooks.Reservas.Service.S_Notebook;
import org.springframework.stereotype.Controller;
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
                                       @RequestParam("parametro") String patrimonio){
        return S_Notebook.cadastrarNotebook(numero,patrimonio);
    }

}
