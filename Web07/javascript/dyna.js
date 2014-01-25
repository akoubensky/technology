// in IE use attachListener instead of addEventListener,
// and detachEvent instead of removeEventListener

window.addEventListener('load', initBody, false);

function initBody() {
  var addRedPara = document.getElementById('addRed');
  var addBluePara = document.getElementById('addBlue');
  var addGreenPara = document.getElementById('addGreen');
  var removeRedPara = document.getElementById('removeRed');
  var removeBluePara = document.getElementById('removeBlue');
  var removeGreenPara = document.getElementById('removeGreen');
  var removeAllPara = document.getElementById('remDyna');
  addRedPara.addEventListener('click', addRedListener, false);
  addBluePara.addEventListener('click', addBlueListener, false);
  addGreenPara.addEventListener('click', addGreenListener, false);
  removeRedPara.addEventListener('click', removeRedListener, false);
  removeBluePara.addEventListener('click', removeBlueListener, false);
  removeGreenPara.addEventListener('click', removeGreenListener, false);
  removeAllPara.addEventListener('click', removeAll, false);
}

function addListener(listener) {
  document.getElementById('para').addEventListener('click', listener, false);
}

function removeListener(listener) {
  document.getElementById('para').removeEventListener('click', listener, false);
}

function addRedListener() { addListener(insertRedParagraph); }
function addBlueListener() { addListener(insertBlueParagraph); }
function addGreenListener() { addListener(insertGreenParagraph); }

function removeRedListener() { removeListener(insertRedParagraph); }
function removeBlueListener() { removeListener(insertBlueParagraph); }
function removeGreenListener() { removeListener(insertGreenParagraph); }

function insertRedParagraph() { insertParagraph('red'); }
function insertBlueParagraph() { insertParagraph('blue'); }
function insertGreenParagraph() { insertParagraph('green'); }

function insertParagraph(color) {
  var pNode = document.getElementById('para');
  var newPara = document.createElement('p');
  newPara.style.color = color;
  newPara.style.marginLeft = '50px';
  var newText = document.createTextNode(
      'А это динамически добавленный параграф!');
  newPara.appendChild(newText);
  pNode.parentNode.insertBefore(newPara, pNode.nextSibling);
}

function removeAll() {
  var pNode = document.getElementById('para');
  var pEndNode = document.getElementById('remDyna');
  for (var p = pNode.nextSibling; p != pEndNode;) {
    var newP = p.nextSibling;
    p.parentNode.removeChild(p);
    p = newP;
  }
}
