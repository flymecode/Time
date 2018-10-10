
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="http://yanshi.sucaihuo.com/jquery/20/2068/demo/css/shouye.css">
    <script type="text/javascript" src="http://yanshi.sucaihuo.com/jquery/20/2068/demo/js/jquery.min.js"></script>
    <script type="text/javascript"
            src="http://yanshi.sucaihuo.com/jquery/20/2068/demo/js/modernizr-custom-v2.7.1.min.js"></script>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
    <script type="text/javascript">
        $(document).ready(function () {
            var $miaobian = $('.Xcontent08>div');
            var $huantu = $('.Xcontent06>img');
            var $miaobian1 = $('.Xcontent26>div');
            $miaobian.mousemove(function () {
                miaobian(this);
            });
            $miaobian1.click(function () {
                miaobian1(this);
            });

            function miaobian(thisMb) {
                for (var i = 0; i < $miaobian.length; i++) {
                    $miaobian[i].style.borderColor = '#dedede';
                }
                thisMb.style.borderColor = '#cd2426';
                $huantu[0].src = thisMb.children[0].src;
            }

            function miaobian1(thisMb1) {
                for (var i = 0; i < $miaobian1.length; i++) {
                    $miaobian1[i].style.borderColor = '#dedede';
                }

                $miaobian.css('border-color', '#dedede');
                thisMb1.style.borderColor = '#cd2426';
                $huantu[0].src = thisMb1.children[0].src;
            }

            $(".Xcontent33").click(function () {
                var value = parseInt($('.input').val()) + 1;
                $('.input').val(value);
            })

            $(".Xcontent32").click(function () {
                var num = $(".input").val()
                if (num > 0) {
                    $(".input").val(num - 1);
                }
            })
        })
    </script>
</head>
<body>
<!--导航条-->
<%@include file="header.jsp" %>
<!--商品详情-->
<div class="Xcontent" style="margin-top: 100px">
    <ul class="Xcontent01">

        <div class="Xcontent06"><img src="${pageContext.request.contextPath}/img/bookInfo/X1.png"></div>
        <ol class="Xcontent08">
            <div class="Xcontent07"><img src="${pageContext.request.contextPath}/img/bookInfo/X1.png"></div>
            <div class="Xcontent09"><img src="${pageContext.request.contextPath}/img/bookInfo/X7.png"></div>
            <div class="Xcontent10"><img src="${pageContext.request.contextPath}/img/bookInfo/X8.png"></div>
            <div class="Xcontent11"><img src="${pageContext.request.contextPath}/img/bookInfo/X9.png"></div>
            <div class="Xcontent12"><img src="${pageContext.request.contextPath}/img/bookInfo/X10.png"></div>
        </ol>
        <ol class="Xcontent13">
            <div class="Xcontent14"><a href="#"><p>${book.title}</p></a></div>
            <div class="Xcontent15"><img src="${pageContext.request.contextPath}/img/bookInfo/X11.png"></div>
            <div class="Xcontent16"><p>淡泊明志，宁静致远</p></div>
            <div class="Xcontent17">
                <p class="Xcontent18">售价</p>
                <p class="Xcontent19">￥<span>${book.price}</span></p>
                <div class="Xcontent20">
                    <p class="Xcontent21">促销</p>
                    <img src="${pageContext.request.contextPath}/img/bookInfo/X12.png">
                    <p class="Xcontent22">领610元新年礼券，满再赠好礼</p>
                </div>
                <div class="Xcontent23">
                    <p class="Xcontent24">服务</p>
                    <p class="Xcontent25">30天无忧退货&nbsp;&nbsp;&nbsp;&nbsp; 48小时快速退款 &nbsp;&nbsp;&nbsp;&nbsp; 满88元免邮</p>
                </div>

            </div>
            <div class="Xcontent26">
                <p class="Xcontent27">库存</p>
                <p class="Xcontent27">34214本</p>
            </div>
            <div class="Xcontent30">
                <p class="Xcontent31">数量</p>
                <div class="Xcontent32"><img src="${pageContext.request.contextPath}/img/bookInfo/X15.png"></div>
                <form>
                    <input id="book_num" class="input" value="1"></form>
                <div class="Xcontent33"><img src="${pageContext.request.contextPath}/img/bookInfo/16.png"></div>
            </div>
            <div class="Xcontent34"><a href="#"><img src="${pageContext.request.contextPath}/img/bookInfo/X17.png"></a>
            </div>
            <div class="Xcontent35"><a href="#" onclick="addCart()"><img
                    src="${pageContext.request.contextPath}/img/bookInfo/X18.png"></a></div>
        </ol>
    </ul>
</div>
<script>
    function addCart() {
        var num = $("#book_num").val();
        var url = "${pageContext.request.contextPath}/cartServlet";
        var param = {"method": "addCart", "bookNumber": num, "bookId":${book.bookId},"bookPrice":${book.price} };
        $.post(url, param);
        var number = $("#cart_number").text();
        var c = parseInt(number)+1;
        $("#cart_number").text(c);
        $("#cart_number").show();

    }

