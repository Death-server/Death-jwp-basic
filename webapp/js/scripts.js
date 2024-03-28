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
        success : function(json, status) {
            onSuccess(json, status, queryString);
        },
        error : onError,
    });
}

function onSuccess(json, status, queryString) {
    var answerTemplate = $("#answerTemplate").html();
    var template = answerTemplate.format(json.writer, new Date(json.createdDate)
        , json.contents, json.answerId);
    $(".qna-comment-slipp-articles").prepend(template);
    $(".link-delete-article").click(deleteAnswer);
    updateDivContent(queryString);
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
        success : function(json, status) {
            deleteSuccess(json, status, queryString);
        },
        error : onError,
    });

}

function deleteSuccess(json, status, queryString) {
    console.log(json, status);
    if(json.status === true) {
        $(this).closest("article").remove();
        updateDivContent(queryString);
    }
}


// div 영역을 업데이트하는 함수
function updateDivContent(queryString) {
    $.ajax({
        type: 'get', // GET 요청을 사용하여 업데이트할 데이터 가져오기
        url: '/api/qna/jsonList', // 해당 데이터를 반환하는 엔드포인트 URL
        data: queryString,
        dataType: 'html', // 반환되는 데이터 형식은 HTML
        success: function (answers) {
            console.log("log : " + answers);
            //todo: 데이터는 가져왔는데 어떻게 해야할지 모르겠어요 ㅠ
            //$(".qna-comment-slipp-articles").each(answers);
        },
        error: onError,
    });
}