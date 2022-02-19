<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="C:\Users\souhir\Desktop\eclipse jee\editor\WebContent\WEB-INF\style.css">
</head>
<body>
 <div class="header"> Codeboard Online IDE </div>
    <div class="control-panel">
        Select Language:
        &nbsp; &nbsp;
        <select id="languages" class="languages" onchange="changeLanguage()">
            <option value="python"> Python </option>
            <option value="c"> C </option>
            <option value="cpp"> C++ </option>
            <option value="php"> PHP </option>
            <option value="js"> Javascript </option>
        </select>
    </div>
    <div class="editor" id="editor"></div>

    <div class="button-container">
        <button class="btn" onclick="executeCode()"> Run </button>
    </div>

    <div class="output"></div>

    <script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="C:\Users\souhir\Desktop\eclipse jee\editor\WebContent\WEB-INF\lib/ace.js"></script>
    <script src="C:\Users\souhir\Desktop\eclipse jee\editor\WebContent\WEB-INF\lib/theme-monokai.js"></script>
   
<script>
    let editor;

window.onload = function() {
    editor = ace.edit("editor");
    editor.setTheme("ace/theme/monokai");
    editor.session.setMode("ace/mode/python");
}

function changeLanguage() {

    let language = $("#languages").val();

    if(language == 'c' || language == 'cpp')editor.session.setMode("ace/mode/c_cpp");
    else if(language == 'php')editor.session.setMode("ace/mode/php");
    else if(language == 'python')editor.session.setMode("ace/mode/python");
    else if(language == 'js')editor.session.setMode("ace/mode/javascript");
}


function executeCode() {
	console.log("gg");
  $.ajax({
           url: 'Exec',
           method: 'GET',
          
           data: {
            language: $("#languages").val(),
            code: editor.getSession().getValue()
           }, success: function(response) {
            $(".output").text(response)
        }
        });
    
}
</script>
</body>
</html>