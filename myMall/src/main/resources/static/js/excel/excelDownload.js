$(document).on('click', '.data-excel', function() {
	var filecsv = $(this).hasClass('filecsv') ? 'filecsv' : false;
	var filexlxs = $(this).hasClass('filexlxs') ? 'filexlxs' : false;
	var dataName = $(this).data('name');

	if (!filecsv && !filexlxs) {
		alert("잘못된 접근입니다. (1)");
		return false;
	}

	if (!dataName) {
		alert("잘못된 접근입니다. (2)");
		return false;
	}

	var param = {
		RETURN_SUCC: 'succ',
		RETURN_FAIL: 'fail',
		NO_FLAG: 'no',
		YES_FLAG: 'yes',
		DATA: dataName,
		TYPE: filexlxs ? filexlxs : filecsv,
		URL: '/excel/requestDownload/' + dataName,
	};

	ExcelList.initConstant(param);

});


var ExcelList = new function() {

	this.initConstant = function(param) {
		if (param) {

			ExcelList.URL = param.URL;
			ExcelList.RETURN_SUCC = param.RETURN_SUCC;
			ExcelList.RETURN_FAIL = param.RETURN_FAIL;
			ExcelList.YES_FLAG = param.YES_FLAG;
			ExcelList.NO_FLAG = param.NO_FLAG;
			ExcelList.DATA = param.DATA;
			ExcelList.TYPE = param.TYPE;
			ExcelList.URL = param.URL;

			getExcelDownload(ExcelList);

		} else {
			console.log("no param");
		}
	}

	function getExcelDownload(ExcelList) {
		$.ajax({
			url: ExcelList.URL,
			method: 'POST',
			dataType: "json",
			success: function(data) {
				console.log(data);
				if (data.parh != null && data.parh) {
					fileDownl(data.parh);
				}
			},
			error: function(request, status, error) {
				console.log("code: " + request.status + "\n" + "message: " + request.responseText + "\n" + "error: " + error);
				alert('데이터를 불러오는데 실패했습니다222');
			}
		});
	}

	function fileDownl(url) {
		var browserName = undefined;
		var userAgent = navigator.userAgent;

		switch (true) {
			case /Trident|MSIE/.test(userAgent):
				browserName = 'ie';
				break;

			case /Edge/.test(userAgent):
				browserName = 'edge';
				break;

			case /Chrome/.test(userAgent):
				browserName = 'chrome';
				break;

			case /Safari/.test(userAgent):
				browserName = 'safari';
				break;

			case /Firefox/.test(userAgent):
				browserName = 'firefox';
				break;

			case /Opera/.test(userAgent):
				browserName = 'opera';
				break;

			default:
				browserName = 'unknown';
		}


		//ie 브라우저 및 EDGE 브라우저 
		if (browserName == 'ie' || browserName == 'edge') {

			//ie11
			var _window = window.open(url, "_blank");
			_window.document.close();
			_window.document.execCommand('SaveAs', true, "file.hwp" || url)
			_window.close();
		} else {

			//chrome
			var filename = url.substring(url.lastIndexOf("/") + 1).split("?")[0];
			var xhr = new XMLHttpRequest();
			xhr.responseType = 'blob';
			xhr.onload = function() {
				var a = document.createElement('a');
				a.href = window.URL.createObjectURL(xhr.response); // xhr.response is a blob
				a.download = filename; // Set the file name.
				a.style.display = 'none';
				document.body.appendChild(a);
				a.click();
				delete a;
			};
			xhr.open('GET', url);
			xhr.send();
		}
	}
}