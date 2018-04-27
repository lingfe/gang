/**
 * @params pageNum  当前页
 * @params pageSize  每页多少条数据
 * @params total  总页数
 * @params url 分页请求路径,如果不给定的话,取当前路径
 */
$.fn.extend({
	/**
	 * 分页1
	 * @param url  数据查询路径
	 * @param  params  查询参数(json)
	 * @param  callback  回调方法,传递数据列表
	 */
	pages : function(url, params, callback) {
		var o = this;
		
		if(!url) return ;
		
		// 这个要换成下面的获取随机数方法,后面你自己换
		var _idSuffix = function() {
			return new Date().getTime() + Math.round(Math.random() * 10);
		}
		
		// 分页容器id
		var pageId = '_page_' + _idSuffix();
		// 上一页id
		var prevId = '_page_prev_' + _idSuffix();
		// 下一页id
		var nextPageId = '_page_next_' + _idSuffix();
		// 最后一页
		var lastPageId = '_page_last_' + _idSuffix();
		// 输入数字跳转
		var iptPage = '_page_jump_ipt_' + _idSuffix();
		// 跳转确定
		var pageJumpId = '_page_jump_confirm_' + _idSuffix();
		// 点击数字跳转
		var pageSelectId = '_page_select_' + _idSuffix();
		// 总共多少页
		var pageCountId = '_page_count_' + _idSuffix();
		// 当前页样式
		var selectedCss = "background-color:#bbb;color:#FFF;";
		
		
		
		var str = '<div><a href="javascript:;" class="pn-prev disabled" id="'+ prevId +'"><i>&lt;</i>上一页</a><div id="'+ pageSelectId +'" style="display:inline-block;"></div>';
		
		var getData = function(_params, _callback) {
			$.ajax({
				url : url,
				data : _params,
				type : 'POST',
				dataType : 'JSON',
				success : function(respData) {
					eval('var _respData = ('+ respData +')');
					_callback(_respData.dataList);
					if(callback && typeof(callback) === 'function') {
						var l = (_respData && _respData.dataList && _respData.dataList.list) ? _respData.dataList.list : null;
						callback(l);
					}
				}
			});
		};
		
		str += '<a href="javascript:;" id="'+ nextPageId +'">下一页<i>&gt;</i></a>\
				<a href="javascript:;" id="'+ lastPageId +'">尾页</a>\
				<span class="p-skip">\
				<em>共<b id="'+ pageCountId +'"></b>页&nbsp;&nbsp;到第</em>\
			 	<input id="'+ iptPage +'" value="1" onkeyup="checkNum(this)" onblur="checkNum(this)" class="input-txt">\
			 	<em>页</em><a href="javascript:;" id="'+ pageJumpId +'" class="btn btn-default">确定</a>\
			 </span>\
		</div>';
		
		o.html(str);
		
		// 初始化
		var _initPage = function(_params) {
			var pageSelList = '';
			getData(_params, function(_respData) {
				var pages = _respData;
				if(pages) {
					if(pages.pageCount < 5) {
						for(var _i = 1; _i <= pages.pageCount; _i ++) {
							pageSelList += '<a href="javascript:;"'+ (pages.pageNum == _i ? (" style=\""+ selectedCss +"\"") : "") +'>'+ _i + '</a>';
						}
					} else {
						var _s = pages.pageCount - pages.pageNum;
					    
					    var _start = 0;
					    var _end = 0;
					    
					    if(_s >= 5) {
					    	_start = pages.pageNum > 5 ? pages.pageNum - 2 : 1;
					    	_end = pages.pageNum > 5 ? pages.pageNum + 2 : 5;
					    } else {
					    	_start = pages.pageCount - 4;
					    	_end = pages.pageCount;
					    }
					    
					    for(var _i = _start; _i <= _end; _i ++) {
					    	pageSelList += '<a href="javascript:;"'+ (pages.pageNum == _i ? (" style=\""+ selectedCss +"\"") : "") +'>'+ _i + '</a>';
					    }
					}
					
					$("#" + pageCountId).text(pages.pageCount);
					
					var hidePage = {firstPage : pages.firstPage, lastPage : pages.lastPage, nextPage : pages.nextPage, pageCount : pages.pageCount, prevPage : pages.prevPage, total : pages.total};
					
					var _hidePageInfo = $(o).find("._hidePageInfo");
					var _hp = (JSON.stringify(hidePage)).replace(new RegExp("\"","gm"), "#");
					if(_hidePageInfo && _hidePageInfo.length > 0) {
						_hidePageInfo.val(_hp);
					} else {
						$(o).append('<input type="hidden" class="_hidePageInfo" value="'+ _hp +'" />');
					}
					
				} else {
					pageSelList += '<a href="javascript:;" style="'+ selectedCss +'">1</a>';
				}
				
				$("#" + pageSelectId).html(pageSelList);
				
				// 点击数字跳转
				$('#' + pageSelectId).find("a").bind("click", function(e) {
					var _o = $(this);
					var _txt = parseInt(_o.text());
					if(params.page == _txt) {
						return
					}
					params.page = _txt
					_initPage(params);
				});
			});
		};
		
		// 分页初始化
		if(!params.page) params.page = 1;
		if(!params.rows) params.rows = 5;
		_initPage(params);
		
		var getHidePageInfo = function(_o) {
			var _hv = $(_o).find("._hidePageInfo").val();
			var _hidePageInfo = null;
			
			if(!(_hv == null || _hv == '')) { 
				_hv = _hv.replace(new RegExp("#","gm"), "\"");
				_hidePageInfo = JSON.parse(_hv);
			}
			return _hidePageInfo;
		};
		
		// 上一页
		$("#" + prevId).bind("click", function(e) {
			var _hidePageInfo = getHidePageInfo(o);
			if(params.page == 1) {
				return
			}
			params.page = _hidePageInfo.prevPage;
			_initPage(params);
		});
		
		// 下一页
		$("#" + nextPageId).bind("click", function(e) {
			var _hidePageInfo = getHidePageInfo(o);
			if(params.page == _hidePageInfo.lastPage) {
				return
			}
			params.page = _hidePageInfo.nextPage;
			_initPage(params);
		});
		
		// 最后一页
		$("#" + lastPageId).bind("click", function(e) {
			var _hidePageInfo = getHidePageInfo(o);
			params.page = _hidePageInfo.lastPage;
			_initPage(params);
		});
		
		// 跳转确定
		$("#" + pageJumpId).bind("click", function(e) {
			var _hidePageInfo = getHidePageInfo(o);
			var _v = $("#" + iptPage).val();
			if(!(_v == null || _v == '' || isNaN(_v))) {
				if(_v <=0 || _v > _hidePageInfo.lastPage){
					return;
				}
				var _p = parseInt(_v);
				params.page = _p;
				_initPage(params);
			}
		});
		
		return o;
	},
	
	/**
	 * 分页2
	 */
	customPage : function(pageNum, pageSize, total, url) {
		
		var pageTotal =  Math.ceil( total / pageSize );
		
		var o = this;
		var selectedCss = "background-color:#bbb;color:#FFF;";
		var reqNum = 0;
		
		var reqFn = function(_p) {
			var _href = window.location.href;
			var _url;
			if(url) {
				_url = url + "?page=" + _p + "&rows=" + pageSize;
			} else {
				var _params = _href.substring(_href.indexOf(window.location.pathname) +(window.location.pathname).length);
				if(_params) {
					var _ps = _params.substring(1);
					var _psArr = _ps.split("&");
					var _str = "";
					for(var _i = 0; _i < _psArr.length; _i++) {
						var _pp = _psArr[_i].split("=");
						if(_pp[0] == 'page') {
							_pp[1] = _p;
						} else if(_pp[0] == 'rows') {
							_pp[1] = pageSize;
						}
						
						_str += _pp[0] + "=" + _pp[1] + "&";
					}
					
					_str = _str.substring(0, _str.length - 1);
					
					if(_str.indexOf('page') == -1) {
						_str += "&page=" + _p;
					}
					
					if(_str.indexOf('rows') == -1) {
						_str += "&rows=" + pageSize;
					}
					
					_url = window.location.pathname + "?" + _str;
				} else {
					_url = _href + "?page=" + _p + "&rows=" + pageSize;
				}
			}
			
			window.location.href = _url;
		};
		
		// 上一页
		var  _prevPage = function(){
			reqNum = pageNum == 1 ? 1 : pageNum - 1;
			
			if(pageNum == 1){
				//提示或者返回
				return
			}
			
			reqFn(reqNum);
		};
		
		// 下一页
		var _nextPage = function(){
			reqNum = pageNum == pageTotal ? pageNum : pageNum + 1;
			
			if(pageNum == pageTotal){
				//提示或者返回
				return
			}
			
			reqFn(reqNum);
		};
		
		// 选择页
		var _selectPage = function(num) {
			if(num == pageNum) {
				return ;
			}
			reqFn(num); 
		};
		
		//尾页
		var _lastPage = function() {
			if(pageNum == pageTotal){
				//提示或者返回
				return
			}else{
				reqNum = pageTotal;
				reqFn(reqNum);
			}
		};
		
		//页面跳转
		var _pageJump = function() {
			reqNum = $("#_pagejs_pageJump_onkeydown").val();
			if(reqNum <=0 || reqNum >= pageTotal){
				return;
			}else{
				reqFn(reqNum);
			}
		}
		
		var _pageJump1 = function() {
			if(event.keyCode==13){
				_pageJump();
				return false;
			}
		};
		
		var initPage = '<div class="Paging"><a href="javascript:;" class="pn-prev disabled" id="_pagejs_prev"><i>&lt;</i>上一页</a>';
		if(pageTotal < 5) {
			for(var _i=1; _i<=pageTotal; _i++) {
				initPage += '<a href="javascript:;"'+ (pageNum == _i ? (" style=\""+ selectedCss +"\"") : "") +'  id="_pagejs_selectPage_'+ _i +'">'+ _i + '</a>';
			}
		}else {
		    var _s = pageTotal - pageNum;
		    
		    var _start = 0;
		    var _end = 0;
		    
		    if(_s >= 5) {
		    	_start = pageNum > 5 ? pageNum - 2 : 1;
		    	_end = pageNum > 5 ? pageNum + 2 : 5;
		    } else {
		    	_start = pageTotal - 4;
		    	_end = pageTotal;
		    }
		    
		    for(var _i = _start; _i <= _end; _i ++) {
		    	initPage += '<a href="javascript:;"'+ (pageNum == _i ? (" style=\""+ selectedCss +"\"") : "") +'  id="_pagejs_selectPage_'+ _i +'">'+ _i + '</a>';
		    }
		    
		}
		
		initPage += '<a href="javascript:;" id="_pagejs_nextPage">下一页<i>&gt;</i></a>\
					<a href="javascript:;" id="_pagejs_lastPage">尾页</a>\
					<span class="p-skip">\
				 	<em>共<b>'+ pageTotal +'</b>页&nbsp;&nbsp;到第</em>\
				 	<input value="1" id="_pagejs_pageJump_onkeydown" onkeyup="checkNum(this)" onblur="checkNum(this)" class="input-txt">\
				 	<em>页</em><a href="javascript:;" id="_pagejs_pageJump" class="btn btn-default">确定</a>\
				 </span>\
			</div>';
		
		o.html(initPage);
		
		$("#_pagejs_prev").bind("click", function(e) {
			_prevPage()
		})
		
		$("a[id^='_pagejs_selectPage_']").bind("click", function(e) {
			var textNum = parseInt($(this).text());
			_selectPage(textNum)
		})
		
		$("#_pagejs_nextPage").bind("click", function(e) {
			_nextPage()
		})
		
		$("#_pagejs_lastPage").bind("click", function(e) {
			_lastPage()
		})
		
		$("#_pagejs_pageJump").bind("click", function(e) {
			_pageJump()
		})
		
		$("#_pagejs_pageJump_onkeydown").bind("keydown", function(e) {
			_pageJump1()
		})
		
		if(total == 0){
			$("#_pagejs_prev").css({"opacity":"0.5"});
			$("#_pagejs_nextPage").css({"opacity":"0.5"});
			$("#_pagejs_lastPage").css({"opacity":"0.5"});
			$("#_pagejs_pageJump").css({"opacity":"0.5"});
			$("#_pagejs_pageJump_onkeydown").css({"opacity":"0.5"});
		}
		if(pageNum == 1){
			$("#_pagejs_prev").css({"opacity":"0.5"});
			$("#_pagejs_lastPage").css({"opacity":"0.5"});
			$("#_pagejs_pageJump").css({"opacity":"0.5"});
		}
		if(pageNum == pageTotal){
			$("#_pagejs_nextPage").css({"opacity":"0.5"});
		}
		return o;
	},
	
	randomNum : function() {
		// 获取随机数的方法扩展
	}
});