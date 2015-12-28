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


    $(document).on("click", ".login-user",
        function () {
            $.ajax({
                type: "POST",
                url: "login",
                data: $("#login-form").serialize(),
                success: function (record) {
                    console.log(record);
                    clearValidation();
                    //reset form with last input
                    $('#login-form').trigger("reset");
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
});
