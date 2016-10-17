$(document).on("submit", "#addDepartment", function(event) {
    var $form = $(this);
    
    $.post($form.attr("action"), $form.serialize(), function(response) {
        response = response.trim();
        if(response === 'Succesful addition'){
            location.reload();
        } else {
            alert(response);
        }
        
    });

    event.preventDefault(); // Important! Prevents submitting the form.
});

function deptDelete(deptRow){
    var deptName = deptRow.cells[1].innerHTML;
    var deptId = deptRow.cells[0].innerHTML;
    
    var sureToDelete = confirm('Are you sure you want to delete department ' 
            + deptName
            + ' from database? It will delete all employees associated with this department. This action can not be undone.');
    
    if (sureToDelete) {
        $.post("DepartmentsDeletingServlet", { id: deptId }, function (response) {
            response = response.trim();
            if (response === 'Succesful delete') {
                location.reload();
            } else {
                alert(response);
            }
        });
    }
}

function deptEdit(deptRow) {
    var deptName = deptRow.cells[1].innerHTML;
    var deptId = deptRow.cells[0].innerHTML;
    
    document.getElementById('edit-department-message').textContent = 
            'You are going to edit data of department with id ' + deptId + ' and name ' + deptName;
    
    document.getElementById('edit-department-id').value = deptId;
    document.getElementById('edit-department-name').value = deptName;
    
    document.getElementById('edit-department-modal').style.display = 'block';
}

$(document).on("submit", "#editDepartment", function(event) {
    var $form = $(this);
    
    $.post($form.attr("action"), $form.serialize(), function(response) {
        response = response.trim();
        if(response === 'Succesful editing'){
            location.reload();
        } else {
            alert(response);
        }
        
    });

    event.preventDefault(); // Important! Prevents submitting the form.
});