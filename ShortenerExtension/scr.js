chrome.tabs.query({active: true, currentWindow: true}, function(arrayOfTabs) {

     var activeTab = arrayOfTabs[0];
     var activeTabId = arrayOfTabs[0].id;

     $.ajax({
     	type: "POST",
     	url: "http://shorten1.jelastic.neohost.net/add",
          data: {url : activeTab.url},
     	success: function(response) {
     		$("#shortened").val(response);
               $("#shortened").select();
               document.execCommand('copy');
     	},
     	error: function(e) {
     		console.log("Cannot connect!!!")
     	}
     });
     
  });


