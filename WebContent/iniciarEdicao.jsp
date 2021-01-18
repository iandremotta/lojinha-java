<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="Assets/header.jsp" />

<form method="POST" action="editarProduto">
    <h1>Edi��o de Produto</h1>

    <hr>

    <div class="form-group">
        <label for="id"><b>ID</b></label>
        <input class="form-control" type="text" placeholder="ID" value="${produto.id}" name="id" readonly>
    </div>

    <div class="form-group">
        <label for="descricao"><b>Descri��o</b></label>
        <input class="form-control" type="text" placeholder="Coloque a Descri��o" value="${produto.descricao}" name="descricao" required>
    </div>
    <div class="form-group">
        <label for="preco"><b>Pre�o</b></label>
        <input class="form-control" type="text" placeholder="Digite o Pre�o" value="${produto.preco}" name="preco" required>
    </div>
    <div class="form-group">
        <label for="unidade"><b>Unidade</b></label>
        <input class="form-control" type="text" placeholder="Digite a unidade do produto" value="${produto.unidade}" name="unidade" required>
    </div>
    <br><br>
    <button class="btn btn-primary" type="submit">Atualizar</button>
    <a href="listarProdutos"><button class="btn btn-danger">Voltar</button></a>

</form>


<jsp:include page="Assets/footer.jsp" />