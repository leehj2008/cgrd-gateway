$(function(){
	$('#taskTable').datagrid({
		url: '/',
		method: 'post',
		queryParams: {
			//patientid:'41'//$("#institutionid").combobox("getText")
		},
		title: '任务列表',
		iconCls: 'icon-save',
		//toolbar:tbautoseg,
		//width: 80%,
		//height: 90%,
		fit:true,
		fitColumns: true,
		singleSelect: true,
		idField:'atlasid', 
		columns:[[
			{field:'patientid',title:'患者编号',width:70,align:'center'},
			{field:'atlasname',title:'部位',width:100},
			{field:'progress',title:'状态',width:70,align:'center'}
		]]
	});
});
