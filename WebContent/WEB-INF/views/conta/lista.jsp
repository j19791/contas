<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<script src="resources/js/jquery.js"></script><!-- convenção: esses arquivos devem ficar dentro da pasta resources  -->
</head>
<script>




//fazer uma requisição para a action pagaConta, passando o ID da conta a ser marcada como paga.
function pagaAgora(id) {
	  $.get("pagaConta?id="+id, function(){
		//callback: funcao q sera chamada qdo a requisicao voltar p/ o navegador
		  $("#conta_" + id).html("Paga");
		  $("#contaLink_" + id).html("Paga");
		  });
	}


</script>


<body>

<a href="logout">Logout</a>
<br>
Bem Vindo ${usuario.login}

    <table style="height: 10px; width: 775px;" border="1">
        <tr>
        <th>Código</th>
        <th>Descrição</th>
        <th>Valor</th>
        <th>Tipo</th>
        <th>Paga?</th>
        <th>Data de pagamento</th>
        </tr>

        <c:forEach items="${todasContas}" var="conta">
        <tr>
            <td>${conta.id}</td>
            <td>${conta.descricao}</td>
            <td>${conta.valor}</td>
            <td>${conta.tipo}</td>
            <td id="conta_${conta.id}">
                <c:if test="${conta.paga eq false}">
                    Não paga
                </c:if>
                <c:if test="${conta.paga eq true }">
                    Paga!
                </c:if>
            </td>
            <td><fmt:formatDate value="${conta.dataPagamento.time}" pattern="dd/MM/yyyy"/></td>
            <td><a href="removeConta?id=${conta.id}">Remover</a></td>
            <td><a href="mostraConta?id=${conta.id}">Alterar</a></td>
            <td id="contaLink_${conta.id}">
			  <c:if test="${conta.paga eq false}">
			    <a href="#" onClick="pagaAgora(${conta.id})">
			      Pagar agora!
			    </a>
			  </c:if>
			  <c:if test="${conta.paga eq true }">
			    Paga!
			  </c:if>
			</td>
        </tr>        
        </c:forEach>
    </table>

</body>
</html>