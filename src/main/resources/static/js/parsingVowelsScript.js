jQuery('document').ready(function(){

    var position = 0;

	$('#button').on('click',function(){

        $.ajax({
            url: 'dataforposition',
            data: {val: $('#line').val()},
            dataType: 'json',
            success: function (data) {
                toBuildOnlyBlack(data,'.selectEmphasis');
            },
            error: function (data) {
                alert('error:' + data);
            }
        });
	});

    $('.selectEmphasis').on('click','.onlyblack',function(){

        takeOff('position');
        $(this).attr('id','position');
        position = $(this).attr('data');

        $.ajax({
            url: 'data',
            data: {val: $('#line').val(),position: position},
            dataType: 'json',
            success: function (data) {
                toBuild(data,'.result');
            },
            error: function (data) {
                alert('error:' + data);
            }
        });
    });

    function takeOff(attr){

        var elems = $('#'+attr);
        var elemsTotal = elems.length;
        for(var i=0; i<elemsTotal; ++i){
            $(elems[i]).attr('id', i)
        }
    }

	function toBuild(data,div) {

        //delete
        $('.red').remove();
        $('.black').remove();

        for(var j=0; j<data.length; ++j){
        	if((data[j].red == 1)&&(data[j].strong==0)){
                $('<span>').attr('class','red').text(data[j].simbol).appendTo(div);
			}
			else if((data[j].red == 1)&&(data[j].strong==1)){
                $('<span>').attr('class','red').text(data[j].simbol).attr('id','strong').appendTo(div);
            }
			else {
                $('<span>').attr('class','black').text(data[j].simbol).appendTo(div);
			}

        }
    }

    function toBuildOnlyBlack(data,div) {

        //delete
        $('.red').remove();
        $('.black').remove();
        $('.onlyBlackNonPosition').remove();
        $('.onlyblack').remove();

        for(var j=0; j<data.length; ++j){

            if(data[j].red == 1){
                $('<span>').attr('class','onlyblack')
                    .text(data[j].simbol)
                    .attr('data',data[j].position)
                    .appendTo(div);
            }
            else {
                $('<span>').attr('class','onlyBlackNonPosition')
                    .text(data[j].simbol)
                    .attr('data',data[j].position)
                    .appendTo(div);
            }


        }
    }

});