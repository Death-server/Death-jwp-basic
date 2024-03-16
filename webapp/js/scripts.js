String.prototype.format = function() {
  var args = arguments;
  return this.replace(/{(\d+)}/g, function(match, number) {
    return typeof args[number] != 'undefined'
        ? args[number]
        : match
        ;
  });
};

$(".answerWrite input[type=submit]").click(addAnswer);

function addAnswer(e) {
    e.preventDefault();
    var queryString = $("form[name=answer]").serialize();

    $.ajax({
        type : 'post',
        url : '/api/qna/addAnswer',
        data : queryString,
        dataType : 'json',
        success : onSuccess,
        error : onError,
    });
}

function onSuccess(json, status) {
    var answerTemplate = $("#answerTemplate").html();
    var template = answerTemplate.format(json.writer, new Date(json.createdDate)
    , json.contents, json.answerId);
    $(".qna-comment-slipp-articles").prepend(template);
    $(".link-delete-article").click(deleteAnswer);

    history.go(0);
    console.log(json);
}

function onError(status, error) {
    console.error("Error : ", status, error);
}

$(".link-delete-article").click(deleteAnswer);


function deleteAnswer(e) {
    e.preventDefault();
    var queryString = $(this).closest("form").children();
    console.log(queryString);
    $.ajax({
            type : 'post',
            url : '/api/qna/deleteAnswer',
            data : queryString,
            dataType : 'json',
            success : deleteSuccess,
            error : onError,
        });

}

function deleteSuccess(json, status) {
            console.log(json, status);
            if(json.status == true) {
                $(this).closest("article").remove();
                history.go(0);
       }
}

