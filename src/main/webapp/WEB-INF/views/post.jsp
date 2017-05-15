<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE Html>
<html>
<head>
    <jsp:include page="common/head.jsp" flush="false"/>
</head>
<body>

<!-- 顶部导航 -->
<div class="navbar navbar-fixed-top main-nav" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <a href="${g_domain}" class="navbar-brand navbar-brand-my">
                FuzhuTech
            </a>
        </div>
    </div>
</div>

<div class="container main-container">

    <div class="post-detail-container">

        <div class="row">
            <div class="col-sm-12">
                <h3>${post.title}</h3>
                <div style="margin-top: 22px;border-bottom: 1px dashed #d9d9d9;">
                    <span class="nickName" style="margin-right: 10px">${post.nickName}</span>
                    <span class="date"><fmt:formatDate value="${post.createTime}" pattern="yyy-MM-dd hh:mm:ss"/></span>
                    <span><i class="fa fa-eye" aria-hidden="true"></i>&nbsp;${post.readCount}</span>
                    <span><i class="fa fa-comment" aria-hidden="true"></i>&nbsp;${post.commentCount}</span>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="post-content">
                ${post.content}
            </div>
        </div>
    </div>

    <div class="row">
        <div class="comment-container">
            <div class="col-sm-12">
                <c:choose>
                    <c:when test="${commentList ==null || fn:length(commentList)==0}">
                        <div class="comment-item-container">
                            <h5>暂无评论</h5>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${commentList}" var="comment">
                            <div class="comment-item-container">
                                <h5>${comment.content}</h5>
                                <p>
                                    <span>
                                        <c:if test="${comment.userNickName != null}">${comment.userNickName}</c:if>
                                        <c:if test="${comment.userNickName == null}">游客</c:if>
                                    </span>
                                    <span class="date"><fmt:formatDate value="${comment.createTime}" pattern="yyy-MM-dd hh:mm:ss"/></span>
                            </div>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</div>

<jsp:include page="common/footer.jsp" flush="false"/>

</body>
</html>
