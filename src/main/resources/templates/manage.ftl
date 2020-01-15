<script type="text/javascript" src="/jquery.3.41.min.js"></script>
<style>
    textarea {
        width : 300px;
        height: 500px;
    }
</style>
<div id="textForm">
    <label for="title">제목</label>
    <input id="title" value="${title}">
    <br>

    <label for="title">내용</label>
    <textarea id="contentText">${contentText}</textarea>
    <br>

    <button id="btnUpdate">Send</button>
</div>
<div id="result"></div>
<script>
    $(document).ready(function() {
       $("#btnUpdate").on("click", function() {

           let data = {
               title : $("#title").val(),
               contentText : $("#contentText").val()
           };

           $.ajax({
               type: "POST",
               url: "/texts",
               contentType: "application/json",
               data: JSON.stringify(data),
               success: function( data ) {
                   $("#result").text(data);
                   location.href = "/";
               },
               dataType: "JSON"
           });
       });
    });
</script>