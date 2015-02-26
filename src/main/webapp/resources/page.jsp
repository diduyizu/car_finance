<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="inline pull-right page">
    <a href="${ctx}${page_url}">首页</a>
    <a href="${ctx}${page_url}?page=${prepage}">上一页</a>
    <span class='current'>${current_page}</span>/${pages} 页
    <a href="${ctx}${page_url}?page=${nextpage}">下一页</a>
    <a href="${ctx}${page_url}?page=${pages}">尾页</a>
</div>