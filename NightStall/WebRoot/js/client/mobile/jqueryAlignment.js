(function(e) {
    e.fn.tagsAlignment = function(t) {
        var n = e.extend({
            childTagName: "a",
            tagMargin: 5
        },t);
        this.each(function(t, i) {
            var a = e(i).width();
            $child = e(i).children(n.childTagName);
            for (var r = [], s = 0; s < $child.length; s++) {
                $child[s].style.width = "auto";
                var o = $child[s].offsetWidth + n.tagMargin + 1;
                o > a ? r[s] = a: r[s] = o
            }
						
            var l = function(e) {
                for (var t = Math.floor((a - e.rwidth) / e.rzise), n = (a - e.rwidth) % e.rzise, i = 0; i < e.rzise; i++) {
                    var r = $child[i + e.rindex];
                    n > i ? r.style.width = r.offsetWidth + t + 2 + "px": r.style.width = r.offsetWidth + t + (i === e.rzise - 1 ? 0 : 1) + "px"
                }
            };
			
           	for (var c = 0, d = 0, u = 0, s = 0; s < r.length; s++) if (d++, c += r[s], c > a) {
                var p = {
                    rindex: u,
                    rzise: d - 1,
                    rwidth: c - r[s]
                };
                u = s,
                d = 0,
                c = 0,
                s--,
                l(p)
            }
        })
    }
}) (jQuery)