</script>
<div class="container jumbotron">
    <div class="page-header container" style="margin-top: 0px;">
        <h3>读书简介</h3>
    </div>
    <div class="panel-body">
        <p>
            《遮蔽的天空》是蒙尘70年的20世纪美国文学经典，讲述了我们一生中会遇到的绝大多数问题：爱情、婚姻、旅行、死亡、存在价值、人生意义。意识到人生虚无的人，比任何人都更渴望真实地活着。
            　　二战结束后，三个美国知识分子前往非洲撒哈拉旅行。波特和姬特结婚12年后彼此疏远，渴望通过这次旅行来恢复感情。然而他们在空旷浩渺的沙漠中，逐渐迷失了自己，两人更加疏远。在死亡降临时，他们才真正面对彼此，发现每个人的生命都极其有限，但为时已晚，过于触碰虚无的他们注定要在沙漠里永远流浪下去……
            “因为我们不知道死亡何时降临，我们才会以为生命是一口永不干涸的井。然而每件事情都只会发生一个特定的次数，一个很少的次数，真的。你还会想起多少次童年的那个特定的下午，那个已经深深成为你生命一部分、没有它你便无法想象自己人生的下午？也许还有四五次。也许更少。你还会看到多少次满月升起？也许二十次。然而我们却总觉得这些都是无穷的。”——摘自本书
        </p>
        <p>
            《遮蔽的天空》是蒙尘70年的20世纪美国文学经典，讲述了我们一生中会遇到的绝大多数问题：爱情、婚姻、旅行、死亡、存在价值、人生意义。意识到人生虚无的人，比任何人都更渴望真实地活着。
        </p>
        　　<p>
        二战结束后，三个美国知识分子前往非洲撒哈拉旅行。波特和姬特结婚12年后彼此疏远，渴望通过这次旅行来恢复感情。然而他们在空旷浩渺的沙漠中，逐渐迷失了自己，两人更加疏远。在死亡降临时，他们才真正面对彼此，发现每个人的生命都极其有限，但为时已晚，过于触碰虚无的他们注定要在沙漠里永远流浪下去……

        “因为我们不知道死亡何时降临，我们才会以为生命是一口永不干涸的井。然而每件事情都只会发生一个特定的次数，一个很少的次数，真的。你还会想起多少次童年的那个特定的下午，那个已经深深成为你生命一部分、没有它你便无法想象自己人生的下午？也许还有四五次。也许更少。你还会看到多少次满月升起？也许二十次。然而我们却总觉得这些都是无穷的。”——摘自本书
    </p>
        <p>
            【推荐语】
        <p> ◆蒙尘70年的世纪文学经典！数次被出版社退稿，几度绝版。
        </p>
        <p>◆美国文学史上罕有的同时选两大文学榜单的经典著作：兰登书屋现代文库20世纪 100部伟大英语小说；《时代周刊》1923年以来100部伟大英语小说。
        </p>
        <p>◆与《局外人》《不能承受的生命之轻》一起并列为存在主义文学经典。
        </p>
        <p>◆1949年首先在英国出版，随后在美国出版，出版后连续10周登上《纽约时报》畅销榜，在美国平装版首印就多达20万册，两个月内加印三次，但此后却几度绝版。
        </p>
        <p>◆1990年，《遮蔽的天空》由贝托鲁奇（《末代皇帝》）改编成电影，获金球奖、英国电影学院奖等多项大奖。鲍尔斯本人亦有出色演出。
        </p>
        <p>◆2017年，坂本龙一因为太爱这本书，甚至将鲍尔斯在电影中朗读书中精彩段落的原声编了自己新专辑中的一首歌曲《Fullmoon》。
        </p>
        <p> ◆经典中的经典！田纳西·威廉斯、威廉·巴勒斯、安东尼·伯吉斯、诺曼·梅勒、贝托鲁奇、坂本龙一、詹妮弗·康纳利等著名作家、导演、作曲家、演员联袂推荐的人生之书。
        </p>
        <p>◆《遮蔽的天空》讲述了我们一生中会遇到的绝大多数问题：爱情、婚姻、旅行、死亡、存在价值、人生意义……
        </p>
        <p>◆意识到人生虚无的人，比任何人都更渴望真实地活着。
        </p>

    </div>

    <%--<div class="page-header container" style="margin-top: 10px;">--%>
        <%--<h3>图书详情</h3>--%>
    <%--</div>--%>
    <%--<div align="center">--%>
        <%--<img src="${pageContext.request.contextPath}/img/book_list/1.jpg" class="img-thumbnail">--%>
    <%--</div>--%>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        $.goup({
            trigger: 100,
            bottomOffset: 150,
            locationOffset: 100,
            title: '返回顶部',
            titleAsText: true
        });
    });
</script>
<!--页脚-->
<%@include file="footer.jsp" %>
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.goup.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>
