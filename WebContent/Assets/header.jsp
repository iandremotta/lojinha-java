<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" href="https://getbootstrap.com/docs/4.3/examples/cover/cover.css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        
        <style>
            .btn {
                border-radius:  18px;
            }
        </style>
    </head>
    <body>
        <div class="container">

            <div class="container d-flex w-100 h-100 p-3 mx-auto flex-column">
                <header class="masthead mb-auto">

                    <div class="inner">
                        <h3 class="masthead-brand"><a href="index.jsp">Lojinha</a></h3>

                        <nav class="nav nav-masthead justify-content-center">
                            <c:if test="${usuarioLogado != null}">
                                <a class="nav-link" href="listarProdutos">Listar Produtos</a>
                                <a class="nav-link" href="cadastrar.jsp">Novo Produto</a>
                                <a class="nav-link" href="carrinho.jsp">Meu Carrinho</a>
                            </c:if>  

                            <c:if test="${usuarioLogado == null}">
                                <a class="nav-link" href="login">Entrar</a>
                            </c:if>  

                            <c:if test="${usuarioLogado != null}">
                                <a class="nav-link" href="logout">Sair</a>
                            </c:if>  

                        </nav>
                    </div> 

                </header>
