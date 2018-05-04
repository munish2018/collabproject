console.log("Into the blog comment Controller");

//myApp.controller('blogcommcntrl', function($scope, $http, $location,$rootScope,$cookieStore)
myApp.controller('blogcommcntrl', function($scope, $http, $location,$rootScope)
	{
	console.log("Inside  the blog comment Controller function");
	$scope.blogcomm = {
		blogcommentid:0,
		blogid : 0,
		blogcommenttext:'',
		commentdate : '',
		username : 'munish'
	};
	console.log("blog id:"+$rootScope.blogid);
	$scope.blogcommdata;
	
	
	$scope.insertblogcomm=function()
	{
		console.log("Enter into insertBlogcoment Method");
		$http.post('http://localhost:8081/middleend/addblogcomment/'+$rootScope.blog1.blogid,$scope.blogcomm)
		.then(fetchAllBlog(),function(response)
     	  {
			console.log('Status Text:'+response.statusText);
	      });	
		fetchAllBlogComment();
		$location.path("/blogcomment");
	};
	
	$scope.updateblogcomm=function(commId)
	{
		console.log("Enter into updateBlogcomment  Method");
		$http.put('http://localhost:8081/middleend/updblogcomment/'+$rootScope.blog1.blogid+'/'+commId,$rootScope.blogcomm1)
		.then(fetchAllBlogComment(),function(response)
     	  {
			console.log('Status Text:'+response.statusText);
			  });		
		fetchAllBlogComment();
		$location.path("/blogcomment");
	};
		
	$scope.editblogcomm=function(commId)
	{
		console.log('into edit blog comment');
		$http.get('http://localhost:8081/middleend/getblogcomment/'+commId)
		.then(function(response)
		{
			console.log(response.data);
			$scope.blogcomm=response.data;
			$rootScope.blogcomm1=response.data;
			//$cookieStore.put('blogcommdetails',response.data);
			console.log('Status Text:'+response.statusText);
			$location.path("/updateblogcomment");
		});
		$location.path("/updateblogcomment");
	};
	
	$scope.deleteblogcomm=function(commentId)
	{
		console.log('into delete blog comment');
		$http.post('http://localhost:8081/middleend/deleteblogcomment/'+commentId)
		.then(function(response)
		{
			console.log(response.data);
			 $scope.blog=response.data;
			console.log('Status Text:'+response.statusText);
		});
		fetchAllBlogComment();
		$location.path("/blogcomment");
	};
	
	
	function fetchAllBlogComment()
	{
		console.log('Fetching All Blogs Comment');
		console.log("display blog comment blog id:"+$rootScope.blog1.blogid);
		$http.get('http://localhost:8081/middleend/listblogcomments/'+$rootScope.blog1.blogid)
		.then(function(response)
				{
			            $scope.blogcommdata=response.data;
			            $location.path("/blogcomment");
				});
	};
	
	fetchAllBlogComment();
});




