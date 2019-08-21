function readSingleFile(e) {
  var file = e.target.files[0];
  if (!file) {
    return;
  }
  var reader = new FileReader();
  reader.onload = function(e) {
    var contents = e.target.result;
    displayContents(contents);
    parseFile(contents);
  };
  reader.readAsText(file);
}

function displayContents(contents) {
  var element = document.getElementById('file-content');
  element.textContent = contents;
  $.ajax({
               type: "POST",
               contentType: "application/json",
               url: "/save_test",
               data: contents,
               dataType: "Json",
           });
}

function parseFile(contents){
    objStr = contents.split('\n');
    var nametest = objStr[0].split(';')
    var data = "{\"name\":"+"\""+nametest[0]+"\","+"\n \"graduation\":[";
    for (var i = 1; i<10; i++) {
            var text = objStr[i].split(';');
            data += text[1];
          if(i==9)break;
          data +=",";
        }
    data+="],\n";

    var typetest = objStr[10].split(';');
        data += "\"type\":\"";
        data += typetest [1] =="" ? typetest[2] : typetest [1]+"\",\n"

    var repassing = objStr[11].split(';');
        data += "\"re-passing\":\"";
        data += repassing [1] == "" ? "no" : "yes"
        data += "\",\n"

    var someanswers = objStr[12].split(';');
        data += "\"some answers\":\"";
        data += someanswers [1] == "" ? "no" : someanswers[1]
        data += "\",\n"

    var timelimit = objStr[13].split(';');
        data += "\"time limit\":\"";
        data += timelimit [1] == "" ? "no" : timelimit[1]
        data += "\",\n"

    data += "\"test\":[";
    for(var i=21;i<objStr.length-1;i++){
        var answer = objStr[i].split(';');
            data += "\n{\"question\":\"";
            data += answer [0];
            data += "\",\n";

             data += "\"trueAnswer\":\"";
             data += answer [1];
             data += "\",\n" ;

             data += "\"point\":\"";
             data += answer [2];
             data += "\",\n";
            data += "\"answers\":[";
                for(var option=3; option<answer.length; option++){
                       data +="\"";
                       data += answer[option];
                       if(option==answer.length-1 && i==objStr.length-2){
                       data += option == answer.length-1 ? "\"]}" : "\",";
                       }else{
                         data += option == answer.length-1 ? "\"]}," : "\",";
                       }
                }
                if(i==objStr.length-1)data.substring(0,data.length-1);

        }
           data += "] }"
    displayContents(data) ;
    }

    document.getElementById('file-input')
    document.addEventListener('change', readSingleFile, false);
