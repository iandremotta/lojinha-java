<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:include page="Assets/header.jsp" />

<h1>Carrinho</h1>

<hr>
<form method="POST" action="finalizarCompra">
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

        <c:forEach items="${lista}" var="p">

            <tr>
                <td>${p.id}</td>
                <td>${p.descricao}</td>
                <td><fmt:formatNumber value="${p.preco}" type="currency"/></td>
                <td>${p.unidade}</td>
                <c:if test="${usuarioLogado != null}">
                    <td>
                        <a href="removerProduto?id=${p.id}&preco=${p.preco}"><button class="btn btn-danger" type="button">Remover do Carrinho</button></a>
                    </td>
                </c:if>  
            </tr> 

        </c:forEach>
    </table>
    <c:if test="${lista.size() > 0}">

        <div class="row">
            <div class="col-6">
                <label for="fmpag">Forma de pagamento:</label>
                <select class="form-control" name="fmpag" id="fmpag" class="form-control" required>
                    <option value="">Escolha...</option>
                    <option value="DEBITO">Débito</option>
                    <option value="CREDITO">Crédito</option>
                </select>
            </div>
            <div class="col-6 text-right" style="font-size:25px;">
                <div>Total do Carrinho: <fmt:formatNumber value="${total}" type="currency"/></div>
            </div>
        </div>


    </c:if> 

    <br><br>
    <input type="submit" class="btn btn-primary btn-block" value="Finalizar Compra">
</form>

<script>

    document.addEventListener("DOMContentLoaded", function (event) {
        let array = ['${lista}'];
        array.forEach(myFunction)

        function myFunction(item, index) {
            console.log(item.preco);
        }
    });

</script>
<jsp:include page="Assets/footer.jsp" />
