console.log("Into the forum comment Controller");

//myApp.controller('blogcommcntrl', function($scope, $http, $location,$rootScope,$cookieStore)
myApp.controller('forumcommcntrl', function($scope, $http, $location,$rootScope)
	{
	console.log("Inside  the blog comment Controller function");
	$scope.forumcomm = {
			forumcommentid:0,
			forumid : 0,
			forumcommenttext:'',
		commentdate : '',
		username : 'munish'
	};
	console.log("forum id:"+$rootScope.forumid);
	$scope.forumcommdata;
	
	
	$scope.insertforumcomm=function()
	{
		console.log("Enter into insertforumcoment Method");
		$http.post('http://localhost:8081/middleend/addforumcomment/'+$rootScope.forum1.forumid,$scope.forumcomm)
		.then(fetchAllForumComment(),function(response)
     	  {
			console.log('Status Text:'+response.statusText);
	      });	
		fetchAllForumComment();
		$location.path("/forumcomment");
	};
	
	
	function fetchAllForumComment()
	{
		console.log('Fetching All forum Comment');
		console.log("display forum comment forum id:"+$rootScope.forum1.forumid);
		$http.get('http://localhost:8081/middleend/listforumcomments/'+$rootScope.forum1.forumid)
		.then(function(response)
				{
			            $scope.forumcommdata=response.data;
			            $location.path("/forumcomment");
				});
	};
	
	fetchAllForumComment();
});




