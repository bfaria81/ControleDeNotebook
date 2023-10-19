$("#enviar").click(cadastrarUsuario);

function cadastrarUsuario(){
    let nome = $("#nome").val();
    let cargo = $("#cargo option:selected").val();
    let matricula = $("#matricula").val();
    let email = $("#email").val();

    $.ajax({
        type: "POST",
        url: "/cadastro/usuario",
        data:{
            nome : nome,
            cargo : cargo,
            matricula : matricula,
            email : email,

        },
        success: function(data){
            alert(data);
        },
        error: function(){
            alert("Falha na comunicação com o servidor!");
        }
    });
}

function salvarEditUsuario(){
    let nome = $("#nome").val();
    let cargo = $("#cargo option:selected").val();
    let matricula = $("#matricula").val();
    let email = $("#email").val();
    let ativo = $("#ativo").prop("checked");
    let senhaAtual = $("#senhaAtual").val();
    let novaSenha = $("#novaSenha").val();
    let confSenha = $("#confSenha").val();

    $.ajax({
        type: "POST",
        url: "/edit/usuario",
        data:{
            nome: nome,
            cargo: cargo,
            matricula: matricula,
            email: email,
            ativo: ativo,
            senhaAtual: senhaAtual,
            novaSenha: novaSenha,
            confSenha: confSenha,
        },
        success: function(data){
            if(data.sucesso){
                $("#boasVindas").text("Bem vindo " + nome + " !");
                alert("OK: " + data.mensagem);

            } else {
                alert("Erro: " + data.mensagem);
            }
        },
        error: function(){
             alert("Falha na comunicação com o servidor!");
        }
    });
}