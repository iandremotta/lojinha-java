<jsp:include page="Assets/header.jsp" />

<h1>Cadastro de Usuário</h1>
<hr>
<form method="POST" action="cadastroUser">
    <div class="form-group">
        <label for="descricao">E-mail: </label>
        <input type="text" class="form-control" name="email" required>
    </div>
    <div class="form-group">
        <label for="preco">Senha: </label>
        <input type="password" class="form-control col-5" name="senha" required>
    </div>

    <br><br>
    <button class="btn btn-primary" type="submit">Cadastrar</button>
    <a class="btn btn-danger" href="index.jsp">Voltar</a>
</form>



<jsp:include page="Assets/footer.jsp" />