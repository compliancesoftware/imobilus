<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="commons/header.jsp"/>

<div class="menu-toggle-next-btn">
    <i class="material-icons">reorder</i>
</div>
<div class="cabecalho">
    <div class="logomarca" style="background-image:url('resources/images/logomarca.png');"></div>
    <div class="apresentacao">
        <p>Bem vindo ‡ Imobilus</p>
    </div>
</div>

<jsp:include page="commons/navbar.jsp"/>

<div class="content">
    <div class="galeria">
        <img src="resources/images/carrossel/anuncio1.png" alt="An√∫ncio 1" />
        <img src="resources/images/carrossel/anuncio2.png" alt="An√∫ncio 2" />
        <img src="resources/images/carrossel/anuncio3.png" alt="An√∫ncio 3" />
        <img src="resources/images/carrossel/anuncio4.png" alt="An√∫ncio 4" />
        <img src="resources/images/carrossel/anuncio5.png" alt="An√∫ncio 5" />    
    </div>
    <div class="lancamentos">
        <div class="lancamento" id="lancamento1">
            <div class="image-div" style="background-image: url('resources/images/lancamentos/lancamento1.png');">
            </div>
            <p class="image-label">Apartamento</p>
            <div class="info">
                <div class="info-municipio">Recife</div>
                <div class="info-tipo">APARTAMENTO</div>
                <div class="info-bairro">Boa Vista</div>
                <div class="info-detalhes">3 QUARTOS | 89.0M≤</div>
            </div>
        </div>
        <div class="lancamento" id="lancamento2">
            <div class="image-div" style="background-image: url('resources/images/lancamentos/lancamento2.png');">
            </div>
            <p class="image-label">Apartamento</p>
            <div class="info">
                <div class="info-municipio">Jaboat√£o dos Guararapes</div>
                <div class="info-tipo">APARTAMENTO</div>
                <div class="info-bairro">Curado 2</div>
                <div class="info-detalhes">2 QUARTOS | 39.0M≤</div>
            </div>
        </div>
        <div class="lancamento" id="lancamento3">
            <div class="image-div" style="background-image: url('resources/images/lancamentos/lancamento3.png');">
            </div>
            <p class="image-label">Apartamento</p>
            <div class="info">
                <div class="info-municipio">Recife</div>
                <div class="info-tipo">APARTAMENTO</div>
                <div class="info-bairro">Boa Vista</div>
                <div class="info-detalhes">3 QUARTOS | 89.0M≤</div>
            </div>
        </div>
        <div class="lancamento" id="lancamento4">
            <div class="image-div" style="background-image: url('resources/images/lancamentos/lancamento4.png');">
            </div>
            <p class="image-label">Apartamento</p>
            <div class="info">
                <div class="info-municipio">Recife</div>
                <div class="info-tipo">APARTAMENTO</div>
                <div class="info-bairro">Boa Vista</div>
                <div class="info-detalhes">3 QUARTOS | 89.0M≤</div>
            </div>
        </div>
        <div class="lancamento" id="lancamento5">
            <div class="image-div" style="background-image: url('resources/images/lancamentos/lancamento5.png');">
            </div>
            <p class="image-label">Apartamento</p>
            <div class="info">
                <div class="info-municipio">Recife</div>
                <div class="info-tipo">APARTAMENTO</div>
                <div class="info-bairro">Boa Vista</div>
                <div class="info-detalhes">3 QUARTOS | 89.0M≤</div>
            </div>
        </div>
    </div>
    <div class="cadastro">
        <form action="cadastre">
            <p>Cadastre-se e receba nossas novidades por e-mail</p>
            <input type="text" name="email" placeholder="E-mail">
            <button type="submit">Cadastrar-se</button>
        </form>
    </div>
</div>
        
<jsp:include page="commons/footer.jsp"/>