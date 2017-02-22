$(function(){
   var arr = [];
   $('#button').on('click', function(e){
      e.preventDefault();
      arr.push($('#searchBox').val());
      $('#searchBox').val('');
      $('#outputsearch').val(arr);
   });
});