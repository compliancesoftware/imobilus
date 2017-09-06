//The needed screen adjustments
function doScreenAdjustments(){
    console.log("************* Adjusting to the screen... ************");
    
    console.log("**** Adjust main title to screen available width ****");
    var width = $(window).width();
    if(width > 592){
        width = $(".cabecalho").width() - $(".logomarca").width() - 30;
        $(".apresentacao").width(width);
    }
    
    console.log("****************** Build gallery ********************");
    $('.galeria').slick({
        mobileFirst: true,
        autoplay: true,
        autoplaySpeed: 3000,
        speed: 500,
        appendDots: '.galeria',
        arrows: true,
        slideToShow: 1,
        fade: true,
        cssEase: 'linear',
        dots: true,
        infinite: true
    });

    console.log("*************** Build mini gallery ******************");
    $('.lancamentos').slick({
        mobileFirst: true,
        infinite: true,
        slidesToShow:3,
        slidesToScroll:3,
        appendDots: '.lancamentos',
        variableWidth: true,
        centerMode: true,
        swipe: true,
        swipeToSlide: true,
        touchMove: true,
        dots: true
    });

    console.log("*********************** DONE! ***********************");
}

//Here is possible to assign actions to buttons
function bindEvents(){
    console.log("****************** Binding elements events... ******************");

    console.log("************* Binding navbar toggle button click... ************");
    $(".menu-toggle-next-btn").click(function(){
        // $(".menu-toggle-next-btn").hide();
        $(".navbar").toggle("slide");
    });

    console.log("********** Binding navbar toggle next button click... **********");
    $(".menu-toggle-btn").click(function(){
        $(".navbar").toggle("slide");
        // $(".menu-toggle-next-btn").show();
    });

    console.log("*************************** DONE! ******************************");
}

//On Document Ready, do the stub
$(document).ready(function(){
    doScreenAdjustments();
    bindEvents();
});

//On Window Resize event, do screen adjustments anymore
$(window).resize(function(){
    doScreenAdjustments();
});