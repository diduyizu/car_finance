<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="inline pull-right page">
    <a href="${baseurl}${page_url}">首页</a>
    <a href="${baseurl}${page_url}?page_index=${prepage}">上一页</a>
    <span class='current'>${current_page}</span>/${pages} 页
    <a href="${baseurl}${page_url}?page_index=${nextpage}">下一页</a>
    <a href="${baseurl}${page_url}?page_index=${pages}">尾页</a>
</div>