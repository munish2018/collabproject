console.log("chat controller");

myApp.controller('ChatController', function($scope,$rootScope,chatService)
{
	console.log("inside the chat controller");
	$scope.messages=[];
	$scope.message="";
	$scope.max=140;
	
	$scope.addMessage=function()
	{
		console.log("inside the add message ");
		chatService.send($rootScope.user1.username+":" +$scope.message);
		$scope.message="";
	};

	chatService.receive().then(null,null,function(message)
	{
		$scope.messages.push(message);
	});	

});
