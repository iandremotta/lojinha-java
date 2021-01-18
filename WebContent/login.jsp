<jsp:include page="Assets/header.jsp" />

<h1>Login</h1>
<hr>

<main role="main">


    <form method="POST" action="login">


        <div class="form-group">
            <label for="inputEmail">Endereço de Email</label>
            <input type="email" class="form-control col-5" placeholder="Email" name="email" required><br>
        </div>
        <div class="form-group ">
            <label for="inputpass">Senha</label>
            <input type="password" class="form-control col-5" placeholder="Senha" name="senha" required><br>
        </div>

        <input type="submit" class="btn btn-primary" value="Entrar">

        <br><br>

        <div class="">
            <p>${msgErro}</p>
        </div>


    </form>

</main>

<jsp:include page="Assets/footer.jsp" />