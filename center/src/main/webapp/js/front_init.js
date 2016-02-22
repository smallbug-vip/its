/**
 * 
 */
$(function(){
	$.parser.parse();
	initLayout();
});

function initLayout(){
	initEast();
	initCenter();
}

function initEast(){
	$('#front-east').layout('add',{
	    region: 'east',
	    title: 'West Title',
	    style: 'width: 10%',
	    split: true,
	    tools: [{
	        iconCls:'icon-add',
	        handler:function(){alert('add')}
	    },{
	        iconCls:'icon-remove',
	        handler:function(){alert('remove')}    
	    }]
	});  
}

function initCenter(){
	$('#front-east').layout('add',{
		 title:'当前车况',
		 region:'center',
		 style:'height: 100%'
	});
}