<%@page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<body>

	<h1>My First Google Map</h1>

	<div id="googleMap" style="width: 100%; height: 400px;"></div>

	<script>
		function myMap() {
			var latLng = new google.maps.LatLng(37.571063, 126.992225);

			var mapProp = {
				center : latLng,
				zoom : 30,
			};

			var map = new google.maps.Map(document.getElementById("googleMap"),
					mapProp);

			var marker = new google.maps.Marker(
					{
						position : latLng,
						animation : google.maps.Animation.BOUNCE,
						icon : 'https://cdn0.iconfinder.com/data/icons/roundettes-for-os-x-vol-v/512/Angry_Birds-128.png'
					});
			marker.setMap(map); // 맵에 마커 등록

			new google.maps.Marker(
					{
						position : new google.maps.LatLng(37.571333, 126.992225),
						animation : google.maps.Animation.BOUNCE,
						icon : 'https://cdn0.iconfinder.com/data/icons/roundettes-for-os-x-vol-v/512/Angry_Birds-128.png'
					}).setMap(map); // 맵에 마커 등록

			new google.maps.Marker(
					{
						position : new google.maps.LatLng(37.571402, 126.992225),
						animation : google.maps.Animation.BOUNCE,
						icon : 'https://cdn0.iconfinder.com/data/icons/roundettes-for-os-x-vol-v/512/Angry_Birds-128.png'
					}).setMap(map); // 맵에 마커 등록		

			var infowindow = new google.maps.InfoWindow(
					{
						content : "이곳이 <h3 style='color:red'><img src='https://cdn0.iconfinder.com/data/icons/simpline-mix/64/simpline_60-128.png'></h3>이다!"
					});

			google.maps.event.addListener(marker, 'click', function() {
				map.setCenter(marker.getPosition());
				infowindow.open(map, marker);
				var pos = map.getZoom();
				map.setZoom(9);
				setTimeout(function() { //일정 시간뒤에 하고싶은것 정하기...
					map.setZoom(pos);
				}, 2400);
			});
		}
	</script>

	<script
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCbZQKbsB2o-9yTNlIM88SsZZ6Vo6xjPeU&callback=myMap"></script>

</body>
</html>