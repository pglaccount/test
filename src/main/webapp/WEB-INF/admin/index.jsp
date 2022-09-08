
<%--
  Created by IntelliJ IDEA.
  User: 无意穿堂风
  Date: 2022/5/30/0030
  Time: 9:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.bundle.js"></script>
    <style>
        #tbody img,#orders img{
            width: 100px;
        }
        .table td{
            vertical-align: inherit !important;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col text-center">
            <h3 class="text-center">欢迎您：${sessionScope.user.username}</h3>
        </div>
        <div class="col text-center">
            <h5 class="text-center btn btn-primary" onclick="logout()">退出登录</h5>
        </div>
       </div>

    <div class="select">
        <ul class="nav nav-tabs" id="myTab" role="tablist">
            <li class="nav-item" role="presentation">
                <a class="nav-link active" id="home-tab" data-toggle="tab" href="#productList" role="tab" aria-controls="home" aria-selected="true">商品列表</a>
            </li>
            <li class="nav-item" role="presentation">
                <a class="nav-link" id="profile-tab" data-toggle="tab" href="#orderView" role="tab" aria-controls="profile" aria-selected="false">订单列表</a>
            </li>
        </ul>
        <div class="tab-content" id="myTabContent">
            <div class="tab-pane fade show active" id="productList" role="tabpanel" aria-labelledby="home-tab">

                <div class="row">
                    <div class="col">
                        <div class="goods">
                            <form>
                                <table id="goods" class="table table-striped">
                                    <thead>
                                    <tr>
                                        <th scope="col"><input type="checkbox" id="allCheck" onclick="checkAll(this)" name="allCheck" value="allCheck"></th>
                                        <th scope="col">id</th>
                                        <th scope="col">名字</th>
                                        <th scope="col">价钱</th>
                                        <th scope="col">图片</th>
                                    </tr>
                                    </thead>
                                    <tbody id="tbody">
                                    </tbody>
                                </table>
                                <button class="btn btn-danger fixed-bottom" type="button" onclick="buy()">购买</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <div class="tab-pane fade" id="orderView" role="tabpanel" aria-labelledby="profile-tab">

                <div class="row">
                    <div class="col">
                        <div class="goods">
                            <form>
                                <table id="orders" class="table table-striped">
                                    <thead>
                                    <tr>
                                        <th scope="col">订单号</th>
                                        <th scope="col">商品编号</th>
                                        <th scope="col">名字</th>
                                        <th scope="col">价钱</th>
                                        <th scope="col">图片</th>
                                    </tr>
                                    </thead>
                                    <tbody id="orderList">

                                    </tbody>

                                </table>
                            </form>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>





</div>

<script type="text/javascript">
        function getAllPrdoucts(){
        $.ajax({
            url: '${pageContext.request.contextPath}/product/getAllProduct',
            method: 'GET',
            contentType: 'application/json',
            dataType: 'json',
            success: function (response) {

                if (response.length){
                    response.forEach(item => {
                        $("#tbody").append(" <tr>\n" +
                            "<td><input type=\"checkbox\" onclick='checkOne()' class='good' name=\"goods\" value=\""+ item.id +"\"></td>" +
                            "                        <td scope=\"row\">" + item.id +"</td>\n" +
                            "                        <td>" + item.name +"</td>\n" +
                            "                        <td>"+ item.price+"</td>\n" +
                            "                        <td><img src='${pageContext.request.contextPath}/"+ item.image+ "'></td>\n" +
                            "                    </tr>")
                    })
                }else {
                    $("#tbody").html("<tr><td colspan='5'><h2 class='text-center'>空空如也</h2><tr></td>")
                }
                console.log(response)
            },
            error: function (msg) {

            }
        });}

        getAllPrdoucts();
        order();

    function order(){
        $.ajax({
            url: '${pageContext.request.contextPath}/order/getOrders',
            method: 'GET',
            contentType: 'application/json',
            dataType: 'json',
            <%--data: {userId: ${sessionScope.user.id}},--%>
            success: function (response) {
                $("#orderList").html("");
                if (response.length){

                    //对订单循环
                    response.forEach(order => {
                        //对商品循环
                        if (order.products){
                            order.products.forEach(product => {
                                $("#orderList").append(" <tr>\n" +
                                    "                        <td scope=\"row\">" + order.id +"</td>\n" +
                                    "                        <td>" + product.id +"</td>\n" +
                                    "                        <td>" + product.name +"</td>\n" +
                                    "                        <td>"+ product.price+"</td>\n" +
                                    "                        <td><img src='${pageContext.request.contextPath}/"+ product.image+ "'></td>\n" +
                                    "                    </tr>")
                            })
                        }

                    })
                }else {
                    $("#orderList").html("<tr><td colspan='5'><h2 class='text-center'>空空如也</h2><tr></td>")
                }
                console.log(response)
            },
            error: function (msg) {
                $("#orderList").html("<tr><td colspan='5'><h2 class='text-center'>空空如也</h2><tr></td>")
            }
        })
    }

    function checkAll(element) {
        console.log(element.checked)
        $(":checkbox").prop("checked", element.checked)
    }
    function checkOne() {
        var isAllCheck = true;
        $(".good").each((index,item) => {
            console.log(index)
            console.log(item)
            if (item.checked == false){
                isAllCheck = false;
            }
        })
        console.log(isAllCheck)
        $("#allCheck").prop("checked", isAllCheck)
    }
    function buy() {
        var goods = [];
        $(".good").each((index,item) => {
            if (item.checked){
                goods.push(item.value);
            }
        })
        var data = JSON.stringify(goods);
        $.ajax({
            url: '${pageContext.request.contextPath}/order/addOrder',
            method: 'POST',
            contentType: 'application/json',
            dataType: 'text',//响应的数据类型
            data: data,
            success: (response) => {
                order();
                console.log(response)
                if (response == "success"){
                    $(":checkbox").prop("checked",false)
                    alert("购买成功")
                    $("#profile-tab").click()
                }else if (response == "error"){
                    alert("出错了")
                }
            },
            error: (msg) => {
                console.log("error")
                console.log(msg)
        }
        })
    }
    function logout() {
        $.ajax({
            url: '${pageContext.request.contextPath}/user/logout',
            success: (response) => {
                console.log(response)
                if (response){
                    window.location.href = "${pageContext.request.contextPath}/login.jsp"
                }
            },
            error: (msg) => {
                console.log(msg)
            }
        })
    }
</script>

</body>
</html>
