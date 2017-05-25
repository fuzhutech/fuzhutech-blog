<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE Html>
<html>
<head>
    <jsp:include page="common/head.jsp" flush="false"/>
    <link href="${g_domain}/jsp-styles.css" rel="stylesheet"/>
</head>
<body>
<!-- 顶部导航 -->
<div class="navbar navbar-fixed-top main-nav" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <a href="" class="navbar-brand navbar-brand-my">
                FuzhuTech
            </a>
        </div>
    </div>
</div>

<!--div id="description">
    <a href=".">扶竹技术 - www.fuzhutech.com</a>用以记录学习和成长的地方，着力于保险公司、保险资产管理公司投资业务系统的学习研究,尤其关注投资清算、估值核算、系统运维方面......
</div-->

<!-- 主体内容区域 -->
<div class="container main-container">
    <div class="row">
        <div class="col-sm-9">

            <div class="post-list-container">
                <div class="row">
                    <div class="col-sm-12">

                        <c:forEach items="${postList}" var="post">
                            <div class="post-item-container">
                                <h4>
                                    <a href="${g_domain}/p/${post.id}">
                                        <c:if test="${post.status=='1'}">私密:</c:if>
                                            ${post.title}
                                    </a>
                                </h4>

                                <div style="padding-bottom: 10px">
                                    <div class="post_content"
                                         style="text-indent: 1.5em;line-height: 150%;display: inline;">
                                        <p class="excerpt">${post.excerpt}...
                                            <a class="more_link" href="${g_domain}/p/${post.id}">阅读全文</a>
                                        </p>
                                    </div>
                                </div>

                                <p>
                                    <span class="nickName" style="margin-right: 10px">${post.nickName}</span>
                                    <span class="date"><fmt:formatDate value="${post.createTime}" pattern="yyyy-MM-dd hh:mm:ss"/></span>
                                    <span><i class="fa fa-eye" aria-hidden="true"></i>&nbsp;${post.readCount}</span>
                                    <span><i class="fa fa-comment" aria-hidden="true"></i>&nbsp;${post.commentCount}</span>
                                </p>

                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>


        </div>
        <div class="col-sm-3">

            <div class="social-channel-container">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">扶竹技术</h3>
                    </div>
                    <div class="panel-body">
                        记录学习和成长的地方，着力于保险资产管理相关投资业务系统的学习研究,尤其关注投资清算、估值核算、系统运维方面......
                    </div>
                </div>
            </div>

            <div class="online-contact-container">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">联系方式</h3>
                    </div>
                    <div class="list-group">
                        <a target="_blank"
                           href="//shang.qq.com/wpa/qunwpa?idkey=a0ebf6485da4c7c3a691a6193ba0f05ba3a17c5a6d6825da4e831a5c587e08a5"
                           class="list-group-item">
                            <i class="fa fa-qq" aria-hidden="true"></i>群:559878181
                            <img src="assets/imgs/1493273833559.png" class="img-responsive">
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>


</div>

<jsp:include page="common/footer.jsp" flush="false"/>

</body>
</html>
