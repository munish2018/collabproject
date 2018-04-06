console.log("Into the Job Controller");
myApp.controller('jobcntrl', function($scope, $http, $location,$rootScope)
	{
	console.log("Inside  the Job Controller function");
	$scope.job = {
		jobid : 0,
		company_name:'',
		contact:'',
		experience : 0,
		job_description: '',
		job_title:'',
		location : '',
		posted_on:'',
		salary:0,
		skills_required:'',
		vacancies:0
	};
	
	$scope.jobdata;
	
	
	$scope.insertjob=function()
	{
		console.log("Enter into insertforum Method");
		console.log('insert data:'+$scope.job);
		$http.post('http://localhost:8081/middleend/addjob',$scope.job)
		.then(fetchAllJob(),function(response)
     	  {
			console.log('Status Text:'+response.statusText);
	      });			
	};
	
	$scope.updatejob=function(jobId)
	{
		console.log("Enter into updatejob Method");
		$http.put('http://localhost:8081/middleend/updatejob/'+jobId,$rootScope.job1)
		.then(fetchAllJob(),function(response)
     	  {
			console.log('Status Text:'+response.statusText);
			$location.path("/job");
	      });			
	};
		
	$scope.editjob=function(jobId)
	{
		console.log('into edit job');
		$http.get('http://localhost:8081/middleend/getjob/'+jobId)
		.then(function(response)
		{
			console.log(response.data);
			$scope.job=response.data;
			$rootScope.job1=response.data;
			console.log('Status Text:'+response.statusText);
			$location.path("/updatejob");
		});
	};
	
	$scope.deletejob=function(jobId)
	{
		console.log('into delete job');
		$http.post('http://localhost:8081/middleend/deletejob/'+jobId)
		.then(function(response)
		{
			console.log(response.data);
			 $scope.job=response.data;
			console.log('Status Text:'+response.statusText);
			$location.path("/job");
		});
	};
	

	
	function fetchAllJob()
	{
		console.log('Fetching All jobs');
		$http.get('http://localhost:8081/middleend/listjobs')
		.then(function(response)
				{
			            $scope.jobdata=response.data;
				});
	};
	
	fetchAllJob();
});
