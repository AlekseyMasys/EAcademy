function readSingleFile(e) {
  var file = e.target.files[0];
  if (!file) {
    return;
  }
  var reader = new FileReader();
  reader.onload = function(e) {
    var contents = e.target.result;
    displayContents(contents);
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
               data: "{\"name\":\""+ contents+"\"}",
               dataType: "Json",
           });
}

document.getElementById('file-input')
  document.addEventListener('change', readSingleFile, false);