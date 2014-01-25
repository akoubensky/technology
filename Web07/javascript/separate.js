window.onload = initBody;

function initBody() {
  var pNode = document.getElementById('para');
  pNode.onclick = insertNewParagraph;
}

function insertNewParagraph() {
  var pNode = document.getElementById('para');
  var newPara = document.createElement('p');
  newPara.style.color = 'red';
  newPara.style.marginLeft = '50px';
  var newText = document.createTextNode(
      'А это динамически добавленный параграф!');
  newPara.appendChild(newText);
  pNode.parentNode.insertBefore(newPara, pNode.nextSibling);
}
