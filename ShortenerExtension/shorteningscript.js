chrome.tabs.query({active: true, currentWindow: true}, function(arrayOfTabs) {

     var activeTab = arrayOfTabs[0];

     $.ajax({
     	type: "POST",
     	url: "http://shorten1.jelastic.neohost.net/add",
             data: {url : activeTab.url},
     	success: function(response) {
     		$("#shortened").val(response);
                     $("#shortened").select();
     	},
     	error: function(e) {
     		console.log("Cannot connect!!!")
     	}
     });
     $("#copier").click(function(){
          $("#shortened").select();
          document.execCommand('copy');     
     });
     
  });


