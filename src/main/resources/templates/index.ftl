<button onclick="location.href = '/manage'">new</button>
<ol>
    <#list texts as title>
        <li><a href="/manage/${title}">${title}</a></li>
    </#list>
</ol>