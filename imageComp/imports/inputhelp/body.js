

<script type="text/javascript" language="javascript">
 (function() {
    var id = document.getElementById('search-form');
    if (id && id.txtInput) {
      var name = id.txtInput;
      var unclicked = function() {
          if (name.value == '') {
              name.style.background = '#FFFFFF url(images/googbg.png) left no-repeat';
          }
       };
       var clicked = function() {
          name.style.background = '#ffffff';
       };
    name.onfocus = clicked;
    name.onblur = unclicked;
    unclicked();
    }
  })();
</script>
