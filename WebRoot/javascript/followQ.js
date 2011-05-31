var req = null;
var req2 = null;
function unfollowQ(viewed, user) {
	var url = "unfollowquestion?qid=" + viewed + "&" + "uid=" + user;
	if (window.XMLHttpRequest) {
		req2 = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		// 微软IE6
		req2 = new ActiveXObject("Microsoft.XMLHTTP");
	}
	if (null != req2) {
		req2.open("GET", url, true);
		req2.onreadystatechange = comeback0; // 回调函数的方式，就是一个监听器
		req2.send(null);
	}
}
function comeback0() {
	if (req2.readyState == 4) {
		if (req2.status == 200) {
			var respText = req2.responseText;
			if ("OK" == respText) {
				document.getElementById("unfollow_button").innerHTML = "无视成功";
			}
		}
	}
}
function followQ(viewd, user) {
	var url = "followquestion?qid=" + viewd + "&" + "uid=" + user;
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}
	if (null != req) {
		req.open("GET", url, true);
		req.onreadystatechange = comeback; // 回调函数的方式，就是一个监听器
		req.send(null);
	}
}
function comeback() {
	if (req.readyState == 4) {
		if (req.status == 200) {
			var respText = req.responseText;
			if ("OK" == respText) {
				document.getElementById("follow_button").innerHTML = "关注成功";
			}
		}
	}
}
