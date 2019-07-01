jQuery('document').ready(function(){
	
	$('#button').on('click',function(){

        $.ajax({
            url: 'data',
            data: {val: $('#line').val()},
            dataType: 'json',
            success: function (data) {
                toBuild(data);
            },
            error: function (data) {
                alert('error:' + data);
            }
        });
		
	});

	function toBuild(data) {

        //delete
        $('.red').remove();
        $('.black').remove();

        for(var j=0; j<data.length; ++j){
        	if(data[j].red == 1){
                $('<span>').attr('class','red').text(data[j].simbol).appendTo('.result');
			}
			else {
                $('<span>').attr('class','black').text(data[j].simbol).appendTo('.result');
			}

        }
    }
});