package org.tbwork.anole.gui.domain.model;

import lombok.Data;

@Data
public class Config {

	private String key;
	private int type;
	private String value;
	private String desc;
	private String env;
	private String lastModifier; 
}
