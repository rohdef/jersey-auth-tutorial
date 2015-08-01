/**
 * License MIT
 * Created by Rohde Fischer
 */
$(function() {
    // jQuery references
    var $currentToken = $("#currentToken");
    var $username = $("#username");
    var $password = $("#password");
    var $settingsExample = $("#settingsExample");
    var $error = $("#error");
    var $errorStatus = $("#errorStatus");
    var $errorText = $("#errorText");
    var $responseArea = $("#responseArea");
    var $getAnswer = $("#getAnswer");
    var $getValuables = $("#getValuables");
    var $getMedicine = $("#getMedicine");
    var $getJoke = $("#getJoke");
    var $requestArea = $("#requestCode");

// Event handlers for the ajax calls
    var loginSuccess = function(session) {
        if (!session) {
            alert("Login failed");
            return;
        }

        window.session = session;
        $currentToken.text(JSON.stringify(session, null, "    "));
    };
    var error = function(jqXHR, textStatus, errorThrown) {
        $errorStatus.text(textStatus);
        $errorText.text(errorThrown);
        $error.fadeIn("slow");
    };

// Variables for authentication, loginSettings being the request object for jQuery's $.ajax
    window.session = { name: null, token: null };
    var loginRequest = { username: null, password: null };
    var loginSettings = {
        method: "POST",
        contentType: "application/json; charset=utf-8",
        url: "./rest/auth/login",
        dataType: "json",
        data: JSON.stringify(loginRequest),
        success: loginSuccess,
        error: error
    };
    var serviceTestSuccess = function(data) {
        $responseArea.text(JSON.stringify(data, null, "    "));
    };
    var serviceTestSettings = function(service) {
        var settings = {
            method: "GET",
            headers: {
                Authorization: "Basic " + btoa(window.session.name + ":" + window.session.token)
            },
            contentType: "application/json; charset=utf-8",
            url: "./rest/" + service,
            dataType: "json",
            success: serviceTestSuccess,
            error: error
        };
        $requestArea.text(JSON.stringify(settings, null, "    "));
        return settings;
    };

// Helpers handlers
    var changeHandler = function() {
        loginRequest.username = $username.val();
        loginRequest.password = $password.val();

        loginSettings.data = JSON.stringify(loginRequest);
        $settingsExample.text(JSON.stringify(loginSettings, null, "    "));
    };
    $settingsExample.text(JSON.stringify(loginSettings, null, "    "));

    $username.keyup(changeHandler);
    $password.keyup(changeHandler);

    $("#fiktivusLnk").on("click", function() {
        $username.val("fiktivus");
        $password.val("maximus");
        changeHandler();
    });
    $("#realisLnk").on("click", function() {
        $username.val("realis");
        $password.val("minimalis");
        changeHandler();
    });

    $("#loginBtn").on("click", function() {
        $.ajax(loginSettings);
    });

    $("#errorClose").on("click", function() {
        $error.fadeOut("slow");
    });

    $currentToken.text(JSON.stringify(session, null, "    "));

    $getAnswer.on("click", function() {
        $.ajax(serviceTestSettings("service/answer"));

    });

    $getValuables.on("click", function() {
        $.ajax(serviceTestSettings("service/valuables"));
    });

    $getMedicine.on("click", function() {
        $.ajax(serviceTestSettings("service/medicine"));
    });

    $getJoke.on("click", function() {
        $.ajax(serviceTestSettings("service/funny"));
    });
});