console.log("Into the Forum Controller");
myApp.controller('forumcntrl', function($scope, $http, $location,$rootScope)
	{
	console.log("Inside  the Forum Controller function");
	$scope.forum = {
		forumid : 0,
		forumname:'',
		forumcontent:'',
		createdate : '',
		status : 'A',
		username : 'munish'
	};
	
	$scope.forumdata;
	
	
	$scope.insertforum=function()
	{
		console.log("Enter into insertforum Method");
		$http.post('http://localhost:8081/middleend/addforum',$scope.forum)
		.then(fetchAllForum(),function(response)
     	  {
			console.log('Status Text:'+response.statusText);
	      });			
	};
	
	$scope.updateforum=function(forumId)
	{
		console.log("Enter into updateForum Method");
		$http.put('http://localhost:8081/middleend/updateforum/'+forumId,$rootScope.forum1)
		.then(fetchAllForum(),function(response)
     	  {
			console.log('Status Text:'+response.statusText);
			$location.path("/forum1");
	      });			
	};
		
	$scope.editforum=function(forumId)
	{
		console.log('into edit forum');
		$http.get('http://localhost:8081/middleend/getforum/'+forumId)
		.then(function(response)
		{
			console.log(response.data);
			$scope.forum=response.data;
			$rootScope.forum1=response.data;
			console.log('Status Text:'+response.statusText);
			$location.path("/updateforum");
		});
	};
	$scope.displaycomment=function(forumId)
	{
		console.log('into display forum');
		console.log('into display forum'+forumId);
		$http.get('http://localhost:8081/middleend/getforum/'+forumId)
		.then(function(response)
		{
			console.log(response.data);
			$scope.forum=response.data;
			$rootScope.forum1=response.data;
			console.log('Status Text:'+response.statusText);
			$location.path("/forumcomment");
		});
	};
	
	$scope.deleteforum=function(forumId)
	{
		console.log('into delete forum');
		$http.post('http://localhost:8081/middleend/deleteforum/'+forumId)
		.then(function(response)
		{
			console.log(response.data);
			 $scope.forum=response.data;
			console.log('Status Text:'+response.statusText);
			$location.path("/forum1");
		});
	};
	
	

	
	
	function fetchAllForum()
	{
		console.log('Fetching All Forums');
		$http.get('http://localhost:8081/middleend/listforums')
		.then(function(response)
				{
			            $scope.forumdata=response.data;
				});
	};
	
	fetchAllForum();
});




