myApp.controller('usercntrl',function($scope,$http,$location,$rootScope)
{
	console.log(" inside the usercontroller function");
	$scope.user={loginname:'',password:'',role:'',username:'',mobileno:'',address:'',isonline:''};
	
	$scope.login=function()
	{
		console.log("Logging Function");
		console.log("user name :"+$scope.user.loginname);
		console.log("password :"+$scope.user.password);
		$http.put('http://localhost:8081/middleend/login',$scope.user)
			.then(function(response)
			{
				console.log(response.status);
				console.log(response.data);
				$scope.user=response.data;
				$rootScope.currentUser=response.data;
				console.log($rootScope.currentUser.role);
					if($rootScope.currentUser.role=="roleadmin")
					{
						console.log('AdminPage');
					}
					else
					{
						console.log('UserPage');
					}
				$location.path("/userhome");
			});
	};
	$rootScope.logout=function()
	{
		console.log('Logout Function');
		delete $rootScope.currentUser;
		$location.path("/logout");
	};
	
});