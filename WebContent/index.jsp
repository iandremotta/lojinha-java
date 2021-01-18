<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="Assets/header.jsp" />

<c:if test="${msg != null}">
    <div class="alert alert-success alert-dismissible fade show text-center" role="alert">
        <h5>${msg}</h5>
        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
            <span aria-hidden="true">&times;</span>
        </button>
    </div>
</c:if> 

<div class="row">
    <div class="col-6 mx-auto col-md-4 order-md-2">
        <img src="Assets/images/store.png" class="img-fluid">

    </div>
    <div class="col-md-6 order-md-1 text-center text-md-left pr-md-5">
        <h1 class="mb-3">Bem-vindo a Lojinha!</h1>
        <div class="text-center text-md-left">
            <c:if test="${usuarioLogado == null}">
                <a class="btn btn-primary" href="<%=request.getContextPath()%>/cadastrarUsuario.jsp">Criar Conta</a>
            </c:if> 
        </div>
    </div>
</div>

<jsp:include page="Assets/footer.jsp" /> 