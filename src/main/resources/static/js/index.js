var index = {
    init : function () {
        var _this = this;

        $('#btn-save').on('click', function () {
            _this.save();
        });
        $('#btn-update').on('click', function () {
            _this.update();
        });
        $('#btn-delete').on('click', function () {
            _this.delete();
        });
    },
    save : function () {
        var data = {
            title: $('#title').val(),
            memberName: $('#memberName').val(),
            content: $('#content').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/notice',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert("공지가 등록 되었습니다.");
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
     update : function () {
         var data = {
             title: $('#title').val(),
             content: $('#content').val()
         };
         var id = $('#id').val();

         $.ajax({
             type: 'PUT',
             url: '/api/v1/notice/' + id,
             dataType: 'json',
             contentType: 'application/json; charset=utf-8',
             data: JSON.stringify(data)
         }).done(function () {
             alert('공지가 수정되었습니다.');
             window.location.href = '/';
         }).fail(function (error) {
             alert(JSON.stringify(error));
         });
     },
     delete : function () {
         var id = $('#id').val();

         $.ajax({
             type: 'DELETE',
             url: '/api/v1/notice/' + id,
             dataType: 'json',
             content: 'application/json; charset=utf-8'
         }).done(function () {
             alert('공지가 삭제 되었습니다.')
             window.location.href = '/';
         }).fail(function () {
             alert(JSON.stringify(error));
         });
     }
};

index.init();