$(function() {
    $.ajax({
        url: 'http://bar.example.dev:3000/cookies',
        method: 'POST',
        xhrFields: {
            withCredentials: true
        },
        success: function(data) {
            $('#cookies').text(data);
        }
    });
});
