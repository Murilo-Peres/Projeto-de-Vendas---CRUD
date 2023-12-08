var valores = [];
var id_venda = 0;

function novo(){
	var form = document.getElementById("formulario");
	var lista = document.getElementById("lista");
	var b = document.getElementById("b");
	form.style.display = "block";
	
	lista.style.display = "none";
	
	b.style.display = "none";
	
    id_venda = 0;
    var nome_produto = document.getElementById("nome_produto");
    var preco = document.getElementById("preco");
    var quantidade = document.getElementById("quantidade");
    var nome_cliente = document.getElementById("nome_cliente");

	nome_produto.value = "";
	preco.value = "";
    quantidade.value = "";  
    nome_cliente.value = "";
    
		nome_produto.focus();
}
function salvar(){
	if (document.getElementById("nome_produto").value == ""){
		alert("Campo Produto é obrigatório!")
		return;
	} else if (document.getElementById("preco").value == ""){
		alert("Campo Preco é obrigatório!")
		return;
	}else if (document.getElementById("quantidade").value == ""){
		alert("Campo quantidade é obrigatório!")
		return;
	}else if (document.getElementById("nome_cliente").value == ""){
		alert("Campo cliente é obrigatório!")
		return;
	}
	
	var p = {
	id_venda: id_venda,
	nome_produto: document.getElementById("nome_produto").value,
	preco: document.getElementById("preco").value,
	quantidade: document.getElementById("quantidade").value,
	nome_cliente: document.getElementById("nome_cliente").value
};

	if(id_venda == 0) {
		metodo = "POST";
	} else {
		metodo = "PUT";
	}
	
	
	fetch("http://localhost:8080/testeee/Vendas",
		{method: metodo,
		 body: JSON.stringify(p)
		 }
	)
	.then(resp => resp.json())
	.then(function(retorno){
		alert(retorno.mensagem);
	
		
		var form = document.getElementById("formulario");
		var lista = document.getElementById("lista");
		
		form.style.display = "none";
		
		lista.style.display = "block";

		listar();
	});
}


function cancelar(){
	var form = document.getElementById("formulario");
	var lista = document.getElementById("lista");
	var b = document.getElementById("b");
	
	form.style.display = "none";
	
	lista.style.display = "block";
	
	b.style.display = "block";
}

function listar(){
	var lista = document.getElementById("dados");
	lista.innerHTML = "<tr><td colspan=4>Aguarde, carregando...</td></tr>";
	var b = document.getElementById("b");
    b.style.display = "block";
	
	fetch("http://localhost:8080/testeee/Vendas")
	.then(resp => resp.json())
	.then(dados => mostrar(dados));
}
	
function mostrar(dados){
	valores = dados;
	var lista = document.getElementById("dados");
	lista.innerHTML = "";
	for (var i in dados){
		lista.innerHTML += "<tr>" +
    "<td>" + dados[i].id_venda + "</td>" +
    "<td>" + dados[i].nome_produto + "</td>" +
    "<td>" + dados[i].preco + "</td>" +
    "<td>" + dados[i].quantidade + "</td>" +
    "<td>" + dados[i].nome_cliente + "</td>" +
    '<td><input type="button" value="Excluir" onclick="excluir(' + i + ')"/></td>' +
    '<td><input type="button" value="Alterar" onclick="alterar(' + i + ')"/></td>' +
    "</tr>";
    }
    

	
}


function excluir(i) {
	
	id_venda = valores[i].id_venda;

	fetch("http://localhost:8080/testeee/Vendas/" + id_venda,
		{method: "DELETE"
		}
	)
	.then(resp => resp.json())
	.then(function(retorno){
		alert(retorno.mensagem);
		
		var form = document.getElementById("formulario");
		var lista = document.getElementById("lista");
		
		form.style.display = "none";
		
		lista.style.display = "block";

		listar();
	});
}

function alterar(i) {
	var form = document.getElementById("formulario");
	var lista = document.getElementById("lista");
	var b = document.getElementById("b");
	
	
	b.style.display = "none";
	
	form.style.display = "block";
	
	lista.style.display = "none";
	

    id_venda = valores[i].id_venda;
    var nome_produto = document.getElementById("nome_produto");
    var preco = document.getElementById("preco");
    var quantidade = document.getElementById("quantidade");
    var nome_cliente = document.getElementById("nome_cliente");
	
    nome_produto.value = valores[i].nome_produto;
    preco.value = valores[i].preco;
    quantidade.value = valores[i].quantidade; 
    nome_cliente.value = valores[i].nome_cliente;  
	
	nome_produto.focus();
 
}

listar();

