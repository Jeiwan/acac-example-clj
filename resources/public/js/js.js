$(function() {
    $.post(
        'http://bar.example.dev:3000/cookies',
        {},
        function(data) {
            $('#cookies').text(data);
        }
    );
});
