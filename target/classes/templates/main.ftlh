<#import "parts/common.ftlh" as c>
<#include "parts/security.ftlh">


<@c.page>

    <ul style="position: absolute;
  top: 60px;
  bottom: 0px;
  left: 300px;
  right: 300px;
  overflow: scroll;
  margin: 0;
  padding: 0;
  /*display: flex;*/
  /*flex-direction: column-reverse;*/
  /*align-content: baseline;*/
    overflow-x: hidden;

  font-size: 16px;
  font-family: Arial, sans-serif;
  -webkit-overflow-scrolling: touch;">

        <#list messages as message >
        <li style=" padding: 10px 20px;
  border-bottom: 1px solid #ccc;
">
                <tr>
                <th style="width: 100px;"><p style="font-weight: bold;">${message.authorName}</p></th>
                <th>${message.comment}</th>
            </tr>
        </li>
        </#list>
    </ul>
    <table style="position: absolute;bottom: 20px;">
        <form method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <tbody>
            <th><input style="width: 1000px; height: 40px; float:left;" type="text" name="comment" placeholder="сообщение"></th>
            <th>
                <button type="submit" class="btn btn-success">отправить</button>
            </th></tr>
            </tbody>
        </form>
    </table>
</@c.page>