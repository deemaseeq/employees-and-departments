$(document).on("submit", "#addEmployee", function (event) {
    var $form = $(this);

    var date = document.getElementById('add-employee-date').value;

    if (isValidDate(date)) {
        $.post($form.attr("action"), $form.serialize(), function (response) {
            response = response.trim();
            if (response === 'Succesful addition') {
                location.reload();
            } else {
                alert(response);
            }

        });
    } else {
        document.getElementById('add-employee-date-error').innerHTML = "Please enter date in format YYYY-MM-DD";
    }

    event.preventDefault(); // Important! Prevents submitting the form.
});

function empAdd() {
    document.getElementById('add-employee-date-error').innerHTML = "";
    document.getElementById('add-employee-modal').style.display = 'block';
}

function empDelete(empRow) {
    var empName = empRow.cells[1].innerHTML;
    var empId = empRow.cells[0].innerHTML;

    var sureToDelete = confirm('Are you sure you want to delete employee '
            + empName
            + ' from database? This action can not be undone.');

    if (sureToDelete) {
        $.post("EmployeesDeletingServlet", {id: empId}, function (response) {
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
    var empId = empRow.cells[0].innerHTML;
    var empName = empRow.cells[1].innerHTML;
    var empEmail = empRow.cells[2].innerHTML;
    var empHireDate = empRow.cells[3].innerHTML;
    var empSalary = empRow.cells[4].innerHTML;

    document.getElementById('edit-employee-message').textContent =
            'You are going to edit data of employee with id ' + empId + ' and name ' + empName;

    document.getElementById('edit-employee-id').value = empId;
    document.getElementById('edit-employee-name').value = empName;
    document.getElementById('edit-employee-email').value = empEmail;
    document.getElementById('edit-employee-date').value = empHireDate;
    document.getElementById('edit-employee-salary').value = empSalary;

    document.getElementById('edit-employee-date-error').innerHTML = "";

    document.getElementById('edit-employee-modal').style.display = 'block';
}

$(document).on("submit", "#editEmployee", function (event) {
    var $form = $(this);

    var date = document.getElementById('edit-employee-date').value;

    if (isValidDate(date)) {
        
        $.post($form.attr("action"), $form.serialize(), function (response) {
            response = response.trim();
            if (response === 'Succesful editing') {
                location.reload();
            } else {
                alert(response);
            }

        });
    } else {
        document.getElementById('edit-employee-date-error').innerHTML = "Please enter date in format YYYY-MM-DD";
    }

    event.preventDefault(); // Important! Prevents submitting the form.
});

function isValidDate(dateString) {
    var regEx = /^\d{4}-\d{2}-\d{2}$/;

    if (!dateString.match(regEx))
        return false;  // Invalid format

    var d;

    if (!((d = new Date(dateString)) | 0))
        return false; // Invalid date (or this could be epoch)

    return d.toISOString().slice(0, 10) === dateString;
}
