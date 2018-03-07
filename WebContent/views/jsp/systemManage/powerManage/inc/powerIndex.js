$(function () {
    var  strcondition = '1,2,3,4';
    var ids = strcondition.split(',');
    if (ids != null) {
        $('#example').val(ids);
        $('#example').multiselect("refresh");
    }

});