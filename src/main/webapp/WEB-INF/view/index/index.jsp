<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 视窗 -->
<meta name="viewport" content="width=device-width,initial-scale=1.0">
<title>今日头条</title>
<style type="text/css">
.ex {
	width: 250px;
	overflow: hidden; /*超出范围隐藏*/
	text-overflow: ellipsis; /*超出用省略号 */
	display:-webkit-box; /*弹性伸缩盒子显示*/
	-webkit-box-orient:vertical; /*垂直排列子元素*/
	-webkit-line-clamp:2; /*显示的行数*/
}
.channel-fixed {
    position: fixed !important;
    top: 10px;
    z-index: 30;
}
.card-fixed{
	position: fixed !important;
    top: 10px;
    z-index: 30;
}
</style>
</head>
<link rel="stylesheet" href="/resource/css/bootstrap.min.css">
<link rel="stylesheet" href="/resource/css/index.css">
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/resource/js/popper.min.js"></script>
<script type="text/javascript" src="/resource/js/bootstrap.min.js"></script>
<body>
	<div class="container-fluid">
		<div class="row" style="height:55px">
			<div class="col-md-12 bg-dark pt-2" >
				<font color="white" size="5">下载APP</font>
				<div class="float-sm-right">
					<c:if test="${sessionScope.user==null}">
						<button class="btn btn-link btn-sm text-decoration-none"
							data-toggle="modal" data-target="#register" onclick="toLogin()">
							<font color="white">登录</font>
						</button>
						<button class="btn btn-link btn-sm text-decoration-none"
							data-toggle="modal" data-target="#register" onclick="reg()">
							<font color="white">注册</font>
						</button>
					</c:if>
					<c:if test="${sessionScope.user!=null}">
						<div class="btn-group dropleft">
							<button type="button" class="btn btn-sm btn-secondary dropdown-toggle"
								data-toggle="dropdown" aria-haspopup="true"
								aria-expanded="false">${sessionScope.user.username}</button>
							<div class="dropdown-menu">
								<!-- Dropdown menu links -->
								<ul>
									<li><a href="/my" target="_blank">个人中心</a></li>
									<li><a onclick="logout()" href="#">注销</a></li>
								</ul>				
							</div>
						</div>
					</c:if>
				</div>
			</div>
		</div>
		<div class="row mt-2">
			<!-- 左侧菜单 -->
			<div class="col-md-2" >
				<div id="left">
					<ul>
						<li class="mb-3"><a href="/"><img
								src="/resource/images/indexlogo.png"
								style="width: 108px; height: 27px;"></a></li>
						<li><a
							class="channel-item ${article.channelId==null?'active':''}"
							href="/">热点</a></li>
						<c:forEach items="${requestScope.channelList}" var="channel">
							<li><a
								class="channel-item ${article.channelId==channel.id?'active':''}"
								href="/?channelId=${channel.id}">${channel.name}</a></li>
						</c:forEach>
					</ul>
				</div>
			</div>
			<div class="col-md-7">
				<!-- 如果栏目为空，默认显示广告和热点文章 -->
				<c:if test="${article.channelId==null}">
					 <!-- 广告 -->
					<div class="mb-3">
						<div id="carouselExampleCaptions" class="carousel slide"
							data-ride="carousel">
							<ol class="carousel-indicators">
								<c:forEach items="${requestScope.slideList}" var="slide" varStatus="i">
									<li data-target="#carouselExampleCaptions" data-slide-to="${i.index}"
										class="${i.count==1?'active':''}" ></li>
								</c:forEach>
							</ol>
							<div class="carousel-inner">
								<c:forEach items="${requestScope.slideList}" var="slide" varStatus="i">
									<div class="carousel-item ${i.count==1?'active':''}">
										<img src="/pic/${slide.picture}" style="height:300px" class="d-block w-100 text-center rounded" alt="...">
										<div class="carousel-caption d-none d-md-block">
											<h5>${slide.title}</h5>
										</div>
									</div>
								</c:forEach>
							</div>
							<a class="carousel-control-prev" href="#carouselExampleCaptions"
								role="button" data-slide="prev"> <span
								class="carousel-control-prev-icon" aria-hidden="true"></span> <span
								class="sr-only">Previous</span>
							</a> 
							<a class="carousel-control-next" href="#carouselExampleCaptions"
								role="button" data-slide="next"> <span
								class="carousel-control-next-icon" aria-hidden="true"></span> <span
								class="sr-only">Next</span>
							</a>
						</div>
					</div>
					<!-- 热点文章 -->
					 <div>
					 	 <c:forEach items="${requestScope.articleList}" var="article">
							<div class="media">
								<a href="/detail?id=${article.id}" target="_blank" class="text-dark font-weight-bold">
									<img src="/pic/${article.picture}" class="rounded mr-3"
									style="width: 156px; height: 101.8px" alt="...">
								</a>
								<div class="media-body">
									<h5 class="mt-0">
										<a href="/detail?id=${article.id}" target="_blank" class="text-dark font-weight-bold">${article.title}</a>
									</h5>
									${article.user.username} &nbsp;
									<fmt:formatDate value="${article.created}" pattern="yyyy-MM-dd HH:mm:ss"/>
								</div>
							</div>
							<hr>
						</c:forEach>
						<jsp:include page="/WEB-INF/view/common/pages.jsp" />
					 </div>
				</c:if>
				<c:if test="${article.channelId!=null}">
					<!-- 文章的分类 -->
					<div>
						<ul class="subchannel">
							<li
								class="sub-item ${article.categoryId==null?'sub-selected':''}"><a
								href="/?channelId=${article.channelId}">全部</a></li>
							<c:forEach items="${categoryList}" var="category">
								<li
									class="sub-item ${article.categoryId==category.id?'sub-selected':''}"><a
									href="/?channelId=${article.channelId}&categoryId=${category.id}">${category.name}</a></li>
							</c:forEach>
						</ul>
					</div>


					<!-- 显示分类下的文章 -->
					<div>
						<c:forEach items="${requestScope.articleList}" var="article">
							<div class="media">
								<a href="/detail?id=${article.id}" target="_blank" class="text-dark font-weight-bold">
									<img src="/pic/${article.picture}" class="rounded mr-3"
									style="width: 156px; height: 101.8px" alt="...">
								</a>
								<div class="media-body">
									<h5 class="mt-0">
										<a href="/detail?id=${article.id}" target="_blank" class="text-dark font-weight-bold">${article.title}</a>
									</h5>
									${article.user.username} &nbsp;
									<fmt:formatDate value="${article.created}" pattern="yyyy-MM-dd HH:mm:ss"/>
								</div>
							</div>
							<hr>
						</c:forEach>
						<jsp:include page="/WEB-INF/view/common/pages.jsp" />
					</div>
				</c:if>
			</div>
			
			<!-- 右边显示最新的五条文章 -->
			<div class="col-md-3">
			<!-- ====================搜索框开始======================== -->
			<form action="/search" method="get">
					<div class="input-group mb-3">
						<input type="text" name="key" value="${key}" class="form-control"
							placeholder="从es索引库中搜索" aria-label="Recipient's username"
							aria-describedby="button-addon2">
						<div class="input-group-append">
							<button class="btn btn-outline-secondary" id="button-addon2">搜索</button>
						</div>
					</div>
				</form>
				<!-- =================搜索框结束=========================== -->
				<div class="card" style="width: 18rem;" id="right">
					<div class="card-header">
						<b>24小时热闻</b>
					</div>
					<div class="card-body">
						<ul class="list-unstyled">
							<c:forEach items="${requestScope.hot24Articles}" var="article">
								<li class="media">
									<a href="/detail?id=${article.id}" target="_blank" class="text-dark font-weight-bold">
										<img src="/pic/${article.picture}" class="mr-3 rounded" alt="..." style="width:50px;height:50px">
									</a>
									<div class="media-body ex">
										<a href="/detail?id=${article.id}" target="_blank" class="text-dark">${article.title}</a>
									</div>
								</li>
								<hr>
							</c:forEach>
						</ul>
					</div>
				</div>
				
				<div class="card" style="width: 18rem;">
					<div class="card-header">
						<b>${channelName}最新文章</b>
					</div>
					<div class="card-body">
						<ul class="list-unstyled">
							<c:if test="${empty requestScope.recentArticles}">
								暂无新闻
							</c:if>
							<c:forEach items="${requestScope.recentArticles}" var="article">
								<li class="media">
									<a href="/detail?id=${article.id}" target="_blank" class="text-dark font-weight-bold">
										<img src="/pic/${article.picture}" class="mr-3 rounded" alt="..." style="width:50px;height:50px">
									</a>
									<div class="media-body ex">
										<a href="/detail?id=${article.id}" target="_blank" class="text-dark">${article.title}</a>
									</div>
								</li>
								<hr>
							</c:forEach>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	
	<!-- 注册登录页面 -->
