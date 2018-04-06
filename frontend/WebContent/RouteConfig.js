var myApp=angular.module("myApp",['ngRoute']);

myApp.config(function($routeProvider)
{
	$routeProvider.when("/",{templateUrl:"template/home.html"})
	           .when("/home",{templateUrl:"template/home.html"})
	           .when("/blog",{templateUrl:"c_blog/blog.html"})
	           .when("/forum",{templateUrl:"c_forum/forum.html"})
	           .when("/job",{templateUrl:"c_job/job.html"})
	           .when("/login",{templateUrl:"c_user/login.html"})
	           .when("/registration",{templateUrl:"c_user/registration.html"})
	           .when("/updateblog",{templateUrl:"c_blog/updateblog.html"})
	           .when("/updateforum",{templateUrl:"c_forum/updateforum.html"})
	           .when("/updatejob",{templateUrl:"c_job/updatejob.html"})
	           .when("/updateprofile",{templateUrl:"c_user/UpdateProfile.html"})
});

