jQuery('document').ready(function(){

    var position = 0;
    var countAccent = 0;
    var object;

    $('#button').on('click',function(){

        $(".divEnter").toggleClass("leave");
        $(".divChoice").toggleClass("come");


        $.ajax({
            url: 'analyze',
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

        takeOff('position',$(this).attr('word'));
        $(this).attr('accent',1);
        setDataObject($(this).attr('word'),$(this).attr('data'),1);

        if (accentEstablished() == countAccent ) {

            var objectJson = JSON.stringify(object);
            $.ajax({
                type: 'POST',
                url: 'data',
                contentType: "application/json",
                data: objectJson,
                dataType: "json",
                success: function (data) {
                    toBuild(data, '.result');

                    $(".divChoice").toggleClass("come");
                    $(".divResult").toggleClass("come");
                },
                error: function (data) {
                    alert('error:' + data);
                }
            });
        };
    });

    $('.back').on('click',function(){
        $(".divEnter").toggleClass("leave");
        $(".divResult").toggleClass("come");
    });

    function takeOff(attr,word){

        var elems = $('[word='+word+']');
        var elemsTotal = elems.length;
        for(var i=0; i<elemsTotal; ++i){
            $(elems[i]).attr('accent', 0);
            setDataObject(word,i,0);
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

        object = data;

        for(var i=0; i<data.length; ++i) {

            var word = data[i];

            for (var j = 0; j < word.length; ++j) {

                if ( word[j].red == 1) {
                    $('<span>').attr('class', 'onlyblack')
                        .text( word[j].simbol)
                        .attr('data',  word[j].position)
                        .attr('word',i)
                        .appendTo(div);
                }
                else {
                    $('<span>').attr('class', 'onlyBlackNonPosition')
                        .text( word[j].simbol)
                        .attr('data',  word[j].position)
                        .appendTo(div);
                }
            }

            if (i!=data.length-1){
                $('<span>').attr('class', 'onlyBlackNonPosition')
                    .text(' ')
                    .attr('data', 0)
                    .appendTo(div);
            }

            countAccent++;
        }
    }

    function accentEstablished(){
        return $('[accent=1]').length;
    }

    function setDataObject(i,j,val){
        object[Number.parseInt(i)][Number.parseInt(j)].accent = val;
    }

});