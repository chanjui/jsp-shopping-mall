<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${requestScope.products ne null}">
    <c:forEach var="item" items="${requestScope.products}">
        <div class="product-item">
            <img src="${fn:split(item.prod_image, ',')[0]}" alt="상품 이미지" class="product-image" onclick="location.href='Controller?type=productDetails&action=select&prod_no=${item.id}'">
            <div class="product-info">
                <p class="product-brand">
                    <strong onclick="location.href='Controller?type=productDetails&action=select&prod_no=${item.id}'">${item.brand}</strong>
                    <c:choose>
                        <c:when test="${not empty sessionScope.customer_info}">
                            <c:choose>
                                <c:when test="${item.user_like_status eq '1'}">
                                    <i class="bi bi-heart-fill" onclick="unlikeProduct(${item.id})"></i>
                                </c:when>
                                <c:otherwise>
                                    <i class="bi bi-heart" onclick="likeProduct(${item.id})"></i>
                                </c:otherwise>
                            </c:choose>
                        </c:when>
                        <c:otherwise>
                            <i class="bi bi-heart" onclick="notLoginLike()"></i>
                        </c:otherwise>
                    </c:choose>
                </p>
                <div onclick="location.href='Controller?type=productDetails&action=select&prod_no=${item.id}'">
                    <p class="product-name">${item.name}</p>
                    <p class="product-price">
                        <del><fmt:formatNumber value="${item.price}"/>원</del>
                        <strong><fmt:formatNumber value="${item.saled_price}"/>원</strong>
                    </p>
                </div>
            </div>
        </div>
    </c:forEach>
</c:if>
