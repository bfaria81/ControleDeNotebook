$("#enviar").click(enviaCadastroNotebook);

function enviaCadastroNotebook(){
    let nome = $("#nome").val();
    let ocupacao = $("#ocupacao").val();
    let matricula = $("#matricula").val();
    let email = $("#email").val();
    let senha = $("#senha").val();
    let conf_Senha = $("#conf_Senha").val();

    $.ajax({
        type: "POST",
        url: "/cadastro/usuario",
        data:{
            nome : nome,
            ocupacao : ocupacao,
            matricula : matricula,
            email : email,
            senha : senha,
            conf_Senha : conf_Senha

        },
        success: function(data){
            alert(data);
        },
        error: function(){
            alert("Falha na comunicação com o servidor!");
        }
    });
}