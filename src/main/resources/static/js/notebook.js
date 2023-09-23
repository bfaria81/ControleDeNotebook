$("#enviar").click(enviaCadastroNotebook);

function enviaCadastroNotebook(){
    let numero = $("#numero").val();
    let patrimonio = $("#patrimonio").val();

    $.ajax({
        type: "POST",
        url: "/cadastro/notebook",
        data:{
            numero : numero,
            patrimonio : patrimonio
        },
        success: function(data){
            alert(data);
        },
        error: function(){
            alert("Deu Ruim!");
        }
    });
}