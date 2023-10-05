function controleDeRotas(url){
    switch(url){
        case "/logout":
            gerarSwal(url);
            break;
        case "cadastro/notebook":
            //Renderizar Tela
            //Definir as ações dos componentes
            $("#enviar").click(enviaCadastroNotebook);
            break;

    }
}



