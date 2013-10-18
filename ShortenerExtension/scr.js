
chrome.tabs.query({active: true, currentWindow: true}, function(arrayOfTabs) {

     // since only one tab should be active and in the current window at once
     // the return variable should only have one entry
     var activeTab = arrayOfTabs[0];
     var activeTabId = arrayOfTabs[0].id; // or do whatever you need
     //document.getElementById('currentLink').innerHTML = activeTab.url;
     // $("#shortened").val(activeTab.url);
     // $("#shortened").select();
     // $("#longUrl").html(activeTab.url);
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


