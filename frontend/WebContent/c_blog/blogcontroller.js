console.log("Into the Controller");
myApp.controller('blogcntrl', function($scope, $http, $location,$rootScope)
	{
	console.log("Inside  the Controller function");
	$scope.blog = {
		blogid : 0,
		blogname:'',
		blogcontent:'',
		createdate : '',
		likes:0,
		status : 'A',
		username : 'munish'
	};
	
	$scope.blogdata;
	
	$scope.insertblog=function()
	{
		console.log("Enter into insertBlog Method");
		$http.post('http://localhost:8081/middleend/addblog',$scope.blog)
		.then(fetchAllBlog(),function(response)
     	  {
			console.log('Status Text:'+response.statusText);
	      });			
	};
	
	$scope.updateblog=function(blogId)
	{
		console.log("Enter into updateBlog Method");
		$http.put('http://localhost:8081/middleend/updateblog/'+blogId,$rootScope.blog1)
		.then(fetchAllBlog(),function(response)
     	  {
			console.log('Status Text:'+response.statusText);
			$location.path("/blog");
	      });			
	};
		
	$scope.editblog=function(blogId)
	{
		console.log('into edit blog');
		$http.get('http://localhost:8081/middleend/getblog/'+blogId)
		.then(function(response)
		{
			console.log(response.data);
			$scope.blog=response.data;
			$rootScope.blog1=response.data;
			console.log('Status Text:'+response.statusText);
			$location.path("/updateblog");
		});
	};
	
	$scope.deleteblog=function(blogId)
	{
		console.log('into delete blog');
		$http.post('http://localhost:8081/middleend/deleteblog/'+blogId)
		.then(function(response)
		{
			console.log(response.data);
			 $scope.blog=response.data;
			console.log('Status Text:'+response.statusText);
			$location.path("/blog");
		});
	};
	
	$scope.incrblog=function(blogId)
	{
		console.log('into increment blog');
		$http.put('http://localhost:8081/middleend/incrblog/'+blogId)
		.then(function(response)
		{
			console.log(response.data);
			 $scope.blog=response.data;
			console.log('Status Text:'+response.statusText);
			$location.path("/blog");
		});
	};
	
	function fetchAllBlog()
	{
		console.log('Fetching All Blogs');
		$http.get('http://localhost:8081/middleend/listblogs')
		.then(function(response)
				{
			            $scope.blogdata=response.data;
				});
	};
	
	fetchAllBlog();
});




