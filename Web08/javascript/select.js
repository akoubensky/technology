window.onload = init;

function init() {
  var select = document.getElementsByTagName('select')[0];
  select.onchange = onChange;
  select.value = 0;
  doChange(select);
}

function onChange(event) {
  doChange(event.target);
}

function doChange(select) {
  var val = select.value;
  var selectedOption = select.options[select.selectedIndex];
  var value = selectedOption.value;
  var text = selectedOption.text;
  document.getElementById('select-val').lastChild.nodeValue = '������� �������� ������: ' + val;
  document.getElementById('value').lastChild.nodeValue = '������� value ���������� ��������: ' + value;
  document.getElementById('text').lastChild.nodeValue = '������� text ���������� ��������: ' + text;
}
