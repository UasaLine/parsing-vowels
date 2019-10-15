jQuery('document').ready(function(){

    var position = 0;
    var countAccent = 0;
    var listWord;

    $('#button').on('click',function(){

        $(".divEnter").toggleClass("leave");
        $(".divChoice").toggleClass("come");


        $.ajax({
            url: 'analyze',
            data: {val: $('#line').val()},
            dataType: 'json',
            success: function (data) {
                toBuildOnlyBlack(data,'.selectEmphasis');
                drowPointer();
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
        drowPointer();

        if (accentEstablished() == countAccent ) {

            var objectJson = JSON.stringify(listWord);
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

        for (var i=0; i<data.length; ++i){

            var word = data[i];
            for (var j = 0; j < word .length; ++j) {
                if ((word[j].red == 1) && (word[j].strong == 0)) {
                    $('<span>').attr('class', 'red').text(word [j].simbol).appendTo(div);
                }
                else if ((word[j].red == 1) && (word[j].strong == 1)) {
                    $('<span>').attr('class', 'red').text(word [j].simbol).attr('id', 'strong').appendTo(div);
                }
                else {
                    $('<span>').attr('class', 'black').text(word[j].simbol).appendTo(div);
                }

            }
            if (i!=data.length-1){
                $('<span>').attr('class', 'black').text(' ').appendTo(div);
            }
        }
    }

    function toBuildOnlyBlack(data,div) {

        //delete
        $('.red').remove();
        $('.black').remove();
        $('.onlyBlackNonPosition').remove();
        $('.onlyblack').remove();

        listWord = data;
        countAccent = 0;

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
        listWord[Number.parseInt(i)][Number.parseInt(j)].accent = val;
    }

    function drowPointer(){
        $('#pointer').text(''+$('#pointer').attr('data')+' осталось: '+(countAccent-accentEstablished()));
    }

});