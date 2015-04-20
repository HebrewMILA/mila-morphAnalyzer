
function setCookie(cname, cvalue, hours) {
    var d = new Date();
    d.setTime(d.getTime() + (hours*60*60*1000));
    var expires = "expires="+d.toUTCString();
    document.cookie = cname + "=" + encodeURIComponent(cvalue) + "; " + expires;
}

function getCookie(cname) {
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for (var i=0; i<ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0)==' ')
			c = c.substring(1);
        if (c.indexOf(name) == 0)
			return decodeURIComponent(c.substring(name.length, c.length));
    }
    return "";
}
var TAcookieName = 'analysis_input';
function TAsetCookie(data) { setCookie(TAcookieName, data, 3);  }
function TAgetCookie() { return getCookie(TAcookieName);  }
function TAdeleteCookie() { setCookie(TAcookieName, '', -3); }
