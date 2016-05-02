'use strict';

var gulp = require('gulp'),
	concat = require('gulp-concat'),
	uglify = require('gulp-uglify'),
	htmlmin = require('gulp-htmlmin'),
	templateCache = require('gulp-angular-templatecache'),
	sass = require('gulp-sass');
	

gulp.task('angular', function(){
	return gulp
			.src('src/main/resources/angular/**/*.js')
			.pipe(concat('angular.app.js'))
			//.pipe(uglify())
			.pipe(gulp.dest('src/main/resources/static/'));
});

gulp.task('templates', function () {
	  return gulp.src('src/main/resources/angular/views/**/*.html')
	  	.pipe(htmlmin({collapseWhitespace: true}))
	    .pipe(templateCache('angular.views.js', { module:'angular.me.views', standalone:true }))
	    .pipe(gulp.dest('src/main/resources/static/'));
});

gulp.task('sass', function(){
	  return gulp.src('src/main/resources/static/assets/css/*.scss')
	  	.pipe(sass())
	    .pipe(gulp.dest('src/main/resources/static/assets/css'));
});

gulp.task('watch-js', function() {
    return gulp.watch('src/main/resources/angular/**/*.js', ['angular'])
});

gulp.task('watch-html', function() {
    return gulp.watch('src/main/resources/angular/views/**/*.html', ['templates'])
});

gulp.task('watch-sass', function() {
    return gulp.watch('src/main/resources/static/assets/css/**/*.scss', ['sass'])
})

gulp.task('default', ['sass', 'angular', 'templates', 'watch-sass', 'watch-js', 'watch-js', 'watch-html']);