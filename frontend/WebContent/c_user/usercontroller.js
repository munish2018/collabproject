console.log("Into the User Controller");
myApp.controller('usercntrl', function($scope, $http, $location, $rootScope) {
	console.log("Inside  the User Controller function");
	$scope.user = {
		loginname : '',
		password : '',
		username : '',
		mobileno : '',
		address : '',
		isonline : '',
		role : ''
	};

	$scope.userdata;
	$scope.login = function() {
		console.log("Enter into login Method");
		console.log("user name" + $scope.user.loginname);
		$http.post('http://localhost:8081/middleend/login', $scope.user).then(
				function(response) {
					console.log('Status Text:' + response.statusText);
					console.log(response.data);
					$scope.userdata = response.data;
					$rootScope.user1 = response.data;
					// $cookieStore.put('userdetails',response.data);
					console.log('Status Text:' + response.statusText);
					$location.path("/userhome");
				});
	};

	$scope.logout = function() {
		console.log("Enter into logout Method");
		$http.get('http://localhost:8081/middleend/logout');
		delete $rootScope.user1;
		$location.path("/home");
	};

	$scope.insertuser = function() {
		console.log("Enter into save userdetail  Method");
		$http.post('http://localhost:8081/middleend/adduser', $scope.user)
				.then(function(response) {
					console.log('Status Text:' + response.statusText);
					console.log(response.data);
					$scope.userdata = response.data;
					$rootScope.user1 = response.data;
					console.log('Status Text:' + response.statusText);
					$location.path("/home");
				});
		};

});
