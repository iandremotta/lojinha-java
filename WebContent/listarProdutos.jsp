<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:include page="Assets/header.jsp" />

<h1>Lista de Produtos</h1>
<hr>

<table class="table table-dark">
    <tr>
        <th scope="col">ID</th>
        <th scope="col">DESCRIÇÃO</th>
        <th scope="col">PREÇO</th>
        <th scope="col">UNIDADE</th>
            <c:if test="${usuarioLogado != null}">
            <th scope="col">OPÇÕES</th>
            </c:if> 
    </tr>

    <c:forEach items="${produto}" var="p">

        <tr>
            <td>${p.id}</td>
            <td>${p.descricao}</td>
            <td><fmt:formatNumber value="${p.preco}" type="currency"/></td>
            <td>${p.unidade}</td>            
            <c:if test="${usuarioLogado != null}">

                <td>
                    <a href="iniciarEdicaoProduto?id=${p.id}"><button class="btn btn-warning"><i class="fa fa-pencil" aria-hidden="true"></i></button></a>
                    <a href="excluirProduto?id=${p.id}"><button class="btn btn-danger"><i class="fa fa-trash" aria-hidden="true"></i></button></a>
                    <a href="adicionarNoCarrinho?id=${p.id}&preco=${p.preco}"><button class="btn btn-success"><i class="fa fa-cart-arrow-down" aria-hidden="true"></i></button></a> 
                </td>
            </c:if>  
        </tr> 

    </c:forEach>
</table>
<jsp:include page="Assets/footer.jsp" />
