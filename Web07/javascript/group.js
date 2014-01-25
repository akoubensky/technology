window.addEventListener('load', initBody, false);
var index = 0;

function initBody() {
  document.getElementsByTagName('h1')[0]
      .addEventListener('click', setStyles, false);
}

function setStyles() {
  var emElements = document.getElementsByTagName('em');
  var color = ['white', 'yellow'][index = 1-index];
  for (var i = 0; i < emElements.length; ++i) {
    emElements[i].style.backgroundColor = color;
  }
}

