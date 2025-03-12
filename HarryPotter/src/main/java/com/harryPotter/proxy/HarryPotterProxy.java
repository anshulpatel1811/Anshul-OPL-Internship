package com.harryPotter.proxy;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class HarryPotterProxy {

	
	public HarryPotterProxy(String characterName, String location, String house) {
		super();
		this.characterName = characterName;
		this.location = location;
		this.house = house;
	}
	private Long id;
	
	private String characterName;
	private String location;
	private String house;
	private String spell;
	private String quote;
	
}
