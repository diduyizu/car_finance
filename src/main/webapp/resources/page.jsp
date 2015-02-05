<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="inline pull-right page">
    <a href='"${ctx}/people/people/index'>首页</a>
    <a href="${ctx}/people/people/index?page=${prepages}">上一页</a>
    <span class='current'>${current_page}</span>/${pages} 页
    <a href="${ctx}/people/people/index?page=${nextpages}">下一页</a>
    <a href='${ctx}/people/people/index?page=${pages}'>尾页</a>
</div>