chrome.tabs.query({active: true, currentWindow: true}, function(arrayOfTabs) {

     var activeTab = arrayOfTabs[0];

     $.ajax({
     	type: "POST",
     	url: "<name>",
             data: {url : activeTab.url},
     	success: function(response) {
     		$("#shortened").val(response);
                     $("#shortened").select();
     	},
     	error: function(e) {
     		console.log("Cannot connect!!!");
     	}
     });
     $("#copier").click(function(){
          $("#shortened").select();
          document.execCommand('copy');     
     });
     
  });


