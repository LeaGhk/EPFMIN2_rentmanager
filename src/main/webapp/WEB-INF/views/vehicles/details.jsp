<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/views/common/head.jsp"%>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <%@ include file="/WEB-INF/views/common/sidebar.jsp" %>

<div class="content-wrapper">
<section class="content">
    <div class="row">
        <div class="col-md-3">
            <div class="box box-primary">
                <div class="box-body box-profile">
                    <h3 class="profile-username text-center">${vehicle.constructeur} ${vehicle.modele}</h3>
                    <h3 class="profile-username text-center">${vehicle.nb_places} places</h3>
                    <ul class="list-group list-group-unbordered">
                        <li class="list-group-item"> <b>Reservation(s)</b> <a class="pull-right">${nbReservations}</a> </li>
                        <li class="list-group-item"> <b>Clients(s)</b> <a class="pull-right">${nbClients}</a> </li>
                    </ul>
                </div>
            </div>
        </div>

        <div class="col-md-9">
            <div class="nav-tabs-custom">
                <ul class="nav nav-tabs">
                    <li class="active"><a href="#rents" data-toggle="tab">Reservations</a></li>
                    <li><a href="#users" data-toggle="tab">Clients</a></li>
                </ul>
                <div class="tab-content">
                    <div class="active tab-pane" id="rents">
                        <div class="box-body no-padding">
                            <table class="table table-striped">
                                <tr>
                                    <th style="width: 10px">#</th>
                                    <th>Client</th>
                                    <th>Date de debut</th>
                                    <th>Date de fin</th>
                                </tr>
                                <c:forEach items="${reservations}" var="res">
                                <tr>
                                    <td>${res.id}.</td>
                                    <td>${res.client.prenom} ${res.client.nom}</td>
                                    <td>${res.debut}</td>
                                    <td>${res.fin}</td>
                                </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </div>

                    <div class="tab-pane" id="users">
                        <div class="box-body no-padding">
                            <table class="table table-striped">
                                <tr>
                                    <th style="width: 10px">#</th>
                                    <th>Nom</th>
                                    <th>Prenom</th>
                                    <th>Email</th>
                                </tr>

                                <c:forEach items="${clients}" var="c">
                                <tr>
                                    <td>${c.id}.</td>
                                    <td>${c.nom}</td>
                                    <td>${c.prenom}</td>
                                    <td>${c.email}</td>
                                </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</section>
</div>

    <%@ include file="/WEB-INF/views/common/footer.jsp" %>
</div>
    <%@ include file="/WEB-INF/views/common/js_imports.jsp" %>
</body>
</html>
