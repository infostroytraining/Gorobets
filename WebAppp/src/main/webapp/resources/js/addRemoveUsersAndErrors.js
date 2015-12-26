$(document).ready(function () {

    function addEventListener() {
        $('.remove-user').click(function () {
            var user_id = $(this).attr('id');
            $.post("remove", {
                id: user_id
            }).success(function (data) {
                $('table#content tr#' + user_id).hide('slow');
                console.log(user_id);
            });
        });
    }

    addEventListener();

    function clearValidation() {
        $('span.error').remove();
        $('.form-group').removeClass('has-error');
    }

    $(document).on("click", ".add-user",
        function () {
            $.ajax({
                type: "POST",
                url: "add",
                data: $("#user-form").serialize(),
                success: function (record) {
                    console.log(record);
                    clearValidation();
                    // append received record to the end of the table
                    var email_td = "<td>" + record.email + "</td>";
                    var password_td = "<td>" + record.password + "</td>";
                    var name_td = "<td>" + record.name + "</td>";
                    var surname_td = "<td>" + record.surname + "</td>";
                    var remove_td = '<td> <a id="' + record.id + '"' + 'class="remove-user">remove</a> </td>';
                    $('#content tr:last').after('<tr id="' + record.id + '">' + email_td + password_td + name_td + surname_td + remove_td + '</tr>');
                    //<td><a id="${user.id}" class="remove-user">remove</a></td>
                    //reset form with last input
                    $('#user-form').trigger("reset");
                    addEventListener();
                },
                error: function (data) {
                    console.log(data);
                    clearValidation();
                    $.each(data.responseJSON, function (key, value) {
                        console.log(key);
                        console.log(value);
                        //append error message to the input field and add has-error class
                        $('#form-' + key).addClass("has-error");
                        $('#' + key + ".col-sm-8").append("<span class='error'>" + value + "</span>");
                    });
                }
            });
        });

    /*Uncomment to use modal window*/

    /*	  $('.remove-user').click(function() {
     var id = $(this).attr('id');
     console.log(id);
     $('button.remove').attr("id", id);
     });

     $('button.remove').click(function() {
     var user_id = $(this).attr('id');
     $.post("remove", {
     id : user_id
     }).success(function(data) {
     $('table#content tr#' + user_id).hide('slow'); console.log(user_id); })
     });*/
});
