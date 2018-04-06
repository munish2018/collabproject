myApp.controller('usercntrl',function($scope,$http,$location,$rootScope)
{
	console.log(" inside the usercontroller function");
	$scope.user={loginname:'',password:'',role:'',username:'',mobileno:'',address:'',isonline:''};
	
	$rootScope.login=function()
	{
		console.log("Logging Function");
		
		$http.post('http://localhost:8081/middleend/login',$scope.user)
			.then(function(response)
			{
				console.log(response.status);
				console.log(response.data);
				$scope.user=response.data;
				$rootScope.currentUser=response.data;
				console.log($rootScope.currentUser.role);
					if($rootScope.currentUser.role=="roledmin")
					{
						console.log('AdminPage');
					}
					else
					{
						console.log('UserPage');
					}
				$location.path("/updateprofile");
			});
	};
	$rootScope.logout=function()
	{
		console.log('Logout Function');
		delete $rootScope.currentUser;
		$location.path("/logout");
	};
	
});