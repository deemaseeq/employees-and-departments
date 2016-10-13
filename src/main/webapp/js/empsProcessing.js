$(document).on("submit", "#addEmployee", function (event) {
    var $form = $(this);

    $.post($form.attr("action"), $form.serialize(), function (response) {
        response = response.trim();
        if (response === 'Succesful addition') {
            location.reload();
        } else {
            alert(response);
        }

    });

    event.preventDefault(); // Important! Prevents submitting the form.
});

function empDelete(empRow) {
    var empName = empRow.cells[1].innerHTML;
    var empId = empRow.cells[0].innerHTML;

    var sureToDelete = confirm('Are you sure you want to delete employee '
            + empName
            + ' from database? This action can not be undone.');

    if (sureToDelete) {
        $.post("EmployeesDeletingServlet", { id: empId }, function (response) {
            response = response.trim();
            if (response === 'Succesful delete') {
                location.reload();
            } else {
                alert(response);
            }
        });
    }
}

function empEdit(empRow) {
    var empName = empRow.cells[1].innerHTML;
    var empId = empRow.cells[0].innerHTML;
    
    document.getElementById('edit-employee-message').textContent =
            'You are going to edit data of employee with id ' + empId + ' and name ' + empName;
    document.getElementById('edit-employee-id').value = empId;
    
    document.getElementById('edit-employee-modal').style.display = 'block';
}

$(document).on("submit", "#editEmployee", function (event) {
    var $form = $(this);

    $.post($form.attr("action"), $form.serialize(), function (response) {
        response = response.trim();
        if (response === 'Succesful editing') {
            location.reload();
        } else {
            alert(response);
        }

    });

    event.preventDefault(); // Important! Prevents submitting the form.
});