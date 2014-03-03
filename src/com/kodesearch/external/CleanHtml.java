package com.kodesearch.external;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;

public class CleanHtml {
	public static String html2text(String html) {

		Document doc = Jsoup.parse(html);
		doc.select("b").remove();
		doc.outputSettings().charset("UTF-8");
		html = Jsoup.clean(doc.body().html(), Whitelist.simpleText());
		//html = StringEscapeUtils.unescapeHtml(html);
		return html;
		// return Jsoup.clean(bodyHtml, whitelist)(html).toString();

	}
}
