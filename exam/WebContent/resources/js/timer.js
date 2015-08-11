
							/*alert("AA");
							var a = 3600;
							var myVar = setInterval(function() {
								a = myTimer(a)
							}, 1000);*/
					
							//document.getElementById("timeSet").innerHTML ='10' ;
							function myTimer(time) {
								//console.log("myTimer callled");
								alert("timer");
								var sec_num = parseInt(time, 10); // don't forget the second param
								var hours = Math.floor(sec_num / 3600);
								var minutes = Math.floor((sec_num - (hours * 3600)) / 60);
								var seconds = sec_num - (hours * 3600) - (minutes * 60);
								if (hours < 10) {
									hours = "0" + hours;
								}
								if (minutes < 10) {
									minutes = "0" + minutes;
								}
								if (seconds < 10) {
									seconds = "0" + seconds;
								}
								var t = hours + ':' + minutes + ':' + seconds;
								//document.getElementById("timeSet").innerHTML = t;
								return time - 1;
							}
