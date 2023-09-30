//atribui a funcao para o botao castrar
$("#login").click(function(enviarDadosLogin);

	function enviarDadosLogin(){
	    let matricula = $("#matricula").val();
	    let senha = $("#senha").val();

	    $.ajax({
	        type: "POST",
	        url: "/login",
	        data:{
	            matricula: matricula,
	            senha: senha
	        },
	        success: function(data){
	            if(data){
	                window.location.href="/home";
	            }
	        },
    	    error: function(){
    	        alert("Falha ao enviar dados")
    	    }
	    });
	}



