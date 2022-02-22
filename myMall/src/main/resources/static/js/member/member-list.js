var MemberList = new function() {
	this.RETURN_SUCC;
	this.RETURN_FAIL;
	this.YES_FLAG;
	this.NO_FLAG;
	this.PAGE_NUM;
	this.PAGE_LIMIT;
	this.URL;

	this.initConstant = function(param) {
		if (param) {

			MemberList.URL = param.URL;
			MemberList.RETURN_SUCC = param.RETURN_SUCC;
			MemberList.RETURN_FAIL = param.RETURN_FAIL;
			MemberList.YES_FLAG = param.YES_FLAG;
			MemberList.NO_FLAG = param.NO_FLAG;
			MemberList.currentPageNo = 1;
			MemberList.recordsPerPage = 10;
			MemberList.pageSize = 10;
			MemberList.searchKeyword = '';
			MemberList.searchType = '';

			getListInfo(MemberList);

		} else {
			console.log("no param");
		}
	}

	function getListInfo(setting) {
		$.ajax({
			url: '/member/getUserList.do',
			type: 'post',
			data: setting,
			dataType: 'json',
			success: function(data) {
				if (data.length > 0 && data[0].result == MemberList.RETURN_SUCC) {
					gridWrite(data);
				} else {
					alert('데이터를 불러오는데 실패했습니다');
				}
			},
			error: function(request, status, error) {
				console.log("code: " + request.status + "\n" + "message: " + request.responseText + "\n" + "error: " + error);
				alert('데이터를 불러오는데 실패했습니다222');
			}
		});
	}
	function gridWrite(list) {
		var arr = [];
		if (list && list.length > 0) {
			//		console.log(list);
			list.forEach(function(d, idx) {
				console.log(d);
				arr[idx] = {
					"usridx": d.usridx,
					"id": d.id,
					"name": d.name,
					"email": d.email,
					"tel": d.tel,
					"gender": d.gender,
					"createdtime": d.createdtime,
					"userauth": d.userauth,
				};
			});
			// 	console.log(arr);
		}

		const columnDefs = [
			{ headerName: "회원번호", field: "usridx" },
			{ headerName: "아이디", field: "id" },
			{ headerName: "회원명", field: "name" },
			{ headerName: "이메일", field: "email" },
			{ headerName: "연락처", field: "tel" },
			{ headerName: "성별", field: "gender" },
			{ headerName: "가입일자", field: "createdtime" },
			{ headerName: "회원유형", field: "userauth" }
		];


		var gridOptions = {
				columnDefs: columnDefs,
				rowData: arr,
				defaultColDef: {
					editable: false,
					width: 100
				},
				rowSelection: 'single', /* 'single' or 'multiple',*/
				enableColResize: true,
				enableSorting: true,
				enableFilter: true,
				enableRangeSelection: true,
				suppressRowClickSelection: false,
				animateRows: true,
				suppressHorizontalScroll: true,
				localeText: { noRowsToShow: '조회 결과가 없습니다.' },
				getRowStyle: function(param) {
					if (param.node.rowPinned) {
						return { 'font-weight': 'bold', background: '#dddddd' };
					}
					return { 'text-align': 'center' };
				},
				getRowHeight: function(param) {
					if (param.node.rowPinned) {
						return 30;
					}
					return 24;
				},
				onGridReady: function(event) {
					event.api.sizeColumnsToFit();
				},
				onGridSizeChanged: function(event) {
					event.api.sizeColumnsToFit();
				},
				onRowEditingStarted: function(event) {
					console.log('never called - not doing row editing');
				},
				onRowEditingStopped: function(event) {
					console.log('never called - not doing row editing');
				},
				onCellEditingStarted: function(event) {
					console.log('cellEditingStarted');
				},
				onCellEditingStopped: function(event) {
					console.log('cellEditingStopped');
				},
				onRowClicked: function(event) {
					console.log('onRowClicked');
					var selectedRows = gridOptions.api.getSelectedRows();
//									var detailPage =/*[[${detailPage}]]*/;
					location.href = MemberList.URL + '?user_id=' + selectedRows[0].id;
					//  			userDeatilSelect(selectedRows);
	
				},
				onCellClicked: function(event) {
					console.log('onCellClicked');
				},
				isRowSelectable: function(event) {
					console.log('isRowSelectable');
					return true;
				},
				onSelectionChanged: function(event) {
					console.log('onSelectionChanged');
				},
				onSortChanged: function(event) {
					console.log('onSortChanged');
				},
				onCellValueChanged: function(event) {
					console.log('onCellValueChanged');
				},
				getRowNodeId: function(data) {
					return null;
				},
				// 리드 상단 고정 
				setPinnedTopRowData: function(data) {
					return null;
				},
				// 그리드 하단 고정 
				setPinnedBottomRowData: function(data) {
					return null;
				},
				getRowHeight: function(params) {
					return 28 * (Math.floor(list.length / 60) + 1);
				},
				debug: false
		};
		
//		document.addEventListener("DOMContentLoaded", () => {
		    const gridDiv = document.querySelector("#myGrid");
		    new agGrid.Grid(gridDiv, gridOptions);
//		});
	}
};
