
/*
w2ui part - rearranging layout and stuff
*/
var tokens = 'main';
var tables = 'preview';
var form = 'right';
var xml = 'left';
var isUserAnalysis = window.location.search.indexOf('id') >= 0;

function prepareToolbar() {
	var toolbar = {
		items: [
			{ type: 'check',  id: 'home',  caption: '<span style="color:red; font-weight: bold; ">ניתוח נוסף</span>', checked: !isUserAnalysis },
			{ type: 'spacer' },
			{ type: 'button', caption: "Download", disabled:!isUserAnalysis, id: 'xml_download',},
			{ type: 'check', caption: 'XML', disabled: false, id: 'xml_view' },
		],
		onClick: function(event) {
			switch (event.target) {
				case 'home':  w2ui['layout'].toggle(form, false);	break;
				case 'xml_download':  window.open(document.location.href+'&raw&download', '_self'); break;
				case 'xml_view':  w2ui['layout'].toggle(xml, false); break;
			}
		}
	};
	return toolbar;
}

function rearrangeLayout() {
	var pstyle = 'border: 1px solid #dfdfdf; padding: 5px;';
	function move(item) { return document.getElementById(item); }
	$('#layout').w2layout({
		name: 'layout',
		panels: [
			{ type: 'top',	style: pstyle, hidden: false, toolbar: prepareToolbar(), size: '25' },
			{ type: tokens,	style: pstyle, hidden: false, resizable: true, 	title: 'טקסט מנותח',
				content: move('article_tokens') },
			{ type: form,	style: pstyle, hidden: isUserAnalysis, resizable: true, size: '50%',	title: 'טקסט לניתוח',
				content: move('form')  },
			{ type: tables, style: pstyle, hidden: false, resizable: true, size: '40%',	title: 'ניתוח מפורט',
				content: move('tables') },
			{ type: xml, style: pstyle, hidden: true, resizable: true, size: '55%',	title: 'XML',
				content: move('xml') },
		]
	});
}

function attachInflections(self, pointerType, LEXICON_ID) {
	$(self).removeAttr('onmouseover');
	if (LEXICON_ID == '0')
		return;
	function popup(data){ 
		$(self).click(function() { w2popup.open({title : 'הטיות', body : data}); });
	}
	$.ajax({ 
		url: 'inflections/'+pointerType+'.jsp?lexiconId='+LEXICON_ID,
		success: popup
	});
}
