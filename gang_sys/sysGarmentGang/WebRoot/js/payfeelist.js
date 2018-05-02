var PAYFEES={
		
		//add添加服装款式类型信息
		add_style:function(){
			qikoo.dialog.confirm('确定要添加此到数据库吗？',function(){
				$("#param").val('add');
				$("#form_add").submit();
	        },function(){ });
		},
		add_style_html:function(that,pageIndex,pageNum,userName){
			if($(that).val()=="取消"){
				$("#add_style_html").html('');
				$(that).val("添加");
				$(that).attr("class","cz_button add");
				return;
			}
			var str='<form action="manage/styleTypeManage.action" method="post" id="form_add">'
					+'<input type="hidden" name="pageIndex" value="'+pageIndex+'">'
					+'<input type="hidden" name="pageNum" value="'+pageNum+'">'
					+'<input type="hidden" name="param" value="add">'
					+'<input type="hidden" name="creator" value="'+userName+'">'
					+'<table class="tablelist" >'
					+'<tr>'
					+'<td style="width: 10%;"><input type="text" class="input_text" name="id" readonly="readonly" value="系统自动生成"/></td>'
						+'<td style="width: 10%;"><input placeholder="请输入款式名称"  required="required" type="text" class="input_text" name="styleName" value=""/></td>'
						+'<td style="width: 10%;"><input placeholder="没有上级?" type="text" class="input_text" name="superiorId" value=""/></td>'
						+'<td style="width: 6%;">'
						+'<input type="hidden" name="isDisplay" id="isDisplay" value="是">'
						+'<input onclick="javascript:PAYFEES.from_isDisplay(this,null);" type="button" class="cz_button is_block" value="显示中" />'
					+'</td>'
					+'<td style="width: 10%;"><input type="text" class="input_text" readonly="readonly" value="默认当前"/></td>'
					+'<td style="width: 10%;"><input type="text" class="input_text" readonly="readonly" value="默认当前"/></td>'
					+'<td style="width: 3%;"><input type="number" class="input_text" name="state" value="0"/></td>'
					+'<td style="width: 3%;"><input type="text" class="input_text" readonly="readonly" value="0"/></td>'
					+'<td style="width: 13%;">'
						+'<input type="button" onclick="javascript:PAYFEES.add_style();" class="cz_button update" value="保存" />'
					+'</td>'
					+'</tr>'
					+'</table>'
					+'</form>';
			$("#add_style_html").prepend(str);
			$(that).val("取消");
			$(that).attr("class","cz_button cancel");
		},
		
		
		
		//是否显示
		from_isDisplay:function(that,id){
			if(id==null){
				id='';
			}
			$("#param"+id).val('isDisplay');
			if($(that).val() == '显示中'){
				$(that).val("隐藏中");
				$("#isDisplay"+id).val('否');
				$(that).attr("class","cz_button is_none");
			}else{
				$(that).val("显示中");
				$("#isDisplay"+id).val('是');
				$(that).attr("class","cz_button is_block");
			}
			if(id!=""&&id!=null&&id!='undefined'){
				$("#form_update"+id).submit();
			}
			
		},
		//修改
		from_update:function(id){
			qikoo.dialog.confirm('确定要修改此吗？',function(){
				$("#param"+id).val('update');
				$("#form_update"+id).submit();
	        },function(){ });
		},
		//删除
		from_delete:function(id){
			qikoo.dialog.confirm('确定要删除此吗？',function(){
				$("#param"+id).val('delete');
				$("#form_update"+id).submit();
	        },function(){ });
		},
};