<div class="modal fade" id="register" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="title"></h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body" id="passport">
        	
      </div>
    </div>
  </div>
</div>
</body>
<script type="text/javascript">
	//注册
	function reg(){
		$("#passport").load("/passport/reg");
		$("#title").text("用户注册");
	}
	function toLogin(){
		$("#passport").load("/passport/login");
		$("#title").text("用户登录");
	}
	$(function() {
		$(window).scroll(function() {
			//获取垂直滚动的距离
			var scrollTop = $(document).scrollTop();
			if (scrollTop >= 50) {
				$("div#left").addClass("channel-fixed");
			} else {
				$("div#left").removeClass("channel-fixed");
			}
			if(scrollTop >= 1010){
				$("div#right").addClass("card-fixed");
			} else {
				$("div#right").removeClass("card-fixed");
			}
		});
	})
	function page(pageNum) {
		var channelId = "${article.channelId}";
		var categoryId = "${article.categoryId}";
		var key = "${key}";
		var url = "";
		if(key!=""){
			url = "/search?pageNum=" + pageNum+"&key="+key;
		}else{
			url = "/?pageNum=" + pageNum;
			
		}
		if (channelId) {
			url += "&channelId=" + channelId;
		}
		if (categoryId) {
			url += "&categoryId=" + categoryId;
		}
		location.href = url;
	}
	function logout(){
		$.ajax({
			url:"/passport/logout",
			success:function(success){
				if(success){
					location.reload();
				}else{
					alert("异常错误!");
				}
			}
		})
	}
	$("#register").on("hide.bs.modal",function(){
		location.reload();
	})
</script>
</html>