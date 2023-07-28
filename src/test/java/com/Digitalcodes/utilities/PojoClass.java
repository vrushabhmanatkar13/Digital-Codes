package com.Digitalcodes.utilities;

import java.util.Iterator;
import java.util.StringTokenizer;

public class PojoClass {
	
	private String section;
	private String subsection;
	private String title;
	private String chapter;
	
	
	/*
	 * public PojoClass(String section, String subsection, String title, String
	 * chapter) { this.section=section; this.subsection=subsection;
	 * this.title=title; this.chapter=chapter; }
	 */
	
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public String getSubsection() {
		return subsection;
	}
	public void setSubsection(String subsection) {
		this.subsection = subsection;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getChapter() {
		return chapter;
	}
	public void setChapter(String chapter) {
		this.chapter = chapter;
	}
	
	
	}


