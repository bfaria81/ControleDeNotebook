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