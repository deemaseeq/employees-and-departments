/*  document.getElementsByClassName('dept-selector').onclick = function () {
 deptName = this.firstChild.nextSibling.toString();
 alert(deptName);
 alert('lol');
 };*/

var empsTable = document.getElementById('emps-table');

function deptSelection(deptsCell) {
    
    showAllDepts();

    deptName = deptsCell.innerHTML;

    for (i = 1; i < empsTable.rows.length; i++) {
        if (empsTable.rows[i].cells[5].innerHTML !== deptName) {
            empsTable.rows[i].style.display = 'none';
        }
    }
};

function showAllDepts() {
    for (i = 1; i < empsTable.rows.length; i++) {
        empsTable.rows[i].style.display = '';
    }
}